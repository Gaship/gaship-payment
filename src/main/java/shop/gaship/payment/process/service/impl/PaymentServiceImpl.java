package shop.gaship.payment.process.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.gaship.payment.cancelhistory.dto.request.CancelPaymentResponseDto;
import shop.gaship.payment.cancelhistory.entity.PaymentCancelHistory;
import shop.gaship.payment.cancelhistory.repository.PaymentCancelHistoryRepository;
import shop.gaship.payment.exception.FileUploadFailureException;
import shop.gaship.payment.exception.SavePaymentFileException;
import shop.gaship.payment.history.dto.request.PaymentHistoryRequest;
import shop.gaship.payment.history.dto.request.PaymentHistoryRequestDto;
import shop.gaship.payment.history.entity.PaymentHistory;
import shop.gaship.payment.history.enumm.PaymentProvider;
import shop.gaship.payment.history.repository.PaymentHistoryRepository;
import shop.gaship.payment.history.service.PaymentHistoryService;
import shop.gaship.payment.process.adapter.OrderAdapter;
import shop.gaship.payment.process.adapter.dto.request.CancelOrderRequestDto;
import shop.gaship.payment.process.adapter.dto.request.FailCancelOrderRequestDto;
import shop.gaship.payment.process.adapter.dto.request.SuccessOrderRequestDto;
import shop.gaship.payment.process.dto.CancelOrderInfo;
import shop.gaship.payment.process.dto.request.OrderPaymentCancelRequestDto;
import shop.gaship.payment.process.dto.request.PaymentSuccessRequestDto;
import shop.gaship.payment.process.dto.response.OrderPaymentResponseDto;
import shop.gaship.payment.process.exception.CancelPaymentFailureException;
import shop.gaship.payment.process.exception.InvalidCancelAmountException;
import shop.gaship.payment.process.exception.InvalidOrderAmountException;
import shop.gaship.payment.process.exception.PaymentCancelException;
import shop.gaship.payment.process.exception.PaymentFailureException;
import shop.gaship.payment.process.exception.PaymentParserException;
import shop.gaship.payment.process.exception.ShopServerException;
import shop.gaship.payment.process.factory.PaymentBuilderFactory;
import shop.gaship.payment.process.factory.domain.Payment;
import shop.gaship.payment.process.factory.domain.PaymentParser;
import shop.gaship.payment.process.service.PaymentService;
import shop.gaship.payment.util.FileUploadUtil;

/**
 * 결제관련 요청 처리로직을 위한 payment service interface 구현체.
 *
 * @author : 김세미
 * @since 1.0
 * @see PaymentService
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final OrderAdapter orderAdapter;
    private final FileUploadUtil fileUploadUtil;
    private final PaymentBuilderFactory paymentBuilderFactory;
    private final PaymentHistoryService paymentHistoryService;
    private final PaymentHistoryRepository paymentHistoryRepository;
    private final PaymentCancelHistoryRepository paymentCancelHistoryRepository;

    /**
     * {@inheritDoc}
     *
     * @throws InvalidOrderAmountException 결제 금액이 유효하지 않습니다.
     */
    @Override
    @Transactional
    public void successPayment(PaymentSuccessRequestDto requestDto) {
        OrderPaymentResponseDto orderPaymentResponseDto = orderAdapter
                .getOrderByNo(requestDto.getOrderId());

        checkValidAmount(orderPaymentResponseDto, requestDto.getAmount());

        Payment payment = paymentBuilderFactory
                .build(PaymentProvider
                        .valueOf(requestDto.getProvider()));

        try {
            JsonNode paymentResponseData = payment.getAdapter()
                    .requestSuccessPayment(requestDto);

            orderAdapter.successOrder(SuccessOrderRequestDto.builder()
                        .paymentKey(requestDto.getPaymentKey())
                        .orderNo(requestDto.getOrderId())
                    .build());

            savePaymentHistory(paymentResponseData,
                    requestDto.getOrderId(),
                    payment.getParser());

        } catch (PaymentFailureException e) {
            failPayment(requestDto.getPaymentKey(),
                    PaymentProvider.valueOf(requestDto.getProvider()),
                    orderPaymentResponseDto);

            log.error("error: {}, message: {}", e.getClass(), e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional
    public void failPayment(String paymentKey,
                            PaymentProvider paymentProvider,
                            OrderPaymentResponseDto orderPaymentResponseDto) {

        PaymentHistory failPaymentHistory = PaymentHistory.createFailedHistory(
                PaymentHistoryRequestDto.builder()
                        .paymentKey(paymentKey)
                        .provider(paymentProvider)
                        .orderNo(orderPaymentResponseDto.getNo())
                        .totalAmount(orderPaymentResponseDto.getTotalOrderAmount())
                        .requestedAt(LocalDateTime.now())
                .build());

        paymentHistoryRepository.save(failPaymentHistory);

        PaymentCancelHistory paymentCancelHistory = paymentCancelHistoryRepository.save(
                PaymentCancelHistory.builder()
                        .canceledAt(failPaymentHistory.getApprovedAt())
                        .paymentHistory(failPaymentHistory)
                        .cancelReason(PaymentFailureException.MESSAGE)
                        .cancelAmount(0L)
                .build());

        orderAdapter.failCancelOrder(FailCancelOrderRequestDto.builder()
                .paymentCancelHistoryNo(paymentCancelHistory.getNo())
                        .restoreOrderProductNos(orderPaymentResponseDto.getOrderProductNos())
                .build());
    }

    @Override
    @Transactional
    public void cancelPayment(Integer orderNo, OrderPaymentCancelRequestDto requestDto) {
        String orderPaymentKey =
                orderAdapter.getCancelOrderDetails(orderNo)
                        .getPaymentKey();

        PaymentHistory paymentHistory =
                paymentHistoryService.findPaymentHistory(orderPaymentKey);

        Long totalCancelAmount = getTotalCancelAmount(
                requestDto.getCancelOrderInfos(),
                paymentHistory.getBalanceAmount());

        PaymentCancelHistory paymentCancelHistory =
                paymentCancelHistoryRepository.save(
                        PaymentCancelHistory.builder()
                            .paymentHistory(paymentHistory)
                            .cancelAmount(totalCancelAmount)
                            .cancelReason(requestDto.getCancelReason())
                            .canceledAt(LocalDateTime.now())
                        .build()
                );

        try {
            orderAdapter.cancelOrder(
                    CancelOrderRequestDto
                            .builder()
                            .paymentCancelHistoryNo(paymentCancelHistory.getNo())
                            .cancelReason(requestDto.getCancelReason())
                            .cancelOrderInfos(requestDto.getCancelOrderInfos())
                            .build()
            );
        } catch (ShopServerException e) {
            throw new PaymentCancelException();
        }

        Payment payment = paymentBuilderFactory
                .build(paymentHistory.getPaymentProvider());

        try {
            CancelPaymentResponseDto cancelPaymentResponseDto =
                    payment.cancel(orderPaymentKey,
                            requestDto,
                            totalCancelAmount);

            paymentHistory
                    .cancelPartial(cancelPaymentResponseDto.getBalanceAmount());
            paymentCancelHistory
                    .cancelComplete(cancelPaymentResponseDto.getCanceledAt());
        } catch (CancelPaymentFailureException e) {
            orderAdapter.failCancelOrder(
                    FailCancelOrderRequestDto.builder()
                            .paymentCancelHistoryNo(paymentCancelHistory.getNo())
                            .restoreOrderProductNos(requestDto.getCancelOrderInfos().stream()
                                    .map(CancelOrderInfo::getCancelOrderProductNo)
                                    .collect(Collectors.toList()))
                    .build()
            );

            log.error("cause : {} , message : {}", e.getCause(), e.getMessage());
        } catch (PaymentParserException e) {
            paymentHistory
                    .cancelPartial(paymentHistory.getBalanceAmount() - totalCancelAmount);
            paymentCancelHistory
                    .cancelComplete(LocalDateTime.now());

            log.error("cause : {} , message : {}", e.getCause(), e.getMessage());
        }
    }

    private void checkValidAmount(OrderPaymentResponseDto orderPaymentResponseDto,
                                  Long amount) {
        if (!orderPaymentResponseDto
                .getTotalOrderAmount()
                .equals(amount)) {
            throw new InvalidOrderAmountException();
        }
    }

    private void savePaymentHistory(JsonNode paymentResponseData,
                                    Integer orderNo,
                                    PaymentParser parser) {
        try {
            fileUploadUtil.writePaymentFile("/payment-result",
                    "payment-success_" + orderNo, paymentResponseData);
        } catch (FileUploadFailureException e) {
            throw new SavePaymentFileException();
        } finally {
            PaymentHistoryRequest paymentHistoryRequest =
                    parser.parse(orderNo, paymentResponseData);

            paymentHistoryRequest.register(paymentHistoryService);
        }
    }

    private Long getTotalCancelAmount(List<CancelOrderInfo> cancelOrderInfoList, Long balanceAmount) {
        Long totalCancelAmount = 0L;

        for (CancelOrderInfo cancelOrderInfo : cancelOrderInfoList) {
            totalCancelAmount += cancelOrderInfo.getCancelAmount();
        }

        if (balanceAmount < totalCancelAmount) {
            throw new InvalidCancelAmountException();
        }

        return totalCancelAmount;
    }
}

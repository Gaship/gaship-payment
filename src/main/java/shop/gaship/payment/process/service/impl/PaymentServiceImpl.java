package shop.gaship.payment.process.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.gaship.payment.exception.FileUploadFailureException;
import shop.gaship.payment.exception.SavePaymentFileException;
import shop.gaship.payment.history.dto.request.PaymentHistoryRequest;
import shop.gaship.payment.history.service.PaymentHistoryService;
import shop.gaship.payment.process.adapter.OrderAdapter;
import shop.gaship.payment.process.dto.request.PaymentSuccessRequestDto;
import shop.gaship.payment.process.dto.response.OrderResponseDto;
import shop.gaship.payment.process.exception.InvalidOrderAmountException;
import shop.gaship.payment.process.factory.PaymentBuilderFactory;
import shop.gaship.payment.process.factory.domain.Payment;
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

    @Override
    @Transactional
    public void successPayment(PaymentSuccessRequestDto requestDto) {
        checkValidAmount(requestDto.getOrderId(), requestDto.getAmount());

        Payment payment = paymentBuilderFactory
                .build(requestDto.getProvider());

        JsonNode paymentResponseData = payment.getAdapter()
                .requestSuccessPayment(requestDto);

        try {
            fileUploadUtil.writePaymentFile("/payment-result",
                    requestDto.getOrderId(), paymentResponseData);

        } catch (FileUploadFailureException e) {
            throw new SavePaymentFileException();

        } finally {
            PaymentHistoryRequest paymentHistoryRequest =
                    payment.getParser()
                            .parse(paymentResponseData);

            paymentHistoryRequest.register(paymentHistoryService);
        }
    }

    @Override
    public void failPayment(String code, String message, String orderId) {
        // 개발 예정
    }

    private void checkValidAmount(String orderId, Long amount) {
        OrderResponseDto orderResponseDto = orderAdapter
                .getOrderById(orderId);

        if (orderResponseDto
                .getTotalOrderAmount()
                .equals(amount)) {
            throw new InvalidOrderAmountException();
        }
    }
}

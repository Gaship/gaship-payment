package shop.gaship.payment.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import shop.gaship.payment.adapter.PaymentHistoryAdapter;
import shop.gaship.payment.adapter.TossAdapter;
import shop.gaship.payment.domain.Payment;
import shop.gaship.payment.dto.request.FailedPaymentHistoryAddRequestDto;
import shop.gaship.payment.dto.request.PaymentRequestDto;
import shop.gaship.payment.dto.request.SuccessfulPaymentHistoryAddRequestDto;
import shop.gaship.payment.exception.FileUploadFailureException;
import shop.gaship.payment.service.PaymentService;
import shop.gaship.payment.util.FileUploadUtil;

import java.io.IOException;

/**
 * 결제관련 요청 처리로직을 위한 payment service interface 구현체.
 *
 * @author : 김세미
 * @since 1.0
 * @see shop.gaship.payment.service.PaymentService
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final TossAdapter tossAdapter;
    private final PaymentHistoryAdapter paymentHistoryAdapter;
    private final FileUploadUtil fileUploadUtil;

    @Override
    public void successPayment(String paymentKey, String orderId, Long amount){
        Payment paymentResult = tossAdapter.requestSuccessPayment(
                PaymentRequestDto
                        .builder()
                        .paymentKey(paymentKey)
                        .orderId(orderId)
                        .amount(amount)
                        .build()
        );

        try {
            fileUploadUtil.writePaymentFile("/payment-result", paymentResult);
        } catch (IOException e) {
            throw new FileUploadFailureException();
        } finally {
            paymentHistoryAdapter.addSuccessfulPaymentHistory(
                    SuccessfulPaymentHistoryAddRequestDto.builder()
                            .payment(paymentResult)
                            .build()
            );
        }
    }

    @Override
    public void failPayment(String code, String message, String orderId) {
        paymentHistoryAdapter.addFailedPaymentHistory(
                FailedPaymentHistoryAddRequestDto
                .builder()
                .orderId(orderId)
                .errorCode(code)
                .errorMessage(message)
                .build());
    }
}

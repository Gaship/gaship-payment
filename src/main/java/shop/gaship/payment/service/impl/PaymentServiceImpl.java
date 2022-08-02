package shop.gaship.payment.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.gaship.payment.adapter.PaymentHistoryAdapter;
import shop.gaship.payment.adapter.TossAdapter;
import shop.gaship.payment.dto.request.FailedPaymentHistoryRequestDto;
import shop.gaship.payment.dto.request.PaymentRequestDto;
import shop.gaship.payment.service.PaymentService;

/**
 * 결제관련 요청 처리로직을 위한 payment service interface 구현체.
 *
 * @author : 김세미
 * @since 1.0
 * @see shop.gaship.payment.service.PaymentService
 */
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final TossAdapter tossAdapter;
    private final PaymentHistoryAdapter paymentHistoryAdapter;

    @Override
    public void successPayment(PaymentRequestDto requestDto){
        tossAdapter.requestSuccessPayment(requestDto);
    }

    @Override
    public void failPayment(String code, String message, String orderId) {
        paymentHistoryAdapter.addFailedPaymentHistory(
                FailedPaymentHistoryRequestDto
                .builder()
                .orderId(orderId)
                .errorCode(code)
                .errorMessage(message)
                .build());
    }
}

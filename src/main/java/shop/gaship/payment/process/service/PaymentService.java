package shop.gaship.payment.process.service;

import shop.gaship.payment.process.dto.request.PaymentSuccessRequestDto;

/**
 * 결제 요청 관련 처리 로직을 위한 service interface.
 *
 * @author : 김세미
 * @since 1.0
 */
public interface PaymentService {
    void successPayment(PaymentSuccessRequestDto requestDto);

    void failPayment(String code, String message, String orderId);
}

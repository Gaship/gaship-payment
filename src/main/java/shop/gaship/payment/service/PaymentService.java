package shop.gaship.payment.service;

import shop.gaship.payment.dto.request.PaymentRequestDto;

/**
 * 결제 요청 관련 처리 로직을 위한 service interface.
 *
 * @author : 김세미
 * @since 1.0
 */
public interface PaymentService {
    void addPayment(PaymentRequestDto requestDto);
}

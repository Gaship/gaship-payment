package shop.gaship.payment.adapter;

import shop.gaship.payment.domain.Payment;
import shop.gaship.payment.dto.request.PaymentRequestDto;

/**
 * Toss Payment 결제 관련 요청을 위한 adapter interface.
 *
 * @author : 김세미
 * @since 1.0
 */
public interface TossAdapter {
    Payment requestSuccessPayment(PaymentRequestDto requestDto);
}

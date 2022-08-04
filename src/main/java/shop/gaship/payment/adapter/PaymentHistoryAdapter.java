package shop.gaship.payment.adapter;

import shop.gaship.payment.dto.request.FailedPaymentHistoryAddRequestDto;
import shop.gaship.payment.dto.request.SuccessfulPaymentHistoryAddRequestDto;

/**
 * 결제이력 관련 요청을 처리하는 adapter interface.
 *
 * @author : 김세미
 * @since 1.0
 */
public interface PaymentHistoryAdapter {
    void addSuccessfulPaymentHistory(SuccessfulPaymentHistoryAddRequestDto requestDto);

    void addFailedPaymentHistory(FailedPaymentHistoryAddRequestDto requestDto);
}

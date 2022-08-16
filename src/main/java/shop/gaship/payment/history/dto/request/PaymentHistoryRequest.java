package shop.gaship.payment.history.dto.request;

import shop.gaship.payment.history.service.PaymentHistoryService;

/**
 * 결제이력 등록 요청 interface 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
public interface PaymentHistoryRequest {
    void register(PaymentHistoryService paymentHistoryService);
}

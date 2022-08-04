package shop.gaship.payment.service;

/**
 * 결제 요청 관련 처리 로직을 위한 service interface.
 *
 * @author : 김세미
 * @since 1.0
 */
public interface PaymentService {
    void successPayment(String paymentKey, String orderId, Long amount);

    void failPayment(String code, String message, String orderId);
}

package shop.gaship.payment.status.service;

import shop.gaship.payment.status.entity.PaymentStatusCode;

/**
 * 결제상태 service interface 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
public interface PaymentStatusService {
    PaymentStatusCode findPaymentStatus(String statusCodeName);
}

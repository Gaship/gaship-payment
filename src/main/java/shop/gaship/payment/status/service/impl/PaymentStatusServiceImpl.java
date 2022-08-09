package shop.gaship.payment.status.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.gaship.payment.status.entity.PaymentStatusCode;
import shop.gaship.payment.status.exception.StatusCodeNotFoundException;
import shop.gaship.payment.status.repository.PaymentStatusRepository;
import shop.gaship.payment.status.service.PaymentStatusService;

/**
 * 결제상태 service interface 구현체입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class PaymentStatusServiceImpl implements PaymentStatusService {
    private final PaymentStatusRepository paymentStatusRepository;

    @Override
    public PaymentStatusCode findPaymentStatus(String value) {
        return paymentStatusRepository.findByStatusValue(value)
                .orElseThrow(StatusCodeNotFoundException::new);
    }
}

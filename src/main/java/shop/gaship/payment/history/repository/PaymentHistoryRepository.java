package shop.gaship.payment.history.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.gaship.payment.history.entity.PaymentHistory;

/**
 * 결제이력 repository interface 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, String> {
}

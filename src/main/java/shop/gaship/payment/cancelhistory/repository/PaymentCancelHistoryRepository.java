package shop.gaship.payment.cancelhistory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.gaship.payment.cancelhistory.entity.PaymentCancelHistory;

/**
 * 결제취소이력 repository interface.
 *
 * @author : 김세미
 * @since 1.0
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface PaymentCancelHistoryRepository
        extends JpaRepository<PaymentCancelHistory, Integer> {
}

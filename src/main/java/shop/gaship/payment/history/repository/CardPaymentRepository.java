package shop.gaship.payment.history.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.gaship.payment.history.entity.CardPayment;

/**
 * 카드결제 repository interface 입니다.
 *
 * @author : 김세미
 * @since 1.0
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface CardPaymentRepository extends JpaRepository<CardPayment, String> {
}

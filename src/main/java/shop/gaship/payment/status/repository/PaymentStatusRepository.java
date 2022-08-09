package shop.gaship.payment.status.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.gaship.payment.status.entity.PaymentStatusCode;

/**
 * 결제상태 jpa repository interface 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
public interface PaymentStatusRepository extends JpaRepository<PaymentStatusCode, Integer> {
    @Query("select s from PaymentStatusCode s where s.value = :value")
    Optional<PaymentStatusCode> findByStatusValue(@Param("value") String value);
}

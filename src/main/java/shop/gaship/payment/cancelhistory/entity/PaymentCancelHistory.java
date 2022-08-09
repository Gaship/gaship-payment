package shop.gaship.payment.cancelhistory.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.gaship.payment.history.entity.PaymentHistory;

/**
 * 결제취소이력 entity class 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Getter
@NoArgsConstructor
@Entity
@Table(name = "payment_cancel_histories")
public class PaymentCancelHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_cancel_history_no")
    private Integer no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_key", nullable = false)
    private PaymentHistory paymentHistory;

    @NotNull
    private Integer canceledOrderProductNo;

    @NotNull
    private Integer cancelAmount;

    @NotNull
    private String cancelReason;

    @NotNull
    private LocalDateTime canceledAt;

    @Column(name = "canceled_failure_message")
    private String failureMessage;
}

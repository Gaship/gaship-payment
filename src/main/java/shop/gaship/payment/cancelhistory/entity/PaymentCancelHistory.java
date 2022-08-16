package shop.gaship.payment.cancelhistory.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import lombok.Builder;
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

    @Column(name = "is_complete", columnDefinition = "tinyint(1) default 0")
    private boolean complete;

    @NotNull
    private Long cancelAmount;

    @NotNull
    private String cancelReason;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime canceledAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime cancelApprovedAt;

    @Column(name = "canceled_failure_message")
    private String failureMessage;

    /**
     * 결제 취소 이력을 생성하는 생성자입니다.
     *
     * @param paymentHistory the payment history
     * @param cancelAmount   the cancel amount
     * @param cancelReason   the cancel reason
     * @param canceledAt     the canceled at
     */
    @Builder
    public PaymentCancelHistory(PaymentHistory paymentHistory,
                                Long cancelAmount,
                                String cancelReason,
                                LocalDateTime canceledAt) {
        this.paymentHistory = paymentHistory;
        this.cancelAmount = cancelAmount;
        this.cancelReason = cancelReason;
        this.canceledAt = canceledAt;
    }

    /**
     * 결제 취소 처리가 완료되었을때 해당 결제 취소 이력의 완료값을 true 로 변경합니다.
     */
    public void cancelComplete(LocalDateTime cancelApprovedAt) {
        this.complete = true;
        this.cancelApprovedAt = cancelApprovedAt;
    }

    /**
     * 결제 취소 요청이 실패했을때 해당 결제 취소 이력에 실패 내용을 추가합니다.
     *
     * @param failureMessage 결제 취소 요청의 실패 내용입니다. (String)
     */
    public void modifyFailureMessage(String failureMessage) {
        this.failureMessage = failureMessage;
    }
}

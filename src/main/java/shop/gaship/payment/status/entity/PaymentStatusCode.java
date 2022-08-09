package shop.gaship.payment.status.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 결제상태 entity class 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Getter
@NoArgsConstructor
@Entity
@Table(name = "payment_status_codes")
public class PaymentStatusCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_status_code_no")
    private Integer no;

    @Column(name = "payment_status_value", nullable = false)
    private String value;

    @Column(name = "status_explanation")
    private String explanation;

    @Builder
    public PaymentStatusCode(String value, String explanation) {
        this.value = value;
        this.explanation = explanation;
    }
}

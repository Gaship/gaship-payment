package shop.gaship.payment.history.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.gaship.payment.history.dto.request.PaymentHistoryRequestDto;
import shop.gaship.payment.status.entity.PaymentStatusCode;


/**
 * 결제이력 entity class 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Getter
@NoArgsConstructor
@Entity
@Table(name = "payment_histories")
@Inheritance(strategy = InheritanceType.JOINED)
public class PaymentHistory {
    @Id
    private String paymentKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_status_no")
    private PaymentStatusCode paymentStatusCode;
    @NotNull
    private String orderId;
    @NotNull
    private String orderName;
    @NotNull
    private String paymentMethod;
    @NotNull
    private Long totalAmount;
    @NotNull
    private LocalDateTime requestedAt;

    private LocalDateTime approvedAt;
    private Long balanceAmount;
    private String currency;
    private String country;
    private String receiptUrl;

    @Column(name = "payment_failure_message")
    private String failureMessage;

    /**
     * Instantiates a new Payment history.
     *
     * @param requestDto    결제이력에 대한 데이터입니다. (PaymentHistoryRequestDto)
     * @param paymentStatus 결제 결과에 대한 상태입니다. (PaymentStatusCode)
     */
    public PaymentHistory(PaymentHistoryRequestDto requestDto, PaymentStatusCode paymentStatus) {
        this.paymentKey = requestDto.getPaymentKey();
        this.paymentStatusCode = paymentStatus;
        this.orderId = requestDto.getOrderId();
        this.orderName = requestDto.getOrderName();
        this.paymentMethod = requestDto.getPaymentMethod();
        this.totalAmount = requestDto.getTotalAmount();
        this.requestedAt = requestDto.getRequestedAt();
        this.approvedAt = requestDto.getApprovedAt();
        this.balanceAmount = requestDto.getBalanceAmount();
        this.currency = requestDto.getCurrency();
        this.country = requestDto.getCountry();
        this.receiptUrl = requestDto.getReceiptUrl();
    }
}

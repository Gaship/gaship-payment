package shop.gaship.payment.history.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.gaship.payment.history.dto.request.PaymentHistoryRequestDto;
import shop.gaship.payment.history.enumm.PaymentProvider;
import shop.gaship.payment.status.enumm.PaymentStatus;


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

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private PaymentProvider paymentProvider;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private PaymentStatus paymentStatus;

    @NotNull
    @Column(unique = true)
    private Integer orderNo;

    @NotNull
    private Long totalAmount;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime requestedAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime approvedAt;
    private String orderName;
    private String paymentMethod;
    private Long balanceAmount;
    private String currency;
    private String country;
    private String receiptUrl;

    /**
     * 결제 요청이 성공한 경우의 결제 이력을 생성합니다.
     *
     * @param requestDto 결제 이력 생성 요청 dto 입니다.
     */
    public PaymentHistory(PaymentHistoryRequestDto requestDto) {
        this.paymentKey = requestDto.getPaymentKey();
        this.paymentProvider = requestDto.getProvider();
        this.paymentStatus = PaymentStatus.SUCCESS;
        this.orderNo = requestDto.getOrderNo();
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

    /**
     * 결제 요청이 실패한 경우의 결제 이력을 생성합니다.
     *
     * @param requestDto 결제 이력 생성 요청 dto 입니다.
     * @return 결제 실패 이력을 반환합니다. (PaymentHistory)
     */
    public static PaymentHistory createFailedHistory(
            PaymentHistoryRequestDto requestDto) {
        PaymentHistory failedPaymentHistory = new PaymentHistory();

        failedPaymentHistory.paymentKey = requestDto.getPaymentKey();
        failedPaymentHistory.paymentProvider = requestDto.getProvider();
        failedPaymentHistory.paymentStatus = PaymentStatus.FAIL;
        failedPaymentHistory.orderNo = requestDto.getOrderNo();
        failedPaymentHistory.orderName = requestDto.getOrderName();
        failedPaymentHistory.paymentMethod = requestDto.getPaymentMethod();
        failedPaymentHistory.totalAmount = requestDto.getTotalAmount();
        failedPaymentHistory.requestedAt = requestDto.getRequestedAt();

        return failedPaymentHistory;
    }

    /**
     * 결제 전체 취소하는 경우 해당 결제 이력의 결제 상태를 CANCELED 로 수정하고
     * 취소 가능 잔여 금액을 0원으로 수정합니다.
     */
    public void cancel() {
        this.paymentStatus = PaymentStatus.CANCELED;
        this.balanceAmount = 0L;
    }

    /**
     * 결제 부분 취소를 하는 경우 해당 결제 이력의 결제 상태를 PARTIAL_CANCELED 로 수정하고
     * 취소 가능 잔여 금액을 수정합니다.
     *
     * @param balanceAmount 결제 부분 취소 완료 후 남은 취소 가능 금액입니다. (Long)
     */
    public void cancelPartial(Long balanceAmount) {
        this.paymentStatus = PaymentStatus.PARTIAL_CANCELED;
        this.balanceAmount = balanceAmount;
    }
}

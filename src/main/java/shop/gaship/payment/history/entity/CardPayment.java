package shop.gaship.payment.history.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.gaship.payment.history.dto.request.impl.CardPaymentHistoryRequest;
import shop.gaship.payment.status.entity.PaymentStatusCode;


/**
 * 카드결제 entity class 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Getter
@NoArgsConstructor
@Entity
public class CardPayment extends PaymentHistory {
    @NotNull
    private String cardCompany;
    @NotNull
    private String approveNo;
    @NotNull
    private Long cardPayAmount;

    /**
     * Instantiates a new Card payment.
     *
     * @param requestDto    카드결제이력에 대한 데이터입니다. (CardPaymentHistoryRequest)
     * @param paymentStatus 결제 결과에 대한 상태입니다. (PaymentStatusCode)
     */
    @Builder
    public CardPayment(CardPaymentHistoryRequest requestDto, PaymentStatusCode paymentStatus) {
        super(requestDto.getPaymentHistory(), paymentStatus);
        this.cardCompany = requestDto.getCardCompany();
        this.approveNo = requestDto.getCardApproveNo();
        this.cardPayAmount = requestDto.getCardPayAmount();
    }
}

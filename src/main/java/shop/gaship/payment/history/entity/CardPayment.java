package shop.gaship.payment.history.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.gaship.payment.history.dto.request.impl.CardPaymentHistoryRequest;


/**
 * 카드결제 entity class 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Getter
@NoArgsConstructor
@Entity(name = "card_payments")
public class CardPayment extends PaymentHistory {
    @NotNull
    private String cardCompany;
    @NotNull
    private String approveNo;
    @NotNull
    private Long cardPayAmount;

    /**
     * 카드를 통한 결제가 성공한 경우 카드 결제 이력을 생성하는 생성자입니다.
     *
     * @param requestDto    카드결제이력에 대한 데이터입니다. (CardPaymentHistoryRequest)
     */
    @Builder
    public CardPayment(CardPaymentHistoryRequest requestDto) {
        super(requestDto.getPaymentHistory());
        this.cardCompany = requestDto.getCardCompany();
        this.approveNo = requestDto.getCardApproveNo();
        this.cardPayAmount = requestDto.getCardPayAmount();
    }
}

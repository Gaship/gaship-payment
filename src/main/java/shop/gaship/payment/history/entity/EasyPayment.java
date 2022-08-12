package shop.gaship.payment.history.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.gaship.payment.history.dto.request.impl.EasyPaymentHistoryRequest;


/**
 * 간편결제 entity class 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Getter
@NoArgsConstructor
@Entity(name = "easy_payments")
public class EasyPayment extends PaymentHistory {
    @NotNull
    private String easyPayProvider;
    @NotNull
    private Long easyPayAmount;

    /**
     * 간편 결제를 통해 결제에 성공한 경우 간편 결제 이력을 생성하는 생성자입니다.
     *
     * @param requestDto    간편결제에 대한 데이터입니다. (EasyPaymentHistoryRequest)
     */
    @Builder
    public EasyPayment(EasyPaymentHistoryRequest requestDto) {
        super(requestDto.getPaymentHistory());
        this.easyPayProvider = requestDto.getEasyPayProvider();
        this.easyPayAmount = requestDto.getEasyPayAmount();
    }
}

package shop.gaship.payment.history.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.gaship.payment.history.dto.request.impl.EasyPaymentHistoryRequest;
import shop.gaship.payment.status.entity.PaymentStatusCode;



/**
 * 간편결제 entity class 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Getter
@NoArgsConstructor
@Entity
public class EasyPayment extends PaymentHistory {
    @NotNull
    private String easyPayProvider;
    @NotNull
    private Long easyPayAmount;

    /**
     * Instantiates a new Easy payment.
     *
     * @param requestDto    간편결제에 대한 데이터입니다. (EasyPaymentHistoryRequest)
     * @param paymentStatus 결제 결과에 대한 상태입니다. (PaymentStatusCode)
     */
    @Builder
    public EasyPayment(EasyPaymentHistoryRequest requestDto, PaymentStatusCode paymentStatus) {
        super(requestDto.getPaymentHistory(), paymentStatus);
        this.easyPayProvider = requestDto.getEasyPayProvider();
        this.easyPayAmount = requestDto.getEasyPayAmount();
    }
}

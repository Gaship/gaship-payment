package shop.gaship.payment.process.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 결제 성공 승인 요청 data transfer object 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Getter
@NoArgsConstructor
public class PaymentSuccessRequestDto {
    @NotNull(message = "결제 식별키는 필수값입니다.")
    private String paymentKey;

    @Positive(message = "주문 식별번호는 양수입니다.")
    @NotNull(message = "주문 식별번호는 필수값입니다.")
    private Integer orderId;

    @Positive(message = "결제 금액은 0보다 큰 양수입니다.")
    @NotNull(message = "결제 금액은 필수값입니다.")
    private Long amount;

    @NotNull(message = "결제 제공사는 필수값입니다.")
    private String provider;
}

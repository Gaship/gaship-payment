package shop.gaship.payment.dto.request;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 결제요청에 대한 정보를 가지고 있는 request data transfer object.
 *
 * @author : 김세미
 * @since 1.0
 */
@Builder
@Getter
public class PaymentRequestDto {
    @NotBlank(message = "paymentKey 는 필수값입니다.")
    private String paymentKey;

    @NotNull(message = "결제금액은 필수값입니다.")
    private Long amount;
//    @Pattern(regexp = "/[a-zA-Z\\d_\\-=]*/",
//            message = "주문식별 ID 는 영문 대소문자,숫자,_-= 로만 구성가능합니다.")
    @NotBlank(message = "주문식별 ID 는 필수값입니다.")
    @Length(min = 6, max = 64, message = "주문 식별 ID 의 길이는 최소 6이상 최대 64 이하입니다.")
    private String orderId;
}

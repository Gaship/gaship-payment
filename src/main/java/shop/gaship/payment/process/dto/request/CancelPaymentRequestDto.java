package shop.gaship.payment.process.dto.request;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

/**
 * 결제 api 에게 결제 취소 요청을 보내기 위한 dto 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Getter
@Builder
public class CancelPaymentRequestDto {
    @NotNull
    private String cancelReason;
    @NotNull
    private Long cancelAmount;
}

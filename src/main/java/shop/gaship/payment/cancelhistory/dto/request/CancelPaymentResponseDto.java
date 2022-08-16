package shop.gaship.payment.cancelhistory.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;


/**
 * 결제 취소 이력 등록 요청 dto 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Getter
@Builder
public class CancelPaymentResponseDto {
    private Long balanceAmount;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime canceledAt;
}

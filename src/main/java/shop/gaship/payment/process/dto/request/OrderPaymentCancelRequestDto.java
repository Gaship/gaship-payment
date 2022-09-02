package shop.gaship.payment.process.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import shop.gaship.payment.process.dto.CancelOrderInfo;
import java.util.List;

/**
 * 결제 취소 요청 dto 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public class OrderPaymentCancelRequestDto {
    private String cancelReason;
    private List<CancelOrderInfo> cancelOrderInfos;
}

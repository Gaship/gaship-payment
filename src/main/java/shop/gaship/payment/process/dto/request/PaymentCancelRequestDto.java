package shop.gaship.payment.process.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import shop.gaship.payment.process.dto.CancelOrderInfo;

/**
 * 결제 취소 요청 dto 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public class PaymentCancelRequestDto {
    private String paymentKey;
    private String cancelReason;
    private CancelOrderInfo[] cancelOrderInfos;
}

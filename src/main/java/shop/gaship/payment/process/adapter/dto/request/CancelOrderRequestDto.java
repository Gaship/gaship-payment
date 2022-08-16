package shop.gaship.payment.process.adapter.dto.request;

import lombok.Builder;
import lombok.Getter;
import shop.gaship.payment.process.dto.CancelOrderInfo;

/**
 * 주문 취소 처리 요청을 위한 dto 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Getter
@Builder
public class CancelOrderRequestDto {
    private Integer paymentCancelHistoryNo;
    private String cancelReason;
    private CancelOrderInfo[] cancelOrderInfos;
}

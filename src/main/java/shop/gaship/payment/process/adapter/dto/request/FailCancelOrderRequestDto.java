package shop.gaship.payment.process.adapter.dto.request;

import java.util.List;
import lombok.Builder;
import lombok.Getter;


/**
 * 결제 취소 실패로 인한 주문 취소 실패 요청을 위한 dto 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Getter
@Builder
public class FailCancelOrderRequestDto {
    private Integer paymentCancelHistoryNo;
    private List<Integer> restoreOrderProductNos;
}

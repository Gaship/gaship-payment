package shop.gaship.payment.process.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 결제 취소하는 주문상품에 대한 정보를 갖는 dto 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public class CancelOrderInfo {
    private Integer cancelOrderProductNo;
    private Long cancelAmount;
}

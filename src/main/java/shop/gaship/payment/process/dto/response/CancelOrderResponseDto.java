package shop.gaship.payment.process.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 취소하려는 주문 상품이 속한 주문의 결제 식별키 요청에 대한 응답 dto 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Getter
@NoArgsConstructor
public class CancelOrderResponseDto {
    private String paymentKey;
}

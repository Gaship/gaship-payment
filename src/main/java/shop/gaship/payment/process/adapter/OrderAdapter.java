package shop.gaship.payment.process.adapter;

import shop.gaship.payment.process.dto.response.OrderResponseDto;

/**
 * 주문 관련 데이터 요청을 위한 adapter interface 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
public interface OrderAdapter {
    OrderResponseDto getOrderById(String orderId);
}

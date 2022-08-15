package shop.gaship.payment.process.adapter;

import shop.gaship.payment.process.adapter.dto.request.CancelOrderRequestDto;
import shop.gaship.payment.process.adapter.dto.request.FailCancelOrderRequestDto;
import shop.gaship.payment.process.adapter.dto.request.SuccessOrderRequestDto;
import shop.gaship.payment.process.dto.response.OrderResponseDto;

/**
 * 주문 관련 데이터 요청을 위한 adapter interface 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
public interface OrderAdapter {
    /**
     * 주문 식별번호를 통한 주문 단건 조회 요청을 보냅니다.
     *
     * @param orderNo 조회하려는 주문 식별번호입니다.
     * @return 조회 요청에 대한 응답을 반환합니다.
     */
    OrderResponseDto getOrderByNo(Integer orderNo);

    /**
     * 주문 취소 요청을 보냅니다.
     *
     * @param requestDto 취소할 주문에 대한 정보입니다.
     */
    void cancelOrder(CancelOrderRequestDto requestDto);

    /**
     * 주문 취소 실패에 대한 처리 요청을 보냅니다.
     *
     * @param requestDto 실패한 주문 취소에 대한 정보입니다.
     */
    void failCancelOrder(FailCancelOrderRequestDto requestDto);

    /**
     * 결제 처리가 성공하여 주문 성공처리 요청을 보냅니다.
     *
     * @param requestDto 성공처리할 주문에 대한 정보입니다.
     */
    void successOrder(SuccessOrderRequestDto requestDto);
}

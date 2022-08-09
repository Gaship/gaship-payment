package shop.gaship.payment.process.adapter;

import com.fasterxml.jackson.databind.JsonNode;
import shop.gaship.payment.process.dto.request.PaymentSuccessRequestDto;

/**
 * 결제 처리 api 에게 결제 승인/조회/취소 요청을 하기 위한 adapter interface 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
public interface PaymentAdapter {
    JsonNode requestSuccessPayment(PaymentSuccessRequestDto requestDto);
}

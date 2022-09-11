package shop.gaship.payment.process.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import shop.gaship.payment.process.dto.response.OrderPaymentResponseDto;

/**
 * 결제 실패 처리 요청을 위한 dto 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public class FailurePaymentRequestDto {
    private String paymentKey;
    private String provider;
    private OrderPaymentResponseDto orderPaymentResponseDto;
}

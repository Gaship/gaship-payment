package shop.gaship.payment.process.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 결제 성공 승인 요청 data transfer object 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Getter
@NoArgsConstructor
public class PaymentSuccessRequestDto {
    private String paymentKey;
    private String orderId;
    private Long amount;
    private String provider;
}

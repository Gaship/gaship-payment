package shop.gaship.payment.history.dto.request;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;


/**
 * 결제이력 등록 요청을 위한 data transfer object 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Getter
@Builder
public class PaymentHistoryRequestDto {
    private String paymentKey;
    private String orderId;
    private String orderName;
    private String paymentMethod;
    private Long totalAmount;
    private LocalDateTime requestedAt;
    private LocalDateTime approvedAt;
    private Long balanceAmount;
    private String currency;
    private String country;
    private String receiptUrl;
}

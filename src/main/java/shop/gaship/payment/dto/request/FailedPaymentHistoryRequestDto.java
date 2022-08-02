package shop.gaship.payment.dto.request;

import lombok.Builder;

/**
 * 실패 결제 이력 등록 요청 request data transfer object.
 *
 * @author : 김세미
 * @since 1.0
 */
@Builder
public class FailedPaymentHistoryRequestDto {
    private final String orderId;
    private final String errorCode;
    private final String errorMessage;
}

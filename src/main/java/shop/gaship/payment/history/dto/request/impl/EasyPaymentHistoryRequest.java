package shop.gaship.payment.history.dto.request.impl;

import lombok.Builder;
import lombok.Getter;
import shop.gaship.payment.history.dto.request.PaymentHistoryRequest;
import shop.gaship.payment.history.dto.request.PaymentHistoryRequestDto;
import shop.gaship.payment.history.service.PaymentHistoryService;

/**
 * 간편결제이력 등록 요청을 위한 request data transfer object 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Getter
@Builder
public class EasyPaymentHistoryRequest implements PaymentHistoryRequest {
    private PaymentHistoryRequestDto paymentHistory;
    private String easyPayProvider;
    private Long easyPayAmount;

    @Override
    public void register(PaymentHistoryService paymentHistoryService) {
        paymentHistoryService.addPaymentHistory(this);
    }
}

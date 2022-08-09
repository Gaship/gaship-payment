package shop.gaship.payment.history.dto.request.impl;

import lombok.Builder;
import lombok.Getter;
import shop.gaship.payment.history.dto.request.PaymentHistoryRequest;
import shop.gaship.payment.history.dto.request.PaymentHistoryRequestDto;
import shop.gaship.payment.history.service.PaymentHistoryService;

/**
 * 카드결제이력 등록을 위한 dto 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Getter
@Builder
public class CardPaymentHistoryRequest implements PaymentHistoryRequest {
    private PaymentHistoryRequestDto paymentHistory;
    private String cardCompany;
    private String cardApproveNo;
    private Long cardPayAmount;

    @Override
    public void register(PaymentHistoryService paymentHistoryService) {
        paymentHistoryService.addPaymentHistory(this);
    }
}

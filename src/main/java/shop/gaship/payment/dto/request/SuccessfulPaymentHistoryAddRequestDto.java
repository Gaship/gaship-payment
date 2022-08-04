package shop.gaship.payment.dto.request;

import lombok.Builder;
import lombok.Getter;
import shop.gaship.payment.domain.Payment;

/**
 * 결제 승인 처리시 결제 이력 등록 요청 data transfer object.
 *
 * @author : 김세미
 * @since 1.0
 */
@Getter
public class SuccessfulPaymentHistoryAddRequestDto {
    private final String paymentKey;
    private final String orderId;
    private final String orderName;
    private final String paymentStatus;
    private final String requestedDatetime;
    private final String approvedDatetime;
    private final String paymentMethod;
    private final Long totalAmount;
    private final String receiptUrl;
    private final Payment.Card cardInfo;
    private final Payment.EasyPay easyPayInfo;

    @Builder
    public SuccessfulPaymentHistoryAddRequestDto(Payment payment) {
        this.paymentKey = payment.getPaymentKey();
        this.orderId = payment.getOrderId();
        this.orderName = payment.getOrderName();
        this.paymentStatus = payment.getStatus();
        this.requestedDatetime = payment.getRequestedAt();
        this.approvedDatetime = payment.getApprovedAt();
        this.paymentMethod = payment.getMethod();
        this.totalAmount = payment.getTotalAmount();
        this.receiptUrl = payment.getReceipt().getUrl();
        this.cardInfo = payment.getCard();
        this.easyPayInfo = payment.getEasyPay();
    }
}

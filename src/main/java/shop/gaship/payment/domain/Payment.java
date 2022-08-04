package shop.gaship.payment.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Toss payment 로의 결제 요청시 반환되는 객체.
 *
 * @author 김세미
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class Payment {
    private String mId;
    private String version;
    private String paymentKey;
    private String status;
    private String transactionKey;
    private String lastTransactionKey;
    private String orderId;
    private String orderName;
    private String requestedAt;
    private String approvedAt;
    private Card card;
    private Cancel[] cancels;
    private String type;
    private EasyPay easyPay;
    private String country;
    private Failure failure;
    private Boolean isPartialCancelable;
    private Receipt receipt;
    private String currency;
    private Long totalAmount; // 총 결제 금액
    private Long balanceAmount; // 취소 가능한 금액
    private String method; // 결제할 때 사용한 결제 수단 (카드)
    @Getter
    @Setter
    public static class Receipt {
        private String url;
    }
    @Getter
    @Setter
    public static class Card {
        private String company;
        private String number;
        private String approveNo;
        private String cardType; // 신용, 체크, 기프트
        private String ownerType; // 개인, 법인
        private String acquireStatus; // 카드 결제의 매입 상태
        private String receiptUrl; // 카드 매출 전표
        private Long amount;
    }

    @Getter
    @Setter
    public static class EasyPay{
        private Integer amount;
        private String provider;
        private Number discountAmount;
    }

    @Getter
    @Setter
    public static class Failure{
        private String code;
        private String message;
    }

    @Getter
    @Setter
    public static class Cancel{
        private Number cancelAmount;
        private String cancelReason;
        private Number refundableAmount;
        private String canceledAt;
        private String transactionKey;
    }
}

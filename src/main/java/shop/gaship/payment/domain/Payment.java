package shop.gaship.payment.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Toss payment 로의 결제 요청시 반환되는 객체.
 *
 * @author 김세미
 * @since 1.0
 */
@Getter
@NoArgsConstructor
@ToString
@Setter
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
    private Boolean useEscrow;
    private Boolean cultureExpense;
    private Card card;
    private String virtualAccount;
    private String transfer;
    private String mobilePhone;
    private String giftCertificate;
    private String cashReceipt;
    private String discount;
    private String cancels;
    private String secret;
    private String type;
    private String easyPay;
    private String country;
    private String failure;
    private Boolean isPartialCancelable;
    private Receipt receipt;
    private String currency;
    private Long totalAmount;
    private Long balanceAmount;
    private Long suppliedAmount;
    private Long vat;
    private Long taxFreeAmount;
    private String method;
    @Getter
    @NoArgsConstructor
    public static class Receipt {
        private String url;
    }
    @Getter
    @NoArgsConstructor
    public static class Card {
        private String company;
        private String number;
        private Integer installmentPlanMonths;
        private Boolean isInterestFree;
        private String interestPayer;
        private String approveNo;
        private Boolean useCardPoint;
        private String cardType;
        private String ownerType;
        private String acquireStatus;
        private String receiptUrl;
        private Long amount;
    }
}

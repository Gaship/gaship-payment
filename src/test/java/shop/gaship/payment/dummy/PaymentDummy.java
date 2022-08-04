package shop.gaship.payment.dummy;

import shop.gaship.payment.domain.Payment;

/**
 * Payment dummy data.
 *
 * @author : 김세미
 * @since 1.0
 */
public class PaymentDummy {
    private PaymentDummy(){}

    private static Payment.Card cardDummy(){
        Payment.Card dummy = new Payment.Card();
        dummy.setCardType("체크");
        dummy.setAmount(15_000L);
        dummy.setAcquireStatus("REQUESTED");
        dummy.setCompany("농협");
        dummy.setNumber("1234123412341234");
        dummy.setApproveNo("13212345334");
        dummy.setOwnerType("개인");
        dummy.setReceiptUrl("dummy.com");

        return dummy;
    }

    private static Payment.EasyPay easyPayDummy() {
        Payment.EasyPay dummy = new Payment.EasyPay();
        dummy.setAmount(15000);
        dummy.setProvider("토스");
        dummy.setDiscountAmount(0);

        return dummy;
    }

    private static Payment.Receipt receiptDummy() {
        Payment.Receipt dummy = new Payment.Receipt();
        dummy.setUrl("dummy.com");

        return dummy;
    }

    public static Payment easyPaymentDummy() {
        Payment dummy = new Payment();
        dummy.setMId(null);
        dummy.setVersion("2022-07-27");
        dummy.setPaymentKey("9xMljweGQBN5OWRapdA8dbdwYqx56Vo1zEqZKLPbmD70vk4y");
        dummy.setStatus("DONE");
        dummy.setTransactionKey("75A4EF7E18D26508C555BAD678723506");
        dummy.setLastTransactionKey("75A4EF7E18D26508C555BAD678723506");
        dummy.setOrderId("o2eLCMGbaddfddJ111uS");
        dummy.setRequestedAt("2022-08-02T14:14:29+09:00");
        dummy.setApprovedAt("2022-08-02T14:14:51+09:00");
        dummy.setCard(null);
        dummy.setCancels(null);
        dummy.setType("NORMAL");
        dummy.setEasyPay(easyPayDummy());
        dummy.setCountry("KR");
        dummy.setFailure(null);
        dummy.setIsPartialCancelable(true);
        dummy.setReceipt(receiptDummy());
        dummy.setCurrency("KRW");
        dummy.setTotalAmount(15_000L);
        dummy.setBalanceAmount(15_000L);
        dummy.setMethod("간편결제");

        return dummy;
    }


    public static Payment cardPaymentDummy(){
        Payment dummy = new Payment();
        dummy.setMId(null);
        dummy.setVersion("2022-07-27");
        dummy.setPaymentKey("9xMljweGQBN5OWRapdA8dbdwYqx56Vo1zEqZKLPbmD70vk4y");
        dummy.setStatus("DONE");
        dummy.setTransactionKey("75A4EF7E18D26508C555BAD678723506");
        dummy.setLastTransactionKey("75A4EF7E18D26508C555BAD678723506");
        dummy.setOrderId("o2eLCMGbaddfddJ111uS");
        dummy.setRequestedAt("2022-08-02T14:14:29+09:00");
        dummy.setApprovedAt("2022-08-02T14:14:51+09:00");
        dummy.setCard(cardDummy());
        dummy.setCancels(null);
        dummy.setType("NORMAL");
        dummy.setEasyPay(null);
        dummy.setCountry("KR");
        dummy.setFailure(null);
        dummy.setIsPartialCancelable(true);
        dummy.setReceipt(receiptDummy());
        dummy.setCurrency("KRW");
        dummy.setTotalAmount(15_000L);
        dummy.setBalanceAmount(15_000L);
        dummy.setMethod("간편결제");

        return dummy;
    }
}

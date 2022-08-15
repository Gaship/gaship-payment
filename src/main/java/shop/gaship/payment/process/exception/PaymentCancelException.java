package shop.gaship.payment.process.exception;

/**
 * 결제 취소 실패시 발생하는 Exception 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
public class PaymentCancelException extends RuntimeException {
    public static final String MESSAGE = "결제 취소에 실패하였습니다.";

    public PaymentCancelException() {
        super(MESSAGE);
    }
}

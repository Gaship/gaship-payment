package shop.gaship.payment.process.exception;

/**
 * 결제 api 로의 결제요청 실패시 발생하는 exception 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
public class PaymentFailureException extends RuntimeException {
    public static final String MESSAGE = "결제 요청이 실패하였습니다.";

    public PaymentFailureException() {
        super(MESSAGE);
    }
}

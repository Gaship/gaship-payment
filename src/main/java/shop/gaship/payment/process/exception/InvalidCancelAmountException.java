package shop.gaship.payment.process.exception;

/**
 * 결제 취소 금액이 유효하지 않을 때 발생하는 exception 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
public class InvalidCancelAmountException extends RuntimeException {
    public static final String MESSAGE = "결제 취소 금액이 유효하지 않습니다.";

    public InvalidCancelAmountException() {
        super(MESSAGE);
    }
}

package shop.gaship.payment.process.exception;

/**
 * 결제 승인 요청받은 금액과 주문데이터의 금액이 일치하지 않을때 발생하는 exception 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
public class InvalidOrderAmountException extends RuntimeException {
    public static final String MESSAGE = "결제 금액이 유효하지 않습니다.";

    public InvalidOrderAmountException() {
        super(MESSAGE);
    }
}

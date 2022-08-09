package shop.gaship.payment.status.exception;

/**
 * 상태코드를 찾지 못했을때 발생하는 exception 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
public class StatusCodeNotFoundException extends RuntimeException {
    public static final String MESSAGE = "결제 상태 코드를 찾을 수 없습니다.";

    public StatusCodeNotFoundException() {
        super(MESSAGE);
    }
}

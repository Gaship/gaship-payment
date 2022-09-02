package shop.gaship.payment.exception;

/**
 * shopping mall 서버로 부터 주문에 대한 정보를
 * 찾을 수 없을때 발생하는 Exception 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
public class OrderNotFoundException extends RuntimeException {
    public static final String MESSAGE = "존재하지 않는 주문입니다.";

    public OrderNotFoundException() {
        super(MESSAGE);
    }
}

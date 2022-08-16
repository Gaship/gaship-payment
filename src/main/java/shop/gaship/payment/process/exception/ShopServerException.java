package shop.gaship.payment.process.exception;

/**
 * shopping mall 과 통신시 500 error 가 발생했을때 사용하는 exception 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
public class ShopServerException extends RuntimeException {
    public static final String MESSAGE = "통신 서버에서 문제가 발생하였습니다.";

    public ShopServerException() {
        super(MESSAGE);
    }
}

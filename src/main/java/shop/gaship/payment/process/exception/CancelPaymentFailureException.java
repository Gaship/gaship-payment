package shop.gaship.payment.process.exception;

/**
 * 결제 api 로의 결제 취소 요청이 실패했을때 발생하는 exception 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
public class CancelPaymentFailureException extends RuntimeException {
    public static final String MESSAGE = "결제 취소가 실패하였습니다.";

    public CancelPaymentFailureException() {
        super(MESSAGE);
    }
}

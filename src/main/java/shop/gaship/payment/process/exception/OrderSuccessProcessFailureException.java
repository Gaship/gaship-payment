package shop.gaship.payment.process.exception;

/**
 * 결제가 완료된 후 주문 완료처리에 실패했을때 발생하는 exception 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
public class OrderSuccessProcessFailureException extends RuntimeException{
    public static final String MESSAGE = "주문 완료 처리에 실패하였습니다.";

    public OrderSuccessProcessFailureException() {
        super(MESSAGE);
    }
}

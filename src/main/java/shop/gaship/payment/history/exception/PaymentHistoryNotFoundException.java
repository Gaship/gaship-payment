package shop.gaship.payment.history.exception;

/**
 * 결제이력 정보를 찾지 못했을때 발생하는 exception 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
public class PaymentHistoryNotFoundException extends RuntimeException {
    public static final String MESSAGE = "결제이력이 존재하지 않습니다.";

    public PaymentHistoryNotFoundException() {
        super(MESSAGE);
    }
}

package shop.gaship.payment.exception;

/**
 * 결제 제공사를 찾을 수 없을때 발생하는 exception 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
public class PaymentProviderNotFoundException extends RuntimeException {
    public static final String MESSAGE = "결제 제공사를 찾을 수 없습니다.";

    public PaymentProviderNotFoundException() {
        super(MESSAGE);
    }
}

package shop.gaship.payment.process.exception;

/**
 * 결제 관련 데이터를 parsing 할때 발생 할 수 있는 exception 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
public class PaymentParserException extends RuntimeException {
    public static final String MESSAGE = "결제 데이터 parsing 을 완료하지 못하였습니다.";

    public PaymentParserException() {
        super(MESSAGE);
    }
}

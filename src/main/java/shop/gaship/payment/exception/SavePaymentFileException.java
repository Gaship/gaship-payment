package shop.gaship.payment.exception;

/**
 * 결제 정보 파일 업로드에 실패했을 경우 발생하는 exception 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
public class SavePaymentFileException extends RuntimeException {
    public static final String MESSAGE = "결제정보 파일업로드에 실패하였습니다.";

    public SavePaymentFileException() {
        super(MESSAGE);
    }
}

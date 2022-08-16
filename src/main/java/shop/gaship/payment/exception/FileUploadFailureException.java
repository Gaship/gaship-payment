package shop.gaship.payment.exception;

/**
 * file upload 실패시 발생하는 exception 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
public class FileUploadFailureException extends RuntimeException {
    public static final String MESSAGE = "파일 업로드에 실패하였습니다.";

    public FileUploadFailureException() {
        super(MESSAGE);
    }
}

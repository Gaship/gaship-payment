package shop.gaship.payment.dataprotection.exception;

/**
 * Secure Key Manager 에서 응답을 제대로 받지 못하거나 요청이 잘못 되었을 경우 발생하는 exception.
 *
 * @author : 김세미
 * @since 1.0
 */
public class NotFoundDataProtectionResponseData extends RuntimeException {
    public NotFoundDataProtectionResponseData(String s) {
        super(s);
    }
}

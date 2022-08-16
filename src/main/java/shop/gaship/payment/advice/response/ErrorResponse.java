package shop.gaship.payment.advice.response;

import lombok.Getter;

/**
 * exception 발생시 responseEntity body 에 해당 exception message 를 담아서 반환하는 객체.
 *
 * @author : 김세미
 * @since 1.0
 */
@Getter
public class ErrorResponse {
    private final String message;

    public ErrorResponse(String message) {
        this.message = message;
    }
}

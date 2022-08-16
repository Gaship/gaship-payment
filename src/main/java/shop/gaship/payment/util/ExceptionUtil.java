package shop.gaship.payment.util;

import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;
import shop.gaship.payment.advice.response.ErrorResponse;
import shop.gaship.payment.exception.RequestFailureException;

/**
 * WebClient error 전역 처리를 위한 util class.
 *
 * @author : 김세미
 * @since 1.0
 */
public class ExceptionUtil {
    private ExceptionUtil() {}

    public static Mono<Throwable> createErrorMono(ClientResponse response) {
        return response.bodyToMono(ErrorResponse.class).flatMap(
                body -> Mono.error(new RequestFailureException(body.getMessage())));
    }
}

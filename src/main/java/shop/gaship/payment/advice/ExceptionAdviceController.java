package shop.gaship.payment.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shop.gaship.payment.advice.response.ErrorResponse;

/**
 * exception 처리 advice controller.
 *
 * @author : 김세미
 * @since 1.0
 */
@Slf4j
@RestControllerAdvice
public class ExceptionAdviceController {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> validException(
            MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .reduce("", (accumulateMsg, nextMessage)  -> accumulateMsg + "\n" + nextMessage)
                .trim();

        log.error("error : {}, message : {}", ex.getAllErrors(), ex.getMessage());
        return ResponseEntity.badRequest().body(new ErrorResponse(message));
    }
}

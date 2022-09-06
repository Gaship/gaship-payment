package shop.gaship.payment.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shop.gaship.payment.advice.response.ErrorResponse;
import shop.gaship.payment.process.exception.OrderSuccessProcessFailureException;
import shop.gaship.payment.process.exception.PaymentFailureException;

import java.net.ConnectException;

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

    @ExceptionHandler({ConnectException.class, OrderSuccessProcessFailureException.class})
    public ResponseEntity<ErrorResponse> connectionExceptionHandler(Exception e) {
        String message = e.getMessage();

        log.error("error : {}, message : {}", e.getCause(), e.getMessage());
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(message));
    }

    @ExceptionHandler({PaymentFailureException.class})
    public ResponseEntity<ErrorResponse> paymentFailureExceptionHandler(PaymentFailureException e) {
        String message = e.getMessage();
        log.error("error : {}, message : {}", e.getCause(), e.getMessage());

        return ResponseEntity
                .internalServerError()
                .body(new ErrorResponse(message));
    }
}

package shop.gaship.payment.process.controller;

import javax.validation.Valid;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.gaship.payment.advice.response.ErrorResponse;
import shop.gaship.payment.process.dto.request.PaymentSuccessRequestDto;
import shop.gaship.payment.process.service.PaymentService;


/**
 * 결제 요청 처리를 위한 rest controller.
 *
 * @author : 김세미
 * @since 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentRestController {
    private final PaymentService paymentService;

    /**
     * 결제가 성공한 경우 결제 승인 처리 Get Mapping 메서드입니다.
     *
     * @return 요청 처리에 대한 상태(HttpStatus.OK)를 담은 ResponseEntity 를 반환합니다.
     */
    @PostMapping("/success")
    public ResponseEntity<Void> paymentSuccess(@Valid @RequestBody PaymentSuccessRequestDto requestDto) {
        paymentService.successPayment(requestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    /**
     * 결제가 실패한 경우 결제 실패 처리 Get Mapping 메서드입니다.
     *
     * @param code    결제가 실패한 원인에 대한 error code 입니다. (String)
     * @param message 결제 실패에 대한 error message 입니다. (String)
     * @param orderId 실패한 결제의 대상이되는 주문을 식별하기 위한 해당 주문 id 입니다. (String)
     * @return 결제 실패 error message 를 담은 ErrorResponse 를 body 로 갖는 ResponseEntity 를 반환하며 상태값은 BAD_REQUEST 입니다.
     */
    @GetMapping("/fail")
    public ResponseEntity<ErrorResponse> paymentFail(
            @ApiParam(required = true) @RequestParam String code,
            @ApiParam(required = true) @RequestParam String message,
            @ApiParam(required = true) @RequestParam String orderId
    ) {
        paymentService.failPayment(code, message, orderId);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(message));
    }
}

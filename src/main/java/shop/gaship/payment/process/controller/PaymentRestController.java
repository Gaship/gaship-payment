package shop.gaship.payment.process.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.gaship.payment.advice.response.ErrorResponse;
import shop.gaship.payment.history.enumm.PaymentProvider;
import shop.gaship.payment.process.dto.request.FailurePaymentRequestDto;
import shop.gaship.payment.process.dto.request.PaymentCancelRequestDto;
import shop.gaship.payment.process.dto.request.PaymentSuccessRequestDto;
import shop.gaship.payment.process.exception.PaymentFailureException;
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
     * @param requestDto 결제 승인 요청 dto 입니다.
     * @return 요청 처리에 대한 상태(HttpStatus.OK)를 담은 ResponseEntity 를 반환합니다.
     */
    @PostMapping("/success")
    public ResponseEntity<Void> paymentSuccess(
            @Valid @RequestBody PaymentSuccessRequestDto requestDto) {

        paymentService.successPayment(requestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    /**
     * 결제가 실패한 경우 결제 실패 처리 Get Mapping 메서드입니다.
     *
     * @param requestDto 실패한 결제 처리 요청 dto 입니다.
     * @return 결제 실패 error message 를 담은 ErrorResponse 를 body 로 갖는
     * ResponseEntity 를 반환하며 상태값은 BAD_REQUEST 입니다.
     */
    @GetMapping("/fail")
    public ResponseEntity<ErrorResponse> paymentFail(FailurePaymentRequestDto requestDto) {
        paymentService.failPayment(requestDto.getPaymentKey(),
                PaymentProvider.valueOf(requestDto.getProvider()),
                requestDto.getOrderResponseDto());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(PaymentFailureException.MESSAGE));
    }

    /**
     * 결제취소 요청을 처리합니다.
     *
     * @param requestDto 결제 취소 요청 dto 입니다.
     * @return body 는 없으며 상태가 OK 인 ResponseEntity 를 반환합니다.
     */
    @PostMapping("/cancel")
    public ResponseEntity<Void> paymentCancel(
            @Valid @RequestBody PaymentCancelRequestDto requestDto) {
        paymentService.cancelPayment(requestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }
}

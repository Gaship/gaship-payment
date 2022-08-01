package shop.gaship.payment.controller;

import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.gaship.payment.dto.request.PaymentRequestDto;
import shop.gaship.payment.service.PaymentService;

/**
 * 결제 요청 처리를 위한 rest controller.
 *
 * @author : 김세미
 * @since 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
@Slf4j
public class PaymentRestController {
    private final PaymentService paymentService;

    @GetMapping("/success")
    public ResponseEntity<Void> paymentSuccess(
            @ApiParam(required = true) @RequestParam String paymentKey,
            @ApiParam(required = true) @RequestParam String orderId,
            @ApiParam(required = true) @RequestParam Long amount
    ){
        PaymentRequestDto requestDto = new PaymentRequestDto();
        requestDto.setPaymentKey(paymentKey);
        requestDto.setOrderId(orderId);
        requestDto.setAmount(amount);

        paymentService.addPayment(requestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/fail")
    public ResponseEntity<Void> paymentFail(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .build();
    }
}

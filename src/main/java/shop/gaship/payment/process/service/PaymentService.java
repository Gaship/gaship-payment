package shop.gaship.payment.process.service;

import shop.gaship.payment.history.enumm.PaymentProvider;
import shop.gaship.payment.process.dto.request.OrderPaymentCancelRequestDto;
import shop.gaship.payment.process.dto.request.PaymentSuccessRequestDto;
import shop.gaship.payment.process.dto.response.OrderResponseDto;

/**
 * 결제 요청 관련 처리 로직을 위한 service interface.
 *
 * @author : 김세미
 * @since 1.0
 */
public interface PaymentService {
    /**
     * 결제 성공 요청을 처리하는 메서드입니다.
     *
     * @param requestDto 결제 성공 요청 dto 입니다.
     */
    void successPayment(PaymentSuccessRequestDto requestDto);

    void failPayment(String paymentKey,
                     PaymentProvider paymentProvider,
                     OrderResponseDto orderResponseDto);

    void cancelPayment(Integer orderNo,
                       OrderPaymentCancelRequestDto requestDto);
}

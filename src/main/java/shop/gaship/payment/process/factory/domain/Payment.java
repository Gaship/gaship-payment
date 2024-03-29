package shop.gaship.payment.process.factory.domain;

import shop.gaship.payment.cancelhistory.dto.request.CancelPaymentResponseDto;
import shop.gaship.payment.history.enumm.PaymentProvider;
import shop.gaship.payment.process.adapter.PaymentAdapter;
import shop.gaship.payment.process.dto.request.OrderPaymentCancelRequestDto;

/**
 * 사용되는 결제 api 별로 동일한 로직을 처리하기 위한 interface 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
public interface Payment {
    PaymentAdapter getAdapter();

    PaymentParser getParser();

    CancelPaymentResponseDto cancel(String paymentKey,
                                    OrderPaymentCancelRequestDto requestDto,
                                    Long totalCancelAmount);

    PaymentProvider getProvider();
}

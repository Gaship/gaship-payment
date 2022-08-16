package shop.gaship.payment.process.factory.domain.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import shop.gaship.payment.cancelhistory.dto.request.CancelPaymentResponseDto;
import shop.gaship.payment.history.enumm.PaymentProvider;
import shop.gaship.payment.process.adapter.PaymentAdapter;
import shop.gaship.payment.process.adapter.impl.TossAdapter;
import shop.gaship.payment.process.dto.request.CancelPaymentRequestDto;
import shop.gaship.payment.process.dto.request.PaymentCancelRequestDto;
import shop.gaship.payment.process.factory.domain.Payment;
import shop.gaship.payment.process.factory.domain.PaymentParser;

/**
 * Toss payments api 를 사용하기 위한 adapter 와 parser 를 제공하는 class 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Component
@RequiredArgsConstructor
public class TossPayment implements Payment {
    private final TossAdapter tossAdapter;
    private final TossParser tossParser;

    @Override
    public PaymentAdapter getAdapter() {
        return this.tossAdapter;
    }

    @Override
    public PaymentParser getParser() {
        return this.tossParser;
    }

    @Override
    public CancelPaymentResponseDto cancel(PaymentCancelRequestDto requestDto,
                                           Long totalCancelAmount) {
        return tossParser.parseCancelData(
                tossAdapter.requestCancelPayment(
                            requestDto.getPaymentKey(),
                            CancelPaymentRequestDto.builder()
                                .cancelReason(requestDto.getCancelReason())
                                .cancelAmount(totalCancelAmount)
                                .build()
                        )
                );
    }

    @Override
    public PaymentProvider getProvider() {
        return PaymentProvider.TOSS;
    }
}

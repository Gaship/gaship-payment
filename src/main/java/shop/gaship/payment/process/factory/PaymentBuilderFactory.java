package shop.gaship.payment.process.factory;

import org.springframework.stereotype.Component;
import shop.gaship.payment.process.factory.domain.Payment;
import shop.gaship.payment.process.factory.domain.impl.TossPayment;

/**
 * 결제 제공 api 별로 처리하기 위한 factory class 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Component
public class PaymentBuilderFactory {
    private final Payment tossPayment;

    public PaymentBuilderFactory(TossPayment tossPayment) {
        this.tossPayment = tossPayment;
    }

    /**
     * provider 의 값에 따라 Payment 를 반환하는 메서드입니다.
     *
     * @param provider TOSS, KAKAO 등의 결제 api provider 정보입니다.
     * @return provider 에 따라 알맞은 adapter 와 parser 를 제공하는 Payment 를 반환합니다.
     */
    public Payment build(String provider) {
        if ("TOSS".equalsIgnoreCase(provider)) {
            return this.tossPayment;
        }
        return null;
    }
}

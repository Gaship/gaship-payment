package shop.gaship.payment.process.factory;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import shop.gaship.payment.exception.PaymentProviderNotFoundException;
import shop.gaship.payment.history.enumm.PaymentProvider;
import shop.gaship.payment.process.factory.domain.Payment;


/**
 * 결제 제공 api 별로 처리하기 위한 factory class 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Component
@RequiredArgsConstructor
public class PaymentBuilderFactory {
    private final List<Payment> payments;

    /**
     * provider 의 값에 따라 Payment 를 반환하는 메서드입니다.
     *
     * @param provider TOSS, KAKAO 등의 결제 api provider 정보입니다.
     * @return provider 에 따라 알맞은 adapter 와 parser 를 제공하는 Payment 를 반환합니다.
     */
    public Payment build(PaymentProvider provider) {
        return payments.stream()
                .filter(payment -> payment.getProvider().equals(provider))
                .findFirst()
                .orElseThrow(PaymentProviderNotFoundException::new);
    }
}

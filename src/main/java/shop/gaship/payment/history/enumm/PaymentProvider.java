package shop.gaship.payment.history.enumm;

import lombok.Getter;

/**
 * 결제 api 제공사 데이터 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Getter
public enum PaymentProvider {
    TOSS("toss payment");

    private final String experience;

    PaymentProvider(String experience) {
        this.experience = experience;
    }
}
package shop.gaship.payment.status.enumm;

import lombok.Getter;

/**
 * 결제에 사용되는 결제 상태값입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Getter
public enum PaymentStatus {
    FAIL("결제실패"),
    SUCCESS("결제성공"),
    CANCELED("전체취소"),
    PARTIAL_CANCELED("부분취소");

    private final String value;

    PaymentStatus(String value) {
        this.value = value;
    }
}
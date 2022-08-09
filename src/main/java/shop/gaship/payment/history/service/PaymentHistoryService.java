package shop.gaship.payment.history.service;

import shop.gaship.payment.history.dto.request.impl.CardPaymentHistoryRequest;
import shop.gaship.payment.history.dto.request.impl.EasyPaymentHistoryRequest;
import shop.gaship.payment.history.entity.CardPayment;
import shop.gaship.payment.history.entity.EasyPayment;
import shop.gaship.payment.status.entity.PaymentStatusCode;

/**
 * 결제이력 관련 service interface 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
public interface PaymentHistoryService {
    /**
     * 카드결제이력을 등록하기 위한 메서드입니다.
     *
     * @param cardPaymentHistoryRequest 카드결제이력 등록 요청 데이터입니다. (CardPaymentHistoryRequest)
     * @author 김세미
     */
    void addPaymentHistory(CardPaymentHistoryRequest cardPaymentHistoryRequest);

    /**
     * 간편결제이력을 등록하기 위한 메서드입니다.
     *
     * @param easyPaymentHistoryRequest 간편결제이력 등록 요청 데이터입니다. (EasyPaymentHistoryRequest)s
     * @author 김세미
     */
    void addPaymentHistory(EasyPaymentHistoryRequest easyPaymentHistoryRequest);

    /**
     * 데이터를 EasyPayment entity 로 변환하여 반환하는 메서드입니다.
     *
     * @param requestDto 간편결제이력 등록 요청 데이터입니다. (EasyPaymentHistoryRequest)
     * @param paymentStatus 결제 성공에 대한 상태입니다. (PaymentStatusCode)
     * @return EasyPayment entity 객체를 반환합니다.
     * @author 김세미
     */
    default EasyPayment dtoToEntity(EasyPaymentHistoryRequest requestDto,
                                    PaymentStatusCode paymentStatus) {
        return EasyPayment.builder()
                .requestDto(requestDto)
                .paymentStatus(paymentStatus)
                .build();
    }

    /**
     * 데이터를 CardPayment entity 로 변환하여 반환하는 메서드입니다.
     *
     * @param requestDto 카드걸제이력 등록 요청 데이터입니다. (CardPaymentHistoryRequest)
     * @param paymentStatus 결제 성공에 대한 상태입니다. (PaymentStatusCode)
     * @return CardPayment entity 객체를 반환합니다.
     * @author 김세미
     */
    default CardPayment dtoToEntity(CardPaymentHistoryRequest requestDto,
                                    PaymentStatusCode paymentStatus) {
        return CardPayment.builder()
                .requestDto(requestDto)
                .paymentStatus(paymentStatus)
                .build();
    }
}

package shop.gaship.payment.process.factory.domain;

import com.fasterxml.jackson.databind.JsonNode;
import shop.gaship.payment.cancelhistory.dto.request.CancelPaymentResponseDto;
import shop.gaship.payment.history.dto.request.PaymentHistoryRequest;

/**
 * 결제 요청에 대한 결과를 parsing 하기 위한 parser interface 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
public interface PaymentParser {
    /**
     * 결제 승인 응답 데이터를 결제이력 데이터로 parsing 합니다.
     *
     * @param responseDto the response dto
     * @return the payment history request
     */
    PaymentHistoryRequest parse(Integer orderNo, JsonNode responseDto);
    CancelPaymentResponseDto parseCancelData(JsonNode responseDto);
}

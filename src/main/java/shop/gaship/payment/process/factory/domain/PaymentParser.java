package shop.gaship.payment.process.factory.domain;

import com.fasterxml.jackson.databind.JsonNode;
import shop.gaship.payment.history.dto.request.PaymentHistoryRequest;

/**
 * 결제 요청에 대한 결과를 parsing 하기 위한 parser interface 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
public interface PaymentParser {
    PaymentHistoryRequest parse(JsonNode responseDto);
}

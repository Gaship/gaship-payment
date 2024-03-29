package shop.gaship.payment.process.factory.domain.impl;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import shop.gaship.payment.cancelhistory.dto.request.CancelPaymentResponseDto;
import shop.gaship.payment.history.dto.request.PaymentHistoryRequest;
import shop.gaship.payment.history.dto.request.PaymentHistoryRequestDto;
import shop.gaship.payment.history.dto.request.impl.CardPaymentHistoryRequest;
import shop.gaship.payment.history.dto.request.impl.EasyPaymentHistoryRequest;
import shop.gaship.payment.history.enumm.PaymentProvider;
import shop.gaship.payment.process.dto.response.TossResponseDto;
import shop.gaship.payment.process.exception.PaymentParserException;
import shop.gaship.payment.process.factory.domain.PaymentParser;


/**
 * toss payment api 로 부터 받은 응답을 entity 객체로 parsing 하기 위한 parser 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Component
@NoArgsConstructor
public class TossParser implements PaymentParser {
    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Override
    public PaymentHistoryRequest parse(Integer orderNo, JsonNode responseDto) {
        TossResponseDto tossResponseDto;

        try {
            tossResponseDto = objectMapper.readValue(responseDto.toString(),
                    TossResponseDto.class);
        } catch (JsonProcessingException e) {
            throw new PaymentParserException();
        }

        PaymentHistoryRequestDto paymentHistoryRequestDto =
                PaymentHistoryRequestDto.builder()
                        .paymentKey(tossResponseDto.getPaymentKey())
                        .provider(PaymentProvider.TOSS)
                        .orderNo(orderNo)
                        .orderName(tossResponseDto.getOrderName())
                        .paymentMethod(tossResponseDto.getMethod())
                        .totalAmount(tossResponseDto.getTotalAmount())
                        .requestedAt(timeParse(tossResponseDto.getRequestedAt()))
                        .approvedAt(timeParse(tossResponseDto.getApprovedAt()))
                        .balanceAmount(tossResponseDto.getBalanceAmount())
                        .currency(tossResponseDto.getCurrency())
                        .country(tossResponseDto.getCountry())
                        .receiptUrl(tossResponseDto.getReceipt().getUrl())
                        .build();

        if (tossResponseDto.getMethod().equals("카드")) {
            return CardPaymentHistoryRequest.builder()
                    .paymentHistory(paymentHistoryRequestDto)
                    .cardCompany(tossResponseDto.getCard().getCompany())
                    .cardApproveNo(tossResponseDto.getCard().getApproveNo())
                    .cardPayAmount(tossResponseDto.getCard().getAmount())
                    .build();
        }

        if (tossResponseDto.getMethod().equals("간편결제")) {
            return EasyPaymentHistoryRequest.builder()
                    .paymentHistory(paymentHistoryRequestDto)
                    .easyPayProvider(tossResponseDto.getEasyPay().getProvider())
                    .easyPayAmount(tossResponseDto.getEasyPay().getAmount())
                    .build();
        }

        return null;
    }

    @Override
    public CancelPaymentResponseDto parseCancelData(JsonNode responseData) {
        TossResponseDto tossResponseDto;

        try {
            tossResponseDto = objectMapper.readValue(responseData.toString(),
                    TossResponseDto.class);
        } catch (JsonProcessingException e) {
            throw new PaymentParserException();
        }

        Optional<TossResponseDto.Cancel> cancelInfo = Arrays.stream(tossResponseDto.getCancels())
                .filter(cancel -> cancel.getTransactionKey()
                        .equals(tossResponseDto.getLastTransactionKey()))
                .findFirst();

        return cancelInfo.map(cancel -> CancelPaymentResponseDto
                    .builder()
                        .balanceAmount(cancel.getRefundableAmount())
                        .canceledAt(timeParse(cancel.getCanceledAt()))
                    .build())
                .orElse(null);
    }

    public LocalDateTime timeParse(String at) {
        return LocalDateTime.from(ZonedDateTime.parse(at));
    }
}
package shop.gaship.payment.process.adapter.impl;

import java.util.Base64;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import shop.gaship.payment.config.TossConfig;
import shop.gaship.payment.process.adapter.PaymentAdapter;
import shop.gaship.payment.process.dto.request.PaymentSuccessRequestDto;
import shop.gaship.payment.util.ExceptionUtil;



/**
 * Toss payment 결제 관련 요청을 위한 TossAdapter interface 구현체.
 *
 * @author : 김세미
 * @since 1.0
 */
@Component
@RequiredArgsConstructor
public class TossAdapter implements PaymentAdapter {
    public static final String TOSS_URL = "/v1/payments";
    private final String secretKey;

    @Override
    public JsonNode requestSuccessPayment(PaymentSuccessRequestDto requestDto) {
        return WebClient
                .create(TossConfig.BASE_URL)
                .post()
                .uri(TOSS_URL + "/" + requestDto.getPaymentKey())
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization",
                        "Basic " + Base64.getEncoder().encodeToString((secretKey + ":").getBytes()))
                .bodyValue(requestDto)
                .retrieve()
                .onStatus(HttpStatus::isError, ExceptionUtil::createErrorMono)
                .bodyToMono(JsonNode.class)
                .block();
    }
}

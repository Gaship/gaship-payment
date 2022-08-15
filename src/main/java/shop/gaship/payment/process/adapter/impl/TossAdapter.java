package shop.gaship.payment.process.adapter.impl;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import shop.gaship.payment.config.TossConfig;
import shop.gaship.payment.process.adapter.PaymentAdapter;
import shop.gaship.payment.process.dto.request.CancelPaymentRequestDto;
import shop.gaship.payment.process.dto.request.PaymentSuccessRequestDto;
import shop.gaship.payment.process.exception.CancelPaymentFailureException;
import shop.gaship.payment.process.exception.PaymentFailureException;



/**
 * Toss payment 결제 관련 요청을 위한 TossAdapter interface 구현체.
 *
 * @author : 김세미
 * @since 1.0
 */
@Component
@RequiredArgsConstructor
@Slf4j
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
                .onStatus(HttpStatus::isError,
                        clientResponse -> Mono.error(new PaymentFailureException()))
                .bodyToMono(JsonNode.class)
                .block();
    }

    @Override
    public JsonNode requestCancelPayment(String paymentKey, CancelPaymentRequestDto requestDto) {
        return WebClient
                .create(TossConfig.BASE_URL)
                .post()
                .uri(TOSS_URL + "/" + paymentKey + "/cancel")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization",
                        "Basic " + Base64.getEncoder().encodeToString((secretKey + ":").getBytes()))
                .bodyValue(requestDto)
                .retrieve()
                .onStatus(HttpStatus::isError,
                        clientResponse -> Mono.error(new CancelPaymentFailureException()))
                .bodyToMono(JsonNode.class)
                .block();
    }
}

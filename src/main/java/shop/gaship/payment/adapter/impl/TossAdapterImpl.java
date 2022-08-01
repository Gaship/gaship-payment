package shop.gaship.payment.adapter.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import shop.gaship.payment.adapter.TossAdapter;
import shop.gaship.payment.config.TossConfig;
import shop.gaship.payment.domain.Payment;
import shop.gaship.payment.dto.request.PaymentRequestDto;
import shop.gaship.payment.util.ExceptionUtil;
import java.util.Base64;

/**
 * Toss payment 결제 관련 요청을 위한 TossAdapter interface 구현체.
 *
 * @author : 김세미
 * @since 1.0
 */
@Component
@RequiredArgsConstructor
public class TossAdapterImpl implements TossAdapter {
    private static final String TOSS_URL = "/v1/payments";
    private final String secretKey;

    @Override
    public Payment requestSuccessPayment(PaymentRequestDto requestDto) {
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
                .bodyToMono(Payment.class)
                .block();
    }
}

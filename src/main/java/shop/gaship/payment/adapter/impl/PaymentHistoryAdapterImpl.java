package shop.gaship.payment.adapter.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import shop.gaship.payment.adapter.PaymentHistoryAdapter;
import shop.gaship.payment.config.ServerConfig;
import shop.gaship.payment.dto.request.FailedPaymentHistoryRequestDto;
import shop.gaship.payment.util.ExceptionUtil;

/**
 * 결제이력 관련 요청을 처리하는 paymentHistory interface 구현체.
 *
 * @author : 김세미
 * @since 1.0
 * @see shop.gaship.payment.adapter.PaymentHistoryAdapter
 */
@Component
@RequiredArgsConstructor
public class PaymentHistoryAdapterImpl implements PaymentHistoryAdapter {

    public static final String PAYMENT_HISTORY_URL = "/api/payments";
    private final ServerConfig serverConfig;

    @Override
    public void addFailedPaymentHistory(FailedPaymentHistoryRequestDto requestDto) {
        WebClient.create(serverConfig.getShoppingMallUrl())
                .post()
                .uri(PAYMENT_HISTORY_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto)
                .retrieve()
                .onStatus(HttpStatus::isError, ExceptionUtil::createErrorMono)
                .bodyToMono(ResponseEntity.class).block();
    }
}

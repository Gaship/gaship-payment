package shop.gaship.payment.process.adapter.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import shop.gaship.payment.config.ServerConfig;
import shop.gaship.payment.process.adapter.OrderAdapter;
import shop.gaship.payment.process.dto.response.OrderResponseDto;
import shop.gaship.payment.process.exception.ShopServerException;

/**
 * 주문관련 데이터 요청을 위한 adapter 구현체입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Component
@RequiredArgsConstructor
public class OrderAdapterImpl implements OrderAdapter {
    public static final String ORDER_URL = "/api/payments/order";
    private final ServerConfig serverConfig;

    @Override
    public OrderResponseDto getOrderById(String orderId) {
        return WebClient.create(serverConfig.getShoppingMallUrl())
                .get()
                .uri(ORDER_URL + "/" + orderId)
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError,
                        clientResponse -> Mono.error(new ShopServerException()))
                .bodyToMono(OrderResponseDto.class)
                .block();
    }
}

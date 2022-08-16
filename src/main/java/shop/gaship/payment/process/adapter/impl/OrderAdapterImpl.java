package shop.gaship.payment.process.adapter.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import shop.gaship.payment.config.ServerConfig;
import shop.gaship.payment.process.adapter.OrderAdapter;
import shop.gaship.payment.process.adapter.dto.request.CancelOrderRequestDto;
import shop.gaship.payment.process.adapter.dto.request.FailCancelOrderRequestDto;
import shop.gaship.payment.process.adapter.dto.request.SuccessOrderRequestDto;
import shop.gaship.payment.process.dto.response.OrderResponseDto;
import shop.gaship.payment.process.exception.ShopServerException;
import shop.gaship.payment.util.ExceptionUtil;

/**
 * 주문관련 데이터 요청을 위한 adapter 구현체입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Component
@RequiredArgsConstructor
public class OrderAdapterImpl implements OrderAdapter {
    public static final String ORDER_URL = "/api/orders";
    private final ServerConfig serverConfig;

    /**
     * {@inheritDoc}
     *
     * @throws ShopServerException Shopping mall 서버에서 문제가 발생하였습니다.
     */
    @Override
    public OrderResponseDto getOrderByNo(Integer orderNo) {
        return WebClient.create(serverConfig.getShoppingMallUrl())
                .get()
                .uri(ORDER_URL + "/" + orderNo)
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError,
                        clientResponse -> Mono.error(new ShopServerException()))
                .bodyToMono(OrderResponseDto.class)
                .block();
    }

    /**
     * {@inheritDoc}
     *
     * @throws ShopServerException Shopping mall 서버에서 문제가 발생하였습니다.
     */
    @Override
    public void cancelOrder(CancelOrderRequestDto requestDto) {
        WebClient.create(serverConfig.getShoppingMallUrl())
                .put()
                .uri(ORDER_URL + "/cancel")
                .bodyValue(requestDto)
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError,
                        clientResponse -> Mono.error(new ShopServerException()))
                .toEntity(Void.class)
                .block();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void failCancelOrder(FailCancelOrderRequestDto requestDto) {
        WebClient.create(serverConfig.getShoppingMallUrl())
                .put()
                .uri(ORDER_URL + "/restore")
                .bodyValue(requestDto)
                .retrieve()
                .onStatus(HttpStatus::isError, ExceptionUtil::createErrorMono)
                .toEntity(Void.class)
                .block();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void successOrder(SuccessOrderRequestDto requestDto) {
        WebClient.create(serverConfig.getShoppingMallUrl())
                .put()
                .uri(ORDER_URL + "/success")
                .bodyValue(requestDto)
                .retrieve()
                .onStatus(HttpStatus::isError, ExceptionUtil::createErrorMono)
                .toEntity(Void.class)
                .block();
    }
}

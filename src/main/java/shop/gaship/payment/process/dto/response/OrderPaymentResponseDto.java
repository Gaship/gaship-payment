package shop.gaship.payment.process.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 주문 데이터 요청에 대한 응답 data transfer object 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderPaymentResponseDto {
    private Integer no;
    private Long totalOrderAmount;
    private List<Integer> orderProductNos;
}

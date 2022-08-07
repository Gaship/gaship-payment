package shop.gaship.payment.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import shop.gaship.payment.service.PaymentService;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * PaymentRestController 테스트 코드.
 *
 * @author : 김세미
 * @since 1.0
 */
@WebMvcTest(PaymentRestController.class)
class PaymentRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    private String dummyPaymentKey;
    private String dummyOrderId;
    private Long dummyAmount;

    @BeforeEach
    void setUp() {
        dummyPaymentKey = "abcd1234qwerJehf";
        dummyOrderId = "1";
        dummyAmount = 15_000L;
    }

    @Test
    @DisplayName("결제 성공 Get Mapping 메서드 테스트")
    void paymentSuccessTest() throws Exception {
        mockMvc.perform(get("/payment/success")
                .queryParam("paymentKey", dummyPaymentKey)
                .queryParam("orderId", dummyOrderId)
                .queryParam("amount", String.valueOf(dummyAmount)))
                .andExpect(status().isOk());

        verify(paymentService).successPayment(any(), any(), any());
    }
}
package shop.gaship.payment.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shop.gaship.payment.adapter.PaymentHistoryAdapter;
import shop.gaship.payment.adapter.TossAdapter;
import shop.gaship.payment.dummy.PaymentDummy;
import shop.gaship.payment.exception.FileUploadFailureException;
import shop.gaship.payment.service.PaymentService;
import shop.gaship.payment.util.FileUploadUtil;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * PaymentService interface 구현체 테스트 코드.
 *
 * @author : 김세미
 * @since 1.0
 */
@ExtendWith(SpringExtension.class)
@Import(PaymentServiceImpl.class)
class PaymentServiceImplTest {

    @Autowired
    private PaymentService paymentService;

    @MockBean
    private TossAdapter tossAdapter;

    @MockBean
    private PaymentHistoryAdapter paymentHistoryAdapter;

    @MockBean
    private FileUploadUtil fileUploadUtil;

    private String dummyPaymentKey;
    private String dummyOrderId;
    private Long dummyAmount;

    @BeforeEach
    void setUp() {
        dummyPaymentKey = "1234qqwerASQ23";
        dummyOrderId = "12";
        dummyAmount = 15_000L;
    }

    @Test
    @DisplayName("결제 승인 처리 메서드 테스트")
    void successPaymentTest() throws IOException {
        when(tossAdapter.requestSuccessPayment(any()))
                .thenReturn(PaymentDummy.cardPaymentDummy());
        doNothing().when(fileUploadUtil).writePaymentFile(any(), any());
        doNothing().when(paymentHistoryAdapter).addSuccessfulPaymentHistory(any());

        assertThatNoException().isThrownBy(() -> paymentService
                .successPayment(
                dummyPaymentKey, dummyOrderId, dummyAmount
        ));

        verify(tossAdapter).requestSuccessPayment(any());
        verify(fileUploadUtil).writePaymentFile(any(), any());
        verify(paymentHistoryAdapter).addSuccessfulPaymentHistory(any());
    }

    @Test
    @DisplayName("결제 승인시 파일 업로드 실패하는 경우 테스트")
    void successPaymentTest_whenFileUploadFail() throws IOException {
        when(tossAdapter.requestSuccessPayment(any()))
                .thenReturn(PaymentDummy.cardPaymentDummy());
        doThrow(IOException.class).when(fileUploadUtil).writePaymentFile(any(), any());
        doNothing().when(paymentHistoryAdapter).addSuccessfulPaymentHistory(any());

        assertThatThrownBy(() -> paymentService.successPayment(
                dummyPaymentKey, dummyOrderId, dummyAmount
        )).isInstanceOf(FileUploadFailureException.class);

        verify(tossAdapter).requestSuccessPayment(any());
        verify(fileUploadUtil).writePaymentFile(any(), any());
        verify(paymentHistoryAdapter).addSuccessfulPaymentHistory(any());
    }
}
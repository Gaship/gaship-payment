package shop.gaship.payment.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shop.gaship.payment.domain.Payment;
import shop.gaship.payment.dummy.PaymentDummy;
import static org.assertj.core.api.Assertions.assertThatNoException;

/**
 * FileUploadUtil 테스트 코드.
 *
 * @author : 김세미
 * @since 1.0
 */
@ExtendWith(SpringExtension.class)
@Import(FileUploadUtil.class)
@TestPropertySource("classpath:application-dev.properties")
class FileUploadUtilTest {
    @Autowired
    private FileUploadUtil fileUploadUtil;

    private Payment paymentDummy;

    @BeforeEach
    void setUp() {
        paymentDummy = PaymentDummy.cardPaymentDummy();
    }

    @Test
    @DisplayName("파일 업로드 테스트")
    void writePaymentFileTest(){
        String uploadDir = "payment";

        assertThatNoException()
                .isThrownBy(() -> fileUploadUtil
                        .writePaymentFile(uploadDir, paymentDummy));
    }
}
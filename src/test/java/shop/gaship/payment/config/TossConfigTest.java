package shop.gaship.payment.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TossConfig 테스트 코드.
 *
 * @author : 김세미
 * @since 1.0
 */
@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(value = {TossConfig.class, DataProtectionConfig.class})
@TestPropertySource("classpath:application.properties")
class TossConfigTest {
    @Autowired
    private TossConfig tossConfig;

    @Test
    void tossConfigEnvironmentTest(){
        assertThat(tossConfig.getSecretKey())
                .isEqualTo("a27e2f63c1e04387af943a4d30ad8350");
    }
}
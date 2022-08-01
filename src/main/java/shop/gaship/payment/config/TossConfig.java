package shop.gaship.payment.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Toss payment 관련 설정 configuration.
 *
 * @author : 김세미
 * @since 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "toss")
public class TossConfig {
    private String secretKey;
    public static final String BASE_URL = "https://api.tosspayments.com";

    @Bean
    public String secretKey(DataProtectionConfig dataProtectionConfig){
        return dataProtectionConfig.findSecretDataFromSecureKeyManager(secretKey);
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}

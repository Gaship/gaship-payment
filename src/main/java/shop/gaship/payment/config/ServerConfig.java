package shop.gaship.payment.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 내부 서버 URL 을 관리하는 설정 configuration.
 *
 * @author : 김세미
 * @since 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "gaship-server")
public class ServerConfig {
    private String authUrl;
    private String shoppingMallUrl;
    private String schedulerUrl;

    public String getAuthUrl() {
        return authUrl;
    }

    public String getShoppingMallUrl() {
        return shoppingMallUrl;
    }

    public String getSchedulerUrl() {
        return schedulerUrl;
    }

    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }

    public void setShoppingMallUrl(String shoppingMallUrl) {
        this.shoppingMallUrl = shoppingMallUrl;
    }

    public void setSchedulerUrl(String schedulerUrl) {
        this.schedulerUrl = schedulerUrl;
    }
}

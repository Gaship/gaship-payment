package shop.gaship.payment.config;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Mysql 데이터 소스를 불러오는 설정.
 *
 * @author : 김세미
 * @since 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "datasource")
@RequiredArgsConstructor
public class DataSourceConfig {
    private final DataProtectionConfig dataProtectionConfig;
    private String driverClassName;
    private String url;
    private String shardurl;
    private String username;
    private String password;

//    @Bean
//    public DataSource getDataSource(DataProtectionConfig dataProtectionConfig) {
//        String secretUrl = dataProtectionConfig.findSecretDataFromSecureKeyManager(url);
//        String secretPassword = dataProtectionConfig.findSecretDataFromSecureKeyManager(password);
//
//        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.driverClassName(driverClassName);
//        dataSourceBuilder.url(secretUrl);
//        dataSourceBuilder.username(username);
//        dataSourceBuilder.password(secretPassword);
//
//        return dataSourceBuilder.build();
//    }

    @Bean(name = "shard-0")
    public DataSource masterDataSource() {

        return getDataSource(dataProtectionConfig.findSecretDataFromSecureKeyManager(url),
                dataProtectionConfig.findSecretDataFromSecureKeyManager(password));
    }

    @Bean(name = "shard-1")
    public DataSource slaveDataSource() {
        return getDataSource(shardurl,
                dataProtectionConfig.findSecretDataFromSecureKeyManager(password));
    }

    /**
     * MySql 설정을 위한 빈 입니다.
     *
     * @param secretUrl      secure key 와 관련된 설정을 위한 객체.
     * @param secretPassword
     * @return 연결 설정하는 ConnectionFactory 반환.
     */
    private DataSource getDataSource(String secretUrl, String secretPassword) {
        Properties properties = new Properties();
        properties.setProperty("url", secretUrl);
        properties.setProperty("user", username);
        properties.setProperty("password", secretPassword);

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDataSourceClassName("com.mysql.cj.jdbc.MysqlDataSource");
        hikariConfig.setMaximumPoolSize(2);
        hikariConfig.setDataSourceProperties(properties);

        return new HikariDataSource(hikariConfig);
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getShardurl() {
        return shardurl;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setShardurl(String shardurl) {
        this.shardurl = shardurl;
    }
}

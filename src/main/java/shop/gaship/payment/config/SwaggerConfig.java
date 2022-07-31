package shop.gaship.payment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger 를 통한 api 문서를 위한 설정 configuration.
 *
 * @author : 김세미
 * @since 1.0
 */
@Configuration
public class SwaggerConfig {
    /**
     * Docket 의 Document type 을 swagger_2 로 설정하고
     * 제목과 설명을 지정하고
     * base package 를 shop.gaship.payment 로 지정한 Docket bean 설정 메서드입니다.
     *
     * @return Docket 을 반환합니다.
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("shop.gaship.payment"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("gaship-payment api with Swagger")
                .description("가십 쇼핑몰의 payment API 문서입니다.")
                .version("1.0.0")
                .build();
    }
}

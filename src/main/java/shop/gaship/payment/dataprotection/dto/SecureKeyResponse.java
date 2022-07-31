package shop.gaship.payment.dataprotection.dto;

import lombok.Getter;

/**
 * NHN Secure Key Manager 의 Secure Key 응답 메시지 타입.
 *
 * @author : 김세미
 * @since 1.0
 */
@Getter
public class SecureKeyResponse {
    private Header header;
    private Body body;


    /**
     * Secure Key 응답 메시지의 header 정보를 담는 inner class 입니다.
     */
    @Getter
    public static class Header {
        private Integer resultCode;
        private String resultMessage;
        private Boolean isSuccessful;
    }

    /**
     * Secure Key 응답 메시지의 body 정보를 담는 inner class 입니다.
     */
    @Getter
    public static class Body {
        private String secret;
    }
}

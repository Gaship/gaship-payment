package shop.gaship.payment.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import shop.gaship.payment.exception.FileUploadFailureException;

/**
 * 파일 업로드를 위한 util.
 *
 * @author : 김세미
 * @since 1.0
 */
@Component
public class FileUploadUtil {
    @Value("${file.upload.url}")
    private String uploadBaseUrl;

    /**
     * 결제 승인 결과를 파일로 서버에 저장하기 위한 메서드.
     *
     * @param uploadDir      업로드 하려는 디렉토리.
     * @param paymentResponseData 파일로 업로드 할 데이터.
     * @throws FileUploadFailureException 파일 업로드에 실패하였을때 발생하는 exception 입니다.
     */
    public void writePaymentFile(String uploadDir, String approveNo, JsonNode paymentResponseData) {
        String date = File.separator + LocalDate.now();
        Path uploadPath = Paths.get(uploadBaseUrl + uploadDir + date);

        if (!Files.exists(uploadPath)) {
            createUploadPath(uploadPath);
        }

        String fileLink = uploadPath + File.separator + approveNo;

        transferFile(fileLink, paymentResponseData);
    }

    /**
     * 업로드 경로의 디렉토리를 생성하는 메서드입니다.
     *
     * @param uploadPath 업로드 경로입니다.
     */
    private void createUploadPath(Path uploadPath) {
        try {
            Files.createDirectories(uploadPath);
        } catch (IOException e) {
            throw new FileUploadFailureException();
        }
    }

    /**
     * payment 를 업로드하는 메서드입니다.
     *
     * @param fileLink      업로드할 파일의 링크입니다.
     * @param paymentResponseData   업로드할 결제 정보입니다.
     */
    private void transferFile(String fileLink, JsonNode paymentResponseData) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(fileLink),
                    objectMapper.writeValueAsString(paymentResponseData));
        } catch (IOException e) {
            throw new FileUploadFailureException();
        }
    }
}

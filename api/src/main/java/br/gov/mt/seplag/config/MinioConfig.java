package br.gov.mt.seplag.config;

import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class MinioConfig {

    private final AppProperties appProperties;

    @Bean
    public MinioClient minioClient() {
        log.info("Starting Min.IO Client");
        var miniProperties =  appProperties.getMinio();
        return MinioClient.builder()
                .endpoint(miniProperties.getEndpoint())
                .credentials(miniProperties.getAccesskey(), miniProperties.getSecretkey())
                .build();
    }
}

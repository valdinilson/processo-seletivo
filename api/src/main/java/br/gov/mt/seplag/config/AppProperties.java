package br.gov.mt.seplag.config;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "app")
@Validated
@Data
public class AppProperties {
    @NotEmpty(message = "A lista de origens permitidas (allowed-api) não pode estar vazia.")
    @URL(message = "Origem inválida (não é uma URL): ${validatedValue}")
    private String allowedApi;
    private MinioProperties minio;

    @Setter
    @Getter
    public static class MinioProperties {
        private String endpoint;
        private String accesskey;
        private String secretkey;
        private String bucketname;
    }
}
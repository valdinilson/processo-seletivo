package br.gov.mt.seplag.service;

import br.gov.mt.seplag.util.HashUtils;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class StorageService {

    private final MinioClient minioClient;

    public void criarBucket(String bucketNome) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.makeBucket(MakeBucketArgs
                .builder()
                .bucket(bucketNome)
                .build());
    }

    public boolean bucketExiste(String bucketNome) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioClient.bucketExists(BucketExistsArgs
                .builder()
                .bucket(bucketNome)
                .build());
    }

    public Optional<String> uploadArquivo(String bucketNome, MultipartFile arquivo) {
        try {
            String hashNomeImagem = gerarHashNomeImagem(arquivo.getOriginalFilename());
            log.info(hashNomeImagem);
            PutObjectArgs putArgs = PutObjectArgs.builder()
                    .bucket(bucketNome)
                    .object(hashNomeImagem)
                    .stream(arquivo.getInputStream(), arquivo.getSize(), -1)
                    .contentType(arquivo.getContentType())
                    .build();

            minioClient.putObject(putArgs);

            return Optional.of(hashNomeImagem);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<String> obterUrlTemporaria(String bucketName, String objectName) {
        try {
            String url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucketName)
                            .object(objectName)
                            .expiry(5, TimeUnit.MINUTES)
                            .build());

            return Optional.ofNullable(url);
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    private String gerarHashNomeImagem(String nomeArquivo) {
        StringBuilder sb = new StringBuilder();
        sb.append(nomeArquivo).append("|")
                .append(System.currentTimeMillis()).append("|")
                .append(UUID.randomUUID());

        String hashSHA256 = HashUtils.gerarHash(sb.toString(), "SHA-256");

        return hashSHA256.substring(0, Math.min(50, hashSHA256.length()));
    }
}

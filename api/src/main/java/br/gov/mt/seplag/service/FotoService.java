package br.gov.mt.seplag.service;

import br.gov.mt.seplag.config.AppProperties;
import br.gov.mt.seplag.domain.Foto;
import br.gov.mt.seplag.domain.Pessoa;
import br.gov.mt.seplag.repository.FotoPessoaRepository;
import br.gov.mt.seplag.response.FotoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;

@Component
@RequiredArgsConstructor
public class FotoService {

    private final FotoPessoaRepository fotoPessoaRepository;
    private final AppProperties appProperties;
    private final StorageService storageService;
    private final PessoaService pessoaService;

    public List<Foto> uploadFotos(Integer id, List<MultipartFile> imagens) {
        Pessoa pessoa = pessoaService.buscarPorId(id);
        validarImagens(imagens);

        String nomeBucket = prepararBucket();

        Set<Foto> fotos = new HashSet<>();
        imagens.forEach(imagem -> fotos.add(criarFoto(pessoa, nomeBucket, imagem)));

        return fotoPessoaRepository.saveAll(fotos);
    }

    public List<FotoResponseDTO> obterUrlTemporaria(Pessoa pessoa) {
        List<FotoResponseDTO> fotos = new ArrayList<>();
        pessoa.getFotos().forEach(foto -> {
            FotoResponseDTO fotoResponseDTO = new FotoResponseDTO();
            storageService.obterUrlTemporaria(foto.getBucket(), foto.getHash())
                    .ifPresent(fotoResponseDTO::setUrl);
            fotos.add(fotoResponseDTO);
        });

        return fotos;
    }

    private void validarImagens(List<MultipartFile> imagens) {
        imagens.forEach(imagem -> {
            String contentType = imagem.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                throw new IllegalArgumentException("Apenas arquivos de imagem são permitidos.");
            }
        });
    }

    private String prepararBucket() {
        String bucket = appProperties.getMinio().getBucketname();
        try {
            if (!storageService.bucketExiste(bucket)) {
                storageService.criarBucket(bucket);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao preparar bucket: " + ex.getMessage(), ex);
        }
        return bucket;
    }

    private Foto criarFoto(Pessoa pessoa, String bucket, MultipartFile imagem) {
        String hash = storageService.uploadArquivo(bucket, imagem)
                .orElseThrow(() -> new RuntimeException("Falha ao armazenar o arquivo."));

        return Foto.builder()
                .pessoa(pessoa)
                .dataFoto(LocalDateTime.now())
                .bucket(bucket)
                .hash(hash)
                .build();
    }

}

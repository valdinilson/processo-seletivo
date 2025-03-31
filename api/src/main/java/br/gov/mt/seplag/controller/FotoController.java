package br.gov.mt.seplag.controller;

import br.gov.mt.seplag.service.FotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Tag(name = "Servidores")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FotoController {

    private final FotoService fotoService;

    @Operation(summary = "Registrar o upload de um ou mais fotos para o Servidor")
    @ApiResponse(responseCode = "201", description = "Sucesso")
    @ApiResponse(responseCode = "404", description = "Não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @PostMapping(value = "/servidores/{id}/fotos", consumes = "multipart/form-data")
    public ResponseEntity<Object> uploadFotos(
            @PathVariable Integer id,
            @Parameter(description = "Lista de fotos a serem enviados",
                    content = @Content(mediaType = "multipart/form-data"), required = true)
            @RequestParam("imagem") List<MultipartFile> imagens) {

        List<Map<String, Integer>> ids = fotoService.uploadFotos(id, imagens).stream()
                .map(foto -> Map.of("id", foto.getId()))
                .collect(Collectors.toList());

        return ResponseEntity.
                status(HttpStatus.CREATED).body(ids);
    }
}

package br.gov.mt.seplag.controller;

import br.gov.mt.seplag.domain.Foto;
import br.gov.mt.seplag.service.FotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Servidores")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FotoController {

    private final FotoService fotoService;

    @Operation(summary = "Registrar o upload de um ou mais fotos para o Servidor")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @ApiResponse(responseCode = "404", description = "Não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @PostMapping("/servidores/{id}/fotos")
    public ResponseEntity<List<Foto>> uploadFotos(@PathVariable Integer id, @RequestParam List<MultipartFile> arquivos) {
        return ResponseEntity.ok(fotoService.uploadFotos(id, arquivos));
    }
}

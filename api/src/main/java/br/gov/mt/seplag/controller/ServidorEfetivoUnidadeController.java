package br.gov.mt.seplag.controller;

import br.gov.mt.seplag.helper.EfetivoUnidadeResponseHelper;
import br.gov.mt.seplag.response.EfetivoUnidadeResponse;
import br.gov.mt.seplag.service.ServidorEfetivoUnidadeService;
import br.gov.mt.seplag.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Unidades")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ServidorEfetivoUnidadeController {

    private final ServidorEfetivoUnidadeService servidorEfetivoUnidadeService;
    private final EfetivoUnidadeResponseHelper efetivoUnidadeResponseHelper;

    @Operation(summary = "Listar Servidores Efetivos lotados na Unidade")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @ApiResponse(responseCode = "204", description = "Sem Conteúdo")
    @ApiResponse(responseCode = "401", description = "Autenticação necessária")
    @ApiResponse(responseCode = "403", description = "Requisição não autorizada")
    @ApiResponse(responseCode = "404", description = "Não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @GetMapping("/unidades/{id}/efetivos")
    public ResponseEntity<Page<EfetivoUnidadeResponse>> buscarPorUnidadeId(@PathVariable Integer id, @ParameterObject Pageable pageable) {
        var pageServidores = servidorEfetivoUnidadeService.listarEfetivosPorUnidade(id, pageable);
        return ResponseUtil.fromPage(efetivoUnidadeResponseHelper.toResponse(id, pageServidores));
    }
}

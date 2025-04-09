package br.gov.mt.seplag.controller;

import br.gov.mt.seplag.mapper.EnderecoMapper;
import br.gov.mt.seplag.response.EnderecoResponse;
import br.gov.mt.seplag.service.EnderecoService;
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

@Tag(name = "Endereços")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService enderecoService;
    private final EnderecoMapper enderecoMapper;

    @Operation(summary = "Listar todos os endereços cadastrados")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @ApiResponse(responseCode = "204", description = "Sem Conteúdo")
    @ApiResponse(responseCode = "401", description = "Autenticação necessária")
    @ApiResponse(responseCode = "403", description = "Requisição não autorizada")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @GetMapping("/enderecos")
    public ResponseEntity<Page<EnderecoResponse>> listarTudo(@ParameterObject Pageable pageable) {
        return ResponseUtil.fromPage(enderecoService.listarTodos(pageable).map(enderecoMapper::toResponse));
    }

    @Operation(summary = "Buscar um endereço pelo ID")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @ApiResponse(responseCode = "401", description = "Autenticação necessária")
    @ApiResponse(responseCode = "403", description = "Requisição não autorizada")
    @ApiResponse(responseCode = "404", description = "Não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @GetMapping("/enderecos/{id}")
    public ResponseEntity<EnderecoResponse> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(enderecoMapper.toResponse(enderecoService.buscarPorId(id)));
    }

}

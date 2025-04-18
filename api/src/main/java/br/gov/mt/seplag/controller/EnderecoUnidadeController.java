package br.gov.mt.seplag.controller;

import br.gov.mt.seplag.mapper.EnderecoMapper;
import br.gov.mt.seplag.request.EnderecoRequest;
import br.gov.mt.seplag.response.EnderecoResponse;
import br.gov.mt.seplag.service.EnderecoUnidadeService;
import br.gov.mt.seplag.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Map;

@Tag(name = "Unidades")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EnderecoUnidadeController {

    private final EnderecoUnidadeService enderecoUnidadeService;
    private final EnderecoMapper enderecoMapper;

    @Operation(summary = "Listar todos os endereços cadastrados para a Unidade")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @ApiResponse(responseCode = "204", description = "Sem Conteúdo")
    @ApiResponse(responseCode = "401", description = "Autenticação necessária")
    @ApiResponse(responseCode = "403", description = "Requisição não autorizada")
    @ApiResponse(responseCode = "404", description = "Não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @GetMapping("/unidades/{id}/enderecos")
    public ResponseEntity<Page<EnderecoResponse>> listarTodosEnderecosPorUnidade(@PathVariable Integer id, @ParameterObject Pageable pageable) {
        return ResponseUtil.fromPage(enderecoUnidadeService.listarPorUnidadeId(id, pageable).map(enderecoMapper::toResponse));
    }

    @Operation(summary = "Inserir um endereço da Unidade")
    @ApiResponse(responseCode = "201", description = "Sucesso")
    @ApiResponse(responseCode = "401", description = "Autenticação necessária")
    @ApiResponse(responseCode = "403", description = "Requisição não autorizada")
    @ApiResponse(responseCode = "404", description = "Não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @PostMapping("/unidades/{id}/enderecos")
    public ResponseEntity<Object> salvarEnderecoPorUnidade(@PathVariable Integer id, @RequestBody @Valid EnderecoRequest enderecoRequest) {
        var endereco = enderecoMapper.toEntity(enderecoRequest);
        var enderecoSalvo = enderecoUnidadeService.salvarPorUnidade(id, endereco);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(enderecoSalvo.getId())
                .toUri();

        return ResponseEntity.created(location).body(Map.of("id", enderecoSalvo.getId()));
    }

    @Operation(summary = "Atualizar um endereço da Unidade")
    @ApiResponse(responseCode = "204", description = "Sucesso")
    @ApiResponse(responseCode = "401", description = "Autenticação necessária")
    @ApiResponse(responseCode = "403", description = "Requisição não autorizada")
    @ApiResponse(responseCode = "404", description = "Não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @PutMapping("/unidades/{idUnidade}/enderecos/{idEndereco}")
    public ResponseEntity<Void> atualizarEnderecoPorUnidade(@PathVariable Integer idUnidade, @PathVariable Integer idEndereco, @RequestBody @Valid EnderecoRequest enderecoRequest) {
        var endereco = enderecoMapper.toEntity(enderecoRequest);
        enderecoUnidadeService.atualizarPorUnidade(idUnidade, idEndereco, endereco);
        return ResponseEntity.noContent().build();
    }

}

package br.gov.mt.seplag.controller;

import br.gov.mt.seplag.mapper.EnderecoMapper;
import br.gov.mt.seplag.request.EnderecoRequest;
import br.gov.mt.seplag.response.EnderecoResponse;
import br.gov.mt.seplag.service.EnderecoPessoaService;
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

@Tag(name = "Servidores")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EnderecoPessoaController {

    private final EnderecoPessoaService enderecoPessoaService;
    private final EnderecoMapper enderecoMapper;

    @Operation(summary = "Listar todos os endereços cadastrados para o Servidor")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @ApiResponse(responseCode = "204", description = "Sem Conteúdo")
    @ApiResponse(responseCode = "404", description = "Não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @GetMapping("/servidores/{id}/enderecos")
    public ResponseEntity<Page<EnderecoResponse>> listarTodosEnderecosPorPessoa(@PathVariable Integer id, @ParameterObject Pageable pageable) {
        return ResponseUtil.fromPage(enderecoPessoaService.listarPorPessoaId(id, pageable).map(enderecoMapper::toResponse));
    }

    @Operation(summary = "Inserir um endereço do Servidor")
    @ApiResponse(responseCode = "201", description = "Sucesso")
    @ApiResponse(responseCode = "404", description = "Não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @PostMapping("/servidores/{id}/enderecos")
    public ResponseEntity<Object> salvarEnderecoPorPessoa(@PathVariable Integer id, @RequestBody @Valid EnderecoRequest enderecoRequest) {
        var endereco = enderecoMapper.toEntity(enderecoRequest);
        var enderecoSalvo = enderecoPessoaService.salvarPorPessoa(id, endereco);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(enderecoSalvo.getId())
                .toUri();

        return ResponseEntity.created(location).body(Map.of("id", enderecoSalvo.getId()));
    }

    @Operation(summary = "Atualizar um endereço do Servidor")
    @ApiResponse(responseCode = "204", description = "Sucesso")
    @ApiResponse(responseCode = "404", description = "Não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @PutMapping("/servidores/{idPessoa}/enderecos/{idEndereco}")
    public ResponseEntity<Void> atualizarEnderecoPorPessoa(@PathVariable Integer idPessoa, @PathVariable Integer idEndereco, @RequestBody @Valid EnderecoRequest enderecoRequest) {
        var endereco = enderecoMapper.toEntity(enderecoRequest);
        enderecoPessoaService.atualizarPorPessoa(idPessoa, idEndereco, endereco);
        return ResponseEntity.noContent().build();
    }

}

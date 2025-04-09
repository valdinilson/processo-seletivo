package br.gov.mt.seplag.controller;

import br.gov.mt.seplag.domain.ServidorEfetivo;
import br.gov.mt.seplag.mapper.ServidorEfetivoMapper;
import br.gov.mt.seplag.request.ServidorEfetivoRequest;
import br.gov.mt.seplag.response.ServidorEfetivoResponse;
import br.gov.mt.seplag.service.ServidorEfetivoService;
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

@Tag(name = "Servidores Efetivos")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ServidorEfetivoController {

    private final ServidorEfetivoService servidorEfetivoService;
    private final ServidorEfetivoMapper servidorEfetivoMapper;

    @Operation(summary = "Listar todos os Servidores Efetivos")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @ApiResponse(responseCode = "204", description = "Sem Conteúdo")
    @ApiResponse(responseCode = "401", description = "Autenticação necessária")
    @ApiResponse(responseCode = "403", description = "Requisição não autorizada")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @GetMapping("/servidores/efetivos")
    public ResponseEntity<Page<ServidorEfetivoResponse>> listarTudo(@ParameterObject Pageable pageable) {
        return ResponseUtil.fromPage(servidorEfetivoService.listarTodos(pageable).map(servidorEfetivoMapper::toResponse));
    }

    @Operation(summary = "Buscar um Servidor Efetivo pelo ID")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @ApiResponse(responseCode = "401", description = "Autenticação necessária")
    @ApiResponse(responseCode = "403", description = "Requisição não autorizada")
    @ApiResponse(responseCode = "404", description = "Não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @GetMapping("/servidores/efetivos/{id}")
    public ResponseEntity<ServidorEfetivoResponse> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(servidorEfetivoMapper.toResponse(servidorEfetivoService.buscarPorId(id)));
    }

    @Operation(summary = "Inserir um Servidor Efetivo")
    @ApiResponse(responseCode = "201", description = "Sucesso")
    @ApiResponse(responseCode = "401", description = "Autenticação necessária")
    @ApiResponse(responseCode = "403", description = "Requisição não autorizada")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @PostMapping("/servidores/efetivos")
    public ResponseEntity<Object> salvar(@RequestBody @Valid ServidorEfetivoRequest servidorEfetivoRequest) {
        var servidorEfetivo = servidorEfetivoMapper.toEntity(servidorEfetivoRequest);
        ServidorEfetivo servidorSalvo = servidorEfetivoService.salvar(servidorEfetivo);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(servidorSalvo.getId())
                .toUri();

        return ResponseEntity.created(location).body(Map.of("id", servidorSalvo.getId()));
    }

    @Operation(summary = "Atualizar um Servidor Efetivo")
    @ApiResponse(responseCode = "204", description = "Sucesso")
    @ApiResponse(responseCode = "401", description = "Autenticação necessária")
    @ApiResponse(responseCode = "403", description = "Requisição não autorizada")
    @ApiResponse(responseCode = "404", description = "Não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @PutMapping("/servidores/efetivos/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Integer id, @RequestBody @Valid ServidorEfetivoRequest servidorEfetivoRequest) {
        var servidorEfetivo = servidorEfetivoMapper.toEntity(servidorEfetivoRequest);
        servidorEfetivoService.atualizar(id, servidorEfetivo);
        return ResponseEntity.noContent().build();
    }

}

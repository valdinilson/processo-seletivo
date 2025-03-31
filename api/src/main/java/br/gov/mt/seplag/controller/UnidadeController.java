package br.gov.mt.seplag.controller;

import br.gov.mt.seplag.domain.Unidade;
import br.gov.mt.seplag.mapper.UnidadeMapper;
import br.gov.mt.seplag.request.UnidadeRequest;
import br.gov.mt.seplag.response.UnidadeResponse;
import br.gov.mt.seplag.service.UnidadeService;
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
public class UnidadeController {

    private final UnidadeService unidadeService;
    private final UnidadeMapper unidadeMapper;

    @Operation(summary = "Listar todas as unidades cadastradas")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @ApiResponse(responseCode = "204", description = "Sem Conteúdo")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @GetMapping("/unidades")
    public ResponseEntity<Page<UnidadeResponse>> listarTudo(@ParameterObject Pageable pageable) {
        return ResponseUtil.fromPage(unidadeService.listarTodos(pageable).map(unidadeMapper::toResponse));
    }

    @Operation(summary = "Buscar uma Unidade pelo ID")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @ApiResponse(responseCode = "404", description = "Não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @GetMapping("/unidades/{id}")
    public ResponseEntity<UnidadeResponse> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(unidadeMapper.toResponse(unidadeService.buscarPorId(id)));
    }

    @Operation(summary = "Inserir uma Unidade")
    @ApiResponse(responseCode = "201", description = "Sucesso")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @PostMapping("/unidades")
    public ResponseEntity<Object> salvar(@RequestBody @Valid UnidadeRequest unidadeRequest) {
        Unidade unidade = unidadeMapper.toEntity(unidadeRequest);
        Unidade unidadeSalva = unidadeService.salvar(unidade);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(unidadeSalva.getId())
                .toUri();

        return ResponseEntity.created(location).body(Map.of("id", unidadeSalva.getId()));
    }

    @Operation(summary = "Atualizar uma Unidade")
    @ApiResponse(responseCode = "204", description = "Sucesso")
    @ApiResponse(responseCode = "404", description = "Não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @PutMapping("/unidades/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Integer id, @RequestBody @Valid UnidadeRequest unidadeRequest) {
        var unidade = unidadeMapper.toEntity(unidadeRequest);
        unidadeService.atualizar(id, unidade);
        return ResponseEntity.noContent().build();
    }

}

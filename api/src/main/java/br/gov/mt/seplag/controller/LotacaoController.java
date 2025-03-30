package br.gov.mt.seplag.controller;

import br.gov.mt.seplag.mapper.LotacaoMapper;
import br.gov.mt.seplag.request.LotacaoDTO;
import br.gov.mt.seplag.response.LotacaoResponseDTO;
import br.gov.mt.seplag.response.ResponsePostDTO;
import br.gov.mt.seplag.service.LotacaoService;
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

@Tag(name = "Lotações")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LotacaoController {

    private final LotacaoService lotacaoService;
    private final LotacaoMapper lotacaoMapper;

    @Operation(summary = "Listar todas as Lotações cadastradas")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @ApiResponse(responseCode = "204", description = "Sem Conteúdo")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @GetMapping("/lotacoes")
    public ResponseEntity<Page<LotacaoResponseDTO>> listarTudo(@ParameterObject Pageable pageable) {
        return ResponseUtil.fromPage(lotacaoService.listarTodos(pageable).map(lotacaoMapper::toResponseDTO));
    }

    @Operation(summary = "Buscar uma lotação pelo ID")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @ApiResponse(responseCode = "404", description = "Não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @GetMapping("/lotacoes/{id}")
    public ResponseEntity<LotacaoResponseDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(lotacaoMapper.toResponseDTO(lotacaoService.buscarPorId(id)));
    }

    @Operation(summary = "Inserir uma lotação")
    @ApiResponse(responseCode = "201", description = "Sucesso")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @PostMapping("/lotacoes")
    public ResponseEntity<ResponsePostDTO> salvar(@RequestBody @Valid LotacaoDTO lotacaoDTO) {
        var lotacao = lotacaoMapper.toEntity(lotacaoDTO);
        var lotacaoSalva = lotacaoService.salvar(lotacao);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(lotacaoSalva.getId())
                .toUri();

        return ResponseEntity.created(location).body(lotacaoMapper.toResponsePostDTO(lotacaoSalva));
    }

    @Operation(summary = "Atualizar uma lotação")
    @ApiResponse(responseCode = "204", description = "Sucesso")
    @ApiResponse(responseCode = "404", description = "Não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @PutMapping("/lotacoes/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Integer id, @RequestBody @Valid LotacaoDTO lotacaoDTO) {
        var lotacao = lotacaoMapper.toEntity(lotacaoDTO);
        lotacaoService.atualizar(id, lotacao);
        return ResponseEntity.noContent().build();
    }
}

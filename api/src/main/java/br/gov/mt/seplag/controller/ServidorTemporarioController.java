package br.gov.mt.seplag.controller;

import br.gov.mt.seplag.domain.ServidorTemporario;
import br.gov.mt.seplag.mapper.ServidorTemporarioMapper;
import br.gov.mt.seplag.request.ServidorTemporarioRequest;
import br.gov.mt.seplag.response.ServidorTemporarioResponse;
import br.gov.mt.seplag.service.ServidorTemporarioService;
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

@Tag(name = "Servidores Temporários")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ServidorTemporarioController {

    private final ServidorTemporarioService servidorTemporarioService;
    private final ServidorTemporarioMapper servidorTemporarioMapper;

    @Operation(summary = "Listar todos os Servidores Temporários")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @ApiResponse(responseCode = "204", description = "Sem Conteúdo")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @GetMapping("/servidores/temporarios")
    public ResponseEntity<Page<ServidorTemporarioResponse>> listarTudo(@ParameterObject Pageable pageable) {
        return ResponseUtil.fromPage(servidorTemporarioService.listarTodos(pageable).map(servidorTemporarioMapper::toResponse));
    }

    @Operation(summary = "Buscar um Servidor Temporário pelo ID")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @ApiResponse(responseCode = "404", description = "Não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @GetMapping("/servidores/temporarios/{id}")
    public ResponseEntity<ServidorTemporarioResponse> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(servidorTemporarioMapper.toResponse(servidorTemporarioService.buscarPorId(id)));
    }

    @Operation(summary = "Inserir um Servidor Temporário")
    @ApiResponse(responseCode = "201", description = "Sucesso")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @PostMapping("/servidores/temporarios")
    public ResponseEntity<Object> salvar(@RequestBody @Valid ServidorTemporarioRequest servidorTemporarioRequest) {
        var servidorTemporario = servidorTemporarioMapper.toEntity(servidorTemporarioRequest);
        ServidorTemporario servidorSalvo = servidorTemporarioService.salvar(servidorTemporario);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(servidorSalvo.getId())
                .toUri();

        return ResponseEntity.created(location).body(Map.of("id", servidorSalvo.getId()));
    }

    @Operation(summary = "Atualizar um Servidor Temporário")
    @ApiResponse(responseCode = "204", description = "Sucesso")
    @ApiResponse(responseCode = "404", description = "Não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @PutMapping("/servidores/temporarios/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Integer id, @RequestBody @Valid ServidorTemporarioRequest servidorTemporarioRequest) {
        var servidorTemporario = servidorTemporarioMapper.toEntity(servidorTemporarioRequest);
        servidorTemporarioService.atualizar(id, servidorTemporario);
        return ResponseEntity.noContent().build();
    }

}

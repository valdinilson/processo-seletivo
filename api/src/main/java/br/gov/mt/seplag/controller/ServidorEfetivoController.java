package br.gov.mt.seplag.controller;

import br.gov.mt.seplag.domain.ServidorEfetivo;
import br.gov.mt.seplag.mapper.ServidorEfetivoMapper;
import br.gov.mt.seplag.request.ServidorEfetivoDTO;
import br.gov.mt.seplag.response.ResponsePostDTO;
import br.gov.mt.seplag.response.ServidorEfetivoResponseDTO;
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
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @GetMapping("/servidores/efetivos")
    public ResponseEntity<Page<ServidorEfetivoResponseDTO>> listarTudo(@ParameterObject Pageable pageable) {
        return ResponseUtil.fromPage(servidorEfetivoService.listarTodos(pageable).map(servidorEfetivoMapper::toResponseDTO));
    }

    @Operation(summary = "Buscar um Servidor Efetivo pelo ID")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @ApiResponse(responseCode = "404", description = "Não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @GetMapping("/servidores/efetivos/{id}")
    public ResponseEntity<ServidorEfetivoResponseDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(servidorEfetivoMapper.toResponseDTO(servidorEfetivoService.buscarPorId(id)));
    }

    @Operation(summary = "Inserir um Servidor Efetivo")
    @ApiResponse(responseCode = "201", description = "Sucesso")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @PostMapping("/servidores/efetivos")
    public ResponseEntity<ResponsePostDTO> salvar(@RequestBody @Valid ServidorEfetivoDTO servidorEfetivoDTO) {
        var servidorEfetivo = servidorEfetivoMapper.toEntity(servidorEfetivoDTO);
        ServidorEfetivo servidorSalvo = servidorEfetivoService.salvar(servidorEfetivo);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(servidorSalvo.getId())
                .toUri();

        return ResponseEntity.created(location).body(servidorEfetivoMapper.toResponsePostDTO(servidorSalvo));
    }

    @Operation(summary = "Atualizar um Servidor Efetivo")
    @ApiResponse(responseCode = "204", description = "Sucesso")
    @ApiResponse(responseCode = "404", description = "Não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @PutMapping("/servidores/efetivos/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Integer id, @RequestBody @Valid ServidorEfetivoDTO servidorEfetivoDTO) {
        var servidorEfetivo = servidorEfetivoMapper.toEntity(servidorEfetivoDTO);
        servidorEfetivoService.atualizar(id, servidorEfetivo);
        return ResponseEntity.noContent().build();
    }

}

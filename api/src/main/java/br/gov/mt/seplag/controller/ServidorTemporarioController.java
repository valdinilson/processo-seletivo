package br.gov.mt.seplag.controller;

import br.gov.mt.seplag.domain.ServidorTemporario;
import br.gov.mt.seplag.mapper.ServidorTemporarioMapper;
import br.gov.mt.seplag.request.ServidorTemporarioDTO;
import br.gov.mt.seplag.response.ResponsePostDTO;
import br.gov.mt.seplag.response.ServidorTemporarioResponseDTO;
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
    public ResponseEntity<Page<ServidorTemporarioResponseDTO>> listarTudo(@ParameterObject Pageable pageable) {
        return ResponseUtil.fromPage(servidorTemporarioService.listarTodos(pageable).map(servidorTemporarioMapper::toResponseDTO));
    }

    @Operation(summary = "Buscar um Servidor Temporário pelo ID")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @ApiResponse(responseCode = "404", description = "Não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @GetMapping("/servidores/temporarios/{id}")
    public ResponseEntity<ServidorTemporarioResponseDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(servidorTemporarioMapper.toResponseDTO(servidorTemporarioService.buscarPorId(id)));
    }

    @Operation(summary = "Inserir um Servidor Temporário")
    @ApiResponse(responseCode = "201", description = "Sucesso")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @PostMapping("/servidores/temporarios")
    public ResponseEntity<ResponsePostDTO> salvar(@RequestBody @Valid ServidorTemporarioDTO servidorTemporarioDTO) {
        var servidorTemporario = servidorTemporarioMapper.toEntity(servidorTemporarioDTO);
        ServidorTemporario servidorSalvo = servidorTemporarioService.salvar(servidorTemporario);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(servidorSalvo.getId())
                .toUri();

        return ResponseEntity.created(location).body(servidorTemporarioMapper.toResponsePostDTO(servidorSalvo));
    }

    @Operation(summary = "Atualizar um Servidor Temporário")
    @ApiResponse(responseCode = "204", description = "Sucesso")
    @ApiResponse(responseCode = "404", description = "Não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @PutMapping("/servidores/temporarios/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Integer id, @RequestBody @Valid ServidorTemporarioDTO servidorTemporarioDTO) {
        var servidorTemporario = servidorTemporarioMapper.toEntity(servidorTemporarioDTO);
        servidorTemporarioService.atualizar(id, servidorTemporario);
        return ResponseEntity.noContent().build();
    }

}

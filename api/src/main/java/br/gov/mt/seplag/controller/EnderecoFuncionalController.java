package br.gov.mt.seplag.controller;

import br.gov.mt.seplag.mapper.EnderecoMapper;
import br.gov.mt.seplag.response.EnderecoResponse;
import br.gov.mt.seplag.service.EnderecoFuncionalService;
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

@Tag(name = "Servidores Efetivos")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EnderecoFuncionalController {

    private final EnderecoFuncionalService enderecoFuncionalService;
    private final EnderecoMapper enderecoMapper;

    @Operation(summary = "Listar endereço funcional pelo nome ou parte do nome do Servidor Efetivo")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @ApiResponse(responseCode = "204", description = "Sem Conteúdo")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @GetMapping("/servidores/efetivos/{nome}/enderecos-funcionais")
    public ResponseEntity<Page<EnderecoResponse>> listarEnderecoFuncionalServidores(@PathVariable String nome, @ParameterObject Pageable pageable) {
        return ResponseUtil.fromPage(enderecoFuncionalService.listarEnderecoFuncionalPorServidorNome(nome, pageable).map(enderecoMapper::toResponse));
    }

}

package br.gov.mt.seplag.controller;

import br.gov.mt.seplag.domain.Usuario;
import br.gov.mt.seplag.mapper.UsuarioMapper;
import br.gov.mt.seplag.request.UsuarioRequest;
import br.gov.mt.seplag.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "Usuários")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioMapper usuarioMapper;

    @Operation(summary = "Registrar novo usuário no sistema")
    @ApiResponse(responseCode = "201", description = "Sucesso")
    @ApiResponse(responseCode = "400", description = "Falha na requisição")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @PostMapping("/usuarios/registrar")
    public ResponseEntity<Object> adicionarUsuario(@RequestBody UsuarioRequest usuarioRequest) {
        Usuario usuario = usuarioMapper.toEntity(usuarioRequest);
        String response = service.salvar(usuario);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("mensagem", response));
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello Word!!";
    }
}

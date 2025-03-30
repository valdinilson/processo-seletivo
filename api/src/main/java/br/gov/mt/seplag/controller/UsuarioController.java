package br.gov.mt.seplag.controller;

import br.gov.mt.seplag.domain.Usuario;
import br.gov.mt.seplag.mapper.UsuarioMapper;
import br.gov.mt.seplag.request.UsuarioDTO;
import br.gov.mt.seplag.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioMapper usuarioMapper;

    @PostMapping("/credenciais/registrar")
    public ResponseEntity<Object> adicionarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        String response = service.salvar(usuario);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("mensagem", response));
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}

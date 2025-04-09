package br.gov.mt.seplag.controller;

import br.gov.mt.seplag.request.AuthRequest;
import br.gov.mt.seplag.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "Autenticação")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @Operation(summary = "Autenticar usuário cadastrado no sistema", description = "Autentica usuário no sistema e retorna o token")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @ApiResponse(responseCode = "400", description = "Falha na requisição")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @PostMapping("/autenticar")
    public ResponseEntity<Object> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("token", tokenService.autenticar(authRequest)));
    }

    @Operation(summary = "Solicitar novo token", description = "Renova o token, caso o atual esteja válido")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @ApiResponse(responseCode = "400", description = "Falha na requisição")
    @ApiResponse(responseCode = "401", description = "Autenticação necessária")
    @ApiResponse(responseCode = "403", description = "Requisição não autorizada")
    @ApiResponse(responseCode = "500", description = "Erro Interno")
    @GetMapping("/token")
    public ResponseEntity<Object> renovarToken(HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("token", tokenService.renovarToken(request)));
    }

}

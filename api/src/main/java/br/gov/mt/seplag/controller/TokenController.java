package br.gov.mt.seplag.controller;

import br.gov.mt.seplag.request.AuthRequest;
import br.gov.mt.seplag.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/autenticar")
    public ResponseEntity<Object> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("token", tokenService.autenticar(authRequest)));
    }

    @GetMapping("/token")
    public ResponseEntity<Object> renovarToken(HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("token", tokenService.renovarToken(request)));
    }

}

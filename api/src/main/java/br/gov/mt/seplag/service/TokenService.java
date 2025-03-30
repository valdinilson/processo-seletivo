package br.gov.mt.seplag.service;

import br.gov.mt.seplag.request.AuthRequest;
import br.gov.mt.seplag.security.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String autenticar(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if (!authentication.isAuthenticated()) {
            throw new UsernameNotFoundException("Usuário e/ou senha inválidos");
        }

        return jwtService.generateToken(authRequest.getUsername());
    }

    public String renovarToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            throw new RuntimeException("Token Inválido");
        }

        String username = jwtService.extractUsername(header
                .substring("Bearer ".length()));

        return jwtService.generateToken(username);
    }
}

package com.clinica.api.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.clinica.api.dto.LoginRequest;
import com.clinica.api.security.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${app.security.usuario}")
    private String usuarioValido;

    @Value("${app.security.senha-hash}")
    private String senhaHash;

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest login) {

        boolean usuarioCorreto = usuarioValido.equals(login.usuario());
        boolean senhaCorreta = usuarioCorreto
                && BCrypt.verifyer().verify(login.senha().toCharArray(), senhaHash).verified;

        if (!senhaCorreta) {
            return ResponseEntity.status(401).body(Map.of("mensagem", "Usuário ou senha inválidos"));
        }

        String token = jwtUtil.gerarToken(login.usuario());

        return ResponseEntity.ok(Map.of("token", token));
    }

    @GetMapping("/status")
    public ResponseEntity<?> status(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        boolean autenticado = authHeader != null
                && authHeader.startsWith("Bearer ")
                && jwtUtil.tokenValido(authHeader.substring(7));

        return ResponseEntity.ok(Map.of("autenticado", autenticado));
    }
}
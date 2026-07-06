package com.clinica.api.controller;

import com.clinica.api.dto.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${app.security.usuario}")
    private String usuarioValido;

    @Value("${app.security.senha}")
    private String senhaValida;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest login, HttpServletRequest request) {

        boolean credenciaisCorretas =
                usuarioValido.equals(login.usuario()) && senhaValida.equals(login.senha());

        if (!credenciaisCorretas) {
            return ResponseEntity.status(401).body(Map.of("mensagem", "Usuário ou senha inválidos"));
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("autenticado", true);

        return ResponseEntity.ok(Map.of("mensagem", "Login realizado com sucesso"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok(Map.of("mensagem", "Logout realizado"));
    }

    @GetMapping("/status")
    public ResponseEntity<?> status(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        boolean autenticado = session != null && Boolean.TRUE.equals(session.getAttribute("autenticado"));
        return ResponseEntity.ok(Map.of("autenticado", autenticado));
    }
}
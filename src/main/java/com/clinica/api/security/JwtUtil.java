package com.clinica.api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${app.security.jwt-secret}")
    private String segredo;

    @Value("${app.security.jwt-expiracao-horas:12}")
    private long expiracaoHoras;

    private SecretKey getChave() {
        return Keys.hmacShaKeyFor(segredo.getBytes());
    }

    public String gerarToken(String usuario) {
        Date agora = new Date();
        Date expiracao = new Date(agora.getTime() + expiracaoHoras * 60 * 60 * 1000);

        return Jwts.builder()
                .setSubject(usuario)
                .setIssuedAt(agora)
                .setExpiration(expiracao)
                .signWith(getChave(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean tokenValido(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getChave())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
package com.br.redec.accessos_terceros_ms.infrastructure.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service //usado quando a classe representa uma regra de negocio ou serviço da aplicacao (um funcionario que executa uma tarefa)
public class TokenService {

    private final String secret = "minha-chave-secreta-super-segura-bradesco-2026-redec";

    // Transforma a nossa string em uma Chave Criptográfica real que o Java entende
    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String usuario){
        return Jwts.builder()
                .issuer("acessos-terceros-ms") //quem esta emitindo o token (nossa api)
                .subject(usuario) //quem é o usuário (pode ser o email, id, etc)
                .issuedAt(new java.util.Date()) //data de emissão do token
                .expiration(Date.from(Instant.now().plus(2, ChronoUnit.HOURS))) //expira em 2 horas
                .signWith(getSignKey()) //carimba com nossa chave secreta
                .compact(); //compacta tudo e gera uma String final
    }

    public String validarToken(String token){
        return Jwts.parser()
                .verifyWith(getSignKey()) //verifica a assinatura do token com nossa chave secreta
                .build()
                .parseSignedClaims(token) //se for falso ou expirado gera uma exception
                .getPayload()
                .getSubject(); //se der tudo certo, devolve o nome do usuario extraído do payload
    }
}

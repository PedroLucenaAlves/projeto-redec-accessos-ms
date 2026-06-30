package com.br.redec.accessos_terceros_ms.infrastructure.adapter.in.web;

import com.br.redec.accessos_terceros_ms.infrastructure.security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final TokenService tokenService;

    //IoT via construtor
    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request) {

        if ("user".equals(request.usuario()) && "123456".equals(request.senha())) {

            String token = tokenService.generateToken(request.usuario());
            return ResponseEntity.ok(token);

        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas!");
        }
    }

}

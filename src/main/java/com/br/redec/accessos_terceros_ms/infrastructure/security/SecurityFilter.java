package com.br.redec.accessos_terceros_ms.infrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component //bean gerenciado pelo spring
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    public SecurityFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token = recuperarToken(request);

        if(token != null) {
            var usuario = tokenService.validarToken(token); //valida o token e extrai o nome do usuario

            if (usuario != null) {
                //cria acesso 'cracha' dizendo que o usuario esta autenticado
                var autenticacao = new UsernamePasswordAuthenticationToken(usuario, null, Collections.emptyList());

                SecurityContextHolder.getContext().setAuthentication(autenticacao); //seta o cracha no contexto de seguranca do spring
            }
        }

            //libera o fluxo para o proximo filtro ou para o controller
            filterChain.doFilter(request, response);
    }

    //metodo auxiliar para pegar apenas a strig do token (sem o Bearer)
    private String recuperarToken(HttpServletRequest request) {

        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}

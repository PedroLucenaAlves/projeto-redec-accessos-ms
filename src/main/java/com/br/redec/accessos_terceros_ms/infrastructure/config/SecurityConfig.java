package com.br.redec.accessos_terceros_ms.infrastructure.config;

import com.br.redec.accessos_terceros_ms.infrastructure.security.SecurityFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // usamos configuration para configurar o Spring, apontando como a logica da classe deve funcionar
@EnableWebSecurity //informa ao spring que estamos trabalhando com seguranca
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) //precisamos desativar o csrf pois ele serve apenas como protecao de dados no envio de formularios (nao e nosso caso)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //dizeno ao Spring que a api não guarda sessões
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/v1/auth/login").permitAll() //permite apenas requisicoes diretas para esse endpoint
                        .anyRequest().authenticated() //o restante precisar estar autenticado para usar
                )
                //adiciona o filtro de autenticacao antes do filtro padrao do spring
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**       BASIC AUTH
    @Bean //este método funciona como uma corrente de filtros antes de bater no controller
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Desabilita o CSRF porque nossa API é Stateless (não guarda sessão) e não usa Cookies
                .csrf(csrf -> csrf.disable())

                // Define a política de sessão como STATELESS 'não guardar nada na memoria' (Essencial para microsserviços)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Define as regras de autorização das requisições HTTP
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()) // Habilita autenticação HTTP Basic (para testes simples)
                .build();
    } **/

}

package com.plataforma.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desabilitado para facilitar testes iniciais
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll() // Libera o banco H2
                .anyRequest().authenticated() // Exige login para o resto
            )
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin())) // Necessário para o H2 aparecer no navegador
            .formLogin(withDefaults()) // Ativa o formulário que você viu
            .httpBasic(withDefaults()); // Permite autenticação básica (útil para o Postman)
            
        return http.build();
    }
}
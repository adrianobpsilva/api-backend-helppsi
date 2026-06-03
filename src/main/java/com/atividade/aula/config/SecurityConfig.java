package com.atividade.aula.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .cors(Customizer.withDefaults())

            .authorizeHttpRequests(auth -> auth

                .requestMatchers(
                    "/cadastrar-psicologo",
                    "/login-psicologo",
                    "/psicologos",
                    "/psicologo/**",
                    "/psicologos/cidade",
                    "/cadastrar-adm",
                    "/adms"
                ).permitAll()

                .anyRequest().authenticated()
            )

            .httpBasic(httpBasic -> httpBasic.disable())

            .formLogin(form -> form.disable());

        return http.build();
    }
}
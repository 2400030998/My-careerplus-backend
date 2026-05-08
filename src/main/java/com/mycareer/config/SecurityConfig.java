package com.mycareer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> {})   // ✅ correct way
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/users/**").permitAll() // 👈 explicitly allow
                .anyRequest().permitAll()
            );

        return http.build();
    }
}
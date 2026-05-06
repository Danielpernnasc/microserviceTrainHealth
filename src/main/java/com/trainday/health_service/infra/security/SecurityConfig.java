package com.trainday.health_service.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.Filter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        private static final String HEALTYSERVICEPOSTBIO = "/healty-service/bioimpedance";
        private static final String HEALTYSERVICEGETBIO = "/healty-service/bioimpedance/*";
        private static final String HEALTYSERVICEPATCH = "/healty-service/bioimpedance/**";
        private static final String HEALTYSERVICEPUT = "/healty-service/bioimpedance/*";
        // private static final String TRAIN_SCHEDULE_EXERCISE = "/train/my-trains/*/schedule/*/exercise/*";
        // private static final String TRAIN_TEMPLATES = "/trainTemplate/templates";
        // private static final String APPLY_TRAIN_TEMPLATE = "/trainTemplate/templates/*/apply";

   private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
        .csrf(csrf -> csrf.disable())

        .sessionManagement(session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )

        .authorizeHttpRequests(auth -> auth 

             .requestMatchers(
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/swagger-ui.html"
                ).permitAll()

                .requestMatchers(HttpMethod.POST, HEALTYSERVICEPOSTBIO).authenticated()
                .requestMatchers(HttpMethod.GET, HEALTYSERVICEGETBIO).permitAll()
                .requestMatchers(HttpMethod.GET, HEALTYSERVICEPATCH).authenticated()
                .requestMatchers(HttpMethod.PUT, HEALTYSERVICEPUT).authenticated()

                .anyRequest().authenticated()
        )

          .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

            .build();


    }

}

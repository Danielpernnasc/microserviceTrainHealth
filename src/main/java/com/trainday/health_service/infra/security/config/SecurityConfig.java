package com.trainday.health_service.infra.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.trainday.health_service.infra.security.JwtAuthFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        private static final String HEALTYSERVICEPOSTBIO = "/bioimpedance";
        private static final String HEALTYSERVICEGETBIO = "/bioimpedance/**";
        private static final String HEALTYSERVICEPATCHBIO = "/bioimpedance/**";
        private static final String HSANALYSISCLINALPOST = "/clinicalAnalysis";
        private static final String HSANALYSISCLINICALGET = "/clinicalAnalysis/**";

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
                .requestMatchers(HttpMethod.PATCH, HEALTYSERVICEPATCHBIO).authenticated()
                .requestMatchers(HttpMethod.POST, HSANALYSISCLINALPOST).authenticated()
                .requestMatchers(HttpMethod.GET, HSANALYSISCLINICALGET).permitAll()


                .anyRequest().authenticated()
        )

          .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

            .build();


    }

}

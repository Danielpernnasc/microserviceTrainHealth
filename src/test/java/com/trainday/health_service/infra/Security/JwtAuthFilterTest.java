package com.trainday.health_service.infra.Security;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.trainday.health_service.infra.security.JwtAuthFilter;
import com.trainday.health_service.infra.security.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ExtendWith(MockitoExtension.class)
public class JwtAuthFilterTest {
    
    @Mock
    JwtService jwtService;

    @InjectMocks
    JwtAuthFilter jwtAuthFilter;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

     @AfterEach
    void clearContext() {
        SecurityContextHolder.clearContext();
    }

    
    @Test
    void shouldSkipFilterWhenAuthHeader() throws Exception {
        when(request.getHeader("Authorization")).thenReturn(null);

        jwtAuthFilter.doFilter(request, response, filterChain);
        verify(filterChain).doFilter(request, response);
        verifyNoInteractions(jwtService);
    }

    @Test
    void shouldSkipAuthWhenTokenIsInvalid() throws Exception {
             when(request.getHeader("Authorization")).thenReturn("Bearer invalid-token");
             when(jwtService.isTokenValid("invalid-token")).thenThrow(new RuntimeException("Token inválido"));

             jwtAuthFilter.doFilter(request, response, filterChain);

             verify(filterChain).doFilter(request, response);

             Authentication auth = SecurityContextHolder.getContext().getAuthentication();
             assertNull(auth);

    }

     @Test 
    void shouldAuthenticateWhenTokenIsValid() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("Bearer valid-token");
        when(jwtService.isTokenValid("valid-token")).thenReturn(true);
        when(jwtService.extractEmail("valid-token")).thenReturn("athlete@host.com");

        jwtAuthFilter.doFilter(request, response, filterChain);
        verify(filterChain).doFilter(request, response);

       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       assertNotNull(auth);
       assertEquals("athlete@host.com", auth.getPrincipal());
       
    }


}

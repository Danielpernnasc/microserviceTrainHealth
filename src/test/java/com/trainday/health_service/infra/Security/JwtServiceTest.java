package com.trainday.health_service.infra.Security;

import static org.junit.jupiter.api.Assertions.*;

import java.security.Key;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.test.util.ReflectionTestUtils;

import com.trainday.health_service.infra.security.JwtService;

public class JwtServiceTest {
    @InjectMocks
    private JwtService jwtService;

      @BeforeEach
    void setUp(){
        jwtService = new JwtService();

        ReflectionTestUtils.setField(jwtService, "secret", "fake-secret-key-for-testing-trainday-2026!!");
        ReflectionTestUtils.setField(jwtService, "expiration", 3600000L);
    }

    @Test
    void shoulReturnKey(){
        Key key = jwtService.testgetKey();
        assertNotNull(key);
    }

    @Test
    void shouldGenerateToken(){
        String token = jwtService.generateToken("falseemail@host.com", "falseemail@host.com", "falseemail@host.com", "123.456.789-00");
         assertNotNull(token);
         assertFalse(token.isBlank());
    }

        @Test
        void shouldExtractEmail(){
            String token = jwtService.generateToken("athlete@host.com", "athlete@host.com", "athlete@host.com", "999.999.999-99");
            String cpf = jwtService.extractCpf(token);
            String email = jwtService.extractEmail(token);
            String userId = jwtService.extracUsername(token);
            assertEquals("athlete@host.com", email);
            assertEquals("athlete@host.com", userId );
            assertEquals("999.999.999-99", cpf);
    }

    
    @Test
    void shouldReturnTrueWhenTokenIsValid(){
        String token = jwtService.generateToken("athlete@host.com", "athlete@host.com", "athlete@host.com", "999.999.999-99");
        assertTrue(jwtService.isTokenValid(token));
    }

      @Test
        void shouldReturnFalseWhenTokenIsValid(){
            assertFalse(jwtService.isTokenValid("token-invalido"));
        }

        
        @Test
        void shouldExtractUsername(){
            String token = jwtService.generateToken("athlete@host.com", "athlete@host.com", "athlete@host.com", "999.999.999-99");
            String username = jwtService.extracUsername(token);
            assertEquals("athlete@host.com", username);
        }

        @Test
        void shouldExtractCPF(){
            String token = jwtService.generateToken("athlete@host.com", "athlete@host.com", "athlete@host.com", "999.999.999-99");
            String cpf = jwtService.extractCpf(token);
            assertEquals("999.999.999-99", cpf);
        }





}

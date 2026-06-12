package com.trainday.health_service.infra.Security.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.trainday.health_service.infra.security.config.SwaggerConfig;

public class SwaggerConfigTest {
    @Test
    void shouldCreateConfigSwagger(){
        SwaggerConfig config = new SwaggerConfig();
        assertNotNull(config);
    }

}

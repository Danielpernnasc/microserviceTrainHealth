package com.trainday.health_service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
class HealthServiceApplicationTests {

	@Test
    void shouldLoadContext() {
        assertNotNull(new HealthServiceApplication());
    }

}

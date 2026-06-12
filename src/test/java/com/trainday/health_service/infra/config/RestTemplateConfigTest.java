package com.trainday.health_service.infra.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

public class RestTemplateConfigTest {

    @Test
    void shoudCreateRestTemplate(){
        RestTemplateConfig template = new RestTemplateConfig();

        RestTemplate config = template.restTemplate();

        assertNotNull(config);


    }

}

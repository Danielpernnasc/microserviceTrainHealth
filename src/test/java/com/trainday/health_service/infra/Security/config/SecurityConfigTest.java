package com.trainday.health_service.infra.Security.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.trainday.health_service.api.controller.BioimpedanceController;
import com.trainday.health_service.aplication.service.BioimpendanceService;
import com.trainday.health_service.infra.security.JwtAuthFilter;
import com.trainday.health_service.infra.security.JwtService;
import com.trainday.health_service.infra.security.config.SecurityConfig;


@WebMvcTest(controllers = {
    BioimpedanceController.class,
})
@ContextConfiguration(classes = {
    BioimpedanceController.class,
    SecurityConfig.class,
    JwtAuthFilter.class
})
public class SecurityConfigTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private BioimpendanceService bioimpendanceService;
        
    @MockBean
    JwtAuthFilter authFilter;

    @MockBean
    private JwtService jwtService;





@Test
void shouldReturnAuthorizedWithToken() throws Exception{
          @SuppressWarnings("unused")
        MvcResult result = mockMvc.perform(
        post("/bioimpedance"))
        .andReturn();
}



}

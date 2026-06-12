package com.trainday.health_service.api.controller;

import com.trainday.health_service.api.DTO.Request.ClinicalAnalysisRequest;
import com.trainday.health_service.aplication.service.ClinicalAnalysisService;
import com.trainday.health_service.domain.models.ClinicalAnalysis;
import com.trainday.health_service.domain.models.enums.AnalysisStatus;
import com.trainday.health_service.infra.security.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClinicalAnalysisControllerTest {
    @Mock
    ClinicalAnalysisService service;

    @Mock
    JwtService jwtService;

    @InjectMocks
    ClinicalAnalysisController controller;

    LocalDateTime now =  LocalDateTime.now();
    @Test
    void shouldcreateAnalysis(){
        ClinicalAnalysisRequest request = ClinicalAnalysisRequest.builder()
                .athleteCpf("999.999.999-99")
                .build();



        ClinicalAnalysis clinicalAnalysis = new ClinicalAnalysis();
        clinicalAnalysis.setCpfAhtlete("999.999.999-99");
        when(jwtService.extractEmail("token"))
                .thenReturn("athlete@host.com");



       when(service.createAnalysisClinical(request, "athlete@host.com"))
               .thenReturn(clinicalAnalysis);


     ResponseEntity<ClinicalAnalysis> response = controller.createAnalysisClinical(request, "Bearer token");
     ClinicalAnalysis result = response.getBody();
     assertNotNull(result);
     assertEquals("999.999.999-99", result.getCpfAhtlete());

    }

    @Test
    void shouldfindByCpf(){
            ClinicalAnalysis clinicalAnalysis = new ClinicalAnalysis();
            clinicalAnalysis.setCpfAhtlete("999.999.999-99");

        when(service.getAnalysisClinical("999.999.999-99"))
                .thenReturn(List.of(clinicalAnalysis));

        List<ClinicalAnalysis> response = controller.findByCpf("999.999.999-99");

        assertNotNull(response);
        assertEquals("999.999.999-99", response.get(0).getCpfAhtlete());
    }



}

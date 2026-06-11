package com.trainday.health_service.api.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.trainday.health_service.api.DTO.Request.BioimpedanceRequest;
import com.trainday.health_service.aplication.service.BioimpendanceService;
import com.trainday.health_service.domain.models.Bioimpedance;
import com.trainday.health_service.domain.models.enums.ActivityLevel;
import com.trainday.health_service.infra.security.JwtService;



@ExtendWith(MockitoExtension.class)
public class BioimpedanceControllerTest {

    @Mock
    BioimpendanceService service;

    @Mock
    JwtService jwtService;

    @InjectMocks
    BioimpedanceController bioController;

    LocalDateTime now = LocalDateTime.now();

    @Test
    void shouldSaveBio(){
        BioimpedanceRequest request = new BioimpedanceRequest(
                "999.999.999-99",
                108.5,
                185.5,
                18,
                82,
                30.174516794895855,
                82.41000000000001,
                18.99,
                ActivityLevel.MODERATE
            );

            Bioimpedance bioimpedance = new Bioimpedance();
            bioimpedance.setId("1");
            bioimpedance.setCpfAhtlete("999.999.999-99");
            bioimpedance.setWeight(108.5);
            bioimpedance.setHeight(185.5);
            bioimpedance.setBodyFatPercentage(18);
            bioimpedance.setBodyLeanMassPercentage(82);
            bioimpedance.setImc(30.174516794895855);
            bioimpedance.setLeanMass(82.41000000000001);
            bioimpedance.setFatMass(18.99);
            bioimpedance.setActivityLevel(ActivityLevel.MODERATE);
            bioimpedance.setAvaliationDate(now);

            when(jwtService.extractEmail("token"))
            .thenReturn("athlete@host.com.");

            when(service.create(request, "athlete@host.com."))
                .thenReturn(bioimpedance);

            ResponseEntity<Bioimpedance> create = bioController.save(request, "Bearer token" );
            
            assertNotNull(create);
            assertEquals(request.cpfAthlete(), create.getBody().getCpfAhtlete());
            assertEquals(request.weight(), create.getBody().getWeight());
            assertEquals(request.height(), create.getBody().getHeight());
            assertEquals(request.bodyFatPercentage(), create.getBody().getBodyFatPercentage());
            assertEquals(request.bodyLeanMassPercentage(), create.getBody().getBodyLeanMassPercentage());
            assertEquals(request.imc(), create.getBody().getImc());
            assertEquals(request.leanMass(), create.getBody().getLeanMass());
            assertEquals(request.fatMass(), create.getBody().getFatMass());
            assertEquals(request.activityLevel(), create.getBody().getActivityLevel());
          
    }

    @Test
    void shouldfindByCpf(){
        Bioimpedance bio = new Bioimpedance();
        bio.setCpfAhtlete("999.999.999-99");


        List<Bioimpedance> bios = List.of(bio);

        when(service.getBioByCpf("999.999.999-99"))
        .thenReturn(bios);

        List<Bioimpedance> result = bioController.findByCpf("999.999.999-99");

        assertEquals("999.999.999-99", result.get(0).getCpfAhtlete());

    }


    @Test
    void shouldpatchBioimpedance(){
        

            Bioimpedance bio = new Bioimpedance();
            bio.setWeight(105.5);


            when(service.patch("1", bio))
            .thenReturn(bio);

            ResponseEntity<Bioimpedance> patch = bioController.patchBioimpedance("1", bio);

            assertNotNull(patch);
            assertEquals(105.5, patch.getBody().getWeight());
    }

}

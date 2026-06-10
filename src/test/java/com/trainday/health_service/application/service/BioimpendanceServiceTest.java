package com.trainday.health_service.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.trainday.health_service.domain.models.AthleteSnapshot;
import com.trainday.health_service.infra.client.AthleteClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.trainday.health_service.api.DTO.Request.BioimpedanceRequest;
import com.trainday.health_service.api.DTO.Response.AthleteSnapshotResponse;
import com.trainday.health_service.aplication.service.BioimpendanceService;

import com.trainday.health_service.domain.models.Bioimpedance;
import com.trainday.health_service.domain.models.enums.ActivityLevel;
import com.trainday.health_service.domain.repository.BioimpedanceRepository;
import com.trainday.health_service.infra.client.AthleteClient;
import org.mockito.stubbing.OngoingStubbing;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BioimpendanceServiceTest {

    @Mock
    private BioimpedanceRepository repository;

    @Mock
    private AthleteClient athleteClient;

    @Mock
    private AthleteClientService athleteClientService;

    @InjectMocks
    private BioimpendanceService service;



    @Test
    void shouldCreateBio(){
        AthleteSnapshotResponse response = AthleteSnapshotResponse.builder()
        .cpf("999.999.999-99")
        .name("Daniel Péricles do Nascimento")
        .email("daniel@host.com")
        .age(45)
        .gender("Male")
        .identity("Cisgender")
        .height(185.5)
        .weight(108.5)
        .build();
    
        BioimpedanceRequest request = BioimpedanceRequest.builder()
            .cpfAthlete("999.999.999-99")
            .weight(108.5)
            .height(185.5)
            .bodyFatPercentage(15)
            .bodyLeanMassPercentage(10)
            .imc(50.0)
            .leanMass(500.0)
            .fatMass(85.0)
            .activityLevel(ActivityLevel.MODERATE)
            .build();

                when(
                    athleteClient.findByCpf(
                        request.cpfAthlete(),
                        "token"
                    )
                ).thenReturn(response);


                when(repository.save(any(Bioimpedance.class)))
                    .thenAnswer(invocation -> invocation.getArgument(0));

                        Bioimpedance result = service.create(request, "token");

    // Assert

    assertNotNull(result);

    verify(athleteClient)
            .findByCpf(request.cpfAthlete(), "token");

    verify(repository)
            .save(any(Bioimpedance.class));

        assertNotNull(result);

        assertEquals("999.999.999-99", result.getCpfAhtlete());

        assertEquals(108.5, result.getWeight(), 0.0001);

        assertEquals(185.5, result.getHeight(), 0.0001);

        assertEquals(15.0, result.getBodyFatPercentage(), 0.0001);

        assertEquals(10.0, result.getBodyLeanMassPercentage(), 0.0001);

        assertEquals(
                ActivityLevel.MODERATE,
                result.getActivityLevel()
        );
                
    }

    @Test
    void shouldGetBioById(){
        Bioimpedance bio = new Bioimpedance();
        bio.setId("123456789");

         when(repository.findById("123456789"))
                .thenReturn(Optional.of(bio));

         Bioimpedance result = service.getBioById("123456789");

         assertNotNull(result);

         assertEquals(
                 "123456789",
                 result.getId()
         );
         verify(repository).findById("123456789");

    }

    @Test
    void shouldThrowExceptionWhenBioNotFound() {

        when(repository.findById("123"))
                .thenReturn(Optional.empty());

        RuntimeException exception =
                assertThrows(
                        RuntimeException.class,
                        () -> service.getBioById("123")
                );

        assertEquals(
                "Bioimpedância não econtrada!123",
                exception.getMessage()
        );

        verify(repository)
                .findById("123");
    }


    @Test
    void shouldgetBioByCPF(){

        AthleteSnapshotResponse athlete = AthleteSnapshotResponse.builder()
                .cpf("999.999.999-99")
                .name("Daniel Péricles do Nascimento")
                .age(45)
                .gender("Male")
                .identity("Cisgender")
                .weight(110.5)
                .height(185.5)
                .build();
        Bioimpedance bio = new Bioimpedance();
        bio.setCpfAhtlete("999.999.999-99");
        List<Bioimpedance> bios = List.of(bio);


        when(athleteClientService.findByCpf("999.999.999-99"))
                .thenReturn(athlete);

        when(repository.findByAthleteCpf("999.999.999-99"))
                .thenReturn(bios);

        List<Bioimpedance> result =
                service.getBioByCpf("999.999.999-99");

        assertNotNull(result);

        assertEquals(1, result.size());
        assertEquals(
                "999.999.999-99",
                result.get(0).getCpfAhtlete()
        );

        verify(athleteClientService).findByCpf("999.999.999-99");
        verify(repository).findByAthleteCpf("999.999.999-99");

    }

    @Test
    void shouldThrowExceptionWhenAthleteIsNull() {

        when(
                athleteClientService.findByCpf(
                        "999.999.999-99"
                )
        ).thenReturn(null);

        RuntimeException exception =
                assertThrows(
                        RuntimeException.class,
                        () -> service.getBioByCpf(
                                "999.999.999-99"
                        )
                );

        assertEquals(
                "Athlete not found",
                exception.getMessage()
        );

        verify(athleteClientService)
                .findByCpf("999.999.999-99");

        verify(repository, never())
                .findByAthleteCpf(any());
    }

    @Test
    void shouldPatch(){

        AthleteSnapshot athleteSnapshot = new AthleteSnapshot(
                "Daniel Pericles do Nascimento",
                "999.999.999-99",
                45,
                "Male",
                "Cisgender",
                108.5,
                185.5
        );


        LocalDateTime now = LocalDateTime.now();
        Bioimpedance bio_ = new Bioimpedance();
        bio_.setId("1");
        bio_.setCpfAhtlete("999.999.999-99");
        bio_.setAthlete(athleteSnapshot);
        bio_.setWeight(108.5);
        bio_.setHeight(185.5);
        bio_.setBodyFatPercentage(18);
        bio_.setBodyLeanMassPercentage(82);
        bio_.setImc(30.174516794895855);
        bio_.setLeanMass(82.41000000000001);
        bio_.setFatMass(18.99);
        bio_.setTmb(2000.625);
        bio_.setGet(3801.1875);
        bio_.setActivityLevel(ActivityLevel.MODERATE);
        bio_.setAvaliationDate(now);

        Bioimpedance patchBio = new Bioimpedance();
        patchBio.setWeight(104.00);
        patchBio.setBodyFatPercentage(12);
        patchBio.setBodyLeanMassPercentage(88);


        when(repository.findById("1"))
                .thenReturn(Optional.of(bio_));

        when(repository.save(any(Bioimpedance.class)))
                .thenReturn(bio_);

        Bioimpedance result = service.patch("1", new Bioimpedance(
                "1",
                "999.999.999-99",
                athleteSnapshot,
                104.00,
                185.5,
                12,
                88,
                30.223552575177457,
                82.41000000000001,
                18.09,
                2000.625,
                3801.1875,
                ActivityLevel.MODERATE,
                now
        ));

        assertNotNull(result);
        assertEquals("1", result.getId());
        assertEquals("999.999.999-99", result.getCpfAhtlete());
        assertEquals(athleteSnapshot, result.getAthlete());
        assertEquals(104.00, result.getWeight());
        assertEquals(185.5, result.getHeight());
        assertEquals(12, result.getBodyFatPercentage());
        assertEquals(88, result.getBodyLeanMassPercentage());
        assertEquals(30.223552575177457, result.getImc());
        assertEquals(91.52, result.getLeanMass());
        assertEquals(12.48, result.getFatMass());
        assertEquals(2054.375, result.getTmb());
        assertEquals(3184.28125, result.getGet());
        assertEquals(ActivityLevel.MODERATE, result.getActivityLevel());
        assertEquals(now, result.getAvaliationDate());
    }



   @ParameterizedTest
   @CsvSource({
           "SEDENTARY,1.2",
           "LIGHT,1.375",
           "MODERATE,1.55",
           "INTENSE,1.725",
           "ATHLETE,1.9"
   })

   void shouldCalculateGetForAllActivityLevels(
           ActivityLevel level,
           Double factor) {

       // Arrange

       AthleteSnapshot athleteSnapshot = new AthleteSnapshot(
               "Daniel Pericles do Nascimento",
               "999.999.999-99",
               45,
               "Male",
               "Cisgender",
               108.5,
               185.5
       );

       Bioimpedance bio = new Bioimpedance();

       bio.setId("1");
       bio.setCpfAhtlete("999.999.999-99");
       bio.setAthlete(athleteSnapshot);
       bio.setWeight(108.5);
       bio.setHeight(185.5);
       bio.setBodyFatPercentage(18);
       bio.setBodyLeanMassPercentage(82);

       // IMPORTANTE
       bio.setActivityLevel(level);

       // valores necessários para o cálculo
       bio.setTmb(2000.625);

       when(repository.findById("1"))
               .thenReturn(Optional.of(bio));

       when(repository.save(any(Bioimpedance.class)))
               .thenAnswer(invocation -> invocation.getArgument(0));

       Bioimpedance patchBio = new Bioimpedance();
       patchBio.setWeight(104.0);

       // Act

       Bioimpedance result = service.patch("1", patchBio);

       // Assert

       assertEquals(level, result.getActivityLevel());

       assertEquals(
               result.getTmb() * factor,
               result.getGet(),
               0.001
       );
   }


}

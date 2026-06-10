package com.trainday.health_service.application.service;

import com.trainday.health_service.api.DTO.Request.ClinicalAnalysisRequest;
import com.trainday.health_service.api.DTO.Response.AthleteSnapshotResponse;
import com.trainday.health_service.aplication.service.ClinicalAnalysisService;

import com.trainday.health_service.domain.models.ClinicalAnalysis;
import com.trainday.health_service.domain.models.enums.AnalysisStatus;
import com.trainday.health_service.domain.repository.ClinicalAnalysisRepository;
import com.trainday.health_service.infra.client.AthleteClient;

import com.trainday.health_service.infra.client.AthleteClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import java.util.List;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClinicalAnalysisServiceTest {
    @Mock
    private AthleteClient athleteClient;

    @Mock
    private ClinicalAnalysisRepository repository;

    @Mock
    private AthleteClientService athleteclientService;

    @InjectMocks
    private ClinicalAnalysisService service;

    LocalDateTime now =  LocalDateTime.now();

    @Test
    void shouldCreateAnalysisClinical(){
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

        ClinicalAnalysisRequest request = ClinicalAnalysisRequest.builder()
                .athleteCpf("999.999.999-99")
                // Hemograma
                .hemoglobin(15.2)
                .hematocrit(45.8)
                .redBloodCells(5.1)
                .whiteBloodCells(7.2)
                .platelets(250.0)

                // Glicemia
                .fastingGlucose(88.0)
                .hba1c(5.4)

                // Hormônios
                .totalTestosterone(650.0)
                .freeTestosterone(18.5)
                .cortisol(14.2)
                .tsh(2.1)
                .t4(1.3)

                // Perfil lipídico
                .totalCholesterol(180.0)
                .hdl(55.0)
                .ldl(105.0)
                .triglycerides(95.0)

                // Função renal e hepática
                .creatinine(1.0)
                .tgo(28.0)
                .tgp(32.0)

                // Vitaminas e minerais
                .vitaminD(42.5)
                .vitaminB12(550.0)
                .iron(95.0)
                .ferritin(120.0)

                // Proteínas
                .totalProtein(7.1)
                .albumin(4.5)
                .creatineKinase(180.0)

                // Informações médicas
                .doctorName("Dr. João Silva")
                .doctorCRM("CRM/SP 123456")
                .observations("Exames dentro dos parâmetros esperados para atleta recreativo.")
                .analysisDate(now)
                .status(AnalysisStatus.COMPLETED)

                .build();

            when(
                    athleteClient.findByCpf(
                            request.athleteCpf(),
                            "token"
                    )

            ).thenReturn(response);


        when(repository.save(any(ClinicalAnalysis.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ClinicalAnalysis result = service.createAnalysisClinical(request, "token");

        assertNotNull(result);
        //CPFAthleta

       assertEquals("999.999.999-99", result.getCpfAhtlete());

        //Hemograma
       assertEquals(15.2, result.getHemoglobin(), 0.001);
       assertEquals(45.8, result.getHematocrit(), 0.001);
       assertEquals(5.1, result.getRedBloodCells(), 0.001);
       assertEquals(7.2, result.getWhiteBloodCells(), 0.001);
       assertEquals(250.0, result.getPlatelets(), 0.001);

       //Glicemia
        assertEquals(88.0, result.getFastingGlucose(), 0.001);
        assertEquals(5.4, result.getHba1c(), 0.001);

        //Hormônios
        assertEquals(650.0, result.getTotalTestosterone(), 0.001);
        assertEquals(18.5, result.getFreeTestosterone(), 0.001);
        assertEquals(14.2, result.getCortisol());
        assertEquals(2.1, result.getTsh());
        assertEquals(1.3, result.getT4());

        //Perfil lipídico
        assertEquals(180.0, result.getTotalCholesterol(), 0.001);
        assertEquals(55.0, result.getHdl());
        assertEquals(105.0, result.getLdl());
        assertEquals(95.0, result.getTriglycerides());

        //Função renal e hepática
        assertEquals(1.0, result.getCreatinine());
        assertEquals(28.0, result.getTgo());
        assertEquals(32.0, result.getTgp());

        //Vitaminas e Minerais
        assertEquals(42.5, result.getVitaminD(), 0.001);
        assertEquals(550.0, result.getVitaminB12(), 0.001);
        assertEquals(95.0, result.getIron(), 0.001);
        assertEquals(120.0, result.getFerritin());

        //Proteínas
        assertEquals(7.1, result.getTotalProtein());
        assertEquals(4.5, result.getAlbumin());
        assertEquals(180.0, result.getCreatineKinase());

        //Doctor
        assertEquals("Dr. João Silva", result.getDoctorName());
        assertEquals("CRM/SP 123456", result.getDoctorCRM());
        assertEquals("Exames dentro dos parâmetros esperados para atleta recreativo.", result.getObservations());
        assertEquals(now, result.getAnalysisDate());
        assertEquals(AnalysisStatus.COMPLETED, result.getStatus());

    }

    @Test
    void shouldGetAnalysisClinical(){
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

        ClinicalAnalysis analysis = new ClinicalAnalysis();
            //Atleta
            analysis.setId("1");
            analysis.setCpfAhtlete("999.999.999-99");
            //Hemograma
            analysis.setHemoglobin(15.2);
            analysis.setHematocrit(45.8);
            analysis.setRedBloodCells(5.1);
            analysis.setWhiteBloodCells(7.2);
            analysis.setPlatelets(250.0);

            //Glicemia
            analysis.setFastingGlucose(88.0);
            analysis.setHba1c(5.4);

            //Hormônios
            analysis.setTotalTestosterone(650.0);
            analysis.setFreeTestosterone(18.5);
            analysis.setCortisol(14.2);
            analysis.setTsh(2.1);
            analysis.setT4(1.3);

            // Perfil lipídico
            analysis.setTotalCholesterol(180.0);
            analysis.setHdl(55.0);
            analysis.setLdl(105.0);
            analysis.setTriglycerides(95.0);

            // Função renal e hepática
            analysis.setCreatinine(1.0);
            analysis.setTgo(28.0);
            analysis.setTgp(32.0);

            // Vitaminas e minerais
            analysis.setVitaminD(42.5);
            analysis.setVitaminB12(550.0);
            analysis.setIron(95.0);
            analysis.setFerritin(120.0);

            // Proteínas
            analysis.setTotalProtein(7.1);
            analysis.setAlbumin(4.5);
            analysis.setCreatineKinase(180.0);

            // Informações médicas
            analysis.setDoctorName("Dr. João Silva");
            analysis.setDoctorCRM("CRM/SP 123456");
            analysis.setObservations("Exames dentro dos parâmetros esperados para atleta recreativo.");
            analysis.setAnalysisDate(now);
            analysis.setStatus(AnalysisStatus.COMPLETED);



            when(repository.findByAthleteCpf("999.999.999-99"))
                .thenReturn(List.of(analysis));

            System.out.println(service);
            System.out.println(athleteclientService);
            when(athleteclientService.findByCpf("999.999.999-99"))
                    .thenReturn(response);
            List<ClinicalAnalysis> result = service.getAnalysisClinical("999.999.999-99");
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals("999.999.999-99", result.get(0).getCpfAhtlete());

    }

    @Test
    void shouldThrowExceptionWhenAnalysisNotFound(){
        AthleteSnapshotResponse response = AthleteSnapshotResponse.builder()
                .cpf(null)
                .build();

        when(athleteclientService.findByCpf("123.456.789-00"))
                .thenReturn(response);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.getAnalysisClinical("123.456.789-00"));
        assertEquals("Athlete not found!", exception.getMessage());

        verify(athleteclientService)
                .findByCpf("123.456.789-00");



    }


}

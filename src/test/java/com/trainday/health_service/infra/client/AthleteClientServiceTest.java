package com.trainday.health_service.infra.client;


import com.trainday.health_service.api.DTO.Response.AthleteSnapshotResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AthleteClientServiceTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    AthleteClientService service;

    @Test
    void shouldAfindByCpf(){

            AthleteSnapshotResponse response = AthleteSnapshotResponse.builder()
                    .cpf("999.999.999-99")
                    .build();

            when(restTemplate.getForObject(
                    "http://localhost:8080/athlete/cpf/999.999.999-99",
                    AthleteSnapshotResponse.class))
                    .thenReturn(response);
        AthleteSnapshotResponse result = service.findByCpf("999.999.999-99");

        assertNotNull(result);
        assertEquals("999.999.999-99", result.cpf());


    }







}

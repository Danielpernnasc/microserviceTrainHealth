package com.trainday.health_service.infra.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.trainday.health_service.api.DTO.Response.AthleteSnapshotResponse;


@Service
public class AthleteClientService {
 
    private final RestTemplate restTemplate;

    public AthleteClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AthleteSnapshotResponse findByCpf(String cpf){
        return restTemplate.getForObject(
            "http://localhost:8080/athlete/cpf/" + cpf,
            AthleteSnapshotResponse.class
        );
    }

    
}

package com.trainday.health_service.infra.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.trainday.health_service.api.DTO.Response.AthleteSnapshotResponse;

@FeignClient(
    name = "athlete-service",
    url = "http://localhost:8080"
)

public interface AthleteClient {

        @GetMapping("/athlete/cpf/{cpf}")
        AthleteSnapshotResponse findByCpf(
            @PathVariable String cpf,
            @RequestHeader("Authorization") String token);

      
    

}

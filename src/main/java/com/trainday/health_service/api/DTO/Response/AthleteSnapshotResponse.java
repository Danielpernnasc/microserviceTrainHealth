package com.trainday.health_service.api.DTO.Response;

public record AthleteSnapshotResponse(
    String cpf,
    String name,
    String email,
    Integer age,
    String gender,
    String identity,
    Double height,
    Double weight
) {
   
}

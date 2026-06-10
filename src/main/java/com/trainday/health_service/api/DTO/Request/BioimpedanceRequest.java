package com.trainday.health_service.api.DTO.Request;

import com.trainday.health_service.domain.models.enums.ActivityLevel;

import lombok.Builder;

@Builder
public record BioimpedanceRequest(
    String  cpfAthlete,
    Double weight,
    Double height, 
    Integer bodyFatPercentage,
    Integer bodyLeanMassPercentage,
    Double imc,
    Double leanMass,  
    Double fatMass,
    ActivityLevel activityLevel
) {
}


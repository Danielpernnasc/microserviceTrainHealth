package com.trainday.health_service.api.DTO.Response;

import java.time.LocalDateTime;

import com.trainday.health_service.domain.models.enums.ActivityLevel;

public record BioimpedanceResponse(
    String  athleteId,
    Double weight,
    Double height, 
    Integer bodyFatPercentage,
    Integer bodyLeanMassPercentage,
    Double imc,
    Double leanMass,  
    Double tmb,       
    Double get,
    ActivityLevel activityLevel,
    LocalDateTime avaliationDate      
) {
  
}


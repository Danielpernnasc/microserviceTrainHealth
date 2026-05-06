package com.trainday.health_service.api.DTO.Request;

import java.time.LocalDateTime;

import com.trainday.health_service.domain.models.enums.ActivityLevel;

public record BioimpedanceRequest(
    String  athleteId,
    Double weight,
    Double height, 
    Double bodyFatPercentage,
    Double bodyLeanMassPercentage,
    Double imc,
    Double leanMass,  
    ActivityLevel activityLevel
) {}

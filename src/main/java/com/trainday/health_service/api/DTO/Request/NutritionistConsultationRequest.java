package com.trainday.health_service.api.DTO.Request;

import java.time.LocalDateTime;

import com.trainday.health_service.domain.models.enums.ConsultationStatus;

public record NutritionistConsultationRequest(
    String athleteId,
     String nutritionistName,
     String nutritionistCRN,
     String observations,
     Double dailyCalorieGoal,
     Double proteinGoal,
     Double carbGoal,
     Double fatGoal,
     String dietDescription,
     String bioimpedanceId,
     ConsultationStatus status,
     LocalDateTime consultationDate
) {
  
}

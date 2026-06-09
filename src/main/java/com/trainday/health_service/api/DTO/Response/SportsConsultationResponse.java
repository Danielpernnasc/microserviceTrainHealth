package com.trainday.health_service.api.DTO.Response;

import java.time.LocalDateTime;

import com.trainday.health_service.domain.models.enums.ConsultationStatus;

public record SportsConsultationResponse(
     String athleteId,
     String educatorName,
     String educatorCREF,
     String observations,
     String trainServiceId,
     String trainName,
     ConsultationStatus status,
     LocalDateTime nextConsultation,
     LocalDateTime consultationDate
) {

}

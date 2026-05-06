package com.trainday.health_service.api.DTO.Request;

import java.time.LocalDateTime;
import java.util.List;

import com.trainday.health_service.domain.models.Prescription;
import com.trainday.health_service.domain.models.enums.ConsultationStatus;

public record MedicConsultationRequest(
     String athleteId,


     String doctorName,
     String doctorCRM,
     String specialty,
     String observations,


     List<Prescription> prescription,

     String clinicalAnalysisId,

     String bioimpedanceId,

     LocalDateTime nextConsultation,
    
     LocalDateTime consultationDate,
    
     ConsultationStatus  status
) {

}

package com.trainday.health_service.api.DTO.Request;

import java.time.LocalDate;

public record PrescriptionRequest(
     String id,           
     String anvisaCode,
     String description,
     LocalDate validity,
     String fileUrl
) {

}

package com.trainday.health_service.api.DTO.Response;

import java.time.LocalDate;

public record PrescriptionResponse(
     String id,           
     String anvisaCode,
     String description,
     LocalDate validity,
     String fileUrl
) {

}

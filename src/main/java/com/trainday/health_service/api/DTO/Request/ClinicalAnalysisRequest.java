package com.trainday.health_service.api.DTO.Request;

import java.time.LocalDateTime;

import com.trainday.health_service.domain.models.enums.AnalysisStatus;
import lombok.Builder;

@Builder
public record ClinicalAnalysisRequest(
    String athleteCpf,
    // Hemograma
     Double hemoglobin,        // g/dL
     Double hematocrit,       // %
     Double redBloodCells,     // milhões/µL
     Double whiteBloodCells,   // mil/µL
     Double platelets,         // mil/µL

    // Glicemia
     Double fastingGlucose,   // mg/dL
     Double hba1c,             // %

    // Hormônios
     Double totalTestosterone, // ng/dL
     Double freeTestosterone,  // pg/mL
     Double cortisol,          // µg/dL
     Double tsh,               // µUI/mL
     Double t4,                // ng/dL

    // Perfil lipídico
     Double totalCholesterol,  // mg/dL
     Double hdl,               // mg/dL
     Double ldl,               // mg/dL
     Double triglycerides,     // mg/dL

    // Função renal e hepática
     Double creatinine,        // mg/dL
     Double tgo,               // U/L
     Double tgp,               // U/L

    // Vitaminas e minerais
     Double vitaminD,          // ng/mL
     Double vitaminB12,        // pg/mL
     Double iron,              // µg/dL
     Double ferritin,          // ng/mL

    // Proteína
     Double totalProtein,      // g/dL
     Double albumin,           // g/dL
     Double creatineKinase,    // U/L (CK — importante pra atletas)

     String doctorName,
     String doctorCRM,
     String observations,
     LocalDateTime analysisDate,
     AnalysisStatus status
) {


}

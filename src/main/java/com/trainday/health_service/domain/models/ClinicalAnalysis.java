package com.trainday.health_service.domain.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.trainday.health_service.domain.models.enums.AnalysisStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "clinical_analysis")
public class ClinicalAnalysis {
     @Id
    private String id;

    private String cpfAhtlete;
    private AthleteSnapshot athlete;

    // Hemograma
    private Double hemoglobin;        // g/dL
    private Double hematocrit;        // %
    private Double redBloodCells;     // milhões/µL
    private Double whiteBloodCells;   // mil/µL
    private Double platelets;         // mil/µL

    // Glicemia
    private Double fastingGlucose;    // mg/dL
    private Double hba1c;             // %

    // Hormônios
    private Double totalTestosterone; // ng/dL
    private Double freeTestosterone;  // pg/mL
    private Double cortisol;          // µg/dL
    private Double tsh;               // µUI/mL
    private Double t4;                // ng/dL

    // Perfil lipídico
    private Double totalCholesterol;  // mg/dL
    private Double hdl;               // mg/dL
    private Double ldl;               // mg/dL
    private Double triglycerides;     // mg/dL

    // Função renal e hepática
    private Double creatinine;        // mg/dL
    private Double tgo;               // U/L
    private Double tgp;               // U/L

    // Vitaminas e minerais
    private Double vitaminD;          // ng/mL
    private Double vitaminB12;        // pg/mL
    private Double iron;              // µg/dL
    private Double ferritin;          // ng/mL

    // Proteína
    private Double totalProtein;      // g/dL
    private Double albumin;           // g/dL
    private Double creatineKinase;    // U/L (CK — importante pra atletas)

    private String doctorName;
    private String doctorCRM;
    private String observations;

    
    @CreatedDate
    private LocalDateTime analysisDate;
    
    private AnalysisStatus status;

 

}

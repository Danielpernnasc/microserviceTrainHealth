package com.trainday.health_service.domain.models;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.trainday.health_service.domain.models.enums.ConsultationStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "medic_consultation")
public class MedicConsultation {

    @Id
    private String id;

    private String athleteId;
    private AthleteSnapshot athlete;

    private String doctorName;
    private String doctorCRM;
    private String specialty;
    private String observations;


    private List<Prescription> prescription;

    private String clinicalAnalysisId;

    private String bioimpedanceId;

    private LocalDateTime nextConsultation;
    
    @CreatedDate
    private LocalDateTime consultationDate;
    
    private ConsultationStatus  status;





    

}

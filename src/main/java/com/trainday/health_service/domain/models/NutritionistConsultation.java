package com.trainday.health_service.domain.models;

import java.time.LocalDateTime;

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
@Document(collection = "nutritionist_consultation")
public class NutritionistConsultation {

    @Id
    private String id;

    private String athleteId;
    private AthleteSnapshot athlete;

    private String nutritionistName;
    private String nutritionistCRN;
    private String observations;


    private Double dailyCalorieGoal;
    private Double proteinGoal;
    private Double carbGoal;
    private Double fatGoal;

    private String dietDescription;

    private String bioimpedanceId;

    private ConsultationStatus status;

    @CreatedDate 
    private LocalDateTime consultationDate;
    

}

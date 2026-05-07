package com.trainday.health_service.domain.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.trainday.health_service.api.DTO.Response.AthleteSnapshotResponse;
import com.trainday.health_service.domain.models.enums.ActivityLevel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "bioimpedance")
public class Bioimpedance {
    @Id
    private String id;

    private String athleteId;
    private AthleteSnapshot athlete;

    private Double weight; //kg;
    private Double height; //cm
    private Double bodyFatPercentage;
    private Double bodyLeanMassPercentage;

    private Double imc;
    private Double leanMass;  // kg
    private Double fatMass;   // kg
    private Double tmb;       // kcal/dia
    private Double get;       // gasto energético total
    private ActivityLevel activityLevel;


    @CreatedDate
    private LocalDateTime avaliationDate;
}

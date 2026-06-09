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
@Document(collection = "sports_consultation")
public class SportsConsultation {

    @Id
    private String id;


    private String athleteId;
    private AthleteSnapshot athlete;

    private String educatorName;
    private String educatorCREF;

    private String observations;

    private String trainServiceId;
    private String trainName;

    private ConsultationStatus status;
    private LocalDateTime nextConsultation;

    @CreatedDate
    private LocalDateTime consultationDate;

    

}

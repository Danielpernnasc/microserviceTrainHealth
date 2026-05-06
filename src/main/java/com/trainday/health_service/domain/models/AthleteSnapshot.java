package com.trainday.health_service.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AthleteSnapshot {
   private String athletaId;
    private String name;
    private Integer age;
    private String gender;
    private String genderIdentity;
    private Double weight;
    private Double height;

}

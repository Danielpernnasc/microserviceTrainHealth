package com.trainday.health_service.domain.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prescription {
    private String id;            
    private String anvisaCode; 
    private String description;
    private LocalDate validity;
    private String fileUrl;

}

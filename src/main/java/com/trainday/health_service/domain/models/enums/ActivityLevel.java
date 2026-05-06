package com.trainday.health_service.domain.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ActivityLevel {
    SEDENTARY("SEDENTÁRIO"),
    LIGHT("LEVE"),
    MODERATE("MODERADO"),
    INTENSE("INTENSO"),
    ATHLETE("ATLETA");


    private final String state;

    ActivityLevel(String state){
        this.state = state;
    }

    @JsonValue
    public String getState() {
        return state;
    }

    @JsonCreator
    public static ActivityLevel forValue(String value){
        for (ActivityLevel al : ActivityLevel.values()){
            if(al.state.equalsIgnoreCase(value)){
                return al;
            }
        }
        throw new IllegalArgumentException("AnalysisStatus invalid: " + value);
    }
    



   

}

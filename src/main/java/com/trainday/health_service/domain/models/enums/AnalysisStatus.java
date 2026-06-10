package com.trainday.health_service.domain.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AnalysisStatus {
    PEDENDING("PENDENTE"),
    COMPLETED("CONCLUÍDO"),
    ALTERED("ALTERADO");

    private final String state;

    AnalysisStatus(String state){
        this.state = state;
    }

    @JsonValue
    public String getState() {
        return state;
    }

    @JsonCreator
    public static AnalysisStatus forValue(String value){
        for (AnalysisStatus as : AnalysisStatus.values()){
            if(as.state.equalsIgnoreCase(value)){
                return as;
            }
        }
        throw new IllegalArgumentException("AnalysisStatus invalid: " + value);
    }

    

}





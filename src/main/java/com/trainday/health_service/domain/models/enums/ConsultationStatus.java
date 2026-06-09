package com.trainday.health_service.domain.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ConsultationStatus {
    SCHEDULE("AGENDA"),
    COMPLETED("CONCLUÍDO"),
    CANCELLED("CANCELADO");

        private final String state;

    ConsultationStatus(String state){
        this.state = state;
    }

    @JsonValue
    public String getState() {
        return state;
    }

    @JsonCreator
    public static ConsultationStatus forValue(String value){
        for (ConsultationStatus as : ConsultationStatus.values()){
            if(as.state.equalsIgnoreCase(value)){
                return as;
            }
        }
        throw new IllegalArgumentException("AnalysisStatus invalid: " + value);
    }

}

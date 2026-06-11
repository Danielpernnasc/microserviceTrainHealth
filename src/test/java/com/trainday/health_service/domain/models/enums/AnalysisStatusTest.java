package com.trainday.health_service.domain.models.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.trainday.health_service.domain.models.enums.AnalysisStatus;

public class AnalysisStatusTest {

    @Test
    void shouldReturnState(){
        String statePendending = AnalysisStatus.PEDENDING.getState();
        String stateCompleted = AnalysisStatus.COMPLETED.getState();
        String stateAltered = AnalysisStatus.ALTERED.getState();

        assertEquals("PENDENTE", statePendending);
        assertEquals("CONCLUÍDO", stateCompleted);
        assertEquals("ALTERADO", stateAltered);
    }

    @Test
    void shouldformValue(){
        AnalysisStatus analysisStatusPendending = AnalysisStatus.forValue("PENDENTE");
        AnalysisStatus analysisStatusCompleted = AnalysisStatus.forValue("CONCLUÍDO");
        AnalysisStatus analysisStatusAltered = AnalysisStatus.forValue("ALTERADO");

        assertEquals(analysisStatusPendending.PEDENDING, analysisStatusPendending);
        assertEquals(analysisStatusCompleted.COMPLETED, analysisStatusCompleted);
        assertEquals(analysisStatusAltered.ALTERED, analysisStatusAltered);
    }

    @Test
    void shouldReturnInvalid(){
        assertThrows(IllegalArgumentException.class, () -> AnalysisStatus.forValue("INVÁLIDO"));
    }

}

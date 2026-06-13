package com.trainday.health_service.domain.models.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ConsultationStatusTest {

    @Test
    void shouldReturnStatusConsultation(){
        String consultationStatusSchedule = ConsultationStatus.SCHEDULE.getState();
        String consultationStatusCompleted = ConsultationStatus.COMPLETED.getState();
        String consultationStatusCancelled = ConsultationStatus.CANCELLED.getState();

        assertEquals("AGENDA", consultationStatusSchedule);
        assertEquals("CONCLUÍDO", consultationStatusCompleted);
        assertEquals("CANCELADO", consultationStatusCancelled);
    }

    @Test
    void shouldReturnformValue(){
        ConsultationStatus consultationStatusSchedule = ConsultationStatus.forValue("AGENDA");
        ConsultationStatus consultationStatusCompleted = ConsultationStatus.forValue("CONCLUÍDO");
        ConsultationStatus consultationStatusCancelled = ConsultationStatus.forValue("CANCELADO");

        assertEquals(consultationStatusSchedule.SCHEDULE, consultationStatusSchedule);
        assertEquals(consultationStatusCompleted.COMPLETED, consultationStatusCompleted);
        assertEquals(consultationStatusCancelled.CANCELLED, consultationStatusCancelled);
    }

    @Test
    void shouldReturnInvalid(){
        assertThrows(IllegalArgumentException.class, () -> ConsultationStatus.forValue("INVÁLIDO"));
    }
 

}

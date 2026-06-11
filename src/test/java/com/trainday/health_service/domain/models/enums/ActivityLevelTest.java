package com.trainday.health_service.domain.models.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.trainday.health_service.domain.models.enums.ActivityLevel;

public class ActivityLevelTest {

    @Test
    void shouldReturnCorrectStates(){
        String stateSedentary = ActivityLevel.SEDENTARY.getState();
        String stateLight = ActivityLevel.LIGHT.getState();
        String stateModerate = ActivityLevel.MODERATE.getState();
        String stateIntense = ActivityLevel.INTENSE.getState();
        String stateAthlete = ActivityLevel.ATHLETE.getState();
        assertEquals("SEDENTÁRIO", stateSedentary);
        assertEquals("LEVE", stateLight);
        assertEquals("MODERADO", stateModerate);
        assertEquals("INTENSO", stateIntense);
        assertEquals("ATLETA", stateAthlete);
    }

    @Test
    void shouldformValue(){
        ActivityLevel activityLevelSedentary = ActivityLevel.forValue("SEDENTÁRIO");
        ActivityLevel activityLevelLight = ActivityLevel.forValue("LEVE");
        ActivityLevel activityLevelModerate = ActivityLevel.forValue("MODERADO");
        ActivityLevel activityLevelIntese = ActivityLevel.forValue("INTENSO");
        ActivityLevel activityLevelAthlete = ActivityLevel.forValue("ATLETA");
        assertEquals(activityLevelSedentary.SEDENTARY, activityLevelSedentary);
        assertEquals(activityLevelLight.LIGHT, activityLevelLight);
        assertEquals(activityLevelModerate.MODERATE, activityLevelModerate);
        assertEquals(activityLevelIntese.INTENSE, activityLevelIntese);
        assertEquals(activityLevelAthlete.ATHLETE, activityLevelAthlete);
    }

    @Test
    void shouldReturnInvalid(){
        assertThrows(
            IllegalArgumentException.class, () -> ActivityLevel.forValue("INVÁLIDO")
        );
    }

  




}

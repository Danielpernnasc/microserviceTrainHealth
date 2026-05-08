package com.trainday.health_service.domain.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.trainday.health_service.domain.models.ClinicalAnalysis;

public interface ClinicalAnalysisRepository extends MongoRepository<ClinicalAnalysis, String> {
        
    List<ClinicalAnalysis> findByAthleteId(String athleteId);
}

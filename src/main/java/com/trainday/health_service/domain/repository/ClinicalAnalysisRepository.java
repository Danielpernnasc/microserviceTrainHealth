package com.trainday.health_service.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.trainday.health_service.domain.models.ClinicalAnalysis;

public interface ClinicalAnalysisRepository extends MongoRepository<ClinicalAnalysis, String> {

}

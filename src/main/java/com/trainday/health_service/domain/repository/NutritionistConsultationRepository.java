package com.trainday.health_service.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.trainday.health_service.domain.models.NutritionistConsultation;

public interface NutritionistConsultationRepository  extends MongoRepository<NutritionistConsultation, String>{

}

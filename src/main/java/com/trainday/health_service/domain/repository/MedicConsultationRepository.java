package com.trainday.health_service.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.trainday.health_service.domain.models.MedicConsultation;

public interface MedicConsultationRepository extends MongoRepository<MedicConsultation, String> {

}

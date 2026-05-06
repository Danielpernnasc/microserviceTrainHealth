package com.trainday.health_service.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.trainday.health_service.domain.models.SportsConsultation;

public interface SportsConsultationRepository extends MongoRepository<SportsConsultation, String> {

}

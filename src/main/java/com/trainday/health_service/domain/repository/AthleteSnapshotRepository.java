package com.trainday.health_service.domain.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.trainday.health_service.domain.models.AthleteSnapshot;


public interface AthleteSnapshotRepository extends MongoRepository<AthleteSnapshot, String>{
    Optional<AthleteSnapshot> findByCpf(String cpfAhtlete);
}

package com.trainday.health_service.domain.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.trainday.health_service.domain.models.Bioimpedance;

public interface BioimpedanceRepository extends MongoRepository<Bioimpedance, String> {
        List<Bioimpedance> findByAthleteCpf(String cpfAthlete);

}

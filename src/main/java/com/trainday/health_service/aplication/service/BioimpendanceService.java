package com.trainday.health_service.aplication.service;

import org.springframework.stereotype.Service;

import com.trainday.health_service.api.DTO.Request.BioimpedanceRequest;
import com.trainday.health_service.api.DTO.Response.BioimpedanceResponse;
import com.trainday.health_service.domain.models.Bioimpedance;
import com.trainday.health_service.domain.models.enums.ActivityLevel;
import com.trainday.health_service.domain.repository.BioimpedanceRepository;

@Service
public class BioimpendanceService {

    private final BioimpedanceRepository repository;
    private static final String Bio_not_found = "Biopedância não econtrada!";

    public BioimpendanceService(BioimpedanceRepository repository){
        this.repository = repository;
    }

    public Bioimpedance create(BioimpedanceRequest req, String AthleteId){

        Bioimpedance bio = new Bioimpedance();
        bio.setAthleteId(req.athleteId());
        bio.setWeight(req.weight());
        bio.setHeight(req.height());
        bio.setBodyFatPercentage(req.bodyFatPercentage());
        bio.setBodyLeanMassPercentage(req.bodyLeanMassPercentage());
        bio.setActivityLevel(req.activityLevel());
        calculateAll(bio);

        return repository.save(bio);
    }

    public BioimpedanceResponse getBioById(String id){
       Bioimpedance bio = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(Bio_not_found  + id));

        return new BioimpedanceResponse(
            bio.getAthleteId(),
            bio.getWeight(),
            bio.getHeight(),
            bio.getFatMass(),
            bio.getLeanMass(),
            bio.getImc(),
            bio.getLeanMass(),
            bio.getTmb(),
            bio.getGet(),
            bio.getActivityLevel(),
            bio.getAvaliationDate()
        );
    }

    public Bioimpedance patch(String id, Bioimpedance req){
       Bioimpedance bio = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(Bio_not_found));

        if(req.getWeight() != null){
            bio.setWeight(req.getWeight());
        }

        if(req.getHeight() != null){
            bio.setHeight(req.getHeight());
        }

        if(req.getBodyFatPercentage() != null){
            bio.setBodyFatPercentage(req.getBodyFatPercentage());
        }

        if(req.getBodyLeanMassPercentage() != null){
            bio.setBodyLeanMassPercentage(req.getBodyLeanMassPercentage());
        }
        calculateAll(bio); 
        return repository.save(bio);
    }

    public Bioimpedance update(String id, BioimpedanceRequest req){
        Bioimpedance bio = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(Bio_not_found + id));
        bio.setAthleteId(req.athleteId());
        bio.setWeight(req.weight());
        bio.setHeight(req.height());
        bio.setBodyFatPercentage(req.bodyFatPercentage());
        bio.setBodyLeanMassPercentage(req.bodyLeanMassPercentage());
        bio.setActivityLevel(req.activityLevel());
        calculateAll(bio);
        return repository.save(bio);
    }

    private void calculateAll(Bioimpedance bio){
        bio.setImc(calcImc(bio.getWeight(), bio.getHeight()));
        bio.setLeanMass(calcLeanMass(bio.getWeight(), bio.getBodyFatPercentage()));
        bio.setFatMass(calcFatMass(bio.getWeight(), bio.getBodyFatPercentage()));
        bio.setTmb(calcTmb(bio.getWeight(), bio.getHeight()));
        bio.setGet(calcGet(bio.getTmb(), bio.getActivityLevel()));



    }

    private Double calcImc(Double weight, Double height){
        double heightMeters = height / 100;
        return weight / (heightMeters * heightMeters);
    }

    private Double calcLeanMass(Double weight, Double fatPercentage){
        return weight * (1 - fatPercentage /100);
    }

    private Double calcFatMass(Double weight, Double fatPercentage){
        return weight * (fatPercentage / 100);
    }

    private Double calcTmb(Double weight, Double height){
        return (10 * weight) + (6.25 * height) - (5 * 30) + 5;
    }

    private Double calcGet(Double tmb, ActivityLevel level){
        return switch (level) {
            case SEDENTARY -> tmb * 1.2;
            case LIGHT -> tmb * 1.375;
            case MODERATE -> tmb * 1.55;
            case INTENSE -> tmb * 1.725;
            case ATHLETE -> tmb * 1.9;
        };
    }

}

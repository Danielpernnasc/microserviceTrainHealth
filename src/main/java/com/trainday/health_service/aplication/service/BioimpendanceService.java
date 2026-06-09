package com.trainday.health_service.aplication.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.trainday.health_service.api.DTO.Request.BioimpedanceRequest;
import com.trainday.health_service.api.DTO.Response.AthleteSnapshotResponse;
import com.trainday.health_service.domain.models.AthleteSnapshot;
import com.trainday.health_service.domain.models.Bioimpedance;
import com.trainday.health_service.domain.models.enums.ActivityLevel;

import com.trainday.health_service.domain.repository.BioimpedanceRepository;
import com.trainday.health_service.infra.client.AthleteClient;
import com.trainday.health_service.infra.client.AthleteClientService;

@Service
public class BioimpendanceService {

    private final BioimpedanceRepository repository;
    private final AthleteClient athleteClient;
    private final AthleteClientService athleteclientService;
    private static final String Bio_not_found = "Biopedância não econtrada!";

    public BioimpendanceService(
        BioimpedanceRepository repository, 
        AthleteClient athleteClient,
        AthleteClientService athleteClientService){
        this.repository = repository;
        this.athleteclientService = athleteClientService;
        this.athleteClient = athleteClient;

    }

   

    public Bioimpedance create(BioimpedanceRequest req, String token){
        AthleteSnapshotResponse athlete =
            athleteClient.findByCpf(
                    req.cpfAhtlete(),
                    token
            );

        AthleteSnapshot athleteSnapshot = new AthleteSnapshot();
        athleteSnapshot.setCpf(athlete.cpf());
        athleteSnapshot.setName(athlete.name());
        athleteSnapshot.setAge(athlete.age());
        athleteSnapshot.setGender(athlete.gender());
        athleteSnapshot.setGenderIdentity(athlete.identity());
        athleteSnapshot.setWeight(athlete.weight());
        athleteSnapshot.setHeight(athlete.height());
      

        Bioimpedance bio = new Bioimpedance();
        bio.setCpfAhtlete(req.cpfAhtlete());
        bio.setAthlete(athleteSnapshot);
        bio.setWeight(req.weight());
        bio.setHeight(req.height());
        bio.setBodyFatPercentage(req.bodyFatPercentage());
        bio.setBodyLeanMassPercentage(req.bodyLeanMassPercentage());
        bio.setActivityLevel(req.activityLevel());
        
        calculateAll(bio);

        return repository.save(bio);
    }



    public Bioimpedance getBioById(String id){
       return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(Bio_not_found  + id));
    }

    public List<Bioimpedance> getBioByCpf(String cpf){
  
        
     AthleteSnapshotResponse athlete =
        athleteclientService.findByCpf(cpf);

      if (athlete == null || athlete.cpf() == null) {
        throw new RuntimeException("Athlete not found");
    }

    return repository.findByAthleteCpf(athlete.cpf());
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

        if(req.getFatMass() != null){
            bio.setFatMass(req.getFatMass());
        }
        calculateAll(bio); 
        return repository.save(bio);
    }

    private void calculateAll(Bioimpedance bio){
        System.out.println("BODY FAT: " + bio.getBodyFatPercentage() + "%");
        System.out.println("WEIGHT: " + bio.getWeight());

        bio.setImc(calcImc(bio.getWeight(), bio.getHeight()));
        if(bio.getBodyFatPercentage() != null 
            && bio.getBodyFatPercentage() > 0) {
                bio.setLeanMass(
                    calcLeanMass(
                        bio.getWeight(),
                        bio.getBodyFatPercentage()
                    )
                );
                bio.setFatMass(
                    calcFatMass(
                        bio.getWeight(),
                        bio.getBodyFatPercentage()
                    )
                );
            }

         bio.setTmb(calcTmb(bio.getWeight(),bio.getHeight()));
         bio.setGet(calcGet(bio.getTmb(),bio.getActivityLevel()));

    }

    private Double calcImc(Double weight, Double height){
        double heightMeters = height / 100;
        return weight / (heightMeters * heightMeters);
    }

    private Double calcLeanMass(Double weight, Integer fatPercentage){
        return weight * (1 - (fatPercentage / 100.0));
    }

    private Double calcFatMass(Double weight, Integer fatPercentage){
        return weight * (fatPercentage / 100.0);
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

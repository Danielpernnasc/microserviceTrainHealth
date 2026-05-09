package com.trainday.health_service.aplication.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.trainday.health_service.api.DTO.Request.ClinicalAnalysisRequest;
import com.trainday.health_service.api.DTO.Response.AthleteSnapshotResponse;
import com.trainday.health_service.domain.models.AthleteSnapshot;
import com.trainday.health_service.domain.models.ClinicalAnalysis;
import com.trainday.health_service.domain.repository.AthleteSnapshotRepository;
import com.trainday.health_service.domain.repository.ClinicalAnalysisRepository;
import com.trainday.health_service.infra.client.AthleteClient;

@Service
public class ClinicalAnalysisService {

    private final ClinicalAnalysisRepository repository;
    private final AthleteClient athleteClient;
    private final AthleteSnapshotRepository athleteRepository;
    private static final String AnalysisClinical_not_found = "Análise Clinicas não econtrada!";

    public ClinicalAnalysisService(
        ClinicalAnalysisRepository repository, 
        AthleteSnapshotRepository athleteRepository,
        AthleteClient athleteClient){
        this.repository = repository;
        this.athleteRepository = athleteRepository;
        this.athleteClient = athleteClient;
     
    }


    public ClinicalAnalysis createAnalysisClinical(ClinicalAnalysisRequest req, String token){
       
        AthleteSnapshotResponse athlete =
                    athleteClient.findById(
                            req.athleteId(),
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

        ClinicalAnalysis clinical = new ClinicalAnalysis();
        clinical.setAthleteId(req.athleteId());
        clinical.setAthlete(athleteSnapshot);
        clinical.setHemoglobin(req.hemoglobin());
        clinical.setHematocrit(req.hematocrit());
        clinical.setRedBloodCells(req.redBloodCells());
        clinical.setWhiteBloodCells(req.whiteBloodCells());
        clinical.setPlatelets(req.platelets());
        clinical.setFastingGlucose((req.fastingGlucose()));
        clinical.setHba1c(req.hba1c());
        clinical.setTotalTestosterone(req.totalTestosterone());
        clinical.setFreeTestosterone(req.freeTestosterone());
        clinical.setCortisol(req.cortisol());
        clinical.setTsh(req.tsh());
        clinical.setT4(req.t4());
        clinical.setTotalCholesterol(req.totalCholesterol());
        clinical.setHdl(req.hdl());
        clinical.setLdl(req.ldl());
        clinical.setTriglycerides(req.triglycerides());
        clinical.setCreatinine(req.creatinine());
        clinical.setTgo(req.tgo());
        clinical.setTgp(req.tgp());
        clinical.setVitaminD(req.vitaminD());
        clinical.setVitaminB12(req.vitaminB12());
        clinical.setIron(req.iron());
        clinical.setFerritin(req.ferritin());
        clinical.setTotalProtein(req.totalProtein());
        clinical.setAlbumin(req.albumin());
        clinical.setCreatineKinase(req.creatineKinase());
        clinical.setDoctorName(req.doctorName());
        clinical.setDoctorCRM(req.doctorCRM());
        clinical.setObservations(req.observations());
        clinical.setStatus(req.status());


        return repository.save(clinical);
    }

    public ClinicalAnalysis getAnalysisClinicalById(String id){
        return repository.findById(id)
                  .orElseThrow(() -> new RuntimeException(AnalysisClinical_not_found  + id));
    }


    public List<ClinicalAnalysis> getAnalysisClinical(String athleteId){
        return repository.findByAthleteId(athleteId);
              
    }

    public List<ClinicalAnalysis> getBioByCpf(String cpf){
        AthleteSnapshot athlete = athleteRepository.findByCpf(cpf)
             .orElseThrow(() -> new RuntimeException("Athlete not found"));
        return repository.findByAthleteId(athlete.getCpf());
    }

}

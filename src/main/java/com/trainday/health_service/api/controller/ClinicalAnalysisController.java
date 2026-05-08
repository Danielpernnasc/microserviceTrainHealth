package com.trainday.health_service.api.controller;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.trainday.health_service.api.DTO.Request.ClinicalAnalysisRequest;
import com.trainday.health_service.aplication.service.BioimpendanceService;
import com.trainday.health_service.aplication.service.ClinicalAnalysisService;
import com.trainday.health_service.domain.models.ClinicalAnalysis;
import com.trainday.health_service.infra.security.JwtService;

import org.slf4j.LoggerFactory;

import java.util.List;

import org.slf4j.Logger;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/clinicalAnalysis")
@SecurityRequirement(name = "bearerAuth")
public class ClinicalAnalysisController {

    private final ClinicalAnalysisService service;
    private final JwtService jwtService;
    private static final Logger log = LoggerFactory.getLogger(BioimpendanceService.class);
    
    public ClinicalAnalysisController(
        ClinicalAnalysisService service,
        JwtService jwtService
    ){
        this.service = service;
        this.jwtService = jwtService;
    }


    @PostMapping
    public ResponseEntity<ClinicalAnalysis> createAnalysisClinical(
        @RequestBody ClinicalAnalysisRequest req,
        @RequestHeader("Authorization") String authHeader
    ){
        log.info("Receive request to create analysis clinical: {}", req);
        String token = authHeader.substring(7);
        String athleteId = jwtService.extractEmail(token);
        ClinicalAnalysis createClinicalAnalysis = service.createAnalysisClinical(req, athleteId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createClinicalAnalysis);

    }

    @GetMapping("/id")
    public ResponseEntity<ClinicalAnalysis> getAnalysisClinicalbyId(
         @PathVariable String id
    ){
        return ResponseEntity.ok(service.getAnalysisClinicalById(id));
    }

    @GetMapping("/analysisClinicas/athlete/{athleteId}")
    public ResponseEntity<List<ClinicalAnalysis>> getAnalysisClinical(
         @PathVariable String athleteId
    ){
        return ResponseEntity.ok(
            service.getAnalysisClinical(athleteId)
        );
    }
    
    


}

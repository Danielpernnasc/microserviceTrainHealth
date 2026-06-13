package com.trainday.health_service.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trainday.health_service.api.DTO.Request.BioimpedanceRequest;
import com.trainday.health_service.aplication.service.BioimpendanceService;
import com.trainday.health_service.domain.models.Bioimpedance;
import com.trainday.health_service.infra.security.JwtService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.slf4j.LoggerFactory;

import java.util.List;

import org.slf4j.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;




@RestController
@RequestMapping("/bioimpedance")
@SecurityRequirement(name = "bearerAuth")
public class BioimpedanceController {

    private final BioimpendanceService service;
    private final JwtService jwtService;

    private static final Logger log = LoggerFactory.getLogger(BioimpendanceService.class);
    
    public BioimpedanceController(
        BioimpendanceService service,
        JwtService jwtService
    ){
        this.service = service;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<Bioimpedance> save(
        @RequestBody BioimpedanceRequest req,
        @RequestHeader("Authorization") String authHeader) {
            log.info("Receive request to create bio: {}", req);
            String token = authHeader.substring(7);
            String athleteId = jwtService.extractEmail(token);
            Bioimpedance creatBioimpedance = service.create(req, athleteId);
               return ResponseEntity.status(HttpStatus.CREATED)
            .body(creatBioimpedance);
    }

    @GetMapping("/cpf/{cpf}")
    public List<Bioimpedance> findByCpf(@PathVariable String cpf) {
        return service.getBioByCpf(cpf);
    }
    

    @PatchMapping("/{id}") 
    public ResponseEntity<Bioimpedance> patchBioimpedance(@PathVariable String id, @RequestBody Bioimpedance req){
        Bioimpedance bio = service.patch(id, req);
        return ResponseEntity.ok(bio);
    }


    


}

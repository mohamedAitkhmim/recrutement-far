package com.far.recrutement.controllers;

import com.far.recrutement.dao.CandidatRepository;
import com.far.recrutement.dao.InscriptionRepository;
import com.far.recrutement.entities.Data;
import com.far.recrutement.metier.Script;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ApiController {
    @Autowired
    InscriptionRepository inscriptionRepository;
    @Autowired
    CandidatRepository candidatRepository;
    @Autowired
    Script batch;

    @GetMapping(value = "/api")
    public List<Data> api(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if (auth == null || !auth.equals("AuthKhalifaBenunesRFAR")) {
            return null;
        }
        return candidatRepository.getStatistics().stream()
                .map(Data::new)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/api/{script}")
    public List<Data> api2(HttpServletRequest request, @PathVariable("script") String script) {
        String auth = request.getHeader("Authorization");
        if (auth == null || !auth.equals("AuthKhalifaBenunesRFAR")) {
            return null;
        }
        if (script.equals("enable")) {
            batch.enableUsersWithEmail();
        }
        if (script.equals("incomplet")) {
            batch.profilIncomplet();
        }
        if (script.equals("inscrit")) {
            batch.inscriptionIncomplet();
        }
        if (script.equals("concours")) {
            batch.updateConcours();
        }
        if (script.equals("convocation")) {
            batch.convocation();
        }
        if (script.equals("convocation3")) {
            batch.convocation3();
        }
        if (script.equals("bulletin")) {
            batch.bulletin();
        }
        if (script.equals("ista")) {
            batch.ista();
        }
        if (script.equals("medecin")) {
            batch.medecin();
        }
        return null;
    }
}

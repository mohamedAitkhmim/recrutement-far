package com.far.recrutement.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.far.recrutement.metier.CandidatMetier;

@RestController
public class CandidatRestController {
	
	@Autowired
	private CandidatMetier candidatMetier;
	
	@RequestMapping("inscription/email")
    public Boolean emailExists(@RequestParam(value="e", defaultValue=" ") String e) {
		return candidatMetier.emailExists(e);
    }

	@RequestMapping("inscription/cnie")
	public Boolean cnieExists(@RequestParam(value="e", defaultValue=" ") String e) {
		return candidatMetier.cnieExists(e);
	}

}

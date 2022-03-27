package com.far.recrutement.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.far.recrutement.dao.SituationRepository;
import com.far.recrutement.models.Situation;

@Service
public class SituationMetier implements ISituationMetier {

	@Autowired
	SituationRepository situationRepository;
	
	@Override
	public Situation addSituation(Situation s) {
		return situationRepository.save(s);
	}

	@Override
	public List<Situation> listSituation() {
		return situationRepository.findAll();
	}

}

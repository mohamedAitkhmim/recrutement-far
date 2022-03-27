package com.far.recrutement.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.far.recrutement.dao.StatutRepository;
import com.far.recrutement.models.Statut;

@Service
public class StatutMetier implements IStatutMetier {

	@Autowired
	StatutRepository statutRepository;
	
	@Override
	public Statut addStatut(Statut s) {
		return statutRepository.save(s);
	}

	@Override
	public List<Statut> listStatut() {
		return statutRepository.findAll();
	}

	@Override
	public Statut getStatutById(Long id) {
		return statutRepository.findById(id).orElse(null);
	}

}

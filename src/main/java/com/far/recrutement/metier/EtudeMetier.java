package com.far.recrutement.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.far.recrutement.dao.EtudeRepository;
import com.far.recrutement.models.Etude;

@Service
public class EtudeMetier implements IEtudeMetier {

	@Autowired
	EtudeRepository etudeRepository;
	@Override
	public Etude addEtude(Etude e) {
		return etudeRepository.save(e);
	}

	@Override
	public List<Etude> listEtude() {
		return etudeRepository.findAll();
	}

}

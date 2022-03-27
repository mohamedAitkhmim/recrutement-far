package com.far.recrutement.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.far.recrutement.dao.SexeRepository;
import com.far.recrutement.models.Sexe;

@Service
public class SexeMetier implements ISexeMetier {

	@Autowired
	SexeRepository sexeRepository;
	
	@Override
	public Sexe addSexe(Sexe s) {
		return sexeRepository.save(s);
	}

	@Override
	public List<Sexe> listSexe() {
		return sexeRepository.findAll();
	}

}

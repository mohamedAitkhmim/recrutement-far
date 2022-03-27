package com.far.recrutement.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.far.recrutement.dao.LangueRepository;
import com.far.recrutement.models.Langue;

@Service
public class LangueMetier implements ILangueMetier {

	@Autowired
	LangueRepository langueRepository;
	
	@Override
	public Langue addLangue(Langue l) {
		return langueRepository.save(l);
	}

	@Override
	public List<Langue> listLangue() {
		return langueRepository.findAll();
	}

}

package com.far.recrutement.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.far.recrutement.dao.MassarRepository;
import com.far.recrutement.models.Massar;

@Service
public class MassarMetier implements IMassarMetier {

	@Autowired
	MassarRepository massarRepository;

	@Override
	public Massar addMassar(Massar m) {
		return massarRepository.save(m);
	}

	@Override
	public List<Massar> listMassar() {
		return massarRepository.findAll();
	}
	@Override
	public List<Massar> addMassars(List<Massar> massars)
	{
		return massarRepository.saveAll(massars);
	}
}

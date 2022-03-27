package com.far.recrutement.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.far.recrutement.dao.EcoleRepository;
import com.far.recrutement.models.Ecole;

@Service
public class EcoleMetier implements IEcoleMetier {

	@Autowired
	EcoleRepository ecoleRepository;
	
	@Override
	public Ecole addEcole(Ecole e) {
		return ecoleRepository.save(e);
	}

	@Override
	public List<Ecole> listEcole() {
		return ecoleRepository.findAll();
	}

}

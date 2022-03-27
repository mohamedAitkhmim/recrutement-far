package com.far.recrutement.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.far.recrutement.dao.VilleRepository;
import com.far.recrutement.models.Ville;

@Service
public class VilleMetier implements IVilleMetier {

	@Autowired
	VilleRepository villeRepository;
	
	@Override
	public Ville addVille(Ville v) {
		return villeRepository.save(v);
	}

	@Override
	public List<Ville> listVille() {
		return villeRepository.findAll();
	}

}

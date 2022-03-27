package com.far.recrutement.metier;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.far.recrutement.dao.NiveauRepository;
import com.far.recrutement.models.Niveau;

@Service
public class NiveauMetier implements INiveauMetier {

	@Autowired
	NiveauRepository niveauRepository;
	
	Logger logger = LoggerFactory.getLogger(NiveauMetier.class);
	
	@Override
	public Niveau addNiveau(Niveau n) {
		return niveauRepository.save(n);
	}

	@Override
	public List<Niveau> listNiveau() {
		return niveauRepository.findAll();
	}

	@Override
	public Niveau getNiveauById(Long id) {
		return niveauRepository.findById(id).orElse(null);
	}

	@Override
	public List<Niveau> listNiveauSup() {
		List<Niveau> ret=new ArrayList<>();
		try {
		List<Niveau> list=listNiveau();
		for(Niveau niveau : list)
		{
			if(niveau.getEtude().getNomEtude().equals("Fac") || niveau.getEtude().getNomEtude().equals("ISTA"))
			{
				ret.add(niveau);
			}
		}
		}
		catch(Exception ex) {
			logger.warn("Problème de niveau "+ex.toString() );
		}
		return ret;
	}

}

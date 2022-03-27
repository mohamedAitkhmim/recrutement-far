package com.far.recrutement.metier;

import java.util.List;

import com.far.recrutement.models.Niveau;

public interface INiveauMetier {
	
	public Niveau addNiveau(Niveau n);
	public List<Niveau> listNiveau();
	public List<Niveau> listNiveauSup();
	public Niveau getNiveauById(Long id);

}

package com.far.recrutement.metier;

import java.util.List;

import com.far.recrutement.models.Ecole;

public interface IEcoleMetier {
	
	public Ecole addEcole(Ecole e);
	public List<Ecole> listEcole();

}

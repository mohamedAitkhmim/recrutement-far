package com.far.recrutement.metier;

import java.util.List;

import com.far.recrutement.models.Etude;

public interface IEtudeMetier {
	
	public Etude addEtude(Etude e);
	public List<Etude> listEtude();

}

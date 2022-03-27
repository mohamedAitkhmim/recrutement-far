package com.far.recrutement.metier;

import java.util.List;

import com.far.recrutement.models.Statut;

public interface IStatutMetier {
	
	public Statut addStatut(Statut s);
	public Statut getStatutById(Long id);
	public List<Statut> listStatut();

}

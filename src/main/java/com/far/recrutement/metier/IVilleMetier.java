package com.far.recrutement.metier;

import java.util.List;

import com.far.recrutement.models.Ville;

public interface IVilleMetier {
	
	public Ville addVille(Ville v);
	public List<Ville> listVille();

}

package com.far.recrutement.metier;

import java.util.List;

import com.far.recrutement.models.Categorie;

public interface ICategorieMetier {
	
	public Categorie addCategorie(Categorie c);
	public Categorie getCategorieById(Long id);
	public List<Categorie> listCategorie();

}

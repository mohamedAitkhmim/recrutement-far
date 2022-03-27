package com.far.recrutement.metier;

import java.util.List;

import com.far.recrutement.models.Langue;

public interface ILangueMetier {
	
	public Langue addLangue(Langue l);
	public List<Langue> listLangue();

}

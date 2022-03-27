package com.far.recrutement.metier;

import java.util.List;

import com.far.recrutement.models.Sexe;

public interface ISexeMetier {
	
	public Sexe addSexe(Sexe s);
	public List<Sexe> listSexe();

}

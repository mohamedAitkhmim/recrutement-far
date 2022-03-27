package com.far.recrutement.metier;

import java.util.List;

import com.far.recrutement.models.Massar;

public interface IMassarMetier {
	
	public Massar addMassar(Massar m);
	public List<Massar> listMassar();
	List<Massar> addMassars(List<Massar> massars);

}

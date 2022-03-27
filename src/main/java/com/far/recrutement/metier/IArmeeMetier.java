package com.far.recrutement.metier;

import java.util.List;

import com.far.recrutement.models.Armee;

public interface IArmeeMetier {
	
	public List<Armee> listArmee();
	public Armee addArmee(Armee armee);

}

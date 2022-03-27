package com.far.recrutement.metier;

import java.util.List;

import com.far.recrutement.models.Situation;

public interface ISituationMetier {
	
	public Situation addSituation(Situation s);
	public List<Situation> listSituation();

}

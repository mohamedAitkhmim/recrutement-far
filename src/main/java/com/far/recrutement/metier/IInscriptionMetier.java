package com.far.recrutement.metier;

import com.far.recrutement.models.Inscription;
import com.far.recrutement.util.Liste;

public interface IInscriptionMetier {
	
	public Inscription addInscription(Inscription i);
	public Liste<Inscription> listInscription();
	public Liste<Inscription> getInscriptionByConcoursId(Long id);
	public Liste<Inscription> getInscriptionByCandidatEmail(String email);
	public Liste<Inscription> getAllInscription();
	public Inscription getInscriptionById(Long id);
	public Boolean inscriptionExists(String email,Long concoursID, Long filiereID);
	public void delete(Long id);
	public Liste<Inscription> listInscriptionAllYears();
	public Liste<Inscription> getAllInscriptionAllYears();
	public Liste<Inscription> getInscriptionByConcoursIdAllYears(Long id);
	public Liste<Inscription> getInscriptionByCandidatEmailAllYears(String email);
}

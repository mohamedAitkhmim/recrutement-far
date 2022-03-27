package com.far.recrutement.models;


import java.io.Serializable;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Situation implements Serializable {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1767400660348650697L;
@Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long situationID;
   private String nomSituation;
   private String nomSituationAr;
   @OneToMany(mappedBy="situation")
   private Collection<Candidat> candidats;
   
   public Situation() {
		super();
	}
	public Situation(String nomSituation,String nomSituationAr) {
		super();
		this.nomSituation = nomSituation;
		this.nomSituationAr = nomSituationAr;
	}
	
	public String getNomSituationAr() {
		return nomSituationAr;
	}
	public void setNomSituationAr(String nomSituationAr) {
		this.nomSituationAr = nomSituationAr;
	}
	public Long getSituationID() {
		return situationID;
	}
	public void setSituationID(Long situationID) {
		this.situationID = situationID;
	}
	public String getNomSituation() {
		return nomSituation;
	}
	public void setNomSituation(String nomSituation) {
		this.nomSituation = nomSituation;
	}
	public Collection<Candidat> getCandidats() {
		return candidats;
	}
	public void setCandidats(Collection<Candidat> candidats) {
		this.candidats = candidats;
	}
	@Override
	public String toString() {
		return  nomSituation;
	}
}
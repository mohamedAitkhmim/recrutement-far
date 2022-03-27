package com.far.recrutement.models;


import java.io.Serializable;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Sexe implements Serializable {
   /**
	 * 
	 */
	private static final long serialVersionUID = 6513516563485107324L;
@Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long sexeID;
   private String nomSexe;
   private String nomSexeAr;
   @OneToMany(mappedBy="sexe")
   private Collection<Candidat> candidats;
   
   public Sexe() {
		super();
	}
	public Sexe(String nomSexe,String nomSexeAr) {
		super();
		this.nomSexeAr = nomSexeAr;
		this.nomSexe = nomSexe;
	}
	
	public String getNomSexeAr() {
		return nomSexeAr;
	}
	public void setNomSexeAr(String nomSexeAr) {
		this.nomSexeAr = nomSexeAr;
	}
	public Long getSexeID() {
		return sexeID;
	}
	public void setSexeID(Long sexeID) {
		this.sexeID = sexeID;
	}
	public String getNomSexe() {
		return nomSexe;
	}
	public void setNomSexe(String nomSexe) {
		this.nomSexe = nomSexe;
	}
	public Collection<Candidat> getCandidats() {
		return candidats;
	}
	public void setCandidats(Collection<Candidat> candidats) {
		this.candidats = candidats;
	}
	@Override
	public String toString() {
		return  nomSexe;
	}
}
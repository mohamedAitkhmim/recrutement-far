package com.far.recrutement.models;


import java.io.Serializable;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Langue implements Serializable {
   /**
	 * 
	 */
	private static final long serialVersionUID = 4483608036804622196L;
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long langueID;
   private String nomLangue;
   private String nomLangueAr;
   @OneToMany(mappedBy="langue")
   private Collection<Candidat> candidats;
   
   public Langue() {
		super();
	}
   public Langue(String nomLangue,String nomLangueAr) {
		super();
		this.nomLangueAr = nomLangueAr;
		this.nomLangue = nomLangue;
	}
   
   public String getNomLangueAr() {
	return nomLangueAr;
   }
   public void setNomLangueAr(String nomLangueAr) {
	this.nomLangueAr = nomLangueAr;
   }
   public Long getLangueID() {
		return langueID;
	}
	public void setLangueID(Long langueID) {
		this.langueID = langueID;
	}
	public String getNomLangue() {
		return nomLangue;
	}
	public void setNomLangue(String nomLangue) {
		this.nomLangue = nomLangue;
	}
	public Collection<Candidat> getCandidats() {
		return candidats;
	}
	public void setCandidats(Collection<Candidat> candidats) {
		this.candidats = candidats;
	}
	@Override
	public String toString() {
		return  nomLangue;
	}
}
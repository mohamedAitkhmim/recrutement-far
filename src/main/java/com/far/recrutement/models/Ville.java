package com.far.recrutement.models;


import java.io.Serializable;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Ville implements Serializable {
   /**
	 * 
	 */
	private static final long serialVersionUID = 2389206327398152016L;
@Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long villeID;
   private String nomVille;
   private String nomVilleAr;
   @JsonIgnore
   @ManyToOne
   private Region region;
   @JsonIgnore
   @OneToMany(mappedBy="ville")
   private Collection<Candidat> candidats;
   
   public Ville() {
		super();
	}
	public Ville(String nomVille,String nomVilleAr, Region region) {
		super();
		this.nomVille = nomVille;
		this.nomVilleAr = nomVilleAr;
		this.region = region;
	}
	
	public String getNomVilleAr() {
		return nomVilleAr;
	}
	public void setNomVilleAr(String nomVilleAr) {
		this.nomVilleAr = nomVilleAr;
	}
	public Long getVilleID() {
		return villeID;
	}
	public void setVilleID(Long villeID) {
		this.villeID = villeID;
	}
	public String getNomVille() {
		return nomVille;
	}
	public void setNomVille(String nomVille) {
		this.nomVille = nomVille;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public Collection<Candidat> getCandidats() {
		return candidats;
	}
	public void setCandidats(Collection<Candidat> candidats) {
		this.candidats = candidats;
	}
	@Override
	public String toString() {
		return  nomVille;
	}
}
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
public class Option implements Serializable {
   /**
	 * 
	 */
	private static final long serialVersionUID = -6534304677786405767L;
@Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long optionID;
   private String nomOption;
   private String nomOptionAr;
   @JsonIgnore
   @OneToMany(mappedBy="option")
   private Collection<Candidat> candidats;
   @JsonIgnore
   @ManyToOne
   private Etude etude;
   
   public Option() {
		super();
	}
	public Option(String nomOption,String nomOptionAr,Etude etude) {
		super();
		this.nomOption = nomOption;
		this.nomOptionAr = nomOptionAr;
		this.etude=etude;
	}
	
	public String getNomOptionAr() {
		return nomOptionAr;
	}
	public void setNomOptionAr(String nomOptionAr) {
		this.nomOptionAr = nomOptionAr;
	}
	public Etude getEtude() {
		return etude;
	}
	public void setEtude(Etude etude) {
		this.etude = etude;
	}
	public Long getOptionID() {
		return optionID;
	}
	public void setOptionID(Long optionID) {
		this.optionID = optionID;
	}
	public String getNomOption() {
		return nomOption;
	}
	public void setNomOption(String nomOption) {
		this.nomOption = nomOption;
	}
	public Collection<Candidat> getCandidats() {
		return candidats;
	}
	public void setCandidats(Collection<Candidat> candidats) {
		this.candidats = candidats;
	}
	@Override
	public String toString()
	{
		return this.nomOption;
	}
}
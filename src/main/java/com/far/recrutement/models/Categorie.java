package com.far.recrutement.models;


import java.io.Serializable;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Categorie implements Serializable {
   /**
	 * 
	 */
	private static final long serialVersionUID = 2465367177916297307L;
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long categorieID;
   private String nomCategorie;
   private String nomCategorieAr;
   @ManyToMany
   private Collection<Ecole> ecoles;
   @OneToMany(mappedBy="categorie")
   private Collection<Concours> concours;
   private boolean bacrequis;
   
   public Categorie() {
		super();
	}
   public Categorie(String nomCategorie,String nomCategorieAr, boolean bacrequis) {
		super();
		this.nomCategorie = nomCategorie;
		this.bacrequis = bacrequis;
		this.nomCategorieAr=nomCategorieAr;
	}
   
   public String getNomCategorieAr() {
	return nomCategorieAr;
   }
   public void setNomCategorieAr(String nomCategorieAr) {
	this.nomCategorieAr = nomCategorieAr;
   }
public Long getCategorieID() {
		return categorieID;
	}
	public void setCategorieID(Long categorieID) {
		this.categorieID = categorieID;
	}
	public String getNomCategorie() {
		return nomCategorie;
	}
	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}
	public Collection<Ecole> getEcoles() {
		return ecoles;
	}
	public void setEcoles(Collection<Ecole> ecoles) {
		this.ecoles = ecoles;
	}
	public Collection<Concours> getConcours() {
		return concours;
	}
	public void setConcours(Collection<Concours> concours) {
		this.concours = concours;
	}
	public boolean isBacrequis() {
		return bacrequis;
	}
	public void setBacrequis(boolean bacrequis) {
		this.bacrequis = bacrequis;
	}
	@Override
	public String toString() {
		return  nomCategorie;
	}
	
}
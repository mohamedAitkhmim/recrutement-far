package com.far.recrutement.models;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Statut implements Serializable {
   /**
	 * 
	 */
	private static final long serialVersionUID = -4936029026125081367L;
	@Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long statutID;
   private String nomStatut;
   private String nomStatutAr;
   
   public Statut() {
		super();
	}
	public Statut(String nomStatut,String nomStatutAr) {
		super();
		this.nomStatut = nomStatut;
		this.nomStatutAr = nomStatutAr;
	}
	
	public String getNomStatutAr() {
		return nomStatutAr;
	}
	public void setNomStatutAr(String nomStatutAr) {
		this.nomStatutAr = nomStatutAr;
	}
	public Long getStatutID() {
		return statutID;
	}
	public void setStatutID(Long statutID) {
		this.statutID = statutID;
	}
	public String getNomStatut() {
		return nomStatut;
	}
	public void setNomStatut(String nomStatut) {
		this.nomStatut = nomStatut;
	}
	@Override
	public String toString() {
		return  nomStatut;
	}
}
package com.far.recrutement.models;


import java.io.Serializable;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Region implements Serializable {
   /**
	 * 
	 */
	private static final long serialVersionUID = 7913470709027547254L;
@Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long regionID;
   private String nomRegion;
   private String nomRegionAr;
   @OneToMany(mappedBy="region" ,fetch = FetchType.LAZY)
   private Collection<Ville> villes;
   @OneToMany(mappedBy="region" ,fetch = FetchType.LAZY)
   private Collection<Candidat> candidats;
   
   public Region() {
		super();
	}
	public Region(String nomRegion,String nomRegionAr) {
		super();
		this.nomRegion = nomRegion;
		this.nomRegionAr = nomRegionAr;
	}
	
	public String getNomRegionAr() {
		return nomRegionAr;
	}
	public void setNomRegionAr(String nomRegionAr) {
		this.nomRegionAr = nomRegionAr;
	}
	public Long getRegionID() {
		return regionID;
	}
	public void setRegionID(Long regionID) {
		this.regionID = regionID;
	}
	public String getNomRegion() {
		return nomRegion;
	}
	public void setNomRegion(String nomRegion) {
		this.nomRegion = nomRegion;
	}
	public Collection<Ville> getVilles() {
		return villes;
	}
	public void setVilles(Collection<Ville> villes) {
		this.villes = villes;
	}
	@Override
	public String toString() {
		return  nomRegion;
	}
	public Collection<Candidat> getCandidats() {
		return candidats;
	}
	public void setCandidats(Collection<Candidat> candidats) {
		this.candidats = candidats;
	}
	
}
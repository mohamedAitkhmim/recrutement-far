package com.far.recrutement.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Armee {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long armeeID;
	String nom;
	public Armee(String nom) {
		super();
		this.nom = nom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Long getArmeeID() {
		return armeeID;
	}
	public void setArmeeID(Long armeeID) {
		this.armeeID = armeeID;
	}
	public Armee() {
		super();
	}
	
}

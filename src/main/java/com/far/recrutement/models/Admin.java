package com.far.recrutement.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Admin extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1762613390219680612L;
	@ManyToOne
	Ecole ecole;
	
	@ManyToMany
	List<Concours> concours;

	public Ecole getEcole() {
		return ecole;
	}

	public void setEcole(Ecole ecole) {
		this.ecole = ecole;
		setRole("ROLE_ADMIN");
	}

	public Admin(String email, String pass, boolean enabled, Ecole ecole) {
		super(email, pass, enabled, "ADMIN");
		this.ecole = ecole;
	}

	public Admin(String email, String pass, boolean enabled) {
		super(email, pass, enabled, "SUPER");
	}

	public Admin() {
		setRole("ROLE_SUPER");
		ecole=null;
	}

	public List<Concours> getConcours() {
		return concours;
	}

	public void setConcours(List<Concours> concours) {
		setRole("ROLE_RPCE");
		this.concours = concours;
	}
}

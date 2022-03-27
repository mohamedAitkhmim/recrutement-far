package com.far.recrutement.models;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Etude implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6159149388378754991L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long etudeID;
	private String nomEtude;
	
	@OneToMany(mappedBy="etude")
	private Collection<Option> options;
	
	@OneToMany(mappedBy="etude")
	private Collection<Niveau> niveaux;

	public Etude() {
		super();
	}

	public Etude(String nomEtude) {
		super();
		this.nomEtude = nomEtude;
	}

	public Long getEtudeID() {
		return etudeID;
	}

	public void setEtudeID(Long etudeID) {
		this.etudeID = etudeID;
	}

	public String getNomEtude() {
		return nomEtude;
	}

	public void setNomEtude(String nomEtude) {
		this.nomEtude = nomEtude;
	}

	public Collection<Option> getOptions() {
		return options;
	}

	public void setOptions(Collection<Option> options) {
		this.options = options;
	}
	@Override
	public String toString() {
		return  nomEtude;
	}
}

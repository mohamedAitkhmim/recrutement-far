package com.far.recrutement.models;


import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Niveau implements Serializable {
   /**
	 * 
	 */
	private static final long serialVersionUID = 6278120258206305316L;
@Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long niveauID;
   private String niveauScolaire;
   private String niveauScolaireAr;
   @ManyToOne
   private Etude etude;
   @OneToMany(mappedBy="niveau")
   private Collection<Candidat> candidats;
   
   public Niveau() {
		super();
	}
	public Niveau(String niveauScolaire,String niveauScolaireAr, Etude etude) {
		super();
		this.niveauScolaire = niveauScolaire;
		this.niveauScolaireAr = niveauScolaireAr;
		this.etude=etude;
	}
	
	
	public String getNiveauScolaireAr() {
		return niveauScolaireAr;
	}
	public void setNiveauScolaireAr(String niveauScolaireAr) {
		this.niveauScolaireAr = niveauScolaireAr;
	}
	public Long getNiveauID() {
		return niveauID;
	}
	public void setNiveauID(Long niveauID) {
		this.niveauID = niveauID;
	}
	public String getNiveauScolaire() {
		return niveauScolaire;
	}
	public void setNiveauScolaire(String niveauScolaire) {
		this.niveauScolaire = niveauScolaire;
	}
	public Collection<Candidat> getCandidats() {
		return candidats;
	}
	public void setCandidats(Collection<Candidat> candidats) {
		this.candidats = candidats;
	}
	public Etude getEtude() {
		return etude;
	}
	public void setEtude(Etude etude) {
		this.etude = etude;
	}
	@Override
	public String toString() {
		return  niveauScolaire;
	}
	
}
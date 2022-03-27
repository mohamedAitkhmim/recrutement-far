package com.far.recrutement.entities;

import java.io.Serializable;

import com.far.recrutement.models.Concours;
import com.far.recrutement.models.Inscription;

public class ConcoursGraphe implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3367839602946007270L;
	String titre;
	int rejette;
	int accepte;
	int encours;
	int total;
	
	public ConcoursGraphe(Concours concours)
	{
		this.titre=concours.getTitre();
		rejette=0;
		accepte=0;
		for(Inscription inscription : concours.getInscriptions() )
		{
			if(inscription.getStatut().getStatutID()==1)
				encours++;
			if(inscription.getStatut().getStatutID()==2)
				accepte++;
			if(inscription.getStatut().getStatutID()==3)
				rejette++;
			total++;
		}
	}
	
	public ConcoursGraphe()
	{}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getRejette() {
		return rejette;
	}

	public void setRejette(int rejette) {
		this.rejette = rejette;
	}

	public int getAccepte() {
		return accepte;
	}

	public void setAccepte(int accepte) {
		this.accepte = accepte;
	}

	public int getEncours() {
		return encours;
	}

	public void setEncours(int encours) {
		this.encours = encours;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
}

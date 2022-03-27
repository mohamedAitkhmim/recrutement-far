package com.far.recrutement.entities;

import java.io.Serializable;

public class RegionGraphe  implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5493808117170149700L;
	private String region;
	private int candidats;
	public RegionGraphe() {
		super();
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public int getCandidats() {
		return candidats;
	}
	public void setCandidats(int candidats) {
		this.candidats = candidats;
	}
	
	

}

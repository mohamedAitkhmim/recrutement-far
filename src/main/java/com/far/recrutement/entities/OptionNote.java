package com.far.recrutement.entities;

import com.far.recrutement.models.Option;

public class OptionNote {
	
	Option option;
	float note;
	int admis=0;
	
	public OptionNote() {
		super();
	}
	
	public OptionNote(Option option, float note) {
		super();
		this.option = option;
		this.note = note;
	}
	
	public Option getOption() {
		return option;
	}
	
	public void setOption(Option option) {
		this.option = option;
	}
	
	public float getNote() {
		return note;
	}
	
	public void setNote(float note) {
		this.note = note;
	}

	public int getAdmis() {
		return admis;
	}

	public void setAdmis(int admis) {
		this.admis = admis;
	}
	
}

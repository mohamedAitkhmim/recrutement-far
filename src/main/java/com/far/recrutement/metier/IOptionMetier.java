package com.far.recrutement.metier;

import java.util.List;

import com.far.recrutement.models.Option;

public interface IOptionMetier {
	
	public Option addOption(Option o);
	public List<Option> listOption();

}

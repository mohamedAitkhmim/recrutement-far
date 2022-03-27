package com.far.recrutement.entities;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.far.recrutement.models.Concours;
import com.far.recrutement.models.Option;
import com.far.recrutement.util.Liste;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Preselection {
	
	private static final Logger logger = LoggerFactory.getLogger(Preselection.class);
	
	Liste<OptionNote> notes = new Liste<>();
	Concours concours;
	OptionNote note;
	Boolean noteUnique;
	int admis;

	@JsonIgnore
    Liste<Option> soptions = new Liste<>();
	
	public Preselection() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public Preselection(Concours concours,List<Option> options) {
		try
		{
			concours.getNiveaux().forEach(niveau -> 
				soptions.addIfNotExist(Liste.fromIterable(niveau.getEtude().getOptions()))
			);
		}
		catch(Exception ex)
		{
			soptions=Liste.fromIterable(options);
			logger.warn(ex.toString());
		}
		this.concours=concours;
		noteUnique=true;
		notes=new Liste<>();
		note=new OptionNote();
		soptions.forEach(option -> 
			notes.add(new OptionNote(option,0))
		);
	}
	
	@SuppressWarnings("unchecked")
	public Preselection(List<OptionNote> notes, Concours concours, OptionNote note, Boolean noteUnique) {
		super();
		this.notes = Liste.fromIterable(notes);
		this.concours = concours;
		this.note = note;
		this.noteUnique = noteUnique;
	}
	public Liste<OptionNote> getNotes() {
		return notes;
	}
	@SuppressWarnings("unchecked")
	public void setNotes(List<OptionNote> notes) {
		this.notes = Liste.fromIterable(notes);
	}
	public Concours getConcours() {
		return concours;
	}
	public void setConcours(Concours concours) {
		this.concours = concours;
	}
	public OptionNote getNote() {
		return note;
	}
	public void setNote(OptionNote note) {
		this.note = note;
	}
	public Boolean getNoteUnique() {
		return noteUnique;
	}
	public void setNoteUnique(Boolean noteUnique) {
		this.noteUnique = noteUnique;
	}

	public int getAdmis() {
		return admis;
	}

	public void setAdmis(int admis) {
		this.admis = admis;
	}
	
	public String getSoptions() {
		String jsonString = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonString = mapper.writeValueAsString(this);
		} catch (Exception ex) {
			logger.info(ex.toString());
		}
		return jsonString;
	}


	public void setSoptions(String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Preselection p = mapper.readValue(json, Preselection.class);
			for(OptionNote on : p.notes)
			{
				soptions.add(on.getOption());
			}
		} catch (Exception ex) {
			logger.info(ex.toString());
		}
		if(noteString.length>0)
			fillOptionNote();
	}
	
	String[] noteString;
	
	public void setNoteString(String[] noteString)
	{
		this.noteString = noteString;
		if(!soptions.isEmpty())
			fillOptionNote();
	}
	
	void fillOptionNote()
	{
		notes=new Liste<>();
		int i=0;
		for(Option option : soptions)
		{
			try {
				notes.add(new OptionNote(option,Float.valueOf( noteString[i])));
			}
			catch(Exception ex) {
				notes.add(new OptionNote(option,0));
			}
			i++;
		}
	}
	
	public String[] getNoteString()
	{
		String[] s=new String[notes.size()];
		int i=0;
		for(OptionNote not : notes)
		{
			s[i]=String.valueOf(not.getNote());
			i++;
		}
		return s;
	}

	
}

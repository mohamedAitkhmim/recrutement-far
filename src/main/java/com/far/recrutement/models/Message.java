package com.far.recrutement.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long messageID;
	@Column(length = 100)
	String nom;
	String email;
	@Column(length = 100)
	String objet;
	@Column(length = 1200)
	String text;
	@Column(length = 1200)
	String replay;
	@Column(length = 2)
	String statut;
	Date date;
	
	public Message() {
		super();
		this.statut="nl";
		this.date=new Date();
	}
	public Message(String nom, String email, String objet, String text) {
		super();
		this.nom = nom;
		this.email = email;
		this.objet = objet;
		this.text = text;
	}
	public Long getMessageID() {
		return messageID;
	}
	public void setMessageID(Long messageID) {
		this.messageID = messageID;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getObjet() {
		return objet;
	}
	public void setObjet(String objet) {
		this.objet = objet;
	}
	public String getText() {
		return text;
	}
	public void setText(String message) {
		this.text = message;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public String getHeader()
	{
		if(text.length()<40)
			return text;
		else
			return text.substring(0, 37)+"...";
	}
	public String getReplay() {
		return replay;
	}
	public void setReplay(String replay) {
		this.replay = replay;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}

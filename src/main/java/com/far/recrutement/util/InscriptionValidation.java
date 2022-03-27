package com.far.recrutement.util;

import java.util.Calendar;

import org.springframework.stereotype.Service;

import com.far.recrutement.models.Candidat;
import com.far.recrutement.models.Concours;
import com.far.recrutement.models.Inscription;
import com.far.recrutement.models.Statut;

@Service
public class InscriptionValidation {

    static final String NOTE_INSUFFISANTE = "Moyenne insuffisante";
    static final String MOTIF_AGE_MAX = "Age supérieur à l'age maximal autorisé";
    static final String MOTIF_AGE_MIN = "Age inferieur à l'age minimal requis";
    static final String MOTIF_BACANCIEN = "Bac ancien";
    static final String MOTIF_LANGUE = "Deuxième langue étrangère non conforme";
    static final String MOTIF_BACREQUIS = "Ne dispose pas du baccalauréat";
    static final String MOTIF_SEXE = "Sexe non conforme";
    static final String MOTIF_NIVEAU = "Ne dispose pas du niveau scolaire requis";
    static final String MOTIF_OPTION = "Option non conforme";
    static final String MOTIF_SITUATION = "Situation non conforme";
    static final String MOTIF_SPORT = "Condition physique";
    static final String MOTIF_NATIONNALITE = "";

    public String getRestrictions(Candidat candidat, Concours concours) {
        String restrictions = "";
		/*
		 * remove restrection before inscription
		 * if(!concours.getSexe().contains(candidat.getSexe()) & (concours.getSexe().size()>0))
		{
			restrictions += MOTIF_SEXE + "<br>";
		}
		if(concours.getAgemax()!=0 && candidat.getAge()>concours.getAgemax())
		{
			restrictions += MOTIF_AGE_MAX + "<br>";
		}
		if(concours.getAgemin()!=0 && candidat.getAge()<concours.getAgemin())
		{
			restrictions += MOTIF_AGE_MIN + "<br>";
		}
		//bac requis
		if(concours.getCategorie().isBacrequis() & candidat.getAnneebac()==0)
		{
			restrictions += MOTIF_BACREQUIS + "<br>";
		}
		if(concours.getNiveaux().contains(candidat.getNiveau()) & concours.getNiveaux().size() != 0)
		{
			restrictions += MOTIF_NIVEAU + "<br>";
		}
		if(concours.getOptions().contains(candidat.getOption()) & concours.getOptions().size() != 0)
		{
			restrictions += MOTIF_OPTION + "<br>";
		}*/
        return restrictions;
    }

    public Boolean isRestricted(Candidat candidat, Concours concours) {
        return (getRestrictions(candidat, concours).length() > 0);
    }

    public Inscription updateStatut(Inscription inscription, Statut rejetee) {
        //validations
        //age max
        if (inscription.getConcours().getAgemax() != 0 && inscription.getCandidat().getAge() > inscription.getConcours().getAgemax()) {
            inscription.setStatut(rejetee);
            inscription.setMotif(MOTIF_AGE_MAX);
        }
        //age min
        if (inscription.getConcours().getAgemin() != 0 && inscription.getCandidat().getAge() < inscription.getConcours().getAgemin()) {
            inscription.setStatut(rejetee);
            inscription.setMotif(MOTIF_AGE_MIN);
        }
        //nationnalité
        if (inscription.getCandidat().getNationnalite().length() > 0) {
            inscription.setStatut(rejetee);
            inscription.setMotif(MOTIF_NATIONNALITE);
        }
        //bac ancien
        if (inscription.getConcours().getCategorie().isBacrequis() & (Calendar.getInstance().get(Calendar.YEAR) - inscription.getCandidat().getAnneebac()) > inscription.getConcours().getBacancien()) {
            inscription.setStatut(rejetee);
            inscription.setMotif(MOTIF_BACANCIEN);
        }
        //bac requis
        if (inscription.getConcours().getCategorie().isBacrequis() & inscription.getCandidat().getAnneebac() == 0) {
            inscription.setStatut(rejetee);
            inscription.setMotif(MOTIF_BACREQUIS);
        }
        //langue
        if (inscription.getCandidat().getLangue() != null && (inscription.getCandidat().getLangue().getNomLangue().equals("Englais") || inscription.getCandidat().getLangue().getNomLangue().equals("Espagnol"))) {
            inscription.setStatut(rejetee);
            inscription.setMotif(MOTIF_LANGUE);
        }
        //situation familliale
        if (!inscription.getCandidat().getSituation().getNomSituation().equals("Célibataire")) {
            inscription.setStatut(rejetee);
            inscription.setMotif(MOTIF_SITUATION);
        }
        //sexe
        if (!inscription.getConcours().getSexe().contains(inscription.getCandidat().getSexe()) & inscription.getConcours().getSexe().size() != 0) {
            inscription.setStatut(rejetee);
            inscription.setMotif(MOTIF_SEXE);
        }
        //Niveau
        if (!inscription.getConcours().getNiveaux().contains(inscription.getCandidat().getNiveau()) & inscription.getConcours().getNiveaux().size() != 0) {
            inscription.setStatut(rejetee);
            inscription.setMotif(MOTIF_NIVEAU);
        }
        //option
        if (!inscription.getConcours().getOptions().contains(inscription.getCandidat().getOption()) & inscription.getConcours().getOptions().size() != 0) {
            inscription.setStatut(rejetee);
            inscription.setMotif(MOTIF_OPTION);
        }
        return inscription;
    }

}

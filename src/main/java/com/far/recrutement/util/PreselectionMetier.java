package com.far.recrutement.util;

import com.far.recrutement.entities.OptionNote;
import com.far.recrutement.entities.Preselection;
import com.far.recrutement.metier.IInscriptionMetier;
import com.far.recrutement.metier.IStatutMetier;
import com.far.recrutement.models.Inscription;
import com.far.recrutement.models.Statut;

public class PreselectionMetier {
	

	
	
	@SuppressWarnings("unchecked")
	public static Preselection updateUsingNumber(Preselection preselection)
	{
		Liste<Inscription> inscriptions = Liste.fromIterable(preselection.getConcours().getInscriptions());
		inscriptions = inscriptions
				.where(ins -> ins.getStatut().getStatutID()!=3l)
				.orderBy(ins -> ins.getCandidat().getNote1())
				.limit(0, preselection.getAdmis());
		
		inscriptions.forEach(ins -> {
			OptionNote optionNote = preselection
					.getNotes()
					.where( on -> ins.getCandidat().getOption().getNomOption().equals(on.getOption().getNomOption()))
					.get(0);
			optionNote.setAdmis(optionNote.getAdmis()+1);
		});
		preselection.setAdmis(0);
		preselection.getNotes().forEach(on -> preselection.setAdmis(preselection.getAdmis() + on.getAdmis()));
		return preselection;
	}
	
	@SuppressWarnings("unchecked")
	public static void executePreselectionNumber(Preselection preselection,IStatutMetier statutMetier,IInscriptionMetier inscriptionMetier)
	{
		Statut accepte=statutMetier.getStatutById(2l);
		Statut rejette=statutMetier.getStatutById(3l);
		Liste<Inscription> inscriptions = Liste.fromIterable(preselection.getConcours().getInscriptions());
		inscriptions = inscriptions
				.where(ins -> ins.getStatut().getStatutID()!=3l)
				.orderBy(ins -> ins.getCandidat().getNote1());
		Liste<Inscription> acceptees =inscriptions.limit(0, preselection.getAdmis());
		Liste<Inscription> rejetees =inscriptions.limit(preselection.getAdmis(),inscriptions.size() );
		acceptees.forEach(ins -> {
			ins.setStatut(accepte);
			inscriptionMetier.addInscription(ins);
		});
		rejetees.forEach(ins ->{
			if(ins.getCandidat().getNote1()==0f)
			{
				ins.setMotif(MOTIF_MASSAR);
			}
			else
			{
				ins.setMotif(MOTIF_NOTE);
			}
			ins.setStatut(rejette);
			inscriptionMetier.addInscription(ins);
		});
	}
	
	static final String MOTIF_NOTE= "Note insuffisante";
	static final String MOTIF_MASSAR="Code Massar Invalide";
	


	
	public static float getNoteMin(Inscription inscription,Preselection preselection)
	{
		//calcule de la note min par option
		if(!preselection.getNoteUnique())
		{
			for(OptionNote optionNote : preselection.getNotes())
			{
				if(optionNote.getOption().getNomOption().equals(inscription.getCandidat().getOption().getNomOption()))
				{
					return optionNote.getNote();
				}
			}
		}
		return preselection.getNote().getNote();
	}
}

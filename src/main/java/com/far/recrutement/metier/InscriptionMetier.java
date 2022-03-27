package com.far.recrutement.metier;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.far.recrutement.dao.FiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.far.recrutement.dao.InscriptionRepository;
import com.far.recrutement.models.Inscription;
import com.far.recrutement.util.Liste;

@Service
public class InscriptionMetier implements IInscriptionMetier {

    @Autowired
    InscriptionRepository inscriptionRepository;
    @Autowired
    FiliereRepository filiereRepository;

    @Override
    public Inscription addInscription(Inscription i) {
        return inscriptionRepository.save(i);
    }

    @Override
    public Liste<Inscription> listInscription() {
        @SuppressWarnings("unchecked")
        Liste<Inscription> inscriptions = Liste.fromIterable(inscriptionRepository.findAll());
        return inscriptions
                .where(ins ->
                        toCalendar(ins.getDateInscription()).get(Calendar.YEAR) == toCalendar(new Date()).get(Calendar.YEAR)
                );
    }

    public Calendar toCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Liste<Inscription> listInscriptionAllYears() {
        return Liste.fromIterable(inscriptionRepository.findAll());
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public Liste<Inscription> getInscriptionByConcoursId(Long id) {
        Liste<Inscription> inscriptions = Liste.fromIterable(inscriptionRepository.getInscriptionsByConcoursId(id));
        return inscriptions
                .where(ins ->
                        toCalendar(ins.getDateInscription()).get(Calendar.YEAR) == toCalendar(new Date()).get(Calendar.YEAR)
                );
    }

    @Override
    public Liste<Inscription> getInscriptionByConcoursIdAllYears(Long id) {
        return inscriptionRepository.getInscriptionsByConcoursId(id);
    }

    @Override
    public Inscription getInscriptionById(Long id) {
        return inscriptionRepository.findById(id).orElse(null);
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public Liste<Inscription> getAllInscription() {
        Liste<Inscription> inscriptions = Liste.fromIterable(inscriptionRepository.findAll());
        return inscriptions
                .where(ins ->
                        toCalendar(ins.getDateInscription()).get(Calendar.YEAR) == toCalendar(new Date()).get(Calendar.YEAR)
                );
    }

    @SuppressWarnings("unchecked")
    @Override
    public Liste<Inscription> getAllInscriptionAllYears() {
        return Liste.fromIterable(inscriptionRepository.findAll());
    }

    @Override
    public void delete(Long id) {
        inscriptionRepository.deleteById(id);
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public Liste<Inscription> getInscriptionByCandidatEmail(String email) {
        Liste<Inscription> inscriptions = Liste.fromIterable(inscriptionRepository.getInscriptionsByCandidatEmail(email));
        return inscriptions
                .where(ins ->
                        toCalendar(ins.getDateInscription()).get(Calendar.YEAR) == toCalendar(new Date()).get(Calendar.YEAR)
                );
    }

    @Override
    public Liste<Inscription> getInscriptionByCandidatEmailAllYears(String email) {
        return inscriptionRepository.getInscriptionsByCandidatEmail(email);
    }

    @Override
    public Boolean inscriptionExists(String email, Long concoursID, Long filiereID) {
        if (filiereID == null) {
            List<Inscription> liste = inscriptionRepository.getInscriptionsByCandidatAndConcours(email, concoursID);
            return liste != null && !liste.isEmpty() && liste.size() >= filiereRepository.countByConcoursConcoursID(concoursID);
        } else {
            List<Inscription> liste = inscriptionRepository.getInscriptionsByCandidatAndConcoursAndFiliere(email, concoursID, filiereID);
            return liste != null && !liste.isEmpty();
        }
    }

}

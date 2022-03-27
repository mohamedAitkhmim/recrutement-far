package com.far.recrutement.dao;


import com.far.recrutement.models.Candidat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.far.recrutement.models.Inscription;
import com.far.recrutement.util.Liste;

import javax.persistence.Tuple;
import java.util.List;

public interface InscriptionRepository extends PagingAndSortingRepository<Inscription, Long> {

    @Query("select ins from Inscription ins where ins.concours.concoursID=?1")
    Liste<Inscription> getInscriptionsByConcoursId(Long id);

    @Query("select ins from Inscription ins where ins.candidat.email=?1")
    Liste<Inscription> getInscriptionsByCandidatEmail(String email);

    @Query("select ins from Inscription ins where ins.candidat.email=?1 and ins.concours.concoursID=?2")
    Liste<Inscription> getInscriptionsByCandidatAndConcours(String email, Long id);

    @Query("select ins from Inscription ins where ins.candidat.email=?1 and ins.concours.concoursID=?2 and ins.filiere.filiereID=?3")
    Liste<Inscription> getInscriptionsByCandidatAndConcoursAndFiliere(String email, Long id, Long filiere);

    @Query("select ins.filiere.filiereID from Inscription ins where ins.candidat.email=?1 and ins.concours.concoursID=?2")
    List<Long> getFilieres(String email, Long concoursID);

//    @Query("select count(ins.inscriptionID) as number, ins.concours.titre as concours, ins.concours.concoursID as id, ins.candidat" +
//            ".sexe.nomSexe as sexe from Inscription ins group by ins.concours.titre, ins.concours.concoursID, ins.candidat.sexe.nomSexe")
//    List<Tuple> getStatistics();

    @Query("select count(ins.inscriptionID) as number, ins.candidat.region.nomRegion as concours, ins.candidat.region.regionID as id, ins.candidat" +
            ".sexe.nomSexe as sexe from Inscription ins WHERE ins.concours.concoursID = 25 group by ins.candidat.region.nomRegion, ins.candidat.region.regionID, ins.candidat.sexe.nomSexe")
    List<Tuple> getStatistics();

    List<Inscription> findByStatutStatutID(Long id);

    void deleteByCandidatOptionEtudeEtudeID(Long etudeID);

    //@Query("select count(i) from Inscription i where i.candidat.userID = ?1")
    long countInscriptionByCandidat(Candidat candidat);

}

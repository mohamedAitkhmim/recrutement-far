package com.far.recrutement.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.far.recrutement.models.Candidat;

import javax.persistence.Tuple;

public interface CandidatRepository extends PagingAndSortingRepository<Candidat, Long> {

    @Query("select c from Candidat c where upper(c.email)= upper(:email)")
    public List<Candidat> getCandidatByEmail(@Param("email") String email);

    @Query("select c from Candidat c where c.codeActivation= :code")
    public List<Candidat> getCandidatByCode(@Param("code") String code);

    @Query("select c from Candidat c where c.codeRecovery= :code")
    public List<Candidat> getCandidatByRecoveryCode(@Param("code") String code);

    @Query("select c from Candidat c where c.anneebac<>0")
    public Page<Candidat> getCandidatWithBac(Pageable pageable);

    @Query("select c from Candidat c where c.anneebac=0")
    public Page<Candidat> getCandidatWithoutBac(Pageable pageable);

    @Query("select c from Candidat c where c.note1=0")
    public Page<Candidat> getCandidatWithInvalideCode(Pageable pageable);

    @Query("select c from Candidat c where c.sexe.sexeID=2")
    public Page<Candidat> getCandidatFemale(Pageable pageable);

    @Query("select c from Candidat c where c.sexe.sexeID=1")
    public Page<Candidat> getCandidatMale(Pageable pageable);

    @Query("select c from Candidat c where c.massar= :massar")
    public List<Candidat> getCandidatsByCodeMassar(@Param("massar") String massar);

    @Query("select c from Candidat c where c.enabled = false")
    public List<Candidat> getInactiveCandidats();

    @Query("select c from Candidat c where (c.option = null or c.langue = null) or (c.massar = '' ) or (c.anneebac=0 and (c.niveau.niveauID=2 or c.niveau.niveauID=3))")
    public List<Candidat> getIncompleteProfileCandidats();

    @Query(value = "select candidat.userid from candidat where userid not in (" +
            "select candidat.userid from candidat inner join inscription on inscription.candidat_userid=candidat.userid " +
            "group by candidat.userid having count(*) > 0)", nativeQuery = true)
    List<BigInteger> candidatsWithoutInscription();

    @Query("select ins.candidat from Inscription ins where ins.concours.concoursID = 6 and ins.statut.statutID = 2")
    public List<Candidat> getCRPTA();

    void deleteByOptionEtudeEtudeID(Long etudeID);

    @Query(value = "select count(*) from inscription inner join candidat on candidat.userid=inscription.candidat_userid where cin=?1 and inscription.concours_concoursid >= 22",nativeQuery = true)
    BigInteger countByCin(String cin);

    @Query("select count(ins.userID) as number, ins.region.nomRegion as concours, ins.region.regionID as id, ins.sexe.nomSexe as sexe from Candidat ins group by ins.region.nomRegion, ins.region.regionID, ins.sexe.nomSexe")
    List<Tuple> getStatistics();

    long countCandidatByCin(String cin);
}

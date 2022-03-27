package com.far.recrutement.metier;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.far.recrutement.models.Candidat;

public interface ICandidatMetier {
	
	public Candidat addCandidat(Candidat c);
	public Candidat getCandidatById(Long id);
	public Candidat getCandidatByEmail(String email);
	public Candidat getCandidatByCode(String code);
	public Iterable<Candidat> listCandidat();
	public Page<Candidat> getAllCandidats(Pageable pageable);
	public Page<Candidat> getCandidatsWithBac(Pageable pageable);
	public Page<Candidat> getCandidatsWithoutBac(Pageable pageable);
	public Page<Candidat> getCandidatsFemale(Pageable pageable);
	public Page<Candidat> getCandidatsMale(Pageable pageable);
	public List<Candidat> getCandidatsByCodeMassar(String massar);
	public Boolean emailExists(String email);
	public Boolean cnieExists(String cnie);
	public Candidat editCandidat(Candidat c);
	Page<Candidat> getCandidatsWithInvalideCode(Pageable pageable);
	Candidat getCandidatByRecoveryCode(String code);
	public Candidat saveCandidat(Candidat c);

}

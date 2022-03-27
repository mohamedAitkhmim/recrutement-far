package com.far.recrutement.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.far.recrutement.dao.CandidatRepository;
import com.far.recrutement.models.Candidat;

@Service
public class CandidatMetier implements ICandidatMetier {
	
	@Autowired
	private CandidatRepository candidatRepository;
	
	@Autowired
	private UserMetier userMetier;

	@Override
	public Candidat addCandidat(Candidat c) {
		return (Candidat) userMetier.save(c);
	}
	
	@Override
	public Candidat editCandidat(Candidat c) {
		return (Candidat) userMetier.reSave(c);
	}

	@Override
	public Iterable<Candidat> listCandidat() {
		return candidatRepository.findAll();
	}

	@Override
	public Boolean emailExists(String email) {
		email=email.toLowerCase();
		List<Candidat> candidats=candidatRepository.getCandidatByEmail(email);
		return !candidats.isEmpty();
	}

	@Override
	public Boolean cnieExists(String cnie) {
		if (candidatRepository.countCandidatByCin(cnie) != 0)
			return true;
		else
			return false;
	}

	@Override
	public Page<Candidat> getAllCandidats(Pageable pageable) {
		return candidatRepository.findAll(pageable);
	}

	@Override
	public Page<Candidat> getCandidatsWithBac(Pageable pageable) {
		return candidatRepository.getCandidatWithBac(pageable);
	}

	@Override
	public Page<Candidat> getCandidatsWithoutBac(Pageable pageable) {
		return candidatRepository.getCandidatWithoutBac(pageable);
	}
	
	@Override
	public Page<Candidat> getCandidatsWithInvalideCode(Pageable pageable) {
		return candidatRepository.getCandidatWithInvalideCode(pageable);
	}

	@Override
	public Candidat getCandidatById(Long id) {
		return candidatRepository.findById(id).orElse(null);
	}

	@Override
	public Candidat getCandidatByEmail(String email) {
		if(candidatRepository.getCandidatByEmail(email).size()>0)
			return candidatRepository.getCandidatByEmail(email).get(0);
		else
			return null;
	}

	@Override
	public Page<Candidat> getCandidatsFemale(Pageable pageable) {
		return candidatRepository.getCandidatFemale(pageable);
	}

	@Override
	public Page<Candidat> getCandidatsMale(Pageable pageable) {
		return candidatRepository.getCandidatMale(pageable);
	}

	@Override
	public List<Candidat> getCandidatsByCodeMassar(String massar) {
		return candidatRepository.getCandidatsByCodeMassar(massar);
	}

	@Override
	public Candidat getCandidatByCode(String code) {
		if(candidatRepository.getCandidatByCode(code).size()>0)
			return candidatRepository.getCandidatByCode(code).get(0);
		else
			return null;
	}
	
	@Override
	public Candidat getCandidatByRecoveryCode(String code) {
		if(code.length()>1 && candidatRepository.getCandidatByRecoveryCode(code).size()>0 )
			return candidatRepository.getCandidatByRecoveryCode(code).get(0);
		else
			return null;
	}
	
	public int size()
	{
		int size=0;
		for(Candidat c : listCandidat())
		{
			//getage max to remove unused notification
			c.getNom();
			size++;
		}
		return size;
	}

	@Override
	public Candidat saveCandidat(Candidat c) {
		return candidatRepository.save(c);
	}

}

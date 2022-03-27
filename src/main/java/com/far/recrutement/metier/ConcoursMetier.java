package com.far.recrutement.metier;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.far.recrutement.dao.ConcoursRepository;
import com.far.recrutement.entities.ConcoursGraphe;
import com.far.recrutement.models.Concours;

@Service
public class ConcoursMetier implements IConcoursMetier {
	
	@Autowired
	ConcoursRepository concoursRepository;

	@Override
	public Concours addConcours(Concours c) {
		return concoursRepository.save(c);
	}

	@Override
	public Concours getConcoursById(Long id) {
		return concoursRepository.findById(id).orElse(null);
	}

	@Override
	public Page<Concours> listConcours(Pageable pageable) {
		return concoursRepository.findAll(pageable);
	}
	
	@Override
	public Iterable<Concours> listConcours() {
		return concoursRepository.findAll();
	}

	@Override
	public Page<Concours> search(String search, Pageable pageable) {
		return concoursRepository.search(search, pageable);
	}
	
	@Override
	public Page<Concours> search(String search,long categorie,long armee, Pageable pageable) {
		return concoursRepository.search(search, (int)categorie, (int)armee,pageable);
	}

	@Override
	public Page<Concours> getConcoursByCategorieId(Long id, Pageable pageable) {
		return concoursRepository.getConcoursByCategorieId(id, pageable);
	}
	
	@Override
	public Page<Concours> getConcoursActifByCategorieId(Long id, Pageable pageable) {
		return concoursRepository.getConcoursActifByCategorieId(id, pageable);
	}

	@Override
	public void delete(Long id) {
		concoursRepository.deleteById(id);
	}

	@Override
	public Page<Concours> getConcoursActifs(Pageable pageable) {
		return concoursRepository.getConcoursActif(pageable);
	}

	public Page<Concours> getConcoursInactifs(Pageable pageable) {
		return concoursRepository.getConcoursInactif(pageable);
	}

	@Override
	public List<ConcoursGraphe> getConcoursGraphe() {
		List<ConcoursGraphe> graphe=new ArrayList<>();
		Iterable<Concours> iterable=concoursRepository.findAll();
		iterable.forEach(concours->
			graphe.add(new ConcoursGraphe(concours))
		);
		return graphe;
	}
	
	public int size()
	{
		int size=0;
		for(Concours c : listConcours())
		{
			//getage max to remove unused notification
			c.getAgemax();
			size++;
		}
		return size;
	}

	@Override
	public Page<Concours> search(String search, Long categorie, Long armee, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}

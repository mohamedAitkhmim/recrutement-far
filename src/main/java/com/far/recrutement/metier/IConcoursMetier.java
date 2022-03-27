package com.far.recrutement.metier;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.far.recrutement.entities.ConcoursGraphe;
import com.far.recrutement.models.Concours;

public interface IConcoursMetier {
	
	public Concours addConcours(Concours c);
	public Page<Concours> listConcours(Pageable pageable);
	public Page<Concours> search(String search,Pageable pageable);
	public Page<Concours> getConcoursByCategorieId(Long id,Pageable pageable);
	public Page<Concours> getConcoursActifByCategorieId(Long id,Pageable pageable);
	public Page<Concours> getConcoursActifs(Pageable pageable);
	public Page<Concours> getConcoursInactifs(Pageable pageable);
	public List<ConcoursGraphe> getConcoursGraphe();
	public Concours getConcoursById(Long id);
	public void delete(Long id);
	public Iterable<Concours> listConcours();
	Page<Concours> search(String search, Long categorie, Long armee, Pageable pageable);
	Page<Concours> search(String search, long categorie, long armee, Pageable pageable);

}

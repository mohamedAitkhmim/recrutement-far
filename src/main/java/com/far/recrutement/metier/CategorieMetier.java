package com.far.recrutement.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.far.recrutement.dao.CategorieRepository;
import com.far.recrutement.models.Categorie;

@Service
public class CategorieMetier implements ICategorieMetier {
	@Autowired
	CategorieRepository categorieRepository;

	@Override
	public Categorie addCategorie(Categorie c) {
		return categorieRepository.save(c);
	}

	@Override
	public List<Categorie> listCategorie() {
		return categorieRepository.findAll();
	}

	@Override
	public Categorie getCategorieById(Long id) {
		return categorieRepository.getOne(id);
	}

}

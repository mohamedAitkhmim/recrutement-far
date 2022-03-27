package com.far.recrutement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.far.recrutement.models.Categorie;


public interface CategorieRepository extends JpaRepository<Categorie,Long> {

}

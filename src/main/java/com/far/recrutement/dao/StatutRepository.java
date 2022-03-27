package com.far.recrutement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.far.recrutement.models.Statut;

public interface StatutRepository extends JpaRepository<Statut,Long> {

}

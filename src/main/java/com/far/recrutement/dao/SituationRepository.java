package com.far.recrutement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.far.recrutement.models.Situation;

public interface SituationRepository extends JpaRepository<Situation,Long> {

}

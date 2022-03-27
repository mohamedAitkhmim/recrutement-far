package com.far.recrutement.dao;

import com.far.recrutement.models.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FiliereRepository extends JpaRepository<Filiere, Long> {
    Long countByConcoursConcoursID(Long councoursId);
}

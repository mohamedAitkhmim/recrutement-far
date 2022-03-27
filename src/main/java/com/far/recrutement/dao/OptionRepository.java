package com.far.recrutement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.far.recrutement.models.Option;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {
    void deleteByEtudeEtudeID(Long etudeId);
    List<Option> findByEtudeEtudeID(Long etudeId);
}

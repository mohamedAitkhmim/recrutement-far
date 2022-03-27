package com.far.recrutement.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.far.recrutement.models.Message;

public interface MessageRepository extends PagingAndSortingRepository<Message,Long> {
	
	@Query("select m from Message m where m.statut = :statut")
	public Page<Message> getMessagesByStatut(@Param("statut") String statut,Pageable pageable);
	
	@Query("select m from Message m where m.statut <> :statut")
	public Page<Message> getMessagesByNotStatut(@Param("statut") String statut,Pageable pageable);
	
}

package com.far.recrutement.metier;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.far.recrutement.models.Message;

public interface IMessageMetier {
	
	public Message save(Message message);
	public Page<Message> getAllMessages(Pageable pageable);
	public Page<Message> getMessagesByStatut(Pageable pageable,String statut);
	public Page<Message> getMessagesByNotStatut(Pageable pageable,String statut);
	public Message getMessageById(Long id);
	public void delete(Long id);

}

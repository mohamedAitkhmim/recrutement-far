package com.far.recrutement.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.far.recrutement.dao.MessageRepository;
import com.far.recrutement.models.Message;

@Service
public class MessageMetier implements IMessageMetier {

	@Autowired
	MessageRepository messageRepository;

	@Override
	public Message save(Message message) {
		return messageRepository.save(message);
	}

	@Override
	public Page<Message> getAllMessages(Pageable pageable) {
		return messageRepository.findAll(pageable);
	}

	@Override
	public Message getMessageById(Long id) {
		return messageRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		messageRepository.deleteById(id);	
	}

	@Override
	public Page<Message> getMessagesByStatut(Pageable pageable, String statut) {
		return messageRepository.getMessagesByStatut(statut, pageable);
	}
	
	@Override
	public Page<Message> getMessagesByNotStatut(Pageable pageable, String statut) {
		return messageRepository.getMessagesByNotStatut(statut, pageable);
	}
}

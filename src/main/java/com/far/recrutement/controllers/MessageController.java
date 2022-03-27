package com.far.recrutement.controllers;
import javax.validation.Valid;

import com.far.recrutement.metier.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.far.recrutement.metier.IMessageMetier;
import com.far.recrutement.models.Message;

@Controller
public class MessageController {
	
	@Autowired
	IMessageMetier messageMetier;
	@Autowired
	private MessageService messageService;
	
	static final String MESSAGE = "message";
	
	Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	@GetMapping("/avis")
	public String  contact(Model model) {
		Message message =new Message();
		model.addAttribute(MESSAGE,message);
		return "Accueil/avis";
	}
	
	@GetMapping("/faq")
	public String  faq(Model model) {
		Message message =new Message();
		model.addAttribute(MESSAGE,message);
		return "Accueil/faq";
	}
	
	@RequestMapping(value = "/message/new")
	public String newConcours(Model model,@Valid Message message, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			
			return "Accueil/contact";
		}
		messageMetier.save(message);
		model.addAttribute(MESSAGE, messageService.get("messageReceived"));
		model.addAttribute("lienTitre", messageService.get("accueil"));
		model.addAttribute("lien", "/");
		return MESSAGE;
	}
}

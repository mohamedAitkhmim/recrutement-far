package com.far.recrutement.controllers;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.far.recrutement.metier.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.far.recrutement.metier.ICandidatMetier;
import com.far.recrutement.models.Candidat;
import com.far.recrutement.util.CaptchaImage;
import com.far.recrutement.util.EmailSender;

@Controller
public class PasswordRecoveryController {
	
	@Autowired
	private ICandidatMetier candidatMetier;
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private EmailSender emailSender;
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value = "/passwordrecovery")
	public String  home(Model model) {
		Candidat candidat = new Candidat();
		CaptchaImage captcha=context.getBean(CaptchaImage.class);
		captcha.reload();
		model.addAttribute("candidat", candidat);
		model.addAttribute("captcha", captcha);
   	 	model.addAttribute("captchaError", captcha.getErrorMessage());
		return "PasswordRecovery/add";
	}
	
	@RequestMapping(value = "/passwordrecovery/{code}")
	public String  changepassword(Model model,@PathVariable(value="code") final String code) {
		Candidat candidat = new Candidat();
		candidat.setCodeRecovery(code);
		model.addAttribute("candidat", candidat);
		return "PasswordRecovery/change";
	}
	
	@RequestMapping(value = "/password")
	public String  setpassword(HttpServletRequest request,HttpSession session,Model model,Candidat candidat) {
		Candidat user = candidatMetier.getCandidatByRecoveryCode(candidat.getCodeRecovery());
		if(user != null)
		{
			user.setPass(candidat.getPass());
			user.setCodeRecovery("");
			candidatMetier.addCandidat(user);
			model.addAttribute("message", messageService.get("passwordUpdated"));
		}
		else
		{
			model.addAttribute("message", messageService.get("informationIncorrect"));
		}
		model.addAttribute("lienTitre", messageService.get("accueil"));
		model.addAttribute("lien", "/");
		return "message";
	}
	
	@RequestMapping(value = "/newpassword")
	public String  home(HttpServletRequest request,HttpSession session,Model model,Candidat candidat,@Valid CaptchaImage captcha) {
		CaptchaImage contextCaptcha=context.getBean(CaptchaImage.class);
		String email = candidat.getEmail();
		String cin = candidat.getCin();
		Candidat user = candidatMetier.getCandidatByEmail(email);
		if(contextCaptcha.isValid(captcha.getUserString()) && user != null && (user.getCin() == null || user.getCin().equals(cin)))
		{
			user.setCodeRecovery(UUID.randomUUID().toString());
			candidatMetier.editCandidat(user);
			emailSender.sendEmailPasswordRecovery(session,user);
			model.addAttribute("message", messageService.get("recoveryEmailSent"));

		}
		else
		{
			model.addAttribute("message", messageService.get("incorrectInformation"));
		}
		model.addAttribute("lienTitre", messageService.get("accueil"));
		model.addAttribute("lien", "/");
		return "message";
	}
	

}

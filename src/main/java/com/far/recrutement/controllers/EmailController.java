package com.far.recrutement.controllers;

import javax.servlet.http.HttpSession;

import com.far.recrutement.metier.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.far.recrutement.metier.ICandidatMetier;
import com.far.recrutement.models.Candidat;
import com.far.recrutement.util.EmailSender;

@Controller
public class EmailController {

    @Autowired
    private ICandidatMetier candidatMetier;
    @Autowired
    private MessageService messageService;

    @Autowired
    EmailSender sender;

    Logger logger = LoggerFactory.getLogger(EmailController.class);

    static final String MESSAGE = "message";
    static final String LIEN_TITRE = "lienTitre";

    @RequestMapping(value = "/envoiEmail/{id}")
    public String envoiEmail(HttpSession session, Model model, @PathVariable(value = "id") final Long id) {
        Candidat candidat = candidatMetier.getCandidatById(id);
        //email
        if (candidat != null) {
            if (!candidat.isEnabled()) {
                try {
                    sender.sendEmailValidation(session, candidat);
                } catch (Exception ex) {
                    logger.debug(ex.toString());
                }
                model.addAttribute(MESSAGE, messageService.get("activationEmailSent"));
                model.addAttribute(LIEN_TITRE, messageService.get("sendEmail"));
                model.addAttribute("lien", "/envoiEmail/" + id);
            } else {
                model.addAttribute(MESSAGE, messageService.get("compteActive"));
                model.addAttribute(LIEN_TITRE, messageService.get("accueil"));
                model.addAttribute("lien", "/");
            }
            return MESSAGE;
        } else {
            return "../public/error/404";
        }
    }

    @RequestMapping(value = "/activer/{code}")
    public String activer(HttpSession session, Model model, @PathVariable(value = "code") final String code) {
        Candidat candidat = candidatMetier.getCandidatByCode(code);
        //email
        if (candidat != null) {
            if (!candidat.isEnabled()) {
                candidat.setEnabled(true);
                candidatMetier.editCandidat(candidat);
                try {
                    sender.sendEmailAfterValidation(session, candidat);
                } catch (Exception ex) {
                    logger.debug(ex.toString());
                }
                model.addAttribute(MESSAGE, messageService.get("compteActivated"));
            } else {
                model.addAttribute(MESSAGE, messageService.get("compteActive"));
            }
            model.addAttribute(LIEN_TITRE, messageService.get("accueil"));
            model.addAttribute("lien", "/");

            return MESSAGE;
        } else {
            return "../public/error/404";
        }
    }

}

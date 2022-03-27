package com.far.recrutement.util;


import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.far.recrutement.models.Candidat;
import com.far.recrutement.models.Message;

@Service
public class EmailSender {

    static final String DOMAINE = "https://recrutement.far.ma";
    static final String NAME = "recrutement.far.ma";

    @Autowired
    private JavaMailSender sender;

    @Autowired
    MessageSource ressource;

    Logger logger = LoggerFactory.getLogger(EmailSender.class);

    public EmailSender() {
        // default implementation ignored
    }

    public void sendEmailValidation(HttpSession session, Candidat candidat) {
        String content = getContent(session, "activation", candidat);
        sendEmail(candidat.getEmail(), content,
                ressource.getMessage("activation", null, LocaleContextHolder.getLocale())
        );
    }

    public void sendEmailAfterValidation(HttpSession session, Candidat candidat) {
        String content = getContent(session, "felicitation", candidat);
        sendEmail(candidat.getEmail(), content,
                "Félicitation");
    }

    public void sendEmailConvocation(HttpSession session, Candidat candidat) {
        String content = getContent(session, "convocation", candidat);
        sendEmail(candidat.getEmail(), content,
                "Convocation");
    }

    public void sendEmailBulletin(HttpSession session, Candidat candidat) {
        String content = getContent(session, "bulletin", candidat);
        sendEmail(candidat.getEmail(), content,
                "Bulletin Tronc Commun");
    }

    public void sendEmailIncomplet(HttpSession session, Candidat candidat) {
        String content = getContent(session, "incomplet", candidat);
        sendEmail(candidat.getEmail(), content,
                "Profil incomplet");
    }

    public void sendEmailSansInscription(HttpSession session, Candidat candidat) {
        String content = getContent(session, "inscription", candidat);
        sendEmail(candidat.getEmail(), content,
                "Inscription Incomplète");
    }

    public void sendEmailPasswordRecovery(HttpSession session, Candidat candidat) {
        String content = getContent(session, "recovery", candidat);
        sendEmail(candidat.getEmail(), content,
                ressource.getMessage("recuperationmdp", null, LocaleContextHolder.getLocale()));
    }

    public void sendMessageReplay(Message message) {
        sendEmail(message.getEmail(),
                message.getReplay(),
                "réponse à " + message.getObjet());
    }

    public void sendEmail(String to, String text, String sujet) {
        try {
            MimeMessage mail = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setFrom("recrutement@contact-far.ma");
            helper.setText(text, true);
            helper.setSubject(sujet);
            sender.send(mail);
            System.out.println("email envoyé to:" + to + "  sujet:" + sujet);
        } catch (Exception ex) {
            logger.warn("impossible d'envoyé l'email to:" + to + " because:" + ex.getMessage());
        }
    }

    String readFile(String fileName) {
        String html = "";
        try {
            html = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        return html;
    }

    String fileTemplate(String html, Candidat candidat, String lang) {
        if (candidat != null) {
            if (candidat.getNom() != null) html = html.replace("{candidat.nom}", candidat.getNom());
            if (candidat.getNomAr() != null) html = html.replace("{candidat.nomAr}", candidat.getNomAr());
            if (candidat.getPrenom() != null) html = html.replace("{candidat.prenom}", candidat.getPrenom());
            if (candidat.getPrenomAr() != null) html = html.replace("{candidat.prenomAr}", candidat.getPrenomAr());
            if (candidat.getCodeRecovery() != null)
                html = html.replace("{candidat.codeRecovery}", candidat.getCodeRecovery());
            if (candidat.getCodeActivation() != null)
                html = html.replace("{candidat.codeActivation}", candidat.getCodeActivation());
        }
        html = html.replace("{site.nom}", NAME);
        html = html.replace("{site.domaine}", DOMAINE);
        return html;
    }

    public String getContent(HttpSession session, String template, Candidat candidat) {
        String lang = "fr";
        try {
            if (session != null && session.getAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE").toString().contains("ar")) {
                lang = "ar";
            }
        } catch (Exception ex) {
            logger.info("no default lang defined");
        }
        String html = readFile("files/emails/" + lang + "/" + template + ".html");
        html = fileTemplate(html, candidat, lang);
        return html;
    }

}

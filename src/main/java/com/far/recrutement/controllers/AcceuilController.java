package com.far.recrutement.controllers;

import java.net.UnknownHostException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.far.recrutement.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.far.recrutement.metier.IConcoursMetier;
import com.far.recrutement.models.Concours;
import com.far.recrutement.util.Internationalisation;
import com.far.recrutement.util.Liste;


@Controller
public class AcceuilController {

    @Autowired
    private IConcoursMetier concoursMetier;

    @RequestMapping(value = "/")
    public String home(HttpServletRequest request, HttpSession session, Model model, @RequestParam(value = "lang", required = false, defaultValue = "fr") final String lang) throws UnknownHostException {


        if (session.getAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE") == null && request.getHeader("Accept-Language") != null) {
            if (request.getHeader("Accept-Language").startsWith("ar") && !lang.startsWith("ar")) {
                return "redirect:?lang=ar";
            }
            if (request.getHeader("Accept-Language").startsWith("fr") && !lang.startsWith("fr")) {
                return "redirect:?lang=fr";
            }
        }

        @SuppressWarnings("unchecked")
        List<Concours> concours = Liste.fromIterable(concoursMetier.getConcoursActifs(null)).getRandomElements(6);
        //List<Concours> concours = Liste.fromIterable(concoursMetier.getConcoursActifs(null));
        System.out.println("----->size :"+concours.size());
        concours = Internationalisation.updateConcours(session, concours);
        System.out.println("----->size :"+concours.size());
        model.addAttribute("concours", concours);

        return "Accueil/index";
    }

    @Autowired
    MessageSource ressource;

    @RequestMapping(value = "/apropos")
    public String about() {
        return "Accueil/about";
    }

    //contact and faq in MessageController

    @RequestMapping(value = "/resultat")
    public String resultat() {

        return "Accueil/resultat";
    }

    @RequestMapping(value = "/mentionslegales")
    public String mentions() {
        return "Accueil/mentions";
    }

    @RequestMapping(value = "/login")
    public String connexion() {
        return "Accueil/connexion";
    }

    @RequestMapping(value = "/contact")
    public String contact(Model model) {
        Message message = new Message();
        model.addAttribute("message", message);
        return "Accueil/contact";
    }

//	@RequestMapping(value = "/ecoles")
//	public String  ecole() {
//		return "Ecole/list";
//	}
//	@RequestMapping(value = "/ecoleSof")
//	public String  ecoleSof() {
//		return "Ecole/listsof";
//	}

}

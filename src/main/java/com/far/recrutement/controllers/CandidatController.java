package com.far.recrutement.controllers;

import com.far.recrutement.dao.CandidatRepository;
import com.far.recrutement.dao.EtablissementCategoryRepository;
import com.far.recrutement.metier.*;
import com.far.recrutement.models.*;
import com.far.recrutement.util.CaptchaImage;
import com.far.recrutement.util.Internationalisation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class CandidatController {
    @Autowired
    private ICategorieMetier categorieMetier;
    @Autowired
    private IEcoleMetier ecoleMetier;
    @Autowired
    private IVilleMetier villeMetier;
    @Autowired
    private IRegionMetier regionMetier;
    @Autowired
    private ILangueMetier langueMetier;
    @Autowired
    private ISexeMetier sexeMetier;
    @Autowired
    private ISituationMetier situationMetier;
    @Autowired
    private INiveauMetier niveauMetier;
    @Autowired
    private IOptionMetier optionMetier;
    @Autowired
    private ICandidatMetier candidatMetier;
    @Autowired
    private ApplicationContext context;
    @Autowired
    private IUserMetier userMetier;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MessageService messageService;

    @Autowired
    private CandidatRepository candidatRepository;

    @Autowired
    private EtablissementCategoryRepository etablissementCategoryRepository;


    Logger logger = LoggerFactory.getLogger(CandidatController.class);

    static final String PAGINATION = "pagination";
    static final String LISTE = "liste";
    static final String AVECBAC = "avecbac";
    static final String CANDIDAT_LISTE = "Candidat/list";


    @GetMapping(value = "/user/profil")
    public String getProfil(Model model, HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Candidat candidat = candidatMetier.getCandidatByEmail(auth.getName());
        Internationalisation.update(session, candidat);
        model.addAttribute("candidat", candidat);
        return "Candidat/profil";
    }

    @GetMapping(value = {"/user/editProfile"})
    public String editGet(Model model, HttpSession session) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Candidat candidat = candidatMetier.getCandidatByEmail(auth.getName());

        CaptchaImage captcha = context.getBean(CaptchaImage.class);
        List<Categorie> categories = categorieMetier.listCategorie();
        model.addAttribute("categories", categories);
        List<Ecole> ecoles = ecoleMetier.listEcole();
        model.addAttribute("ecoles", ecoles);
        List<Ville> villes = villeMetier.listVille();
        villes = Internationalisation.updateVilles(session, villes);
        model.addAttribute("villes", villes);
        List<Region> regions = regionMetier.listRegion();
        regions = Internationalisation.updateRegions(session, regions);
        model.addAttribute("regions", regions);
        List<Langue> langues = langueMetier.listLangue();
        langues = Internationalisation.updateLangues(session, langues);
        model.addAttribute("langues", langues);
        List<Sexe> sexes = sexeMetier.listSexe();
        sexes = Internationalisation.updateSexes(session, sexes);
        model.addAttribute("sexes", sexes);
        List<Situation> situations = situationMetier.listSituation();
        situations = Internationalisation.updateSituation(session, situations);
        model.addAttribute("situations", situations);
        List<Niveau> niveaux = niveauMetier.listNiveau();
        niveaux = Internationalisation.updateNiveaux(session, niveaux).stream()
                .filter(o -> o.getNiveauID() == 1 || o.getNiveauID() == 6)
                .collect(Collectors.toList());
        model.addAttribute("niveaux", niveaux);
        List<Option> options = optionMetier.listOption();
        options = Internationalisation.updateOptions(session, options);
        model.addAttribute("options", options);
        List<Integer> annees = new ArrayList<>();
        int anneeActuelle = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = anneeActuelle; i > anneeActuelle - 20; i--) {
            annees.add(i);
        }
        model.addAttribute("annees", annees);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(villes);
            model.addAttribute("json", json);
        } catch (JsonProcessingException e) {
            logger.warn(e.toString());
        }
        try {
            captcha.reload();
            model.addAttribute("captcha", captcha);
            model.addAttribute("captchaError", captcha.getErrorMessage());
            captcha.setErrorMessage("");
        } catch (Exception ex) {
            logger.warn(ex.toString());
        }
        model.addAttribute("candidat", candidat);

        return "Candidat/add";
    }

    @GetMapping(value = {"/inscription"})
    public String inscriptionGet(Model model, HttpSession session,
                                 @RequestParam(value = "nom", required = false, defaultValue = "") String nom,
                                 @RequestParam(value = "prenom", required = false, defaultValue = "") String prenom,
                                 HttpServletRequest request) {

        Candidat candidat = new Candidat();
        if (!nom.equals("")) {

            if (session.getAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE") != null && session.getAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE").toString().contains("ar")) {
                candidat.setNomAr(nom);
            } else {
                candidat.setNom(nom);
            }
        }
        if (!prenom.equals("")) {
            if (session.getAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE") != null && session.getAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE").toString().contains("ar")) {
                candidat.setPrenomAr(prenom);
            } else {
                candidat.setPrenom(prenom);
            }

        }
        CaptchaImage captcha = context.getBean(CaptchaImage.class);
        List<Categorie> categories = categorieMetier.listCategorie();
        model.addAttribute("categories", categories);
        List<Ecole> ecoles = ecoleMetier.listEcole();
        model.addAttribute("ecoles", ecoles);
        List<Ville> villes = villeMetier.listVille();
        villes = Internationalisation.updateVilles(session, villes);
        model.addAttribute("villes", villes);
        List<Region> regions = regionMetier.listRegion();
        regions = Internationalisation.updateRegions(session, regions);
        model.addAttribute("regions", regions);
        List<Langue> langues = langueMetier.listLangue();
        langues = Internationalisation.updateLangues(session, langues);
        model.addAttribute("langues", langues);
        List<Sexe> sexes = sexeMetier.listSexe();
        sexes = Internationalisation.updateSexes(session, sexes);
        model.addAttribute("sexes", sexes);

        List<EtablissementCategory> etablissementCategories = etablissementCategoryRepository.findAll();
        etablissementCategories = Internationalisation.updateEtablissementCategory(session, etablissementCategories);
        model.addAttribute("etablissementCategories", etablissementCategories);

        List<Situation> situations = situationMetier.listSituation();
        situations = Internationalisation.updateSituation(session, situations);
        model.addAttribute("situations", situations);
        List<Niveau> niveaux = niveauMetier.listNiveau();
        niveaux = Internationalisation.updateNiveaux(session, niveaux);
        model.addAttribute("niveaux", niveaux);
        List<Option> options = optionMetier.listOption();
        options = Internationalisation.updateOptions(session, options);
        model.addAttribute("options", options);
        List<Integer> annees = new ArrayList<>();
        int anneeActuelle = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = anneeActuelle; i > anneeActuelle - 20; i--) {
            annees.add(i);
        }
        //annees.add(2020);
        model.addAttribute("annees", annees);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(villes);
            model.addAttribute("json", json);
        } catch (JsonProcessingException e) {
            logger.warn(e.toString());
        }
        try {
            captcha.reload();
            model.addAttribute("captcha", captcha);
            model.addAttribute("captchaError", captcha.getErrorMessage());
            captcha.setErrorMessage("");
        } catch (Exception ex) {
            logger.warn(ex.toString());
        }

        model.addAttribute("candidat", candidat);

        return "Candidat/add";
    }

    @PostMapping(value = "/ajoutCandidat")
    public String ajoutCandidat(@Valid CaptchaImage captcha, Model model, @Valid Candidat candidat, BindingResult bindingResult, RedirectAttributes rm) {
        CaptchaImage contextCaptcha = context.getBean(CaptchaImage.class);
        if (contextCaptcha.isValid(captcha.getUserString())) {
            if (bindingResult.hasErrors()) {
                logger.debug("has error");
                System.out.println("1");
                return "Candidat/add";
            }

            if (validation(candidat)) {
                try {
                    //pour test sans SMTP server
                    /*candidat.setEnabled(true);*/
                    candidatMetier.addCandidat(candidat);
                    return "redirect:/envoiEmail/" + candidat.getUserID();
                } catch (Exception ex) {
                    logger.warn(ex.toString());
                }

            }
            model.addAttribute("message", messageService.get("emailUsed"));
            model.addAttribute("lienTitre", messageService.get("retour"));
            model.addAttribute("lien", "/inscription");
            return "message";
        } else {
            rm.addFlashAttribute("candidat", candidat);
            return "redirect:/inscription";
        }
    }

    @PostMapping(value = "/user/editCandidat")
    public String editCandidat(@Valid CaptchaImage captcha, @Valid Candidat candidat, BindingResult bindingResult, RedirectAttributes rm) {
        CaptchaImage contextCaptcha = context.getBean(CaptchaImage.class);
        if (contextCaptcha.isValid(captcha.getUserString())) {
            if (bindingResult.hasErrors()) {
                logger.debug("has error");
                return "Candidat/add";
            }

            if (validation2(candidat)) {
                candidat.setEnabled(true);
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                Candidat candidatold = candidatMetier.getCandidatByEmail(auth.getName());
                candidat.setPass(candidatold.getPass());
                candidat.setEmail(candidatold.getEmail());
                candidat.setUserID(candidatold.getUserID());
                candidatMetier.saveCandidat(candidat);
                rm.addFlashAttribute("edited", true);
                return "redirect:/user/profil";
            } else {
                rm.addFlashAttribute("candidat", candidat);
                return "redirect:/user/editProfile";
            }

        } else {
            rm.addFlashAttribute("candidat", candidat);
            return "redirect:/user/editProfile";
        }

    }


    @GetMapping(value = "/ChangePassword")
    public String ChangePassword(HttpSession session, Model model) {
        CaptchaImage captcha = context.getBean(CaptchaImage.class);
        try {
            captcha.reload();
            model.addAttribute("captcha", captcha);
            model.addAttribute("captchaError", captcha.getErrorMessage());
            captcha.setErrorMessage("");
        } catch (Exception ex) {
            logger.warn(ex.toString());
        }
        return "Candidat/ChangePassword";
    }

    @PostMapping(value = "/saveChangePassword")
    public String saveChangePassword(HttpSession session, Model model, @Valid CaptchaImage captcha, RedirectAttributes rm,
                                     @RequestParam(value = "oldPass", required = true) String oldPass,
                                     @RequestParam(value = "newPass", required = true) String newPass,
                                     @RequestParam(value = "confirmPass", required = true) String confirmPass) {

        CaptchaImage contextCaptcha = context.getBean(CaptchaImage.class);
        if (contextCaptcha.isValid(captcha.getUserString())) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userMetier.getUserByEmail(auth.getName());

            if (passwordEncoder.matches(oldPass, user.getPass())) {
                if (newPass.equals(confirmPass)) {
                    //ok
                    user.setPass(newPass);
                    userMetier.save(user);
                    rm.addFlashAttribute("changePassword", true);
                    return "redirect:/ChangePassword";
                } else {
                    rm.addFlashAttribute("errConfirmPass", true);
                    return "redirect:/ChangePassword";
                }
            } else {
                rm.addFlashAttribute("errOldPass", true);
                return "redirect:/ChangePassword";
            }
        } else {
            return "redirect:/ChangePassword";
        }
    }


    public Boolean validation(Candidat candidat) {
        if (candidat.getNiveau() == null || candidat.getSexe() == null || candidat.getSituation() == null)
            return false;
        if (candidat.getVille() == null || candidat.getRegion() == null)
            return false;
        return !candidatMetier.emailExists(candidat.getEmail());
    }

    public Boolean validation2(Candidat candidat) {
        if (candidat.getNiveau() == null || candidat.getSexe() == null || candidat.getSituation() == null)
            return false;
        if (candidat.getVille() == null || candidat.getRegion() == null)
            return false;
        return true;
    }
}


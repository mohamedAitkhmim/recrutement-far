package com.far.recrutement.controllers;

import com.far.recrutement.dao.ConcoursRepository;
import com.far.recrutement.entities.Pagination;
import com.far.recrutement.metier.ICandidatMetier;
import com.far.recrutement.metier.IConcoursMetier;
import com.far.recrutement.metier.IInscriptionMetier;
import com.far.recrutement.metier.MessageService;
import com.far.recrutement.models.Concours;
import com.far.recrutement.util.InscriptionValidation;
import com.far.recrutement.util.Internationalisation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ConcoursController {

    @Autowired
    private ICandidatMetier candidatMetier;
    @Autowired
    private IConcoursMetier concoursMetier;
    @Autowired
    private IInscriptionMetier inscriptionMetier;
    @Autowired
    InscriptionValidation inscriptionValidation;
    @Autowired
    private ConcoursRepository concoursRepository;

    Logger logger = LoggerFactory.getLogger(ConcoursController.class);

    static final String REDIRECT = "redirect:/admin/concours";
    static final String LISTE = "liste";

    @Autowired
    private MessageService messageService;


    @RequestMapping(value = "/concours")
    public String index(HttpSession session, @RequestParam(value = "search", required = false, defaultValue = "") final String search,
                        @RequestParam(value = "page", required = false, defaultValue = "1") final int page,
                        @RequestParam(value = "categorie", required = false, defaultValue = "0") final long categorie,
                        Model model, @RequestParam(value = "armee", required = false, defaultValue = "0") final long armee) {
        Pageable pageable = PageRequest.of(page - 1, 9);
        Page<Concours> listeConcours = concoursMetier.search(search, categorie, armee, pageable);
        Page<Concours> c = concoursRepository.search(search, (int) categorie, (int) armee, pageable);
        listeConcours.forEach(concours -> {
            concours.getEcoleFormation().setDescriptionEcole(concours.getEcoleFormation().getDescriptionEcole().replace("\n", "<br>"));
            concours.getEcoleFormation().setDescriptionEcoleAr(concours.getEcoleFormation().getDescriptionEcoleAr().replace("\n", "<br>"));
        });
        listeConcours = Internationalisation.update(session, listeConcours);
        int totalPages = listeConcours.getTotalPages();
        if (page > totalPages & page != 1) {
            return "redirect:/concours?page=1&search" + search + "=&categorie=" + categorie + "&armee=" + armee;
        }
        Pagination pagination = new Pagination(page, totalPages);
        model.addAttribute("pagination", pagination);
        model.addAttribute(LISTE, listeConcours);
        model.addAttribute("search", search);
        model.addAttribute("categorie", categorie);
        model.addAttribute("armee", armee);
        return "Concours/index";
    }

    @RequestMapping(value = "/concours/details/{concours}")
    public String details(HttpSession session, Model model, @PathVariable(value = "concours") final Long concours) {
        Boolean inscrit = false;
        String restrictions = "";
        Concours concoursO = concoursMetier.getConcoursById(concours);
        if (!concoursO.getVisible()) {
            return "redirect:/";
        }
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            inscrit = inscriptionMetier.inscriptionExists(auth.getName(), concours, null);
            restrictions = inscriptionValidation.getRestrictions(
                    candidatMetier.getCandidatByEmail(auth.getName())
                    , concoursO);
        } catch (Exception ex) {
            logger.debug(ex.toString());
        }
        concoursO.setConditions(concoursO.getConditions().replace("\n", "<br>"));
        concoursO.setConditionsAr(concoursO.getConditionsAr().replace("\n", "<br>"));
        //concoursO.setDescriptionConcours(concoursO.getDescriptionConcours().replace("\n", "<br>"));
        concoursO.getEcoleFormation().setDescriptionEcole(concoursO.getEcoleFormation().getDescriptionEcole().replace("\n", "<br>"));
        concoursO = Internationalisation.update(session, concoursO);
        model.addAttribute("concours", concoursO);
        model.addAttribute("inscrit", inscrit || restrictions.length() > 0);
        if (restrictions.length() == 0) {
            restrictions = messageService.get("dejaInscrit");
        }
        model.addAttribute("restrictions", restrictions);
        return "Concours/details";
    }
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.far.recrutement.controllers;

import com.far.recrutement.dao.FiliereRepository;
import com.far.recrutement.dao.InscriptionRepository;
import com.far.recrutement.entities.Pagination;
import com.far.recrutement.metier.ICandidatMetier;
import com.far.recrutement.metier.IConcoursMetier;
import com.far.recrutement.metier.IInscriptionMetier;
import com.far.recrutement.metier.IStatutMetier;
import com.far.recrutement.metier.MessageService;
import com.far.recrutement.models.Candidat;
import com.far.recrutement.models.Concours;
import com.far.recrutement.models.Filiere;
import com.far.recrutement.models.Inscription;
import com.far.recrutement.models.Statut;
import com.far.recrutement.util.Convocation;
import com.far.recrutement.util.InscriptionValidation;
import com.far.recrutement.util.Internationalisation;
import com.far.recrutement.util.Liste;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserInterfaceController {
    @Autowired
    IInscriptionMetier inscriptionMetier;
    @Autowired
    IConcoursMetier concoursMetier;
    @Autowired
    ICandidatMetier candidatMetier;
    @Autowired
    IStatutMetier statutMetier;
    @Autowired
    Convocation convocation;
    @Autowired
    InscriptionValidation inscriptionValidation;
    @Autowired
    FiliereRepository filiereRepository;
    @Autowired
    InscriptionRepository inscriptionRepository;
    @Autowired
    private MessageService messageService;
    Logger logger = LoggerFactory.getLogger(UserInterfaceController.class);
    static final String REFERER = "Referer";
    static final String MESSAGE = "message";

    public UserInterfaceController() {
    }

    @RequestMapping({"/user/inscriptions"})
    public String lister(Model model, HttpSession session, @RequestParam(value = "page",required = false,defaultValue = "1") final int page) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Liste<Inscription> listeIns = this.inscriptionMetier.getInscriptionByCandidatEmail(auth.getName());
        Candidat candidat = this.candidatMetier.getCandidatByEmail(auth.getName());
        Page<Inscription> liste = listeIns.toPage(page - 1, 50);
        Internationalisation.updateInscriptions(session, liste);
        Pagination pagination = new Pagination(page, liste.getTotalPages());
        Boolean incomplet = (candidat.getOption() == null || candidat.getLangue() == null) && (candidat.getMassar() == null || candidat.getMassar().isEmpty());
        model.addAttribute("incomplet", incomplet);
        model.addAttribute("pagination", pagination);
        model.addAttribute("liste", liste);
        return "UserInterface/list";
    }

    @RequestMapping({"/user/inscriptions/add/{id}"})
    public String add(HttpSession session, HttpServletRequest request, Model model, @PathVariable("id") final Long id, @RequestParam(value = "filiere",required = false) Long filiere) {
        System.err.println("------------->/user/inscriptions/add/{id}");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Concours concours = this.concoursMetier.getConcoursById(id);
        if (!concours.getVisible()) {
            System.err.println("------------->1");
            return "redirect:/";
        } else if (this.inscriptionValidation.isRestricted(this.candidatMetier.getCandidatByEmail(auth.getName()), this.concoursMetier.getConcoursById(id))) {
            System.err.println("------------->2");
            return "redirect:/concours/details?concours=" + id;
        } else {
            if (!this.inscriptionMetier.inscriptionExists(auth.getName(), id, filiere)) {
                System.err.println("------------->3");
                if (filiere == null && !concours.getFilieres().isEmpty()) {
                    List<Long> filieresInscrits = this.inscriptionRepository.getFilieres(auth.getName(), id);
                    List<Filiere> filieres = (List)concours.getFilieres().stream().filter((f) -> {
                        return !filieresInscrits.contains(f.getFiliereID());
                    }).map((f) -> {
                        return Internationalisation.update(session, f);
                    }).collect(Collectors.toList());
                    model.addAttribute("filieres", filieres);
                    model.addAttribute("concours", concours);
                    return "filiere";
                }

                System.err.println("------------->4");
                Candidat candidat = this.candidatMetier.getCandidatByEmail(auth.getName());
                Statut statut = this.statutMetier.getStatutById(1L);
                Statut rejetee = this.statutMetier.getStatutById(3L);
                Inscription inscription = new Inscription(candidat, concours, statut);
                if (filiere != null) {
                    System.err.println("------------->6");
                    inscription.setFiliere((Filiere)this.filiereRepository.getOne(filiere));
                }

                inscription = this.inscriptionValidation.updateStatut(inscription, rejetee);
                long c = this.inscriptionRepository.countInscriptionByCandidat(candidat);
                System.err.println("count : " + c + "id " + candidat.getUserID());
                if (c < 4L) {
                    System.err.println("------------->7");
                    System.err.println("OK");
                    this.inscriptionMetier.addInscription(inscription);
                    model.addAttribute("message", this.messageService.get("inscrit"));
                    model.addAttribute("lienTitre", this.messageService.get("voirMeConcours"));
                    model.addAttribute("lien", "/user/inscriptions");
                } else {
                    model.addAttribute("message", this.messageService.get("dejaInscrit"));
                    String referer = request.getHeader("Referer");
                    model.addAttribute("lien", referer);
                    model.addAttribute("lienTitre", this.messageService.get("retour"));
                }
            } else {
                System.err.println("------------->8");
                model.addAttribute("message", this.messageService.get("dejaInscrit"));
                String referer = request.getHeader("Referer");
                model.addAttribute("lien", referer);
                model.addAttribute("lienTitre", this.messageService.get("retour"));
            }

            return "message";
        }
    }

    @RequestMapping({"/user/nonrecu/{id}"})
    public String nonrecu(HttpServletResponse response, Model model, @PathVariable("id") final Long id) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Candidat candidat = this.candidatMetier.getCandidatByEmail(auth.getName());
        Inscription inscription = this.inscriptionMetier.getInscriptionById(id);
        if (inscription.getStatut().getNomStatut().startsWith("Conv") & inscription.getCandidat().equals(candidat)) {
            inscription.setNonrecu(true);
            this.inscriptionMetier.addInscription(inscription);
            model.addAttribute("message", "Votre convocation sera envoyée à : " + candidat.getEmail() + "</br>Pour plus d'informations appelez ce numéro : " + inscription.getConcours().getEcoleTest().getTelephone());
            model.addAttribute("lien", "/user/inscriptions");
            model.addAttribute("lienTitre", "retour");
            return "message";
        } else {
            return "redirect:/user/inscriptions";
        }
    }
}

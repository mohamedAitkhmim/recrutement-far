package com.far.recrutement.util;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.far.recrutement.models.*;
import org.springframework.data.domain.Page;

public class Internationalisation {

    public static Candidat update(HttpSession session, Candidat candidat) {
        try {
            if (session.getAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE").toString().contains("ar")) {
                candidat.getRegion().setNomRegion(candidat.getRegion().getNomRegionAr());
                candidat.getVille().setNomVille(candidat.getVille().getNomVilleAr());
                candidat.getSexe().setNomSexe(candidat.getSexe().getNomSexeAr());
                candidat.getSituation().setNomSituation(candidat.getSituation().getNomSituationAr());
                if (candidat.getOption() != null) {
                    candidat.getOption().setNomOption(candidat.getOption().getNomOptionAr());
                }
            }
        } catch (Exception ex) {
        }
        return candidat;
    }

    public static Page<Concours> update(HttpSession session, Page<Concours> concoursList) {
        try {
            if (session.getAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE").toString().contains("ar")) {
                for (Concours concours : concoursList) {
                    concours.setTitre(concours.getTitreAr());
                    concours.getCategorie().setNomCategorie(concours.getCategorie().getNomCategorieAr());
                    concours.getEcoleFormation().setNomEcole(concours.getEcoleFormation().getNomEcoleAr());
                    concours.getEcoleFormation().setDescriptionEcole(concours.getEcoleFormation().getDescriptionEcoleAr());
                }
            }
        } catch (Exception ex) {
        }
        return concoursList;
    }

    public static List<Concours> updateConcours(HttpSession session, List<Concours> concoursList) {
        try {
            if (session.getAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE").toString().contains("ar")) {
                for (Concours concours : concoursList) {
                    concours.setTitre(concours.getTitreAr());
                    concours.getEcoleFormation().setNomEcole(concours.getEcoleFormation().getNomEcoleAr());
                    concours.getEcoleFormation().setDescriptionEcole(concours.getEcoleFormation().getDescriptionEcoleAr());
                    concours.getCategorie().setNomCategorie(concours.getCategorie().getNomCategorieAr());
                }
            }
        } catch (Exception ex) {
        }
        return concoursList;
    }

    public static List<Option> updateOptions(HttpSession session, List<Option> options) {
        try {
            if (session.getAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE").toString().contains("ar")) {
                for (Option option : options) {
                    option.setNomOption(option.getNomOptionAr());
                }
            }
        } catch (Exception ex) {
        }
        return options;
    }

    public static List<Categorie> updateCategories(HttpSession session, List<Categorie> categories) {
        try {
            if (session.getAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE").toString().contains("ar")) {
                for (Categorie categorie : categories) {
                    categorie.setNomCategorie(categorie.getNomCategorieAr());
                }
            }
        } catch (Exception ex) {
        }
        return categories;
    }

    public static List<Niveau> updateNiveaux(HttpSession session, List<Niveau> niveaux) {
        try {
            if (session.getAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE").toString().contains("ar")) {
                for (Niveau niveau : niveaux) {
                    niveau.setNiveauScolaire(niveau.getNiveauScolaireAr());
                }
            }
        } catch (Exception ex) {
        }
        return niveaux;
    }

    public static List<Sexe> updateSexes(HttpSession session, List<Sexe> sexes) {
        try {
            if (session.getAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE").toString().contains("ar")) {
                for (Sexe sexe : sexes) {
                    sexe.setNomSexe(sexe.getNomSexeAr());
                }
            }
        } catch (Exception ex) {
        }
        return sexes;
    }

    public static List<EtablissementCategory> updateEtablissementCategory(HttpSession session, List<EtablissementCategory> etablissementCategories) {
        try {
            if (session.getAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE").toString().contains("ar")) {
                for (EtablissementCategory e : etablissementCategories) {
                    e.setNom(e.getNomAr());
                }
            }
        } catch (Exception ex) {
        }
        return etablissementCategories;
    }

    public static List<Langue> updateLangues(HttpSession session, List<Langue> langues) {
        try {
            if (session.getAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE").toString().contains("ar")) {
                for (Langue langue : langues) {
                    langue.setNomLangue(langue.getNomLangueAr());
                }
            }
        } catch (Exception ex) {
        }
        return langues;
    }

    public static List<Situation> updateSituation(HttpSession session, List<Situation> situations) {
        try {
            if (session.getAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE").toString().contains("ar")) {
                for (Situation situation : situations) {
                    situation.setNomSituation(situation.getNomSituationAr());
                }
            }
        } catch (Exception ex) {
        }
        return situations;
    }

    public static List<Statut> updateStatuts(HttpSession session, List<Statut> statuts) {
        try {
            if (session.getAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE").toString().contains("ar")) {
                for (Statut statut : statuts) {
                    statut.setNomStatut(statut.getNomStatutAr());
                }
            }
        } catch (Exception ex) {
        }
        return statuts;
    }

    public static List<Region> updateRegions(HttpSession session, List<Region> regions) {
        try {
            if (session.getAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE").toString().contains("ar")) {
                for (Region region : regions) {
                    region.setNomRegion(region.getNomRegionAr());
                }
            }
        } catch (Exception ex) {
        }
        return regions;
    }

    public static List<Ville> updateVilles(HttpSession session, List<Ville> villes) {
        try {
            if (session.getAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE").toString().contains("ar")) {
                for (Ville ville : villes) {
                    ville.setNomVille(ville.getNomVilleAr());
                }
            }
        } catch (Exception ex) {
        }
        return villes;
    }

    public static Concours update(HttpSession session, Concours concours) {
        try {
            if (session.getAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE").toString().contains("ar")) {
                concours.setDescriptionConcours(concours.getDescriptionConcoursAr());
                concours.setConditions(concours.getConditionsAr());
                concours.getEcoleFormation().setNomEcole(concours.getEcoleFormation().getNomEcoleAr());
                concours.getEcoleFormation().setDescriptionEcole(concours.getEcoleFormation().getDescriptionEcoleAr());
                concours.setTitre(concours.getTitreAr());
                for (Filiere f : concours.getFilieres()) {
                    f.setNom(f.getNomAr());
                }
            }
        } catch (Exception ex) {
        }
        return concours;
    }

    public static Filiere update(HttpSession session, Filiere filiere) {
        try {
            if (session.getAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE").toString().contains("ar")) {
                filiere.setNom(filiere.getNomAr());
            }
        } catch (Exception ex) {
        }
        return filiere;
    }

    public static void updateInscriptions(HttpSession session, Page<Inscription> inscriptions) {
        if (session.getAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE") != null && session.getAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE").toString().contains("ar")) {
            inscriptions.forEach(inscription -> {
                Concours concours = inscription.getConcours();
                concours.setTitre(concours.getTitreAr());
                concours.getEcoleFormation().setNomEcole(concours.getEcoleFormation().getNomEcoleAr());
                concours.getEcoleFormation().setDescriptionEcole(concours.getEcoleFormation().getDescriptionEcoleAr());
                concours.getCategorie().setNomCategorie(concours.getCategorie().getNomCategorieAr());

                inscription.getStatut().setNomStatut(inscription.getStatut().getNomStatutAr());
                if (inscription.getFiliere() != null) {
                    inscription.getFiliere().setNom(inscription.getFiliere().getNomAr());
                }
            });
        }
    }


}

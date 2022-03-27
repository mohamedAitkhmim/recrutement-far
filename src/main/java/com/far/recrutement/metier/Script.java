package com.far.recrutement.metier;

import com.far.recrutement.dao.*;
import com.far.recrutement.models.*;
import com.far.recrutement.util.EmailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class Script {

    @Autowired
    CandidatRepository candidatRepository;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    EmailSender emailSender;
    @Autowired
    ConcoursRepository concoursRepository;
    @Autowired
    EcoleRepository ecoleRepository;
    @Autowired
    CategorieRepository categorieRepository;
    @Autowired
    SexeRepository sexeRepository;
    private static final Logger logger = LoggerFactory.getLogger(Script.class);
    @Autowired
    ConcoursMetier concours;
    @Autowired
    ArmeeRepository armeeRepository;
    @Autowired
    FiliereRepository filieres;
    @Autowired
    InscriptionRepository inscriptionRepository;

    public void profilIncomplet() {
        List<Candidat> candidats = candidatRepository.getIncompleteProfileCandidats();
        System.out.println("candidats profil incomplet :" + candidats.size());
        candidats.forEach(candidat -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("candidat avec profil incomplet id:" + candidat.getUserID());
            emailSender.sendEmailIncomplet(null, candidat);
        });
    }

    public void bulletin() {
        List<Candidat> candidats = candidatRepository.getCRPTA();
        System.out.println("candidats profil demande bulletin :" + candidats.size());
        candidats.forEach(candidat -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                if (candidat != null) {
                    System.out.println("candidat crpta id:" + candidat.getUserID());
                    emailSender.sendEmailBulletin(null, candidat);
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });
    }

    public void inscriptionIncomplet() {
        List<BigInteger> candidats = candidatRepository.candidatsWithoutInscription();
        System.out.println("candidats sans inscription :" + candidats.size());
        candidats.forEach(candidat -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Candidat c = candidatRepository.findById(candidat.longValue()).orElse(null);
            if (c != null) {
                System.out.println("candidat sans inscription id:" + c.getUserID());
                emailSender.sendEmailSansInscription(null, c);
            }
        });
    }

    public void convocation() {
        List<Inscription> inscriptions = inscriptionRepository.findByStatutStatutID(2L);
        inscriptions
                .stream()
                .filter(inscription -> inscription.getDownloaded() == null)
                .forEach(inscription -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        Candidat c = inscription.getCandidat();
                        if (c != null) {
                            System.out.println("candidat convoqué id:" + c.getUserID());
                            emailSender.sendEmailConvocation(null, c);
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                });
    }

    public void convocation3() {
        List<Inscription> inscriptions = inscriptionRepository.findByStatutStatutID(4L);
        inscriptions
                .stream()
                .forEach(inscription -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        Candidat c = inscription.getCandidat();
                        if (c != null) {
                            System.out.println("candidat convoqué id:" + c.getUserID());
                            emailSender.sendEmailConvocation(null, c);
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                });
    }

    public void enableUsersWithEmail() {
        List<Candidat> candidats = candidatRepository.getInactiveCandidats();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity entity = new HttpEntity<>(headers);
        System.out.println("candidats for enable account :" + candidats.size());
        candidats.forEach(candidat -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost/activer/" + candidat.getCodeActivation(), HttpMethod.GET, entity, String.class);
            System.out.println(responseEntity.getStatusCode());
        });
    }

    @Autowired
    EtudeRepository etudeRepository;
    @Autowired
    OptionMetier options;
    @Autowired
    OptionRepository optionRepository;
    @Autowired
    EcoleMetier ecoles;
    @Autowired
    NiveauMetier niveaux;

    @Transactional
    public void medecin() {
        Etude bac = etudeRepository.findById(2L).orElse(null);
        niveaux.addNiveau(new Niveau("Médecin spécialiste", "طبيب متخصص", bac));

        Categorie officier = categorieRepository.findById(1L).get();
        List<Categorie> off = new ArrayList<>();
        off.add(officier);
        Armee sante = armeeRepository.findById(4L).get();
        Ecole erssm2 = ecoleRepository.findById(8L).orElse(null);
        erssm2.setDescriptionEcole("Dans le cadre du renforcement de ses structures sanitaires, les Forces Armés Royales (FAR) organisent un concours pour le recrutement par voie d’intégration des médecins généralistes.\n" +
                "L'intégration au sein du service de santé militaire permet aussi de :\n" +
                "-\tTravailler au sein d'un système sanitaire d'excellence ;\n" +
                "-\tBénéficier d'une formation continue adaptée, au Maroc et à l'étranger ;\n" +
                "-\tDisposer d'un large éventail d'activités et d'opportunités de recherche scientifique ;\n" +
                "-\tGarantir une évolution dans le secteur ambulatoire et dans la hiérarchie des officiers ;\n" +
                "-\tBénéficier des garanties statutaires et de la couverture sociale des FAR. \n");
        erssm2.setDescriptionEcoleAr("تنظم القوات المسلحة الملكية مباراة توظيف لفائدة الأطباء المغاربة الحاصلين على شهادة الدكتوراة في \"الطب العام\".\n" +
                "سيتيح هذا الإنخراط للراغبين في الانضمام إلى صفوف القوات المسلحة الملكية فرص العمل كضباط في إطار منظومة صحية متميزة على الصعيد الوطني  والدولي وضمن فرق طبية ذات خبرة عالية وكذا مواكبة تطورات البحث العلمي المستمر في المستشفيات العسكرية والهيئات التشاركية و الإستفادة من تكوين مستمر خلال المسيرة المهنية.\n" +
                "كما يمكنهم هذا الإنخراط من الإرتقاء في القطاع الطبي و التسلسل الهرمي في إطار مقتضيات النظام الأساسي لضباط القوات المسلحة الملكية و الإستفادة من الضمانات القانونية والخدمات الإجتماعية للقوات المسلحة الملكية.\n");
        ecoleRepository.save(erssm2);

        String DATE_FIN_SOFF = "30/10/2020";
        String DATE_DEBUT_SOFF = "05/10/2020";

        Concours con = new Concours();
        con.setTitre("CONCOURS DE RECRUTEMENT PAR VOIE D’INTEGRATION DES MEDECINS GENERALISTES NATIONAUX");
        con.setTitreAr("مبارات التوظيف عن طريق الادماج");
        con.setCategorie(officier);
        con.setEcoleFormation(erssm2);
        con.setEcoleTest(erssm2);
        con.setDisponible(true);
        con.setAgemax(35);
        con.setConditions("-\tEtre de nationalité marocaine ;\n" +
                "-\tEtre âgé de <b>35</b> ans au plus au <b>30.10.2020</b> ;\n" +
                "-\tEtre titulaire du diplôme de doctorat en médecine générale délivré par les facultés publiques ou privées accréditées, nationales ou étrangères ;\n" +
                "-\tEtre retenu par la commission de présélection sur dossier.");
        con.setConditionsAr("- أن لا يتجاوز سن المرشح 35 سنة لغاية 30 أكتوبر 2020، \n" +
                "- أن يكون حاصلا على شهادة الدكتوراة في الطب العام المسلمة من طرف الكليات العمومية و الخاصة المعتمدة الوطنية والدولية.\n" +
                "- أن يتم قبوله من طرف لجنة الانتقاء الأولي.\n");
        con.setArmee(sante);
        con.setAvis("/doc/avis/medecins.pdf");
        con.setImage("06ea6c87-a53d-4618-96ba-546b401b6db3.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT_SOFF));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
        } catch (ParseException e) {
            logger.warn("parse exception " + e);
        }
        con.setBacancien(200);
        concours.addConcours(con);
        logger.info("concours erssm integration médecins créé avec succées");

    }

    @Transactional
    public void ista() {

        Etude ista = etudeRepository.findById(4L).orElse(null);

        if (concours.size() <= 21) {
            inscriptionRepository.deleteByCandidatOptionEtudeEtudeID(4L);
            candidatRepository.deleteByOptionEtudeEtudeID(4L);
            optionRepository.deleteByEtudeEtudeID(4L);
            options.addOption(new Option("Administrateurs des réseaux et systèmes", "ادارة الشبكة والانظمة", ista));
            options.addOption(new Option("Assistance dentaire", "مساعد في طب الأسنان", ista));
            options.addOption(new Option("Construction et fabrication métallique", "البناء والتركيب المعدني", ista));
            options.addOption(new Option("Dessinateur projeteur", "رسم البناء", ista));
            options.addOption(new Option("Diagnostic et électronique embarquée", "التشخيص والكترونيك السيارات", ista));
            options.addOption(new Option("Electricité et électronique automobile", "الكهرباء والكترونيات السيارات", ista));
            options.addOption(new Option("Electromécanique des systèmes automatisés", "إلكتروميكانيك الأنظمة التلقائية", ista));
            options.addOption(new Option("Exploitation en transport", "تقني متخصص في وسائل النقل", ista));
            options.addOption(new Option("Fabrication mécanique", "الصناعة الميكانيكية", ista));
            options.addOption(new Option("Génie climatique", "هندسة التكييف", ista));
            options.addOption(new Option("Géomètre topographe", "الهندسة الطبوغرافية", ista));
            options.addOption(new Option("Gestion hôtellerie : Hébergement et restauration", "التدبير الفندقي : المطعمة و الإيواء", ista));
            options.addOption(new Option("Gros œuvres", "الاشغال الكبرى", ista));
            options.addOption(new Option("Infographie", "الطبع المعلوماتي", ista));
            options.addOption(new Option("Mécatronique", "ميكاترونيك", ista));
            options.addOption(new Option("Maintenance des engins lourds et véhicules industriels", "صيانة الاليات الثقيلة والمركبات الصناعية", ista));
            options.addOption(new Option("Maintenance des installations industrielles", "صيانة المنشات الصناعية", ista));
            options.addOption(new Option("Prothésiste dentaire", "طقامي الأسنان", ista));
            options.addOption(new Option("Responsable d’exploitation logistique", "مسؤول استغلال اللوجستيك", ista));
            options.addOption(new Option("Technicien d’impression", "فن الطباعة", ista));
            options.addOption(new Option("Réparation des engins à moteur : option automobile", "تقني متخصص في الألات ذات المحرك: مصلح السيارات", ista));
            options.addOption(new Option("Techniques en four et matériel de cuisson", "تقنيات الفرن ومعدات الطبخ", ista));
            options.addOption(new Option("Méthodes, production et qualité option automobile", "تقني متخصص في المناهج، الإنتاج والجودة تخصص السيارات", ista));
            options.addOption(new Option("Techniques de développement informatique", "تقنيات التنمية في المعلوميات", ista));
            options.addOption(new Option("Techniques de secrétariat de direction", "تقنيات كتابة الإدارة", ista));
            options.addOption(new Option("Techniques des réseaux informatiques", "تقنيات الشبكات المعلوميات", ista));
            options.addOption(new Option("Techniques de télécommunications", "تقني متخصص في الإتصالات", ista));
            options.addOption(new Option("Usinage sur machines-outils à commande numérique", "التصنيع على الالات ذات التحكم الرقمي", ista));
            options.addOption(new Option("Efficacité énergétique", "النجاعة الطاقية", ista));

            options.addOption(new Option("Techniques de développement multimédia", "تقنيات تطوير الوسائط المتعددة", ista));
            options.addOption(new Option("Audio visuelle", "السمعي البصري", ista));
            options.addOption(new Option("Génie civil", "الهندسة المدنية", ista));
            options.addOption(new Option("Conducteur de travaux : Travaux publics", "مسير الاشغال : الاشغال  العمومية", ista));

            options.addOption(new Option("Automatisation et instrumentation industrielle", "الآلية و الأدواتية الصناعية", ista));
            options.addOption(new Option("Fabrication en électronique", "الصناعة الالكترونية", ista));
            options.addOption(new Option("Construction métallique", "التركيب المعدني", ista));
            options.addOption(new Option("Diagnostic et électronique embarquée", "التشخيص و الالكترونيات المدمجة", ista));
            options.addOption(new Option("Mécanicien agricole", "ميكانيك الزراعة", ista));
            options.addOption(new Option("Dessinateur de bâtiment ", "رسم البناء", ista));

            options.addOption(new Option("Autres", "آخر", ista));


            String DATE_FIN_SOFF = "30/09/2020";
            String DATE_DEBUT_SOFF = "14/09/2020";

            Categorie sofficier = categorieRepository.findById(2L).get();
            List<Categorie> soff = new ArrayList<>();
            soff.add(sofficier);

            Armee aviation = armeeRepository.findById(1L).get();
            Armee marine = armeeRepository.findById(2L).get();
            Armee terre = armeeRepository.findById(3L).get();
            Armee sante = armeeRepository.findById(4L).get();
            Armee fa = armeeRepository.findById(5L).get();

            Ecole far = ecoles.addEcole(new Ecole("FORCES ARMEES ROYALES"
                    , "Le concours d’admission au cycle des Elèves Sous-officiers issus des Instituts Supérieurs de Technologie Appliquée (ISTA) de l’Armée de terre, est organisé au titre de l’année 2020 dans les spécialités suivantes :\n" +
                    "-\tAdministrateurs des réseaux et systèmes ;\n" +
                    "-\tAssistant dentaire ;\n" +
                    "-\tAudio-visuelle ;\n" +
                    "-\tConstruction et fabrication métallique ;\n" +
                    "-\tDessinateur projeteur ;\n" +
                    "-\tDiagnostic et électronique embarquée ;\n" +
                    "-\tElectricité et électronique automobile ;\n" +
                    "-\tElectromécanique des systèmes automatisés ;\n" +
                    "-\tExploitation en transport ;\n" +
                    "-\tFabrication mécanique ;\n" +
                    "-\tGénie climatique ;\n" +
                    "-\tGéomètre topographe ;\n" +
                    "-\tGestion hôtellerie : Hébergement et restauration ;\n" +
                    "-\tGros œuvres ;\n" +
                    "-\tInfographie ;\n" +
                    "-\tMécatronique ;\n" +
                    "-\tMaintenance des engins lourds et véhicules industriels ;\n" +
                    "-\tMaintenance des installations industrielles ;\n" +
                    "-\tProthésiste dentaire ;\n" +
                    "-\tResponsable d’exploitation logistique ;\n" +
                    "-\tTechnicien d’impression ;\n" +
                    "-\tRéparation des engins à moteur : option automobile ;\n" +
                    "-\tTechniques en four et matériel de cuisson ;\n" +
                    "-\tMéthodes, production et qualité option automobile ;\n" +
                    "-\tTechniques de développement informatique ;\n" +
                    "-\tTechniques de secrétariat de direction ;\n" +
                    "-\tTechniques des réseaux informatiques ;\n" +
                    "-\tTechniques de télécommunications ;\n" +
                    "-\tUsinage sur machines-outils à commande numérique;\n-\tEfficacité énergétique."
                    , ""
                    , "تنظم القوات المسلحة الملكية مباراة لفائدة الشبان المغاربة  التي لا تتجاوز أعمارهم 25 سنة لغاية فاتح اكتوبر2020، الراغبين في الانخراط في سلك ضباط صف القوات البرية، خريجي المعاهد العليا للتكنولوجيا التطبيقية، و ذلك ضمن التخصصات التالية :\n" +
                    "-\tصيانة الاليات الثقيلة والمركبات الصناعية ،\n" +
                    "-\tصيانة المنشات الصناعية ،\n" +
                    "-\tميكاترونيك ،\n" +
                    "-\tطقامي الأسنان ،\n" +
                    "-\tمسؤول استغلال اللوجستيك ،\n" +
                    "-\t فن الطباعة ،\n" +
                    "-\tتقني متخصص في الألات ذات المحرك: مصلح السيارات ،\n" +
                    "-\tتقنيات الفرن ومعدات الطبخ ،\n" +
                    "-\tتقني متخصص في المناهج، الإنتاج والجودة تخصص السيارات ،\n" +
                    "-\tتقنيات التنمية في المعلوميات ،\n" +
                    "-\tتقنيات كتابة الإدارة ،\n" +
                    "-\tتقنيات الشبكات المعلوميات ،\n" +
                    "-\tتقني متخصص في الإتصالات ،\n" +
                    "-\tالتصنيع على الالات ذات التحكم الرقمي.\n-\tادارة الشبكة والانظمة ،\n" +
                    "-\tمساعد في طب الأسنان ،\n" +
                    "-\tالسمعي البصري ،\n" +
                    "-\t البناء والتركيب المعدني ،\n" +
                    "-\tرسم البناء ،\n" +
                    "-\tالتشخيص والكترونيك السيارات ،\n" +
                    "-\tالكهرباء والكترونيات السيارات ،\n" +
                    "-\tإلكتروميكانيك الأنظمة التلقائية ،\n" +
                    "-\tتقني متخصص في وسائل النقل ،\n" +
                    "-\tالصناعة الميكانيكية ،\n" +
                    "-\tهندسة التكييف، \n" +
                    "-\tالهندسة الطبوغرافية ،\n" +
                    "-\t التدبير الفندقي : المطعمة و الإيواء ،\n" +
                    "-\tالاشغال الكبرى ،\n" +
                    "-\tالطبع المعلوماتي ،\n" +
                    "-\tالنجاعة الطاقية\n"
                    //TODO; update with convocation info
                    , soff, "/insignes/erart.png", "à l'Ecole Royale de l'Artillerie - Fès", "Colonel-Major, Directeur de l'Ecole Royale de l'Artillerie", "Fès"));

            Ecole efsno = ecoles.addEcole(new Ecole("FORCES ROYALES AIR"
                    , "Le concours d’admission au cycle des Elèves Sous-officiers issus des Instituts Supérieurs de Technologie Appliquée (ISTA) des Forces Royales Air, est organisé au titre de l’année 2020 dans les spécialités suivantes :\n" +
                    "-\tTechniques de Secrétariat de Direction ;\t\n" +
                    "-\tTechniques de développement informatique ; \t\n" +
                    "-\tTechniques des réseaux informatiques ;\n" +
                    "-\tTechniques de développement multimédia (masculins);\n" +
                    "-\tInfographie ;\n" +
                    "-\tAudio-visuelle (masculins);\n-\tGéomètre topographe (masculins) ;\n" +
                    "-\tElectromécanique des systèmes automatisés (masculins) ;\n" +
                    "-\tGénie Climatique (masculins) ;\n" +
                    "-\tGénie civil (masculins) ;\n" +
                    "-\tConducteur de travaux : Travaux publics (masculins) ;\n" +
                    "-\tGros œuvres (masculins);\n-\tEfficacité énergétique."
                    , ""
                    , "تنظم القوات المسلحة الملكية مباراة لفائدة الشبان المغاربة  التي لا تتجاوز أعمارهم 25 سنة لغاية فاتح اكتوبر2020، الراغبين في الانخراط في سلك تلاميذ ضباط صف القوات الملكية الجوية ، خريجي المعاهد العليا للتكنولوجيا التطبيقية، و ذلك ضمن التخصصات التالية :\n" +
                    "-\tتقنيات كتابة الإدارة،\n" +
                    "-\tالهندسة  الطبوغرافية (ذكورا)،\n" +
                    "-\tتقنيات التنمية في المعلوميات،\nالكتروميكانيك الأنظمة التلقائية (ذكورا)،-\t\n" +
                    "-\tتقنيات شبكات المعلوميات،\n-\tهندسة التكييف (ذكورا)،\n" +
                    "-\tتقنيات تطوير الوسائط المتعددة (ذكورا)، -\n-\tالهندسة المدنية (ذكورا)،\n" +
                    "-\tالطبع المعلوماتي،\n" +
                    "-\tالسمعي البصري (ذكورا)،\n-\tمسير الاشغال : الاشغال  العمومية (ذكورا) ،\n" +
                    "-\tالأشغال الكبرى (ذكورا).\n" +
                    "-\tالسمعي البصري  (ذكورا)،\n-الأشغال الكبرى (ذكورا).-\t\n" +
                    "-\tالنجاعة الطاقية\n"
                    , soff, "/insignes/efsno.png", "à l'Ecole de Formation des Spécialistes Non-Officiers - Marrakech", "Colonel, Commandant la Base Ecoles des Forces Royales Air", "Marrakech"));

            Ecole cimr = ecoles.addEcole(new Ecole("Marine Royale"
                    , "Le concours d’admission au cycle des Elèves Sous-officiers issus des Instituts Supérieurs de Technologie Appliquée (ISTA) de la Marine Royale, est organisé au titre de l’année 2020 dans les spécialités suivantes :\n" +
                    "-\tGénie climatique ; \t\n" +
                    "-\tAutomatisation et instrumentation industrielle ;\n" +
                    "-\tFabrication en électronique ;\n" +
                    "-\tDessinateur de bâtiment ;\n-\tInfographie ;\n" +
                    "-\tConstruction métallique ;\n" +
                    "-\tDiagnostic et électronique embarquée ;\n" +
                    "-\tMécanicien agricol;\n-\tEfficacité énergétique."
                    , "البحرية الملكية"
                    , "تنظم القوات المسلحة الملكية مباراة لفائدة الشبان المغاربة التي لا تتجاوز أعماره25 سنة لغاية فاتح اكتوبر2020، الراغبين في الانخراط في سلك تلاميذ ضباط صف البحرية الملكية ، خريجي المعاهد العليا للتكنولوجيا التطبيقية، و ذلك ضمن التخصصات التالية :\n" +
                    "\n" +
                    "-\tهندسة التكييف،\n-\tالطبع المعلوماتي،\n" +
                    "-\tالآلية و الأدواتية الصناعية،\n-\tالتركيب المعدني،\n" +
                    "-\tالصناعة الالكترونية،\n-\tالتشخيص و الالكترونيات المدمجة،\n" +
                    "-\tرسم البناء،\n-\tميكانيك الزراعة.\n" +
                    "-\tالنجاعة الطاقية\n"
                    , soff, "/insignes/cimr.png", "au Centre d’Instruction de la Marine Royale - Casablanca", "Capitaine de Vaisseau, Commandant le Centre d’Instruction de la Marine Royale", "Casablanca"));

            Concours con = new Concours();
            con.setTitre("CONCOURS D’ADMISSION AU CYCLE DES ELEVES SOUS-OFFICIERS ISSUS DES ISTA DE L’ARMEE DE TERRE ");
            con.setTitreAr("مـبـاراة ولوج سلك تلاميذ ضباط صف القوات البرية، خريجي المعاهد العليا للتكنولوجيا التطبيقية");
            con.setCategorie(sofficier);
            con.setEcoleFormation(far);
            con.setEcoleTest(far);
            con.setDisponible(true);
            con.setAgemax(25);
            con.setAgemin(18);
            con.setConditions("-\tEtre de nationalité marocaine ; \n" +
                    "-\tEtre célibataire ;\n" +
                    "-\tN’avoir aucun antécédent judiciaire ;\n" +
                    "-\tEtre âgé de <b>25 ans au maximum</b>, à la date du <b>1er octobre 2020</b> ;\n" +
                    "-\tEtre bachelier ;\n" +
                    "-\tEtre titulaire du <b>diplôme de technicien spécialisé</b>  délivré par l’un des ISTA relevant de l’OFPPT du Royaume ;\n" +
                    "-\tEtre apte physiquement ;  \n" +
                    "-\tAvoir au minimum une taille de 1,65 m pour les garçons et 1,60 m pour les filles ;\n" +
                    "-\tEtre retenu par la commission de présélection sur dossier.");
            con.setConditionsAr("- الحالة العائلية عازب،\n" +
                    "- بدون سوابق عدلية،\n" +
                    "- أن يكون المرشح حاصلا على شهادة البكالوريا،\n" +
                    "- أن يكون المرشح حاصلا على دبلوم التقني المتخصص المسلمة من طرف احدى المعاهد العليا للتكنولوجيا التطبيقية التابعة لمؤسسات التكوين المهني و انعاش الشغل،\n" +
                    " - التمتع بصحة جسمانية جيدة،\n" +
                    "- القامة 1,65  متر على الأقل بالنسبة للذكور و1,60  متر على الأقل بالنسبة للإناث،\n" +
                    "- ان يتم قبوله من طرف لجنة الانتقاء الأولي.\n");
            con.setArmee(terre);
            con.setAvis("/doc/avis/istaterre.pdf");
            con.setImage("61c875cb-1fb3-4262-9133-b781e991a16b.jpg");
            try {
                con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
                con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT_SOFF));
                con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
            } catch (ParseException e) {
                logger.warn("parse exception " + e);
            }
            concours.addConcours(con);
            logger.info("concours ISTA terre créé avec succées");

            con = new Concours();
            con.setTitre("CONCOURS D’ADMISSION AU CYCLE DES ELEVES SOUS-OFFICIERS ISSUS DES ISTA DES FORCES ROYALES AIR");
            con.setTitreAr("مـبـاراة ولوج سلك تلاميذ ضباط صف القوات الملكية الجوية، خريجي المعاهد العليا للتكنولوجيا التطبيقية");
            con.setCategorie(sofficier);
            con.setEcoleFormation(efsno);
            con.setEcoleTest(efsno);
            con.setDisponible(true);
            con.setAgemax(25);
            con.setAgemin(18);
            con.setConditions("-\tEtre de nationalité marocaine ; \n" +
                    "-\tEtre célibataire ;\n" +
                    "-\tN’avoir aucun antécédent judiciaire ;\n" +
                    "-\tEtre âgé de <b>25 ans au maximum</b>, à la date du <b>1er octobre 2020</b> ;\n" +
                    "-\tEtre bachelier ;\n" +
                    "-\tEtre titulaire du <b>diplôme de technicien spécialisé</b>  délivré par l’un des ISTA relevant de l’OFPPT du Royaume ;\n" +
                    "-\tEtre apte physiquement ;  \n" +
                    "-\tAvoir au minimum une taille de 1,65 m pour les garçons et 1,60 m pour les filles ;\n" +
                    "-\tEtre retenu par la commission de présélection sur dossier.");
            con.setConditionsAr("- الحالة العائلية عازب،\n" +
                    "- بدون سوابق عدلية،\n" +
                    "- أن يكون المرشح حاصلا على شهادة البكالوريا،\n" +
                    "- أن يكون المرشح حاصلا على دبلوم التقني المتخصص المسلمة من طرف احدى المعاهد العليا للتكنولوجيا التطبيقية التابعة لمؤسسات التكوين المهني و انعاش الشغل،\n" +
                    " - التمتع بصحة جسمانية جيدة،\n" +
                    "- القامة 1,65  متر على الأقل بالنسبة للذكور و1,60  متر على الأقل بالنسبة للإناث،\n" +
                    "- ان يتم قبوله من طرف لجنة الانتقاء الأولي.\n");
            con.setArmee(aviation);
            con.setAvis("/doc/avis/istafra.pdf");
            con.setImage("a9c854cd-3b6a-46c8-a04e-13fa677ec1b3.jpg");
            try {
                con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
                con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT_SOFF));
                con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
            } catch (ParseException e) {
                logger.warn("parse exception " + e);
            }
            concours.addConcours(con);
            logger.info("concours ISTA FRA créé avec succées");

            con = new Concours();
            con.setTitre("CONCOURS D’ADMISSION AU CYCLE DES ELEVES SOUS-OFFICIERS ISSUS DES ISTA DE LA MARINE ROYALE");
            con.setTitreAr("مـبـاراة ولوج سلك تلاميذ ضباط صف البحرية الملكية، خريجي المعاهد العليا للتكنولوجيا التطبيقية");
            con.setCategorie(sofficier);
            con.setEcoleFormation(cimr);
            con.setEcoleTest(cimr);
            con.setDisponible(true);
            con.setAgemax(25);
            con.setAgemin(18);
            con.setConditions("-\tEtre de nationalité marocaine ; \n" +
                    "-\tEtre célibataire ;\n" +
                    "-\tN’avoir aucun antécédent judiciaire ;\n" +
                    "-\tEtre âgé de <b>25 ans au maximum</b>, à la date du <b>1er octobre 2020</b> ;\n" +
                    "-\tEtre bachelier ;\n" +
                    "-\tEtre titulaire du <b>diplôme de technicien spécialisé</b>  délivré par l’un des ISTA relevant de l’OFPPT du Royaume ;\n" +
                    "-\tEtre apte physiquement ;  \n" +
                    "-\tAvoir au minimum une taille de 1,65 m pour les garçons et 1,60 m pour les filles ;\n" +
                    "-\tEtre retenu par la commission de présélection sur dossier.");
            con.setConditionsAr("- الحالة العائلية عازب،\n" +
                    "- بدون سوابق عدلية،\n" +
                    "- أن يكون المرشح حاصلا على شهادة البكالوريا،\n" +
                    "- أن يكون المرشح حاصلا على دبلوم التقني المتخصص المسلمة من طرف احدى المعاهد العليا للتكنولوجيا التطبيقية التابعة لمؤسسات التكوين المهني و انعاش الشغل،\n" +
                    " - التمتع بصحة جسمانية جيدة،\n" +
                    "- القامة 1,65  متر على الأقل بالنسبة للذكور و1,60  متر على الأقل بالنسبة للإناث،\n" +
                    "- ان يتم قبوله من طرف لجنة الانتقاء الأولي.\n");
            con.setArmee(marine);
            con.setAvis("/doc/avis/istamarine.pdf");
            con.setImage("ce180b2b-2c9a-4832-bfe3-5f2c9b4214ca.jpg");
            try {
                con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
                con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT_SOFF));
                con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
            } catch (ParseException e) {
                logger.warn("parse exception " + e);
            }
            concours.addConcours(con);
            logger.info("concours ISTA MARINE créé avec succées");
        }

    }

    public void updateConcours() {
        Long size = concoursRepository.count();
        if (size < 10) {
            for (Long i = 1L; i < 5L; i++) {
                Concours concours = concoursRepository.findById(i).get();
                concours.setDisponible(false);
                concoursRepository.save(concours);
            }
        }

        String DATE_FIN_SOFF = "30/07/2020";
        String DATE_DEBUT_SOFF = "15/06/2020";

        Ecole erart = ecoleRepository.findById(4L).get();
        Ecole erb = ecoleRepository.findById(9L).get();
        Ecole cit = ecoleRepository.findById(10L).get();
        Ecole cig = ecoleRepository.findById(11L).get();
        Ecole cift = ecoleRepository.findById(12L).get();
        Ecole erm = ecoleRepository.findById(13L).get();
        Ecole eri = ecoleRepository.findById(14L).get();
        Ecole erc = ecoleRepository.findById(15L).get();
        Ecole ciss = ecoleRepository.findById(16L).get();
        Ecole cii = ecoleRepository.findById(17L).get();
        Ecole efsno = ecoleRepository.findById(18L).get();
        Ecole cimr = ecoleRepository.findById(19L).get();
        Ecole cita = ecoleRepository.findById(20L).get();
        Ecole erpplm = ecoleRepository.findById(21L).get();
        Ecole csfar = ecoleRepository.findById(22L).get();

        Sexe feminin = sexeRepository.findById(2L).get();
        Sexe masculin = sexeRepository.findById(2L).get();

        Categorie sofficier = categorieRepository.findById(2L).get();

        List<Sexe> masculins = new ArrayList<>();
        List<Sexe> feminins = new ArrayList<>();
        masculins.add(masculin);
        feminins.add(feminin);

        Armee aviation = armeeRepository.findById(1L).get();
        Armee marine = armeeRepository.findById(2L).get();
        Armee terre = armeeRepository.findById(3L).get();
        Armee sante = armeeRepository.findById(4L).get();
        Armee fa = armeeRepository.findById(5L).get();

        Concours con = new Concours();
        con.setTitre("Concours d'admission au cycle des Elèves Sous-Officiers de l'Armée de Terre -Spécialité Infanterie-");
        con.setTitreAr("مـبـاراة ولوج سلك تلاميذ ضباط الصف (المشاة)");
        con.setCategorie(sofficier);
        con.setEcoleFormation(eri);
        con.setEcoleTest(eri);
        con.setDisponible(true);
        con.setAgemax(23);
        con.setAgemin(18);
        con.setBacancien(2);
        con.setSexe(masculins);
        con.setConditions("-\tEtre de nationalité marocaine ; \n" +
                "-\tEtre célibataire ;\n" +
                "-\tN’avoir aucun antécédent judiciaire ;\n" +
                "-\tEtre âgé de <b>18 ans au minimum et de 23 ans au maximum</b>, à la date du <b>16.08.2020</b> ;\n" +
                "-\tEtre titulaire d’un baccalauréat des années <b>2018</b> ou <b>2019</b> ou <b>2020</b> ;\n" +
                "-\tEtre apte physiquement ;  \n" +
                "-\tAvoir au minimum une taille de 1,70 m" +
                "-\tEtre retenu par la commission de présélection sur dossier.\n" +
                "Les candidats ayant les conditions suscitées, doivent s’inscrire sur le présent site web avant le <b>30 juillet 2020 inclus.</b>\n" +
                "Ils doivent choisir <b>trois (03)</b> spécialités par ordre de priorité et seront ventilés sur l’une des options en fonction des profils et quotas fixés par spécialité.\n");
        con.setConditionsAr("- الحالة العائلية عازب ،\n" +
                "- بدون سوابق عدلية،\n" +
                "- أن يكون المرشح حاصلا على شهادة البكالوريا دورات 2018 أو 2019 أو 2020،\n" +
                "- التمتع بصحة جسمانية جيدة،\n" +
                "- القامة 1,70  متر على الأقل،\n" +
                "- ان يتم قبوله  من طرف لجنة الانتقاء الأولي.\n" +
                "يتعين على المرشحين المتوفرين على الشروط أعلاه تسجيل طلباتهم على هذا الموقع الالكتروني وذلك قبل 30 يوليوز 2020  كآخر أجل لقبول الترشيحات.\n" +
                "على المرشحين اختيار ثلاث تخصصات حسب الأولوية و سيتم توزيعهم على واحد من هذه التخصصات بالاعتماد على مواصفات و عدد المناصب المطلوبة لكل تخصص.\n");
        con.setArmee(terre);
        con.setAvis("/doc/avis/soffterre.pdf");
        con.setImage("13f91e95-183f-41ea-a7b8-702471b43d2e.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT_SOFF));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
        } catch (ParseException e) {
            logger.warn("parse exception " + e);
        }
        concours.addConcours(con);
        logger.info("concours ERI créé avec succées");

        //CIB

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des Elèves Sous-Officiers de l'Armée de Terre -Spécialité Arme Blindée-");
        con.setTitreAr("مـبـاراة ولوج سلك تلاميذ ضباط الصف (المدرعات)");
        con.setCategorie(sofficier);
        con.setEcoleFormation(erb);
        con.setEcoleTest(erb);
        con.setDisponible(true);
        con.setAgemax(23);
        con.setAgemin(18);
        con.setBacancien(2);
        con.setSexe(masculins);
        con.setConditions("-\tEtre de nationalité marocaine ; \n" +
                "-\tEtre célibataire ;\n" +
                "-\tN’avoir aucun antécédent judiciaire ;\n" +
                "-\tEtre âgé de <b>18 ans au minimum et de 23 ans au maximum</b>, à la date du <b>16.08.2020</b> ;\n" +
                "-\tEtre titulaire d’un baccalauréat des années <b>2018</b> ou <b>2019</b> ou <b>2020</b> ;\n" +
                "-\tEtre apte physiquement ;  \n" +
                "-\tAvoir au minimum une taille de 1,70 m" +
                "-\tEtre retenu par la commission de présélection sur dossier.\n" +
                "Les candidats ayant les conditions suscitées, doivent s’inscrire sur le présent site web avant le <b>30 juillet 2020 inclus.</b>\n" +
                "Ils doivent choisir <b>trois (03)</b> spécialités par ordre de priorité et seront ventilés sur l’une des options en fonction des profils et quotas fixés par spécialité.\n");
        con.setConditionsAr("- الحالة العائلية عازب ،\n" +
                "- بدون سوابق عدلية،\n" +
                "- أن يكون المرشح حاصلا على شهادة البكالوريا دورات 2018 أو 2019 أو 2020،\n" +
                "- التمتع بصحة جسمانية جيدة،\n" +
                "- القامة 1,70  متر على الأقل،\n" +
                "- ان يتم قبوله  من طرف لجنة الانتقاء الأولي.\n" +
                "يتعين على المرشحين المتوفرين على الشروط أعلاه تسجيل طلباتهم على هذا الموقع الالكتروني وذلك قبل 30 يوليوز 2020  كآخر أجل لقبول الترشيحات.\n" +
                "على المرشحين اختيار ثلاث تخصصات حسب الأولوية و سيتم توزيعهم على واحد من هذه التخصصات بالاعتماد على مواصفات و عدد المناصب المطلوبة لكل تخصص.\n");
        con.setArmee(terre);
        con.setAvis("/doc/avis/soffterre.pdf");
        con.setImage("59e62612-9925-4027-ad84-878ef332bed5.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT_SOFF));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
        } catch (ParseException e) {
            logger.warn("parse exception " + e);
        }
        concours.addConcours(con);
        logger.info("concours CIB créé avec succées");

        //ERArt

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des Elèves Sous-Officiers de l'Armée de Terre -Spécialité Artillerie-");
        con.setTitreAr("مـبـاراة ولوج سلك تلاميذ ضباط الصف (المدفعية)");
        con.setCategorie(sofficier);
        con.setEcoleFormation(erart);
        con.setEcoleTest(erart);
        con.setDisponible(true);
        con.setAgemax(23);
        con.setAgemin(18);
        con.setBacancien(2);
        con.setSexe(masculins);
        con.setConditions("-\tEtre de nationalité marocaine ; \n" +
                "-\tEtre célibataire ;\n" +
                "-\tN’avoir aucun antécédent judiciaire ;\n" +
                "-\tEtre âgé de <b>18 ans au minimum et de 23 ans au maximum</b>, à la date du <b>16.08.2020</b> ;\n" +
                "-\tEtre titulaire d’un baccalauréat des années <b>2018</b> ou <b>2019</b> ou <b>2020</b> ;\n" +
                "-\tEtre apte physiquement ;  \n" +
                "-\tAvoir au minimum une taille de 1,70 m" +
                "-\tEtre retenu par la commission de présélection sur dossier.\n" +
                "Les candidats ayant les conditions suscitées, doivent s’inscrire sur le présent site web avant le <b>30 juillet 2020 inclus.</b>\n" +
                "Ils doivent choisir <b>trois (03)</b> spécialités par ordre de priorité et seront ventilés sur l’une des options en fonction des profils et quotas fixés par spécialité.\n");
        con.setConditionsAr("- الحالة العائلية عازب ،\n" +
                "- بدون سوابق عدلية،\n" +
                "- أن يكون المرشح حاصلا على شهادة البكالوريا دورات 2018 أو 2019 أو 2020،\n" +
                "- التمتع بصحة جسمانية جيدة،\n" +
                "- القامة 1,70  متر على الأقل،\n" +
                "- ان يتم قبوله  من طرف لجنة الانتقاء الأولي.\n" +
                "يتعين على المرشحين المتوفرين على الشروط أعلاه تسجيل طلباتهم على هذا الموقع الالكتروني وذلك قبل 30 يوليوز 2020  كآخر أجل لقبول الترشيحات.\n" +
                "على المرشحين اختيار ثلاث تخصصات حسب الأولوية و سيتم توزيعهم على واحد من هذه التخصصات بالاعتماد على مواصفات و عدد المناصب المطلوبة لكل تخصص.\n");
        con.setArmee(terre);
        con.setAvis("/doc/avis/soffterre.pdf");
        con.setImage("24edabd0-2286-44b0-9428-15942b2a3533.png");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT_SOFF));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
        } catch (ParseException e) {
            logger.warn("parse exception " + e);
        }
        concours.addConcours(con);
        logger.info("concours CIArt créé avec succées");

        //ERC

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des Elèves Sous-Officiers de l'Armée de Terre -Spécialité Cavalerie-");
        con.setTitreAr("مـبـاراة ولوج سلك تلاميذ ضباط الصف (الخيالة)");
        con.setCategorie(sofficier);
        con.setEcoleFormation(erc);
        con.setEcoleTest(erm);
        con.setDisponible(true);
        con.setAgemax(23);
        con.setAgemin(18);
        con.setBacancien(2);
        con.setSexe(masculins);
        con.setConditions("-\tEtre de nationalité marocaine ; \n" +
                "-\tEtre célibataire ;\n" +
                "-\tN’avoir aucun antécédent judiciaire ;\n" +
                "-\tEtre âgé de <b>18 ans au minimum et de 23 ans au maximum</b>, à la date du <b>16.08.2020</b> ;\n" +
                "-\tEtre titulaire d’un baccalauréat des années <b>2018</b> ou <b>2019</b> ou <b>2020</b> ;\n" +
                "-\tEtre apte physiquement ;  \n" +
                "-\tAvoir au minimum une taille de 1,70 m" +
                "-\tEtre retenu par la commission de présélection sur dossier.\n" +
                "Les candidats ayant les conditions suscitées, doivent s’inscrire sur le présent site web avant le <b>30 juillet 2020 inclus.</b>\n" +
                "Ils doivent choisir <b>trois (03)</b> spécialités par ordre de priorité et seront ventilés sur l’une des options en fonction des profils et quotas fixés par spécialité.\n");
        con.setConditionsAr("- الحالة العائلية عازب ،\n" +
                "- بدون سوابق عدلية،\n" +
                "- أن يكون المرشح حاصلا على شهادة البكالوريا دورات 2018 أو 2019 أو 2020،\n" +
                "- التمتع بصحة جسمانية جيدة،\n" +
                "- القامة 1,70  متر على الأقل،\n" +
                "- ان يتم قبوله  من طرف لجنة الانتقاء الأولي.\n" +
                "يتعين على المرشحين المتوفرين على الشروط أعلاه تسجيل طلباتهم على هذا الموقع الالكتروني وذلك قبل 30 يوليوز 2020  كآخر أجل لقبول الترشيحات.\n" +
                "على المرشحين اختيار ثلاث تخصصات حسب الأولوية و سيتم توزيعهم على واحد من هذه التخصصات بالاعتماد على مواصفات و عدد المناصب المطلوبة لكل تخصص.\n");
        con.setArmee(terre);
        con.setAvis("/doc/avis/soffterre.pdf");
        con.setImage("28f6c8af-e2e9-4cbd-b3e3-57e6aa5725a7.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT_SOFF));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
        } catch (ParseException e) {
            logger.warn("parse exception " + e);
        }
        concours.addConcours(con);
        logger.info("concours ERC créé avec succées");

        //CIFT

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des Elèves Sous-Officiers de l'Armée de Terre -Spécialité Transport-");
        con.setTitreAr("مـبـاراة ولوج سلك تلاميذ ضباط الصف (النقل)");
        con.setCategorie(sofficier);
        con.setEcoleFormation(cift);
        con.setEcoleTest(erm);
        con.setDisponible(true);
        con.setAgemax(23);
        con.setAgemin(18);
        con.setBacancien(2);
        con.setSexe(masculins);
        con.setConditions("-\tEtre de nationalité marocaine ; \n" +
                "-\tEtre célibataire ;\n" +
                "-\tN’avoir aucun antécédent judiciaire ;\n" +
                "-\tEtre âgé de <b>18 ans au minimum et de 23 ans au maximum</b>, à la date du <b>16.08.2020</b> ;\n" +
                "-\tEtre titulaire d’un baccalauréat des années <b>2018</b> ou <b>2019</b> ou <b>2020</b> ;\n" +
                "-\tEtre apte physiquement ;  \n" +
                "-\tAvoir au minimum une taille de 1,70 m" +
                "-\tEtre retenu par la commission de présélection sur dossier.\n" +
                "Les candidats ayant les conditions suscitées, doivent s’inscrire sur le présent site web avant le <b>30 juillet 2020 inclus.</b>\n" +
                "Ils doivent choisir <b>trois (03)</b> spécialités par ordre de priorité et seront ventilés sur l’une des options en fonction des profils et quotas fixés par spécialité.\n");
        con.setConditionsAr("- الحالة العائلية عازب ،\n" +
                "- بدون سوابق عدلية،\n" +
                "- أن يكون المرشح حاصلا على شهادة البكالوريا دورات 2018 أو 2019 أو 2020،\n" +
                "- التمتع بصحة جسمانية جيدة،\n" +
                "- القامة 1,70  متر على الأقل،\n" +
                "- ان يتم قبوله  من طرف لجنة الانتقاء الأولي.\n" +
                "يتعين على المرشحين المتوفرين على الشروط أعلاه تسجيل طلباتهم على هذا الموقع الالكتروني وذلك قبل 30 يوليوز 2020  كآخر أجل لقبول الترشيحات.\n" +
                "على المرشحين اختيار ثلاث تخصصات حسب الأولوية و سيتم توزيعهم على واحد من هذه التخصصات بالاعتماد على مواصفات و عدد المناصب المطلوبة لكل تخصص.\n");
        con.setArmee(terre);
        con.setAvis("/doc/avis/soffterre.pdf");
        con.setImage("74a4a782-d974-45da-b873-3b2f18995f58.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT_SOFF));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
        } catch (ParseException e) {
            logger.warn("parse exception " + e);
        }
        concours.addConcours(con);
        logger.info("concours CIFT créé avec succées");

        //CIG

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des Elèves Sous-Officiers de l'Armée de Terre -Spécialité Génie-");
        con.setTitreAr("مـبـاراة ولوج سلك تلاميذ ضباط الصف (الهندسة العسكرية)");
        con.setCategorie(sofficier);
        con.setEcoleFormation(cig);
        con.setEcoleTest(cit);
        con.setDisponible(true);
        con.setAgemax(23);
        con.setAgemin(18);
        con.setBacancien(2);
        con.setSexe(masculins);
        con.setConditions("-\tEtre de nationalité marocaine ; \n" +
                "-\tEtre célibataire ;\n" +
                "-\tN’avoir aucun antécédent judiciaire ;\n" +
                "-\tEtre âgé de <b>18 ans au minimum et de 23 ans au maximum</b>, à la date du <b>16.08.2020</b> ;\n" +
                "-\tEtre titulaire d’un baccalauréat des années <b>2018</b> ou <b>2019</b> ou <b>2020</b> ;\n" +
                "-\tEtre apte physiquement ;  \n" +
                "-\tAvoir au minimum une taille de 1,70 m" +
                "-\tEtre retenu par la commission de présélection sur dossier.\n" +
                "Les candidats ayant les conditions suscitées, doivent s’inscrire sur le présent site web avant le <b>30 juillet 2020 inclus.</b>\n" +
                "Ils doivent choisir <b>trois (03)</b> spécialités par ordre de priorité et seront ventilés sur l’une des options en fonction des profils et quotas fixés par spécialité.\n");
        con.setConditionsAr("- الحالة العائلية عازب ،\n" +
                "- بدون سوابق عدلية،\n" +
                "- أن يكون المرشح حاصلا على شهادة البكالوريا دورات 2018 أو 2019 أو 2020،\n" +
                "- التمتع بصحة جسمانية جيدة،\n" +
                "- القامة 1,70  متر على الأقل،\n" +
                "- ان يتم قبوله  من طرف لجنة الانتقاء الأولي.\n" +
                "يتعين على المرشحين المتوفرين على الشروط أعلاه تسجيل طلباتهم على هذا الموقع الالكتروني وذلك قبل 30 يوليوز 2020  كآخر أجل لقبول الترشيحات.\n" +
                "على المرشحين اختيار ثلاث تخصصات حسب الأولوية و سيتم توزيعهم على واحد من هذه التخصصات بالاعتماد على مواصفات و عدد المناصب المطلوبة لكل تخصص.\n");
        con.setArmee(terre);
        con.setAvis("/doc/avis/soffterre.pdf");
        con.setImage("15961afa-e171-4a08-badd-fc5bd8c120f1.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT_SOFF));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
        } catch (ParseException e) {
            logger.warn("parse exception " + e);
        }
        concours.addConcours(con);
        logger.info("concours CIG créé avec succées");

        //CIT

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des Elèves Sous-Officiers de l'Armée de Terre -Spécialité Transmissions-");
        con.setTitreAr("مـبـاراة ولوج سلك تلاميذ ضباط الصف (سلاح الإشارة)");
        con.setCategorie(sofficier);
        con.setEcoleFormation(cit);
        con.setEcoleTest(cit);
        con.setDisponible(true);
        con.setAgemax(23);
        con.setAgemin(18);
        con.setBacancien(2);
        con.setConditions("-\tEtre de nationalité marocaine ; \n" +
                "-\tEtre célibataire ;\n" +
                "-\tN’avoir aucun antécédent judiciaire ;\n" +
                "-\tEtre âgé (e) de <b>18 ans au minimum et de 23 ans au maximum</b>, à la date du <b>16.08.2020</b> ;\n" +
                "-\tEtre titulaire d’un baccalauréat des années <b>2018</b> ou <b>2019</b> ou <b>2020</b> ;\n" +
                "-\tEtre apte physiquement ;  \n" +
                "-\tAvoir au minimum une taille de 1,70 m pour les garçons et 1,60 m pour les filles ;\n" +
                "-\tEtre retenu (e) par la commission de présélection sur dossier.\n" +
                "Les candidats et les candidates ayant les conditions suscitées, doivent s’inscrire sur le présent site web avant le <b>30 juillet 2020 inclus.</b>\n" +
                "Ils (elles) doivent choisir <b>trois (03)</b> spécialités par ordre de priorité et seront ventilés sur l’une des options en fonction des profils et quotas fixés par spécialité.");
        con.setConditionsAr("- الحالة العائلية عازب (ة) ،\n" +
                "- بدون سوابق عدلية،\n" +
                "- أن يكون المرشح (ة) حاصلا (ة) على شهادة البكالوريا دورات 2018 أو 2019 أو 2020،\n" +
                "- التمتع بصحة جسمانية جيدة،\n" +
                "- القامة 1,70  متر على الأقل بالنسبة للذكور و1,60  متر على الأقل بالنسبة للإناث،\n" +
                "- ان يتم قبوله (ها)  من طرف لجنة الانتقاء الأولي.\n" +
                "يتعين على المرشحين والمرشحات المتوفرين على الشروط أعلاه تسجيل طلباتهم على هذا الموقع الالكتروني وذلك قبل 30 يوليوز 2020  كآخر أجل لقبول الترشيحات.\n" +
                "على المرشحين و المرشحات اختيار ثلاث تخصصات حسب الأولوية و سيتم توزيعهم على واحد من هذه التخصصات بالاعتماد على مواصفات و عدد المناصب المطلوبة لكل تخصص.\n");
        con.setArmee(terre);
        con.setAvis("/doc/avis/soffterre.pdf");
        con.setImage("423f15e3-667a-4a5f-9f3e-cc43ee92fc49.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT_SOFF));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
        } catch (ParseException e) {
            logger.warn("parse exception " + e);
        }
        concours.addConcours(con);
        logger.info("concours CIT créé avec succées");

        //ERM

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des Elèves Sous-Officiers de l'Armée de Terre -Spécialité Matériel-");
        con.setTitreAr("مـبـاراة ولوج سلك تلاميذ ضباط الصف (العتاد)");
        con.setCategorie(sofficier);
        con.setEcoleFormation(erm);
        con.setEcoleTest(erm);
        con.setDisponible(true);
        con.setAgemax(23);
        con.setAgemin(18);
        con.setBacancien(2);
        con.setSexe(masculins);
        con.setConditions("-\tEtre de nationalité marocaine ; \n" +
                "-\tEtre célibataire ;\n" +
                "-\tN’avoir aucun antécédent judiciaire ;\n" +
                "-\tEtre âgé de <b>18 ans au minimum et de 23 ans au maximum</b>, à la date du <b>16.08.2020</b> ;\n" +
                "-\tEtre titulaire d’un baccalauréat des années <b>2018</b> ou <b>2019</b> ou <b>2020</b> ;\n" +
                "-\tEtre apte physiquement ;  \n" +
                "-\tAvoir au minimum une taille de 1,70 m" +
                "-\tEtre retenu par la commission de présélection sur dossier.\n" +
                "Les candidats ayant les conditions suscitées, doivent s’inscrire sur le présent site web avant le <b>30 juillet 2020 inclus.</b>\n" +
                "Ils doivent choisir <b>trois (03)</b> spécialités par ordre de priorité et seront ventilés sur l’une des options en fonction des profils et quotas fixés par spécialité.\n");
        con.setConditionsAr("- الحالة العائلية عازب ،\n" +
                "- بدون سوابق عدلية،\n" +
                "- أن يكون المرشح حاصلا على شهادة البكالوريا دورات 2018 أو 2019 أو 2020،\n" +
                "- التمتع بصحة جسمانية جيدة،\n" +
                "- القامة 1,70  متر على الأقل،\n" +
                "- ان يتم قبوله  من طرف لجنة الانتقاء الأولي.\n" +
                "يتعين على المرشحين المتوفرين على الشروط أعلاه تسجيل طلباتهم على هذا الموقع الالكتروني وذلك قبل 30 يوليوز 2020  كآخر أجل لقبول الترشيحات.\n" +
                "على المرشحين اختيار ثلاث تخصصات حسب الأولوية و سيتم توزيعهم على واحد من هذه التخصصات بالاعتماد على مواصفات و عدد المناصب المطلوبة لكل تخصص.\n");
        con.setArmee(terre);
        con.setAvis("/doc/avis/soffterre.pdf");
        con.setImage("61c875cb-1fb3-4262-9133-b781e991a16b.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT_SOFF));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
        } catch (ParseException e) {
            logger.warn("parse exception " + e);
        }
        concours.addConcours(con);
        logger.info("concours ERM créé avec succées");

        //CII

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des Elèves Sous-Officiers de l'Armée de Terre -Spécialité Intendance-");
        con.setTitreAr("مـبـاراة ولوج سلك تلاميذ ضباط الصف (التموين)");
        con.setCategorie(sofficier);
        con.setEcoleFormation(cii);
        con.setEcoleTest(cii);
        con.setDisponible(true);
        con.setAgemax(23);
        con.setAgemin(18);
        con.setBacancien(2);
        con.setConditions("-\tEtre de nationalité marocaine ; \n" +
                "-\tEtre célibataire ;\n" +
                "-\tN’avoir aucun antécédent judiciaire ;\n" +
                "-\tEtre âgé (e) de <b>18 ans au minimum et de 23 ans au maximum</b>, à la date du <b>16.08.2020</b> ;\n" +
                "-\tEtre titulaire d’un baccalauréat des années <b>2018</b> ou <b>2019</b> ou <b>2020</b> ;\n" +
                "-\tEtre apte physiquement ;  \n" +
                "-\tAvoir au minimum une taille de 1,70 m pour les garçons et 1,60 m pour les filles ;\n" +
                "-\tEtre retenu (e) par la commission de présélection sur dossier.\n" +
                "Les candidats et les candidates ayant les conditions suscitées, doivent s’inscrire sur le présent site web avant le <b>30 juillet 2020 inclus.</b>\n" +
                "Ils (elles) doivent choisir <b>trois (03)</b> spécialités par ordre de priorité et seront ventilés sur l’une des options en fonction des profils et quotas fixés par spécialité.");
        con.setConditionsAr("- الحالة العائلية عازب (ة) ،\n" +
                "- بدون سوابق عدلية،\n" +
                "- أن يكون المرشح (ة) حاصلا (ة) على شهادة البكالوريا دورات 2018 أو 2019 أو 2020،\n" +
                "- التمتع بصحة جسمانية جيدة،\n" +
                "- القامة 1,70  متر على الأقل بالنسبة للذكور و1,60  متر على الأقل بالنسبة للإناث،\n" +
                "- ان يتم قبوله (ها)  من طرف لجنة الانتقاء الأولي.\n" +
                "يتعين على المرشحين والمرشحات المتوفرين على الشروط أعلاه تسجيل طلباتهم على هذا الموقع الالكتروني وذلك قبل 30 يوليوز 2020  كآخر أجل لقبول الترشيحات.\n" +
                "على المرشحين و المرشحات اختيار ثلاث تخصصات حسب الأولوية و سيتم توزيعهم على واحد من هذه التخصصات بالاعتماد على مواصفات و عدد المناصب المطلوبة لكل تخصص.\n");
        con.setArmee(terre);
        con.setAvis("/doc/avis/soffterre.pdf");
        con.setImage("58b5e170-91cc-49ac-9539-6b9acddc0eba.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT_SOFF));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
        } catch (ParseException e) {
            logger.warn("parse exception " + e);
        }
        concours.addConcours(con);
        logger.info("concours CII créé avec succées");

        //CISS

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des Elèves Sous-Officiers de l'Armée de Terre -Spécialité Social-");
        con.setTitreAr("مـبـاراة ولوج سلك تلاميذ ضباط الصف (المصالح  الاجتماعية)");
        con.setCategorie(sofficier);
        con.setEcoleFormation(ciss);
        con.setEcoleTest(ciss);
        con.setDisponible(true);
        con.setAgemax(23);
        con.setAgemin(18);
        con.setBacancien(2);
        con.setSexe(feminins);
        con.setConditions("-\tEtre de nationalité marocaine ; \n" +
                "-\tEtre célibataire ;\n" +
                "-\tN’avoir aucun antécédent judiciaire ;\n" +
                "-\tEtre âgée de <b>18 ans au minimum et de 23 ans au maximum</b>, à la date du <b>16.08.2020</b> ;\n" +
                "-\tEtre titulaire d’un baccalauréat des années <b>2018</b> ou <b>2019</b> ou <b>2020</b> ;\n" +
                "-\tEtre apte physiquement ;  \n" +
                "-\tAvoir au minimum une taille de 1,60 m" +
                "-\tEtre retenue par la commission de présélection sur dossier.\n" +
                "Les candidates ayant les conditions suscitées, doivent s’inscrire sur le présent site web avant le <b>30 juillet 2020 inclus.</b>\n" +
                "Elles doivent choisir <b>trois (03)</b> spécialités par ordre de priorité et seront ventilées sur l’une des options en fonction des profils et quotas fixés par spécialité.\n");
        con.setConditionsAr("- الحالة العائلية عازبة ،\n" +
                "- بدون سوابق عدلية،\n" +
                "- أن تكون المرشحة حاصلة على شهادة البكالوريا دورات 2018 أو 2019 أو 2020،\n" +
                "- التمتع بصحة جسمانية جيدة،\n" +
                "- القامة 1,60  متر على الأقل،\n" +
                "- ان يتم قبولها  من طرف لجنة الانتقاء الأولي.\n" +
                "يتعين على المرشحات المتوفرات على الشروط أعلاه تسجيل طلباتهن على هذا الموقع الالكتروني وذلك قبل 30 يوليوز 2020  كآخر أجل لقبول الترشيحات.\n" +
                "على المرشحات اختيار ثلاث تخصصات حسب الأولوية و سيتم توزيعهن على واحد من هذه التخصصات بالاعتماد على مواصفات و عدد المناصب المطلوبة لكل تخصص.\n");
        con.setArmee(terre);
        con.setAvis("/doc/avis/soffterre.pdf");
        con.setImage("189e891c-46e6-473b-8a70-b428d16293b8.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT_SOFF));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
        } catch (ParseException e) {
            logger.warn("parse exception " + e);
        }
        concours.addConcours(con);
        logger.info("concours CISS créé avec succées");

        //PARA

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des Elèves Sous-Officiers de l'Armée de Terre -Spécialité Troupes Aéroportées-");
        con.setTitreAr("مـبـاراة ولوج سلك تلاميذ ضباط الصف (المظليين)");
        con.setCategorie(sofficier);
        con.setEcoleFormation(cita);
        con.setEcoleTest(eri);
        con.setDisponible(true);
        con.setAgemax(23);
        con.setAgemin(18);
        con.setBacancien(2);
        con.setSexe(masculins);
        con.setConditions("-\tEtre de nationalité marocaine ; \n" +
                "-\tEtre célibataire ;\n" +
                "-\tN’avoir aucun antécédent judiciaire ;\n" +
                "-\tEtre âgé de <b>18 ans au minimum et de 23 ans au maximum</b>, à la date du <b>16.08.2020</b> ;\n" +
                "-\tEtre titulaire d’un baccalauréat des années <b>2018</b> ou <b>2019</b> ou <b>2020</b> ;\n" +
                "-\tEtre apte physiquement ;  \n" +
                "-\tAvoir au minimum une taille de 1,70 m" +
                "-\tEtre retenu par la commission de présélection sur dossier.\n" +
                "Les candidats ayant les conditions suscitées, doivent s’inscrire sur le présent site web avant le <b>30 juillet 2020 inclus.</b>\n" +
                "Ils doivent choisir <b>trois (03)</b> spécialités par ordre de priorité et seront ventilés sur l’une des options en fonction des profils et quotas fixés par spécialité.\n");
        con.setConditionsAr("- الحالة العائلية عازب ،\n" +
                "- بدون سوابق عدلية،\n" +
                "- أن يكون المرشح حاصلا على شهادة البكالوريا دورات 2018 أو 2019 أو 2020،\n" +
                "- التمتع بصحة جسمانية جيدة،\n" +
                "- القامة 1,70  متر على الأقل،\n" +
                "- ان يتم قبوله  من طرف لجنة الانتقاء الأولي.\n" +
                "يتعين على المرشحين المتوفرين على الشروط أعلاه تسجيل طلباتهم على هذا الموقع الالكتروني وذلك قبل 30 يوليوز 2020  كآخر أجل لقبول الترشيحات.\n" +
                "على المرشحين اختيار ثلاث تخصصات حسب الأولوية و سيتم توزيعهم على واحد من هذه التخصصات بالاعتماد على مواصفات و عدد المناصب المطلوبة لكل تخصص.\n");
        con.setArmee(terre);
        con.setAvis("/doc/avis/soffterre.pdf");
        con.setImage("035e2dae-1843-4e09-8d8f-d3e29dd37c4b.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT_SOFF));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
        } catch (ParseException e) {
            logger.warn("parse exception " + e);
        }
        concours.addConcours(con);
        logger.info("concours PARA créé avec succées");

        //ERPPLM

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des Elèves Sous-Officiers de l'Armée de Terre -Spécialité Santé militaire-");
        con.setTitreAr("مـبـاراة ولوج سلك تلاميذ ضباط الصف (الصحة العسكرية)");
        con.setCategorie(sofficier);
        con.setEcoleFormation(erpplm);
        con.setEcoleTest(erpplm);
        con.setDisponible(true);
        con.setAgemax(23);
        con.setAgemin(18);
        con.setBacancien(2);
        con.setConditions("-\tEtre de nationalité marocaine ; \n" +
                "-\tEtre célibataire ;\n" +
                "-\tN’avoir aucun antécédent judiciaire ;\n" +
                "-\tEtre âgé (e) de <b>18 ans au minimum et de 23 ans au maximum</b>, à la date du <b>16.08.2020</b> ;\n" +
                "-\tEtre titulaire d’un baccalauréat des années <b>2018</b> ou <b>2019</b> ou <b>2020</b> ;\n" +
                "-\tEtre apte physiquement ;  \n" +
                "-\tAvoir au minimum une taille de 1,70 m pour les garçons et 1,60 m pour les filles ;\n" +
                "-\tEtre retenu (e) par la commission de présélection sur dossier.\n" +
                "Les candidats et les candidates ayant les conditions suscitées, doivent s’inscrire sur le présent site web avant le <b>30 juillet 2020 inclus.</b>\n" +
                "Ils (elles) doivent choisir <b>trois (03)</b> spécialités par ordre de priorité et seront ventilés sur l’une des options en fonction des profils et quotas fixés par spécialité.");
        con.setConditionsAr("- الحالة العائلية عازب (ة) ،\n" +
                "- بدون سوابق عدلية،\n" +
                "- أن يكون المرشح (ة) حاصلا (ة) على شهادة البكالوريا دورات 2018 أو 2019 أو 2020،\n" +
                "- التمتع بصحة جسمانية جيدة،\n" +
                "- القامة 1,70  متر على الأقل بالنسبة للذكور و1,60  متر على الأقل بالنسبة للإناث،\n" +
                "- ان يتم قبوله (ها)  من طرف لجنة الانتقاء الأولي.\n" +
                "يتعين على المرشحين والمرشحات المتوفرين على الشروط أعلاه تسجيل طلباتهم على هذا الموقع الالكتروني وذلك قبل 30 يوليوز 2020  كآخر أجل لقبول الترشيحات.\n" +
                "على المرشحين و المرشحات اختيار ثلاث تخصصات حسب الأولوية و سيتم توزيعهم على واحد من هذه التخصصات بالاعتماد على مواصفات و عدد المناصب المطلوبة لكل تخصص.\n");
        con.setArmee(sante);
        con.setAvis("/doc/avis/soffterre.pdf");
        con.setImage("68a97a4f-748c-4e20-a0ed-c79b9eb3a1c4.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT_SOFF));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
        } catch (ParseException e) {
            logger.warn("parse exception " + e);
        }
        concours.addConcours(con);
        logger.info("concours ERPPLM créé avec succées");

        Filiere filiere = new Filiere("infirmier", "ممرض", con);
        filieres.save(filiere);
        filiere = new Filiere("secrétaire médical", "كاتب طبي", con);
        filieres.save(filiere);

        //Sport

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des Elèves Sous-Officiers de l'Armée de Terre -spécialité sport militaire-");
        con.setTitreAr("مـبـاراة ولوج سلك تلاميذ ضباط الصف (الرياضة العسكرية)");
        con.setCategorie(sofficier);
        con.setEcoleFormation(csfar);
        con.setEcoleTest(cii);
        con.setDisponible(true);
        con.setAgemax(23);
        con.setAgemin(18);
        con.setBacancien(2);
        con.setConditions("-\tEtre de nationalité marocaine ; \n" +
                "-\tEtre célibataire ;\n" +
                "-\tN’avoir aucun antécédent judiciaire ;\n" +
                "-\tEtre âgé (e) de <b>18 ans au minimum et de 23 ans au maximum</b>, à la date du <b>16.08.2020</b> ;\n" +
                "-\tEtre titulaire d’un baccalauréat des années <b>2018</b> ou <b>2019</b> ou <b>2020</b> ;\n" +
                "-\tEtre apte physiquement ;  \n" +
                "-\tAvoir au minimum une taille de 1,70 m pour les garçons et 1,60 m pour les filles ;\n" +
                "-\tEtre retenu (e) par la commission de présélection sur dossier.\n" +
                "Les candidats et les candidates ayant les conditions suscitées, doivent s’inscrire sur le présent site web avant le <b>30 juillet 2020 inclus.</b>\n" +
                "Ils (elles) doivent choisir <b>trois (03)</b> spécialités par ordre de priorité et seront ventilés sur l’une des options en fonction des profils et quotas fixés par spécialité.");
        con.setConditionsAr("- الحالة العائلية عازب (ة) ،\n" +
                "- بدون سوابق عدلية،\n" +
                "- أن يكون المرشح (ة) حاصلا (ة) على شهادة البكالوريا دورات 2018 أو 2019 أو 2020،\n" +
                "- التمتع بصحة جسمانية جيدة،\n" +
                "- القامة 1,70  متر على الأقل بالنسبة للذكور و1,60  متر على الأقل بالنسبة للإناث،\n" +
                "- ان يتم قبوله (ها)  من طرف لجنة الانتقاء الأولي.\n" +
                "يتعين على المرشحين والمرشحات المتوفرين على الشروط أعلاه تسجيل طلباتهم على هذا الموقع الالكتروني وذلك قبل 30 يوليوز 2020  كآخر أجل لقبول الترشيحات.\n" +
                "على المرشحين و المرشحات اختيار ثلاث تخصصات حسب الأولوية و سيتم توزيعهم على واحد من هذه التخصصات بالاعتماد على مواصفات و عدد المناصب المطلوبة لكل تخصص.\n");
        con.setArmee(terre);
        con.setAvis("/doc/avis/soffterre.pdf");
        con.setImage("37cfca0f-a2a6-4d3e-bdfc-f9bedd7a464f.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT_SOFF));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
        } catch (ParseException e) {
            logger.warn("parse exception " + e);
        }
        concours.addConcours(con);
        logger.info("concours SPORT créé avec succées");

        //EFSNO

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des Elèves Sous-Officiers des Forces Royales Air");
        con.setTitreAr("مـبـاراة ولوج سلك تلاميذ ضباط صف القوات الملكية الجوية");
        con.setCategorie(sofficier);
        con.setEcoleFormation(efsno);
        con.setEcoleTest(efsno);
        con.setDisponible(true);
        con.setAgemax(23);
        con.setAgemin(18);
        con.setBacancien(2);
        con.setConditions("\uF0D8\t<U>Conditions communes:</U>\n" +
                "-Etre de nationalité marocaine ; \n" +
                "-Etre célibataire ;\n" +
                "-N’avoir aucun antécédent judiciaire ;\n" +
                "-Etre apte physiquement ; \n" +
                "-Etre âgé (e) de <b>18 ans au minimum et de 23 ans au maximum</b>, à la date du <b>16.08.2020</b> ;\n" +
                "-Avoir une taille minimum de 1,70 m pour les garçons et 1,65 m pour les filles ;\n" +
                "-Etre retenu (e) par la commission de présélection sur dossier.\n" +
                "\uF0D8\t<U>Conditions particulières aux Elèves Sous-officiers \" Personnel technicien spécialisé non navigant \":</U>\n" +
                "-Etre titulaire (e) d’un baccalauréat <b>scientifique ou technique</b> des années <b>2018</b> ou <b>2019</b> ou <b>2020</b>.\n" +
                "\uF0D8\t<U>Conditions particulières aux Elèves Sous-officiers \"Cuisinier\":</U>\n" +
                "-Etre de sexe masculin ;\n" +
                "-Etre titulaire d’un baccalauréat des années <b>2018</b> ou <b>2019</b> ou <b>2020</b>.\n" +
                "Les candidats et les candidates ayant les conditions suscitées, doivent s’inscrire sur le présent site web  avant le <b>30 juillet 2020 inclus.</b> \n");
        con.setConditionsAr("•\tشروط مشتركة:\n" +
                "- الحالة العائلية عازب (ة) ،\t\n" +
                "- بدون سوابق عدلية،\n" +
                "- التمتع بصحة جسمانية جيدة،\n" +
                "- أن يكون سن المرشح (ة) ما بين 18 و23 سنة لغاية 16 غشت 2020،\n" +
                " القامة 1,70  متر على الأقل بالنسبة للذكور و1,65  متر على الأقل بالنسبة للإناث،\n" +
                "- ان يتم قبوله (ها) من طرف لجنة الانتقاء الأولي.\n" +
                "•\tشروط خاصة لتلاميذ ضباط الصف لتخصص \" الأفراد التقنيين المتخصصين الغير الملاحين\":  \n" +
                "- أن يكون المرشح (ة) حاصلا (ة) على شهادة البكالوريا علوم او تقنية دورات 2018 أو 2019 أو 2020. \n" +
                "•\tشروط خاصة لتلاميذ ضباط الصف لتخصص \" طباخ \":\n" +
                "- أن يكون المرشح ذكرا،\n" +
                "- أن يكون المرشح حاصلا على شهادة البكالوريا دورات 2018 أو 2019 أو 2020.\n" +
                "\n" +
                "يتعين المرشحين والمرشحات المتوفرين على الشروط أعلاه تسجيل طلباتهم على هذا الموقع الالكتروني وذلك قبل 30 يوليوز 2020 كآخر أجل لقبول الترشيحات.\n");
        con.setArmee(aviation);
        con.setAvis("/doc/avis/soffair.pdf");
        con.setImage("a9c854cd-3b6a-46c8-a04e-13fa677ec1b3.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT_SOFF));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
        } catch (ParseException e) {
            logger.warn("parse exception " + e);
        }
        concours.addConcours(con);
        logger.info("concours personnel non navigant créé avec succées");

        filiere = new Filiere("Personnel non navigant", "التقنيين المتخصصين الغير الملاحين", con);
        filieres.save(filiere);
        filiere = new Filiere("Cuisinier", "طباخ", con);
        filieres.save(filiere);
//        filiere = new Filiere("Techniciens spécialisés lauréats des ISTA", "تقني متخصص خريج المعاهد المتخصصة للتكنولوجيا التطبيقية", con);
//        filieres.save(filiere);

        //CIMR

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des Elèves Sous-Officiers de la Marine Royale");
        con.setTitreAr("مـبـاراة ولوج سلك تلاميذ ضباط صف البحرية الملكية");
        con.setCategorie(sofficier);
        con.setEcoleFormation(cimr);
        con.setEcoleTest(cimr);
        con.setDisponible(true);
        con.setAgemax(23);
        con.setAgemin(18);
        con.setBacancien(2);
        con.setConditions("-\tEtre de nationalité marocaine ; \n" +
                "-\tEtre célibataire ;\n" +
                "-\tN’avoir aucun antécédent judiciaire ;\n" +
                "-\tEtre âgé (e) de <b>18 ans au minimum et de 23 ans au maximum</b>, à la date du <b>16.08.2020</b> ;\n" +
                "-\tEtre titulaire d’un baccalauréat scientifique des années <b>2018</b> ou <b>2019</b> ou <b>2020</b>.\n" +
                "-\tAvoir une taille minimum de 1,70 m pour les garçons et 1,60 m pour les filles ;\n" +
                "-\tEtre de sexe masculin pour la spécialité \"Fusilier Marin\" ;\n" +
                "-\tEtre apte physiquement ; \n" +
                "-\tEtre apte au service à la mer ;\n" +
                "-\tEtre retenu (e) par la commission de présélection sur dossier.\n" +
                "Les candidats et les candidates ayant les conditions suscitées, doivent s’inscrire sur le présent site web avant le <b>30 juillet 2020 inclus.</b> \n");
        con.setConditionsAr("- الحالة العائلية عازب (ة) ،\n" +
                "- بدون سوابق عدلية،\n" +
                "- أن يكون سن المرشح (ة) ما بين 18 و23 سنة لغاية 16 غشت 2020،\n" +
                "- أن يكون المرشح (ة) حاصلا (ة) على شهادة البكالوريا علوم دورات 2018 أو 2019 أو 2020،\n" +
                "- القامة 1,70  متر على الأقل بالنسبة للذكور و1,60  متر على الأقل بالنسبة للإناث،\n" +
                " -أن يكون المرشح ذكرا بالنسبة لتخصص \" مشاة البحرية،\n" +
                "- التمتع بصحة جسمانية جيدة،\n" +
                "- أن يكون قادرا(ة)  على الخدمة البحرية  ،\n" +
                "- ان يتم قبوله من طرف لجنة الانتقاء الأولي.\n" +
                "يتعين على المرشحين والمرشحات المتوفرين على الشروط أعلاه تسجيل طلباتهم على هذا الموقع الالكتروني وذلك قبل 30 يوليوز 2020  كآخر أجل لقبول الترشيحات.\n");
        con.setArmee(marine);
        con.setAvis("/doc/avis/soffmarine.pdf");
        con.setImage("ce180b2b-2c9a-4832-bfe3-5f2c9b4214ca.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT_SOFF));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN_SOFF));
        } catch (ParseException e) {
            logger.warn("parse exception " + e);
        }
        concours.addConcours(con);
        logger.info("concours personnel navigant marine créé avec succées");

        filiere = new Filiere("Personnel navigant", "الأفراد الملاحين", con);
        filieres.save(filiere);
        filiere = new Filiere("Fusilier Marin", "مشاة البحرية", con);
        filieres.save(filiere);

    }
}

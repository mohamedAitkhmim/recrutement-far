package com.far.recrutement;

import com.far.recrutement.dao.FiliereRepository;
import com.far.recrutement.metier.*;
import com.far.recrutement.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Seed {

    private static final Logger logger = LoggerFactory.getLogger(Seed.class);

    static String DATE_FIN = "07/02/2021";
    static String DATE_DEBUT = "30/01/2021";

    static String DATE_FIN_SOFF = "30/07/2020";
    static String DATE_DEBUT_SOFF = "16/06/2020";

    public static void seed(ApplicationContext context) {
        FiliereRepository filieres = context.getBean(FiliereRepository.class);
        //super admin
        UserMetier users = context.getBean(UserMetier.class);
        Admin user = new Admin("admin", "123698", true);
        Admin user2 = new Admin("fra", "236987", true);
        Admin user3 = new Admin("marine", "369874", true);
        Admin user4 = new Admin("arm", "412369", true);
        Admin user5 = new Admin("sante", "512369", true);
        user2.setConcours(new ArrayList<>());
        user3.setConcours(new ArrayList<>());
        user4.setConcours(new ArrayList<>());
        user5.setConcours(new ArrayList<>());
        users.save(user);
        users.save(user2);
        users.save(user3);
        users.save(user4);
        users.save(user5);
        //categorie
        CategorieMetier categories = context.getBean(CategorieMetier.class);
        Categorie officier = categories.addCategorie(new Categorie("Elève Officier", "ضابط", true));
        Categorie sofficier = categories.addCategorie(new Categorie("Elève Sous-Officier", "ضابط صف", true));
        Categorie militaire = categories.addCategorie(new Categorie("Militaires du rang", "عسكري", true));
        Categorie lycee = categories.addCategorie(new Categorie("Elève du Lycée", "مدرسة ثانوية", true));
        Categorie integration = categories.addCategorie(new Categorie("Recrutement des médecins par voie d'intégration", "مدرسة ثانوية", true));
        logger.info("catégories ajoutées");
        ArrayList<Categorie> offcat = new ArrayList<>();
        ArrayList<Categorie> soffcat = new ArrayList<>();
        ArrayList<Categorie> lyccat = new ArrayList<>();
        ArrayList<Categorie> milcat = new ArrayList<>();
        lyccat.add(lycee);
        lyccat.add(officier);
        offcat.add(officier);
        soffcat.add(sofficier);
        milcat.add(militaire);

        ArmeeMetier armees = context.getBean(ArmeeMetier.class);
        Armee aviation = armees.addArmee(new Armee("Forces Royales Air"));
        Armee marine = armees.addArmee(new Armee("Marine Royale"));
        Armee terre = armees.addArmee(new Armee("Forces Armées Royales"));
        Armee sante = armees.addArmee(new Armee("Service de santé"));
        Armee fa = armees.addArmee(new Armee("Forces auxiliaires"));

        //Ecoles
        EcoleMetier ecoles = context.getBean(EcoleMetier.class);
        Ecole arm = ecoles.addEcole(new Ecole("Académie Royale Militaire"
                , "Cette institution a été créée à Meknès en 1918 sous l’appellation «Ecole Militaire des Elèves-Officiers Marocains Dar El Beida », avant de devenir  l’Académie Royale Militaire (ARM) en 1956. \n" +
                "L’ARM est la pépinière des officiers de l’armée de terre des Forces Armées Royales. Elle est chargée de dispenser des formations universitaires et militaires initiales et continues aussi bien au profit des officiers de l’Armée de Terre, de la Gendarmerie Royale et des Forces Auxiliaires, ainsi que ceux appartenant aux forces terrestres des pays partenaires dans le cadre de la coopération militaire. La formation au sein de l’ARM dure quatre années et sanctionnée par:\n" +
                "-\tune License après trois années d’étude dans les filières suivantes:\n" +
                "•\t<b>Sciences et techniques ;</b>\n" +
                "•\t<b>Sciences juridiques ;\n</b>" +
                "•\t<b>Littérature anglaise.\n</b>" +
                "-\tun Diplôme des Etudes Universitaires et Militaire (DEUM) après quatre années d’étude.\n" +
                "La formation est sanctionnée par la nomination des lauréats au grade de Sous-Lieutenant."
                , "الأكاديمية الملكية العسكرية"
                , "أحدثت هذه المؤسسة بمكناس عام 1918 تحت اسم \" المدرسة العسكرية للتلاميذ الضباط المغاربة – الدّار البيضاء\". وفي عام 1956، أصبحت  تحمل اسم الأكاديمية الملكية العسكرية(ARM) . \n" +
                "تعتبر الأكاديمية الملكية العسكرية نواة لتكوين ضباط القوات البرية للقوات المسلحة الملكية وتوفر التكوين الجامعي والعسكري الأولي والمستمر لصالح ضباط القوات المسلحة الملكية والدرك الملكي والقوات المساعدة، وكذلك القوات البرية للدول الصديقة في إطار التعاون العسكري. يستمر التكوين بالأكاديمية الملكية العسكرية لمدة أربع سنوات يحصل على إثرها التلميذ الضابط على:\n" +
                "-\tالإجازة بعد ثلاث سنوات من الدراسة في المسالك التالية:\n" +
                "•\tالعلوم والتقنيات ؛\n" +
                "•\tالعلوم القانونية ؛\n" +
                "•\tالأدب الإنجليزي.\n" +
                "-\tدبـــلوم الدراسات الجامعية والعسكرية بعد أربع سنوات من الدراسة. \n" +
                "ويتوج التكوين بهذه المؤسسة بالحصول على رتبة ملازم ثان. \n"
                , offcat, "/insignes/arm.png", "à l'Académie Royale Militaire - Meknès", "Général de Division, Directeur de L'Académie Royale Militaire", "Meknès"));

        Ecole lyceearm = ecoles.addEcole(new Ecole("Lycée de l'Académie Royale Militaire"
                , "Le Lycée fait partie intégrante de l’Académie Royale Militaire et partage son site et ses infrastructures ainsi que son histoire. Les origines de l’actuel lycée remontent au 19 janvier 1925. La durée de scolarisation au lycée, où le régime d’internat est obligatoire et gratuit, est de deux années sanctionnées par le diplôme du baccalauréat dans les options Sciences mathématiques ou expérimentales. Le lycée dépend du Ministère de l’Education Nationale au niveau des programmes scolaires. Les élèves de cet établissement ont droit à un enseignement de qualité et évoluent dans un cadre approprié stimulant l’éducation, la formation et la discipline."
                , "ثانوية الأكاديمية الملكية العسكرية"
                , "تعد هذه الثانوية جزء لا يتجزأ من الأكاديمية الملكية العسكرية حيث تتقاسم معها موقعها وبنياتها التحتية وكذا تاريخها، حيث يعود إحداث الثانوية إلى تاريخ  19 يناير 1925. وتمتد مدة الدراسة بالثانوية إلى سنتين يحصل على إثرها التلميذ على شهادة البكالوريا في العلوم الرياضية أو  العلوم التجريبية. وتخضع المناهج الدراسية بالثانوية إلى المقررات التي تعتمدها وزارة التربية الوطنية.  أما نظام الدراسة فهو داخلي ومجاني مما يوفر للتلاميذ المتمدرسين بيئة مناسبة تمكنهم من التعليم والتحصيل في ظروف جد ملائمة تساعد على التربية والتكوين والانضباط."
                , lyccat, "/insignes/arm.png", "Académie Royale Militaire - Meknès", "Général de Division, Directeur de L'Académie Royale Militaire", "Meknès"));

        Ecole crpta = ecoles.addEcole(new Ecole("Collège Royal Préparatoire aux Techniques Aéronautiques"
                , "Le Collège Royal préparatoire aux Techniques Aéronautiques (CRPTA) fait partie intégrante de l’Ecole Royale de l’Air et partage son site et ses infrastructures mais aussi son histoire. La création du Collège Royal Préparatoire aux Techniques Aéronautiques remonte à 1977. Ce lycée d'enseignement général a vu sa mission passer de la préparation de jeunes, qui y sont admis, pour l'obtention du baccalauréat Sciences Mathématiques ou Sciences Expérimentales, en vue d'intégrer les différents cycles de l'Ecole Royale Air, à celle de pourvoir cette dernière en candidats bacheliers Sciences Mathématiques à même de suivre leurs études en classes préparatoires (Mathématiques Supérieures- mathématiques Spéciales). Les programmes d'enseignement sont ceux définis par le Ministère de l'Education Nationale. Le régime des études est l'internat, où l'élève est totalement pris en charge (hébergement, nourriture, habillement, soins médicaux)."
                , "الثانوية الملكية الإعدادية للتقنيات الجوية"
                , "تعد الثانوية الملكية الإعدادية للتقنيات الجوية (CRPTA)  جزءً لا يتجزأ من المدرسة الملكية الجوية، حيث تشاركها موقعها وبنياتها التحتية وتاريخها. ويعود تاريخ إنشاء الثانوية الملكية الإعدادية للتقنيات الجوية إلى عام 1977. وقد تطورت مهمة هذه الثانوية الإعدادية للتعليم العام من إعداد الشباب المقبولين للحصول على شهادة البكالوريا في العلوم الرياضية أو العلوم التجريبية، وذلك بهدف ولوج التكوينات المختلفة بالمدرسة الملكية الجوية، إلى تزويد هذه الأخيرة بالمرشحين الحاصلين على شهادة البكالوريا في العلوم الرياضية وجعلهم قادرين على متابعة دراستهم بالأقسام التحضيرية (الرياضيات العليا - الرياضيات الخاصة). كما أن برامج التعليم تتم حسب المقررات المعتمدة من طرف وزارة التربية الوطنية. أما نظام الدراسة فهو داخلي حيث يتم التكفل بالتلميذ بالكامل (المسكن، التغذية، اللباس، الرعاية الطبية)."
                , lyccat, "/insignes/crpta.png", "à l'Ecole Royale de l'Air - Marrackech", "Général de Brigade de l'Aérienne, Commandant L'Ecole Royale de l'Air", "Marrackech"));

        Ecole erart = ecoles.addEcole(new Ecole("Ecole Royale de l'Artillerie"
                , "Le concours d’admission au cycle des Elèves Sous-officiers issus des Instituts Spécialisés de " +
                "Technologie Appliquée (ISTA) est organisé au titre de l’année 2021"
                , "المدرسة الملكية لسلاح المدفعية"
                , "تنظم القوات المسلحة الملكية مباراة لفائدة الشبان المغاربة ذكورا الذين لا تتجاوز أعمارهم 25 سنة لغاية فاتح ابريل2021،\n" +
                "الراغبين في االنخراط في سلك ضباط الصف ، خريجي المعاهد المتخصصة للتكنولوجيا التطبيقية"
                , soffcat, "/insignes/erart.png", "à l'Ecole Royale de l'Artillerie - Fès", "Colonel-Major, Directeur de l'Ecole Royale de l'Artillerie", "Fès"));

        Ecole era = ecoles.addEcole(new Ecole("Ecole Royale de l'Air",
                "Créée à Marrakech en 1970, l’Ecole Royale de l’Air (ERA) a pour mission la formation initiale et continue des officiers des Forces Royales Air. Le régime des études au sein de l’École Royale de l’Air comprend:\n" +
                        "-\t<b><U>Un Cycle Ingénieur:</U></b> Destiné aux bacheliers sciences mathématiques, orientés aux classes préparatoires, il porte sur l’enseignement supérieur scientifique et technique et la formation militaire et aéronautique. Il est d’une durée de cinq années, réparties comme suit:\n" +
                        "•\tDeux années de Classes Préparatoires Mathématiques et Physique ;\n" +
                        "•\tTrois années du cycle ingénieur sanctionnées par la nomination au grade de Sous-lieutenant. \n" +
                        "Cette formation comprend les filières suivantes:\n" +
                        "•\tPilotage ;\n" +
                        "•\tSystèmes Aéronautiques.\n" +
                        "En parallèle avec la formation scientifique et technique, les élèves-officiers suivent une formation militaire et sportive. Ces formations sont sanctionnées par l’obtention du diplôme d’Officier Ingénieur d’Etat.\n" +
                        "-\t<b><U>Un Cycle Licence:</U></b> Destiné aux bacheliers sciences mathématiques, il porte sur l’enseignement supérieur et la formation militaire et aéronautique. Il est d’une durée de quatre années, réparties comme suit:\n" +
                        "•\tTrois années pour l’obtention d’une License en Aéronautique dans les options principales suivantes:\n" +
                        "+\tPilotage ;\n" +
                        "+\tSystèmes aéronautiques ;\n" +
                        "+\tNavigation ;\n" +
                        "+\tInterprétation et analyse et renseignements.\n" +
                        "Une année d’enseignement militaire et professionnel au terme de laquelle l’élève-officier est nommé au grade de Sous-lieutenant, titulaire du diplôme des Etudes Universitaires et Aéronautiques (DEUA).\n"
                , "المدرسة الملكية الجوية"
                , "تم إحداث  المدرسة الملكية الجوية (ERA) بمراكش عام 1970 وتضطلع هذه  المؤسسة بمهمة التكوين الأولي والمستمر لضباط القوات الملكية الجوية. ويشمل نظام الدراسة في المدرسة الملكية الجوية مايلي:\n" +
                "- سلك المهندسين: بالنسبة للحاصلين على شهادة الباكالوريا تخصص علوم رياضية، والموجهين للأقسام التحضيرية، ويتضمن التعليم العالي العلمي والتقني وكذا التكوين العسكري والطيران. وتستغرق الدراسة مدة خمس سنوات، موزعة على النحو التالي:\n" +
                "-سنتان من الأقسام  التحضيرية في الرياضيات والفيزياء ؛\n" +
                "-ثلاث سنوات في سلك المهندسين يتوج بالحصول على رتبة ملازم ثان.\n" +
                "-يشمل هذا التكوين المسالك التالية:\n" +
                "-قيادة الطائرات ؛\n" +
                "-أنظمة الطيران.\n" +
                "يتابع التلاميذ الضباط تكوينا عسكريا ورياضيا بالموازاة مع التكوين العلمي والتقني، ويتوج هذا التكوين بالحصول على دبلوم ضابط مهندس دولة.\n" +
                "- سلك الإجازة: بالنسبة للحاصلين على شهادة البكالوريا تخصص علوم رياضية، ويرتكز على التعليم العالي والتكوين العسكري والطيران. وتستغرق مدة الدراسة أربع سنوات، موزعة على النحو التالي:\n" +
                "-ثلاث سنوات للحصول على الإجازة في الطيران في التخصصات الرئيسية التالية:\n" +
                "-قيادة الطائرات ؛\n" +
                "-أنظمة الطيران؛\n" +
                "-الملاحة الجوية؛\n" +
                "-تحليل وتأويل المعلومات.\n" +
                "-سنة من التعليم العسكري والمهني حيث يحصل التلميذ الضابط على إثرها على رتبة ملازم ثان، وعلى دبلوم الدراسات الجامعية والطيران.\n"
                , offcat, "/insignes/era.png", "à l'Ecole Royale de l'Air - Marrackech", "Général de Brigade de l'Air, Commandant l'Ecole Royale de l'Air", "Marrackech"));
        Ecole ern = ecoles.addEcole(new Ecole("Ecole Royale Navale",
                "Créée à Casablanca en 1967, l’École Royale Navale (ERN) a pour mission la formation initiale et la formation continue des officiers de la Marine Royale. Le régime des études au sein de l’École Royale Navale comprend:\n" +
                        "-\t<b><U>Un Cycle Ingénieur:</U></b> Destiné aux bacheliers sciences mathématiques, orientés aux classes préparatoires, il porte sur l’enseignement supérieur scientifique et technique et la formation militaire et navale. Il est d’une durée de cinq années, réparties comme suit:\n" +
                        "•\tDeux années de Classes Préparatoires Mathématiques et Physique ;\n" +
                        "•\tDeux années de formation scientifique, technique et professionnelle sanctionnées par la nomination au grade d’Enseigne de Vaisseau de 2°Classe (Sous-lieutenant) \n" +
                        "Cette formation comprend les filières suivantes:\n" +
                        "+ Systèmes Opérationnels Embarqués (SOE) comprenant des options ;\n" +
                        "+ Systèmes Energétiques (SE) comprenant des options ;\n" +
                        "•\tUne année de qualification sanctionnée par l’obtention du diplôme d’Officier Ingénieur d’Etat.\n" +
                        "-\t<b><U>Un Cycle Licence:</U></b> Destiné aux bacheliers sciences mathématiques, âgés de moins de 20 ans, il porte sur l’enseignement supérieur et la formation militaire et navale. Il est d’une durée de quatre années,  réparties comme suit:\n" +
                        "•\tTrois années pour l’obtention d’une Licence Professionnelle dans les filières suivantes:\n" +
                        "+ Électronique de Télécommunications ;\n" +
                        "+ Systèmes RADARS ;\n" +
                        "+ Systèmes d’Armes ;\n" +
                        "+ Thermie ;\n" +
                        "+ Électricité.\n" +
                        "•\tUne année d’enseignement militaire et professionnel au terme de laquelle l’élève-officier est nommé au grade d’Enseigne de Vaisseau de 2° Classe (Sous-lieutenant), titulaire du Diplôme des Etudes Universitaires et Navales (DEUN)."
                , "المدرسة الملكية البحرية"
                , "تم إحداث المدرسة الملكية البحرية (ERN) بالدار البيضاء عام 1967.  وتضطلع هذه المؤسسة بمهمة التكوين الأولي والتكوين المستمر لضباط البحرية الملكية. ويشتمل نظام الدراسة في المدرسة الملكية البحرية على:\n" +
                "\n" +
                "-سلك المهندسين: موجه إلى الحاصلين على شهادة الباكالوريا تخصص علوم رياضية، والموجهين للأقسام التحضيرية. ويتضمن التعليم العالي العلمي والتقني وكذا التكوين العسكري والبحري. وتستغرق مدة الدراسة خمس سنوات، موزعة على النحو التالي: \n" +
                "-سنتان في الأقسام التحضيرية في الرياضيات والفيزياء؛\n" +
                "-سنتان من التكوين العلمي والتقني والمهني تتوج بالتعيين في رتبة ملازم ثان، يشمل هذا التكوين المسالك التالية: \n" +
                "-أنظمة الإركاب العملياتية تتضمن عدة شعب؛\n" +
                "-الأنظمة الطاقية تتضمن عدة شعب؛\n" +
                "-سنة من التأهيل تتوج بالحصول على دبلوم ضابط مهندس دولة.\n" +
                "\n" +
                "-سلك الإجازة: مخصص للحاصلين على شهادة البكالوريا علوم رياضية، والذين لا تتجاوز أعمارهم 20 سنة، ويتضمن التعليم العالي والتكوين العسكري والبحري. وتستغرق مدة الدراسة أربع سنوات، موزعة على النحو التالي:\n" +
                "-ثلاث سنوات للحصول على الإجازة المهنية في الشعب التالية:\n" +
                "-إلكترونيات الاتصالات؛\n" +
                "-أنظمة الرادار؛\n" +
                "-أنظمة الأسلحة؛\n" +
                "-الطاقة الحرارية؛\n" +
                "-الكهرباء. \n" +
                "-سنة من التعليم العسكري والمهني حيث يتخرج التلميذ الضابط على إثرها برتبة ملازم ثان، ويحصل على دبلوم الدراسات الجامعية والبحرية."
                , offcat, "/insignes/ern.png", "Ecole Royale Navale - Casablanca", "Le Capitaine de Vaisseau Major, Directeur de l'Ecole Royale Navale", "Casablanca"));

        Ecole erssm = ecoles.addEcole(new Ecole("Ecole Royale du Service de Santé Militaire"
                , "Créée à Rabat en 1966, l’Ecole Royale du Service de Santé Militaire (ERSSM) est la pépinière des officiers médecins des Forces Armées Royales. Située à Rabat, elle est chargée de la formation universitaire et militaire initiale et continue des médecins des Forces Armées Royales ainsi que ceux appartenant aux pays partenaires dans le cadre de la coopération militaire. La formation au sein de l’ERSSM concerne:\n" +
                "•\tLes médecins pour une durée de sept ans ;\n" +
                "•\tLes chirurgiens-dentistes pour une durée de cinq ans ;\n" +
                "•\tLes pharmaciens pour une durée de six ans.\n" +
                "La formation est sanctionnée par la nomination des lauréats au grade de Lieutenant."
                , "المدرسة الملكية لمصلحة الصحة العسكرية"
                , "تأسست المدرسة الملكية لمصلحة الصحة العسكرية(ERSSM)  عام 1966 بالرباط. وتعتبر هذه المدرسة نواة لتكوين الضباط الأطباء للقوات المسلحة الملكية. وتوفر المدرسة التكوين الجامعي والعسكري الأولي والمستمر لأطباء القوات المسلحة الملكية، وكذلك لأطباء الدول الصديقة في إطار التعاون العسكري.\n" +
                "ويهم التكوين بالمدرسة التخصصات التالية: \n" +
                "\n" +
                "-\tالطب العام لمدة سبع سنوات؛\n" +
                "-\tجراحة الأسنان لمدة خمس سنوات؛\n" +
                "-\tالصيدلة لمدة ست سنوات.\n" +
                "ويتوج التكوين بهذه المؤسسة بالحصول على رتبة ملازم أول. \n"
                , offcat, "/insignes/erssm.png", "à l'Ecole Royale du Service de Santé Militaire - Rabat", "Médecin-Colonel, Directeur de l'Ecole Royale du Service de Santé Militaire", "Rabat"));

        Ecole erssm2 = ecoles.addEcole(new Ecole("Ecole Royale du Service de Santé Militaire (intégration)"
                , "Le recrutement au sein du service de santé des Forces Armées Royales constitue une opportunité pour:\n" +
                "• Servir au sein d’une institution pilote au niveau national et faire partie des équipes médicales expérimentées ;\n" +
                "• Suivre l’évolution continue de la recherche scientifique au niveau des hôpitaux militaires et des organismes partenaires ;\n" +
                "• Bénéficier d’une formation continue durant la carrière professionnelle ;\n" +
                "• Evoluer dans le domaine des secours, dans la hiérarchie militaire et bénéficier des garanties prévues par la loi fondamentale ;\n" +
                "• Bénéficier des services et des avantages sociaux des Forces Armées Royales ;\n" +
                "la formation dure une année et sera sanctionnée par la nomination au grade de médecin-Lieutenant."
                , "المدرسة الملكية للصحة العسكرية"
                , "يتيح الانخراط في مصلحة الصحة العسكرية للقوات المسلحة الملكية فرصة:\n" +
                "- للعمل في إطار منظومة صحية متميزة على الصعيد الوطني و ضمن فرق طبية ذات خبرة عالية،\n" +
                "- مواكبة تطورات البحث العلمي المستمر في المستشفيات العسكرية و الهيئات التشاركية،\n" +
                "-للاستفادة من تكوين مستمر خلال المسيرة المهنية،\n" +
                "-للارتقاء في قطاع الإسعاف و التسلسل الهرمي للضباط وكذلك الاستفادة من الضمانات التي يكفلها النظام الأساسي،\n" +
                "- الاستفادة من الفوائد و الخدمات الاجتماعية للقوات المسلحة الملكية.\n" +
                "\n" +
                "تدوم الدراسة لسنة كامل و سيتم تعيين الخريجين  برتبة طبيب ملازم.\n"
                , offcat, "/insignes/erssm.png", "à l'Ecole Royale du Service de Santé Militaire - Rabat", "Médecin-Colonel, Directeur de l'Ecole Royale du Service de Santé Militaire", "Rabat"));


        Ecole erb = ecoles.addEcole(new Ecole("Ecole Royale des Blindés"
                , "Créée  à Meknès en 1965, l’Ecole Royale des Blindés (ERB) est un établissement des Forces Armées Royales qui assure la formation initiale et continue des Officiers et Sous-officiers de l’Arme Blindée ainsi que ceux des pays partenaires dans le cadre de la coopération militaire. La durée de formation des Elèves Sous-officiers est de deux ans dans différentes spécialités relatives aux engins blindés.\n" +
                "La formation est sanctionnée par la nomination des lauréats au grade de Sergent."
                , "المدرسة الملكية للمدرعات"
                , "أحدثت المدرسة الملكية للمدرعات(ERB)  بمكناس عام 1965. وتوفر هذه المؤسسة التابعة للقوات المسلحة الملكية، تكوينا أوليًا ومستمرًا للضباط وضباط الصف التابعين لسلاح المدرعات لفائدة القوات المسلحة الملكية، وكذا للدول الصديقة في إطار التعاون العسكري. وتمتد فترة تكوين التلاميذ ضباط الصف إلى سنتين في تخصصات مختلفة تتعلق بالمركبات المدرعة.\n" +
                " ويتوج التكوين  بهذه المؤسسة بالحصول على رتبة رقيب. \n"
                , offcat, "/insignes/ERB.png", "à l'Ecole Royale des Blindés - Meknès", "Colonel, Directeur de l'Ecole Royale des Blindés", "Meknès"));

        Ecole cit = ecoles.addEcole(new Ecole("Centre d'Instruction des Transmissions"
                , "Créé à Rabat en 1957 avant être transféré à Kénitra en 1965, le Centre d’Instruction des Transmissions (CIT) est un établissement des Forces Armées Royales qui assure la formation initiale et continue des Officiers et Sous-officiers des Transmissions ainsi que ceux des pays partenaires dans le cadre de la coopération militaire. La durée de formation des Elèves Sous-officiers est de deux ans dans les branches suivantes:\n" +
                "•\tExploitant des Systèmes d’Information et de Communication (S.I.C) ;\n" +
                "•\tTechniques des Systèmes d’Information et de Communication (S.I.C) ;\n" +
                "•\tExploitant des Systèmes de Détection Electromagnétique (S.D.E) ;\n" +
                "•\tInformatique.\n" +
                "La formation est sanctionnée par la nomination des lauréats au grade de Sergent."
                , "مركز التدريب للإشارة"
                , "تم إحداث مركز التدريب للإشارة(CIT)  بالرباط عام 1957 قبل نقله إلى القنيطرة عام 1965. وتوفر هذه المؤسسة التابعة للقوات المسلحة الملكية، التكوين الأولي والمستمر للضباط وضباط الصف التابعين لسلاح الإشارة لفائدة القوات المسلحة الملكية، وكذا للدول الصديقة في إطار التعاون العسكري. وتمتد فترة تكوين التلاميذ ضباط الصف إلى سنتين وتشمل الفروع التالية:\n" +
                "-\tمشغل نظم المعلومات والاتصالات(S.I.C) ؛\n" +
                "-\tتقنيات نظم المعلومات والاتصالات(S.I.C)  ؛\n" +
                "-\tمشغل أنظمة الكشف الكهرومغناطيسي(S.D.E)  ؛\n" +
                "-\tالإعلاميات.\n" +
                "ويتوج التكوين  بهذه المؤسسة بالحصول على رتبة رقيب. \n"
                , offcat, "/insignes/cit.png", "au Centre d'Instruction des Transmissions - Kénitra", "Colonel, Commandant le Centre d'Instruction des Transmissions", "Kénitra"));

        Ecole cig = ecoles.addEcole(new Ecole("Centre d'Instruction Genie"
                , "Créé  à Kenitra en 1964, le Centre d’Instruction du Génie (CIG) est un établissement des Forces Armées Royales qui assure la formation tactique et technique des Officiers et Sous-officiers de l’Arme du Génie et ceux d’autres Armes et Services des FAR ainsi que ceux des pays partenaires dans le cadre de la coopération militaire. La durée de formation des Elèves Sous-officiers est de deux ans dans les branches suivantes:\n" +
                "•\tTerrassement et engins ;\n" +
                "•\tDessin ;\n" +
                "•\tTopométrie ;\n" +
                "•\tSurveillance de chantier.\n" +
                "La formation est sanctionnée par la nomination des lauréats au grade de Sergent."
                , "مركز التدريب للهندسة"
                , "تم إحداث مركز التدريب للهندسة(CIG)  بالقنيطرة عام 1964. وتوفر هذه المؤسسة التابعة للقوات المسلحة الملكية، التكوين الأولي والمستمر للضباط وضباط الصف التابعين لسلاح الهندسة وغيرها من أسلحة ومصالح القوات المسلحة الملكية، وكذا لفائدة الدول الصديقة في إطار التعاون العسكري. وتمتد فترة تكوين التلاميذ ضباط الصف إلى سنتين  وتشمل الفروع التالية:\n" +
                "\t\n" +
                "-\tالحفر والآلات ؛\n" +
                "-\tالرسم الهندسي ؛\n" +
                "-\tالقياس الطوبوغرافي ؛\n" +
                "-\tمراقبة الأوراش.\n" +
                "ويتوج التكوين  بهذه المؤسسة بالحصول على رتبة رقيب.\n"
                , soffcat, "/insignes/cig.png", "Centre d'Instruction Genie - Kénitra", "Colonel, Commandant le Centre d'Instruction Genie", "Kénitra"));
        Ecole cift = ecoles.addEcole(new Ecole("Centre d'Instruction des Formationns des Trains"
                , "Créé à Ain-Harouda en 1964, le Centre d’Instruction des Formations du Train (CIFT) est un établissement des Forces Armées Royales qui assure la formation initiale et continue des Officiers et Sous-officiers de l’Arme du train ainsi que ceux des pays partenaires dans le cadre de la coopération militaire. La durée de formation des Elèves Sous-officiers est de deux ans dans différentes spécialités relatives à la circulation et au transport.\n" +
                "La formation est sanctionnée par la nomination des lauréats au grade de Sergent.",
                "مركز التدريب لوحدات النقل"
                , "تم إحداث مركز التدريب لوحدات النقل(CIFT)  بعين حرودة عام 1964. وتوفر هذه المؤسسة التابعة للقوات المسلحة الملكية، التكوين الأولي والمستمر للضباط وضباط الصف التابعين لسلاح النقل لفائدة القوات المسلحة الملكية، وكذا للدول الصديقة في إطار التعاون العسكري. وتمتد فترة تكوين التلاميذ ضباط الصف إلى سنتين في مختلف التخصصات المتعلقة بالمرور والنقل.\n" +
                "ويتوج التكوين  بهذه المؤسسة بالحصول على رتبة رقيب.\n"
                , soffcat, "/insignes/cift.png", "au Centre d'Instruction des Formationns des Trains - Ain Harrouda", "Colonel, Commandant le Centre d'Instruction des Formationns des Trains", "Ain Harrouda"));

        Ecole erm = ecoles.addEcole(new Ecole("Ecole Royale du Materiel"
                , "Située à Ben Slimane, l’Ecole Royale du Matériel (ERM) est un établissement des Forces Armées Royales qui assure la formation initiale et continue des Officiers et Sous-officiers du matériel ainsi que ceux des pays partenaires dans le cadre de la coopération militaire. La durée de formation des Elèves Sous-officiers est de deux ans dans les branches suivantes:\n" +
                "•\tMécanique générale ;\n" +
                "•\tElectricité auto ;\n" +
                "•\tOptronique et autres spécialités ;\n" +
                "•\tTechniques de gestion.\n" +
                "La formation est sanctionnée par la nomination des lauréats au grade de Sergent."
                , "المدرسة الملكية للعتاد"
                , "تقع المدرسة الملكية للعتاد(ERM)  بابن سليمان، وتوفر هذه المؤسسة التابعة للقوات المسلحة الملكية، التكوين الأولي والمستمر للضباط وضباط الصف التابعين لمصلحة العتاد للقوات المسلحة الملكية، وكذا لفائدة الدول الصديقة في إطار التعاون العسكري. وتمتد فترة تكوين التلاميذ ضباط الصف إلى سنتين  وتشمل الفروع التالية:\n" +
                "\t\n" +
                "-\tميكانيك عامة ؛\n" +
                "-\tكهرباء السيارات ؛\n" +
                "-\tالبصريات وتخصصات أخرى ؛\n" +
                "-\tتقنيات التدبير.\n" +
                "ويتوج التكوين  بهذه المؤسسة بالحصول على رتبة رقيب.\n"
                , soffcat, "/insignes/erm.png", "à l'Ecole Royale du Materiel - Ben Slimane", "Colonel, Directeur de l'Ecole Royale du Materiel", "Ben Slimane"));

        Ecole eri = ecoles.addEcole(new Ecole("Ecole Royale de l'Infanterie"
                , "Créée  à Marrakech en 1976 avant être  réimplantée à Benguérir en 1992, l’Ecole Royale d’Infanterie (ERI) est un établissement des Forces Armées Royales qui assure la formation initiale et continue des Officiers et Sous-officiers de l’Arme Infanterie ainsi que ceux des pays partenaires dans le cadre de la coopération militaire. La durée de formation des Elèves Sous-officiers est de deux ans dans différentes spécialités relatives à l’Arme de l’Infanterie.\n" +
                "La formation est sanctionnée par la nomination des lauréats au grade de Sergent."
                , "المدرسة الملكية للمشاة"
                , "أحدثت المدرسة الملكية للمشاة(ERI)  بمراكش عام 1976. وفي عام 1992، تم نقلها إلى مقرها الحالي بابن جرير. وتوفر هذه المؤسسة التابعة للقوات المسلحة الملكية، التكوين الأولي والمستمر للضباط وضباط الصف التابعين لسلاح المشاة لفائدة القوات المسلحة الملكية، وكذا للدول الصديقة في إطار التعاون العسكري. تمتد فترة تكوين التلاميذ ضباط الصف إلى سنتين وتشمل مختلف تخصصات سلاح المشاة.\n" +
                " ويتوج التكوين  بهذه المؤسسة بالحصول على رتبة رقيب. \n"
                , soffcat, "/insignes/eri.png", "à l'Ecole Royale de l'Infanterie - Benguérir", "Colonel-Major, Directeur de l'Ecole Royale de l'Infanterie", "Benguérir"));

        Ecole erc = ecoles.addEcole(new Ecole("Ecole Royale de la Cavalerie"
                , "Créée à Temara en 1987, l’Ecole Royale de Cavalerie (ERC) est un établissement des Forces Armées Royales qui assure la formation initiale et continue des Officiers et Sous-officiers de la cavalerie des FAR ainsi que ceux des pays partenaires dans le cadre de la coopération militaire. La durée de formation des Elèves Sous-officiers est de deux ans dans les branches suivantes:\n" +
                "•\tCavalerie ;\n" +
                "•\tMaréchalerie ;\n" +
                "•\tMédecine vétérinaire.\n" +
                "La formation est sanctionnée par la nomination des lauréats au grade de Sergent."
                , "المدرسة الملكية للخيالة"
                , "أحدثت المدرسة الملكية للخيالة(ERC)  بتمارة عام 1987. وتوفر هذه المؤسسة التابعة للقوات المسلحة الملكية، التكوين الأولي والمستمر للضباط وضباط الصف التابعين لسلاح الخيالة لفائدة القوات المسلحة الملكية، وكذا للدول الصديقة في إطار التعاون العسكري. وتمتد فترة تكوين التلاميذ ضباط الصف إلى سنتين وتشمل الفروع التالية:\n" +
                "\n" +
                "-\tالخيالة ؛\n" +
                "-\tالنعالة والحدادة ؛\n" +
                "-\tالطب البيطري.\n" +
                "ويتوج التكوين  بهذه المؤسسة بالحصول على رتبة رقيب. \n"
                , soffcat, "/insignes/erc.png", "à l'Ecole Royale de la Cavalerie - Temara", "Colonel, Directeur de l'Ecole Royale de la Cavalerie", "Temara"));

        Ecole ciss = ecoles.addEcole(new Ecole("Centre d'Instruction des Services Sociaux"
                , "Situé à Temara, le Centre d’Instruction des Services Sociaux (CISS) est un établissement des Forces Armées Royales, qui assure  la formation initiale et continue des Officiers et Sous-officiers féminins des services sociaux ainsi que ceux des pays partenaires dans le cadre de la coopération militaire. La durée de formation des Elèves Sous-officiers est de deux ans dans les branches suivantes:\n" +
                "•\tAssistante sociale adjointe ;\n" +
                "•\tEducatrice d’enfants.\n" +
                "La formation est sanctionnée par la nomination des lauréates au grade de Sergent."
                , "مركز التدريب للمصالح الاجتماعية"
                , "يقع مركز التدريب للمصالح الاجتماعية(CISS)  للقوات المسلحة الملكية بتمارة، وهو مؤسسة توفر التكوين الأولي والمستمر للضباط وضباط الصف (الإناث) التابعين للمصالح الاجتماعية، وكذا لفائدة الدول الصديقة في إطار التعاون العسكري. وتمتد فترة تكوين التلاميذ ضباط الصف إلى سنتين وتشمل الفروع التالية:\n" +
                "-\tمساعدة اجتماعية\n" +
                "-\tمربية الأطفال.\n" +
                "ويتوج التكوين  بهذه المؤسسة بالحصول على رتبة رقيب.  \n"
                , soffcat, "/insignes/ciss.png", "au Centre d'Instruction des Services Sociaux - Temara", "Colonel-Major, Commandant le Centre d'Instruction des Services Sociaux", "Temara"));

        Ecole cii = ecoles.addEcole(new Ecole("Centre D’Instruction de l’Intendance"
                , "Situé à Bouknadel, le Centre d’Instruction de l’Intendance (CII) est un établissement des Forces Armées Royales qui assure la formation initiale et continue des Officiers et Sous-officiers destinés à toutes les unités et formations des Forces Armées Royales et des pays partenaires au titre de la coopération militaire dans les spécialités de l’Intendance. La formation des Elèves Sous-officiers dure deux ans dans les branches suivantes:\t\n" +
                "•\tComptabilité ;\n" +
                "•\tSecrétariat médicale.\n" +
                "La formation est sanctionnée par la nomination des lauréats au grade de Sergent."
                , "مركز التدريب للتموين العسكري"
                , "يقع مركز التدريب للتموين العسكري(CII)  ببوقنادل، وهو مؤسسة تابعة للقوات المسلحة الملكية توفر التكوين الأولي والمستمر في تخصصات التموين العسكري للضباط وضباط الصف التابعين لجميع وحدات ومكونات القوات المسلحة الملكية، وكذا لفائدة الدول الصديقة في إطار التعاون العسكري. وتمتد فترة تكوين التلاميذ ضباط الصف إلى سنتين  وتشمل الفروع التالية:\n" +
                "-\tالمحاسبة ؛\n" +
                "-\tالكتابة الطبية.\n" +
                "ويتوج التكوين  بهذه المؤسسة بالحصول على رتبة رقيب. \n"
                , soffcat, "/insignes/cii.png", "au Centre d’Instruction de l’Intendance - Bouknadel", "Colonel, Commandant le Centre D’Instruction de l’Intendance", "Bouknadel"));

        Ecole cfsno = ecoles.addEcole(new Ecole("Ecole de Formation des Spécialistes Non-Officiers"
                , "Située à Marrakech, l’Ecole de Formation des Spécialistes Non-Officiers (EFSNO), a pour mission d’assurer la formation de base et la formation continue du Personnel non-officiers spécialiste des Forces Royales Air. Elle comprend:\n" +
                "•\tDes formations destinées aux bacheliers scientifiques d’une durée de trois années, sanctionnées par la nomination au grade de Sergent ;\n" +
                "•\tUne formation destinée aux lauréats des ISTA d’une durée d’une année, à l’issue de laquelle les élèves sont nommés au grade de Sergent.\n" +
                "Les spécialités enseignées au sein de l’Ecole de Formation des Spécialistes Non-Officiers couvrent les domaines suivants:\n" +
                "-\tLes spécialités techniques aéronautiques ;\n" +
                "-\tLes spécialités de Soutien Opérationnel ;\n" +
                "Les spécialités de soutien de base."
                , "مدرسة تكوين المتخصصين سلك غير الضباط"
                , "تقع مدرسة تكوين المتخصصين سلك غير الضباط(EFSNO)  بمراكش، وتأخذ على عاتقها توفير التكوين الأساسي والتكوين المستمر لفائدة أفراد سلك غير الضباط المتخصصين التابعين للقوات الملكية الجوية. وتشتمل الدراسة بها على:\n" +
                "- تكوينات مخصصة للحاصلين على شهادة البكالوريا العلمية: تمتد لثلاث سنوات، وتتوج بالحصول على رتبة رقيب ؛\n" +
                " -تكوين مخصص لخريجي المعاهد العليا للتكنولوجيا التطبيقية: لمدة سنة، يتم بعدها تعيين التلاميذ ضباط الصف برتبة رقيب.\n" +
                "التخصصات التي يتم تدريسها بهذه المدرسة هي على النحو التالي:\n" +
                "- التخصصات التقنية للطيران ؛\n" +
                "- تخصصات الدعم العملياتي ؛\n" +
                "- تخصصات الدعم الأساسي.\n"
                , soffcat, "/insignes/efsno.png", "à l'Ecole de Formation des Spécialistes Non-Officiers - Marrakech", "Colonel, Commandant la Base Ecoles des Forces Royales Air", "Marrakech"));

        Ecole cimr = ecoles.addEcole(new Ecole("Centre d’Instruction de la Marine Royale"
                , "Créé à Casablanca en 1967, le Centre d’Instruction de la Marine Royale (CIMR), a pour mission la formation initiale et continue du personnel             non-officiers de la Marine Royale. Le régime des études au sein du Centre d’Instruction de la Marine Royale comprend une formation de base, de spécialité et de navalisation destinée aux élèves de la maistrance. Il comprend:\n" +
                "•\tDes formations destinées aux bacheliers scientifiques d’une durée de trois années, sanctionnées par le grade de Second Maître de 2° Classe (sergent) ;\n" +
                "•\tUne formation destinée aux lauréats des ISTA d’une durée d’une année, à l’issue de laquelle les élèves sont nommés au grade de Second Maître de 2° Classe (sergent).\n" +
                "\tLes spécialités enseignées au sein du Centre d’Instruction de la Marine Royale couvrent les domaines suivants:\n" +
                "\tOpérations ;\n" +
                "+ Systèmes d’Information et de Communication ;\n" +
                "+ Armes ;\n" +
                "+ Navigation ;\n" +
                "+ Mécanique, électricité et sécurité ;\n" +
                "+ Hydrographie, océanographie et météorologie ;\n" +
                "+ Aéronavale (Mécanique, électronique et navigation) ;\n" +
                "+ Plongée sous-marine et infanterie ;\n" +
                "+ Administration et comptabilité.\n"
                , "مركز التدريب للبحرية الملكية"
                , "تم إحداث مركز التدريب للبحرية الملكية(CIMR)  بالدار البيضاء عام 1967. وتتمثل مهمة هذه المؤسسة في التكوين الأولي والمستمر لأفراد سلك غير الضباط للبحرية الملكية. ويشمل نظام الدراسة في مركز التدريب للبحرية الملكية تكوينا أساسيا متخصصا، وكذا الملاحة البحرية لفائدة التلاميذ ضباط الصف. ويشتمل هذا التكوين على الشعب التالية:\n" +
                "\uF0FC\tتكوينات مخصصة للحاصلين على شهادة البكالوريا العلمية تمتد لثلاث سنوات، وتتوج بالحصول على رتبة ضابط صف من الدرجة الثانية (رقيب)؛\n" +
                "\uF0FC\tتكوين مخصص لخريجي المعاهد العليا للتكنولوجيا التطبيقية لمدة سنة، ويتوج هذا التكوين بالحصول على رتبة ضابط صف من الدرجة الثانية (رقيب).\n" +
                "وتشمل التخصصات التي يتم تدريسها في مركز التدريب للبحرية الملكية المجالات التالية:\n" +
                "-العمليات؛\n" +
                "-نظم المعلومات والاتصالات؛\n" +
                "-الأسلحة؛\n" +
                "-الملاحة البحرية؛\n" +
                "-الميكانيك والكهرباء والأمن؛\n" +
                "-الهيد روغرافيا، علم المحيطات والأرصاد الجوية؛\n" +
                "-الطيران البحري (الميكانيك والإلكترونيك والملاحة)؛\n" +
                "-الغوص والمشاة؛\n" +
                "-الإدارة والمحاسبة.\n"
                , soffcat, "/insignes/cimr.png", "au Centre d’Instruction de la Marine Royale - Casablanca", "Capitaine de Vaisseau, Commandant le Centre d’Instruction de la Marine Royale", "Casablanca"));

        Ecole cita = ecoles.addEcole(new Ecole("Centre d’Instruction des Troupes Aéroportées"
                , "Situé à Salé, le Centre d’Instruction des Troupes Aéroportées (CITAP) est un établissement de formation des Forces Armées Royales, qui assure la formation initiale et continue des Officiers et Sous-officiers des troupes aéroportées des F.A.R ainsi que ceux des pays partenaires dans le cadre de la coopération militaire. La durée de formation des Elèves Sous-officiers est de deux ans dans les différentes spécialités du métier relatives au parachutisme.\n" +
                "La formation est sanctionnée par la nomination des lauréats au grade de Sergent."
                , "مركز التدريب للمظليين"
                , "يقع مركز التدريب للمظليين(CI/TAP)  بسلا، وهو مؤسسة توفر التدريب الأولي والمستمر للضباط وضباط الصف التابعين لوحدات المظليين للقوات المسلحة الملكية، وكذا لفائدة الدول الصديقة في إطار التعاون العسكري. وتمتد فترة تكوين التلاميذ ضباط الصف إلى سنتين في مختلف تخصصات المظليين.\n" +
                " ويتوج التكوين  بهذه المؤسسة بالحصول على رتبة رقيب. \n"
                , soffcat, "/insignes/citap.png", "à la Base Ecoles/FRA - Salé", "Colonel, Commandant le Centre d’Instruction des Troupes Aéroportées", "Salé"));

        Ecole erpplm = ecoles.addEcole(new Ecole("ERPP Lalla Meryem"
                , "Située à Rabat, l’Ecole Royale du Personnel Paramédical Lalla Meryem (ERPPLM) est un établissement des Forces Armées Royales, qui assure la formation initiale et continue des infirmiers des Forces Armées Royales ainsi que ceux des pays partenaires dans le cadre de la coopération militaire. La durée de formation est de trois ans dans différentes spécialités médicales et paramédicales.\n" +
                "La formation est sanctionnée par la nomination des lauréats au grade de Sergent."
                , "المدرسة الملكية للأطر شبه الطبية للا مريم"
                , "تقع المدرسة الملكية للأطر الشبه الطبية للالة مريم(ERPPLM)  بالرباط، وتوفر هذه المؤسسة التابعة للقوات المسلحة الملكية، التكوين الأولي والمستمر للممرضين التابعين للقوات المسلحة الملكية، وكذا لفائدة الدول الصديقة في إطار التعاون العسكري. وتمتد فترة التكوين ثلاث سنوات في التخصصات الطبية وشبه الطبية.\n" +
                "ويتوج التكوين بهذه المؤسسة بالحصول على رتبة رقيب.\n"
                , soffcat, "/insignes/erpplm.png", "ERPP Lalla Meryem - Rabat", "Pharmacien Officier Féminin Hors Classe, Directeur de l'ERPP Lalla Meryem", "Rabat"));

        Ecole csfar = ecoles.addEcole(new Ecole("Centre Sportif des Forces Armées Royales"
                , "Créé à Salé en 1959, le Centre Sportif (CS/FAR) est un établissement des Forces Armées Royales, qui assure la formation initiale des moniteurs en éducation physique militaire au profit de l’ensemble des unités des Forces Armées Royales ainsi que ceux des pays partenaires dans le cadre de la coopération militaire.\n" +
                "La formation des Elèves Sous-officiers dure deux ans. Elle est sanctionnée par la nomination des lauréats au grade de Sergent."
                , "المركز الرياضي للقوات المسلحة الملكية"
                , "تم إحداث المركز الرياضي(CS/FAR)  للقوات المسلحة الملكية بسلا عام 1959. وتوفر هذه المؤسسة التابعة للقوات المسلحة الملكية، التكوين الأولي للمدربين في مجال التربية البدنية العسكرية لصالح جميع وحدات القوات المسلحة  الملكية، وكذا لفائدة البلدان الصديقة في إطار التعاون العسكري. \n" +
                "ويتوج التكوين بهذه المؤسسة بالحصول على رتبة رقيب. \n"
                , soffcat, "/insignes/csfar.png", "au Centre Sportif des Forces Armées Royales - Salé", "Colonel, Commandant le Centre Sportif des Forces Armées Royales", "Salé"));

        Ecole crfi1 = ecoles.addEcole(new Ecole("AVIS DE CONCOURS DE RECRUTEMENT AUX SEIN DES FORCES ARMEES ROYALES EN QUALITE DE MILITAIRE DU RANG (2°CLASSE)"
                , "Le concours de recrutement des militaires du rang (2°classe) est organisé au titre de l'année 2021 pour les jeunes marocains de sexe masculin agé de 18 à 22 ans à la date du 31.12.2020 "
                , "مـبـاراة ولوج القوات المسلحة الملكية برتبة جندي من الدرجة الثانية"
                , "تنظم القوات المسلحة الملكية مباراة لفائدة الشبان المغاربة ذكورا و المتراوحة أعمارهم ما بين 18 و22 سنة لغاية 31 دجنبر 2020، الراغبين الانخراط في صفوف القوات المسلحة الملكية (برتبة جندي من الدرجة الثانية)."
                , milcat, "/insignes/cfri.png", "", "", "El-hajeb"));

        logger.info("ecoles ajoutées");
        //Langues
        LangueMetier langues = context.getBean(LangueMetier.class);
        Langue anglais = langues.addLangue(new Langue("Anglais", "الإنجليزية"));
        langues.addLangue(new Langue("Allemand", "المانية"));
        langues.addLangue(new Langue("Italien", "الايطالية"));
        langues.addLangue(new Langue("Espagnol", "الاسبانية"));
        logger.info("langues ajoutées");

        //Sexe
        SexeMetier sexes = context.getBean(SexeMetier.class);
        Sexe masculin = sexes.addSexe(new Sexe("Masculin", "ذكر"));
        Sexe feminin = sexes.addSexe(new Sexe("Feminin", "أنثى"));
        List<Sexe> masculins = new ArrayList<>();
        List<Sexe> feminins = new ArrayList<>();
        masculins.add(masculin);
        feminins.add(feminin);
        logger.info("sexes ajoutées");
        //Stituation
        SituationMetier situations = context.getBean(SituationMetier.class);
        situations.addSituation(new Situation("Célibataire", "عازب"));
        situations.addSituation(new Situation("Marié", "متزوج"));
        logger.info("situations ajoutées");
        //Statue
        StatutMetier statuts = context.getBean(StatutMetier.class);
        statuts.addStatut(new Statut("En cours", "جاري"));
        statuts.addStatut(new Statut("Convoqué", "استدعي"));
        statuts.addStatut(new Statut("Rejetée", "مرفوض"));
        statuts.addStatut(new Statut("Admis", "مقبول"));
        statuts.addStatut(new Statut("List d'attente", "لائحة الإنتظار"));
        logger.info("statuts ajoutés");
        //Etude
        EtudeMetier etudes = context.getBean(EtudeMetier.class);
        Etude bac = etudes.addEtude(new Etude("BAC"));
        logger.info("etudes ajoutées");
        //Options
        OptionMetier options = context.getBean(OptionMetier.class);

        options.addOption(new Option("Sciences Maths A", "علوم رياضية ا", bac));
        options.addOption(new Option("Sciences Maths B", "علوم رياضية ب", bac));
        /*options.addOption(new Option("Systèmes automatisés", "الأنظمة التلقائية", ista));
        options.addOption(new Option("Maintenance Aéronautique", "الصيانة في مجال الطيران", ista));
        options.addOption(new Option("Maintenance industrielle", "الصيانة الصناعية", ista));
        options.addOption(new Option("Electronique", "صناعة الالكترونيات", ista));
        options.addOption(new Option("Mécatronique", "ميكاترونيك", ista));
        options.addOption(new Option("Electromécanique des systèmes automatisés", "إلكتروميكانيك الأنظمة التلقائية", ista));
        options.addOption(new Option("Météorologie", "الأرصاد الجوية", ista));
        options.addOption(new Option("Techniques de développement informatique", "تقنيات التنمية في المعلوميات", ista));
        options.addOption(new Option("Techniques des réseaux informatiques", "تقنيات الشبكات المعلوميات", ista));*/

        NiveauMetier niveaux = context.getBean(NiveauMetier.class);
        niveaux.addNiveau(new Niveau("Bac 2020", "باك 2020", bac));
        logger.info("niveaux ajoutés");
        //villes et regions
        VilleMetier villes = context.getBean(VilleMetier.class);
        RegionMetier regions = context.getBean(RegionMetier.class);
        Region region = null;
        Ville ville = null;
        try (
                InputStreamReader isr = new InputStreamReader(new FileInputStream("villesFr.txt"), "utf-8");//IBM437
                BufferedReader flot = new BufferedReader(isr);
                InputStreamReader isr2 = new InputStreamReader(new FileInputStream("villesAr.txt"), "utf-8");//IBM437
                BufferedReader flot2 = new BufferedReader(isr2);
        ) {
            String line;
            String line2;
            boolean nouveau = true;
            while ((line = flot.readLine()) != null && (line2 = flot2.readLine()) != null) {
                logger.info(line + " - " + line2);
                if (nouveau) {
                    region = regions.addRegion(new Region(line, line2));
                    nouveau = false;
                } else if (line.equals("<>")) {
                    nouveau = true;
                } else {
                    ville = villes.addVille(new Ville(line, line2, region));
                }
            }
        } catch (IOException e) {
            logger.info(e.toString());
        }

        ConcoursMetier concours = context.getBean(ConcoursMetier.class);
        Concours con = new Concours();

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des élèves Officiers de l'Académie Royale Militaire");
        con.setTitreAr("الأكاديمية العسكرية الملكية");
        con.setCategorie(officier);
        con.setEcoleFormation(arm);
        con.setEcoleTest(arm);
        con.setDisponible(true);
        con.setConditions("⦁	Etre de nationalité marocaine ;\r\n" +
                "⦁	Etre célibataire ;\r\n" +
                "⦁	Etre âgé(e) de 18 ans au moins et 22 ans au plus, au 31/12/2019 ;\r\n" +
                "⦁	Ne pas avoir encouru de condamnation judiciaire  ;\r\n" +
                "⦁	Etre bachelier (ère) de l’année 2019 ;\r\n" +
                "⦁	Etre apte physiquement ;\r\n" +
                "⦁	Avoir au minimum une taille de 1,70m pour les garçons et 1,60m pour les filles ; \r\n" +
                "⦁	Etre retenu par la commission de présélection.");
        con.setConditionsAr("");
        con.setImage("620e7e5d-47cb-49d6-897f-b9570dc3dd1d.jpg");
        con.setArmee(terre);
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
        } catch (ParseException e) {
            logger.warn("parse exception "+e);
        }
        concours.addConcours(con);
        logger.info("concours arm créé avec succées");

        //ERA ingenieurs

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des élèves Officiers de l'Ecole Royale de l'Air (Cycle Ingénieur)");
        con.setTitreAr("المدرسة الملكية الجوية (سلك المهندسين)");
        con.setCategorie(officier);
        con.setEcoleFormation(era);
        con.setEcoleTest(era);
        con.setDisponible(true);
        con.setConditions("⦁	Etre de nationalité marocaine ;\r\n" +
                "⦁	Etre célibataire ;\r\n" +
                "⦁	Etre âgé de 18 au moins et 20ans au plus, à la fin de l’année en cours;\r\n" +
                "⦁	Ne pas avoir encouru de condamnation judiciaire ;\r\n" +
                "⦁	Etre bachelier de l’année courante série « sciences mathématiques » et orienté vers les classes préparatoires (option mathématiques et physiques);\r\n" +

                "⦁	Etre apte physiquement ;\r\n" +

                "⦁	Avoir une taille minimum de 1.65m pour les garçons et 1.60m pour les filles;\r\n" +
                "⦁	Etre retenu par la commission de présélection.");
        con.setConditionsAr("");
        con.setArmee(aviation);
        con.setImage("979a3953-426c-414a-a787-01ff556c0793.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
        } catch (ParseException e) {
            logger.warn("parse exception "+e);
        }
        concours.addConcours(con);
        user2.getConcours().add(con);
        logger.info("concours era ingenieurs créé avec succées");

        //ERA licence

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des élèves Officiers de l'Ecole Royale de l'Air (Cycle Licence)");
        con.setTitreAr("المدرسة الملكية الجوية (سلك الإجازة)");
        con.setCategorie(officier);
        con.setEcoleFormation(era);
        con.setEcoleTest(era);
        con.setDisponible(true);
        con.setConditions("⦁	Etre de nationalité marocaine ;\r\n" +
                "⦁	Etre célibataire ;\r\n" +
                "⦁	Etre âgé de 18 au moins et 20ans au plus, à la fin de l’année en cours;\r\n" +
                "⦁	Ne pas avoir encouru de condamnation judiciaire ;\r\n" +
                "⦁	être bachelier de l’année courante ou précédente série « sciences mathématiques » ;\r\n" +

                "⦁	Etre apte physiquement ;\r\n" +

                "⦁	Avoir une taille minimum de 1.65m pour les garçons et 1.60m pour les filles;\r\n" +
                "⦁	Etre retenu par la commission de présélection.");
        con.setConditionsAr("");
        con.setArmee(aviation);
        con.setImage("ee4d1a95-76b4-4860-a289-aff002326618.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
        } catch (ParseException e) {
            logger.warn("parse exception "+e);
        }
        concours.addConcours(con);
        user2.getConcours().add(con);
        logger.info("concours era licence créé avec succées");

        //ERN ingenieurs

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des élèves Officiers de l'Ecole Royale Navale(Cycle Ingénieur)");
        con.setTitreAr("المدرسة الملكية البحرية (سلك المهندسين)");
        con.setCategorie(officier);
        con.setEcoleFormation(ern);
        con.setEcoleTest(ern);
        con.setDisponible(true);
        con.setConditions("⦁	Etre de nationalité marocaine ;\r\n" +
                "⦁	Etre célibataire ;\r\n" +
                "⦁	Etre âgé de 18 au moins et 20ans au plus, à la fin de l’année en cours;\r\n" +
                "⦁	Ne pas avoir encouru de condamnation judiciaire ;\r\n" +
                "⦁	Etre bachelier de l’année courante série « sciences mathématiques » et orienté vers les classes préparatoires (option mathématiques et physiques);\r\n" +

                "⦁	Etre apte physiquement ;\r\n" +

                "⦁	Avoir une taille minimum de 1.65m pour les garçons et 1.60m pour les filles;\r\n" +
                "⦁	Etre retenu par la commission de présélection.");
        con.setConditionsAr("");
        con.setArmee(marine);
        con.setImage("1953f48e-098b-4b5b-baa9-40db3d6c28c3.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
        } catch (ParseException e) {
            logger.warn("parse exception "+e);
        }
        concours.addConcours(con);
        logger.info("concours ern ingenieurs créé avec succées");

        //ERN licence

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des élèves Officiers de l'Ecole Royale navale (Cycle Licence)");
        con.setTitreAr("المدرسة الملكية البحرية (سلك الإجازة)");
        con.setCategorie(officier);
        con.setEcoleFormation(ern);
        con.setEcoleTest(ern);
        con.setDisponible(true);
        con.setConditions("⦁	Etre de nationalité marocaine ;\r\n" +
                "⦁	Etre célibataire ;\r\n" +
                "⦁	Etre âgé de 18 au moins et 20ans au plus, à la fin de l’année en cours;\r\n" +
                "⦁	Ne pas avoir encouru de condamnation judiciaire ;\r\n" +
                "⦁	être bachelier de l’année courante ou précédente série « sciences mathématiques » ;\r\n" +

                "⦁	Etre apte physiquement ;\r\n" +

                "⦁	Avoir une taille minimum de 1.65m pour les garçons et 1.60m pour les filles;\r\n" +
                "⦁	Etre retenu par la commission de présélection.");
        con.setConditionsAr("");
        con.setArmee(marine);
        con.setImage("6675c513-ea72-4cd0-8b61-3058b51b9571.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
        } catch (ParseException e) {
            logger.warn("parse exception "+e);
        }
        concours.addConcours(con);
        logger.info("concours ern licence créé avec succées");

        //ERSSM medecins

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des élèves Officiers de l'Ecole Royale du Service de Santé  Militaire (Médecin)");
        con.setTitreAr("أطباء");
        con.setCategorie(officier);
        con.setEcoleFormation(erssm);
        con.setEcoleTest(erssm);
        con.setDisponible(true);
        con.setConditions("⦁	Etre de nationalité marocaine ;\r\n" +
                "⦁	Etre célibataire ;\r\n" +
                "⦁	Ne pas avoir encouru de condamnation judiciaire ;\r\n" +
                "⦁	Etre apte physiquement ;\r\n" +
                "⦁	Avoir une taille minimum de 1.65m pour les garçons et 1.60m pour les filles;\r\n" +
                "⦁	Etre retenu par la commission de présélection.\r\n" +
                "⦁	Etre âgé de 18 au moins et 22ans au plus, à la fin de l’année en cours;\r\n" +


                "⦁	Etre bachelier de l’année 2018, séries « sciences expérimentales » ou « sciences mathématiques » ;");
        con.setConditionsAr("");
        con.setArmee(sante);
        con.setImage("06ea6c87-a53d-4618-96ba-546b401b6db3.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
        } catch (ParseException e) {
            logger.warn("parse exception "+e);
        }
        concours.addConcours(con);
        logger.info("concours erssm médecins créé avec succées");


        //ERSSM dentistes

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des élèves Officiers de l'Ecole Royale du Service de Santé  Militaire (Chirurgiens-dentistes)");
        con.setTitreAr(" المدرسة الملكية للخدمات الصحية العسكرية (جراحة الأسنان)");
        con.setCategorie(officier);
        con.setEcoleFormation(erssm);
        con.setEcoleTest(erssm);
        con.setDisponible(true);
        con.setConditions("⦁	Etre de nationalité marocaine ;\r\n" +
                "⦁	Etre célibataire ;\r\n" +
                "⦁	Ne pas avoir encouru de condamnation judiciaire ;\r\n" +
                "⦁	Etre apte physiquement ;\r\n" +
                "⦁	Avoir une taille minimum de 1.65m pour les garçons et 1.60m pour les filles;\r\n" +
                "⦁	Etre retenu par la commission de présélection.\r\n" +
                "⦁	Etre âgé de 18 au moins et 22ans au plus, à la fin de l’année en cours;\r\n" +


                "⦁	Etre bachelier de l’année 2018, séries « sciences expérimentales » ou « sciences mathématiques » ;");
        con.setConditionsAr("");
        con.setArmee(sante);
        con.setImage("6c4f3918-3d24-4e50-9178-5fb42e6ce141.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
        } catch (ParseException e) {
            logger.warn("parse exception "+e);
        }
        concours.addConcours(con);
        logger.info("concours erssm dentistes créé avec succées");


        //ERSSM d

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des élèves Officiers de l'Ecole Royale du Service de Santé  Militaire (Pharmaciens)");
        con.setTitreAr("المدرسة الملكية للخدمات الصحية العسكرية ( الصيدلة)");
        con.setCategorie(officier);
        con.setEcoleFormation(erssm);
        con.setEcoleTest(erssm);
        con.setDisponible(true);
        con.setConditions("⦁	Etre de nationalité marocaine ;\r\n" +
                "⦁	Etre célibataire ;\r\n" +
                "⦁	Ne pas avoir encouru de condamnation judiciaire ;\r\n" +
                "⦁	Etre apte physiquement ;\r\n" +
                "⦁	Avoir une taille minimum de 1.65m pour les garçons et 1.60m pour les filles;\r\n" +
                "⦁	Etre retenu par la commission de présélection.\r\n" +
                "⦁	Etre âgé de 20 au moins et 22ans au plus, à la fin de l’année en cours;\r\n" +
                "⦁	Etre titulaire du DEUG  ou DEUST ou CEUS ou inscrit en 2eme année d’études universitaires et ce, dans les filières ci-après :\r\n" +
                "-	Science de la Vie(SV) ou sciences de la terre et de l’Univers(STU) ;\r\n" +
                "-	Biologie-Chimie-Géologie(BCG) ;\r\n" +
                "-	Sciences de la vie et de la terres (SVT) ou chimie-Biologie(CB) ;\r\n" +
                "-	Biologie-Géologie(BG).");
        con.setConditionsAr("");
        con.setArmee(sante);
        con.setImage("9b7040bb-ec82-408f-a8de-963645c2b903.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
        } catch (ParseException e) {
            logger.warn("parse exception "+e);
        }
        concours.addConcours(con);
        logger.info("concours erssm pharmacie créé avec succées");


        //ERI

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des élèves Sous-Officiers de l'Armée de Terre -Spécialité Infanterie-");
        con.setTitreAr("المدرسة الملكية للمشاة");
        con.setCategorie(sofficier);
        con.setEcoleFormation(eri);
        con.setEcoleTest(eri);
        con.setDisponible(true);
        con.setConditions("⦁	Etre de nationalité marocaine ;\r\n" +
                "⦁	Etre célibataire ;\r\n" +
                "⦁	Etre âgé de 18 au moins et 23ans au plus, au 16 aout de l’année courante ;\r\n" +
                "⦁	Etre apte physiquement ;\r\n" +
                "⦁	N’avoir aucun  antécédent judiciaire ;\r\n" +
                "⦁	Etre bachelier de l’année courante ou précédente ;\r\n" +

                "⦁	Avoir une taille minimum de 1.70m;\r\n" +
                "⦁	Etre retenu par la commission de présélection;\r\n" +
                "⦁	Sexe masculin.");
        con.setConditionsAr("");
        con.setArmee(terre);
        con.setImage("13f91e95-183f-41ea-a7b8-702471b43d2e.gif");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
        } catch (ParseException e) {
            logger.warn("parse exception "+e);
        }
        concours.addConcours(con);
        logger.info("concours ERI créé avec succées");

        //CIB

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des élèves Sous-Officiers de l'Armée de Terre -Spécialité Arme Blindée-");
        con.setTitreAr("مركز التدريب للمدرعات");
        con.setCategorie(sofficier);
        con.setEcoleFormation(erb);
        con.setEcoleTest(erb);
        con.setDisponible(true);
        con.setConditions("⦁	Etre de nationalité marocaine ;\r\n" +
                "⦁	Etre célibataire ;\r\n" +
                "⦁	Etre âgé de 18 au moins et 23ans au plus, au 16 aout de l’année courante ;\r\n" +
                "⦁	Etre apte physiquement ;\r\n" +
                "⦁	N’avoir aucun  antécédent judiciaire ;\r\n" +
                "⦁	Etre bachelier de l’année courante ou précédente ;\r\n" +

                "⦁	Avoir une taille minimum de 1.65m;\r\n" +
                "⦁	Etre retenu par la commission de présélection;\r\n" +
                "⦁	Sexe masculin.");
        con.setConditionsAr("");
        con.setArmee(terre);
        con.setImage("59e62612-9925-4027-ad84-878ef332bed5.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
        } catch (ParseException e) {
            logger.warn("parse exception "+e);
        }
        concours.addConcours(con);
        logger.info("concours CIB créé avec succées");

        //CIA

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des élèves Sous-Officiers de l'Armée de Terre -Spécialité Artillerie-");
        con.setTitreAr("مركز تدريب المدفعية");
        con.setCategorie(sofficier);
        con.setEcoleFormation(erart);
        con.setEcoleTest(erart);
        con.setDisponible(true);
        con.setConditions("⦁	Etre de nationalité marocaine ;\r\n" +
                "⦁	Etre célibataire ;\r\n" +
                "⦁	Etre âgé de 18 au moins et 23ans au plus, au 16 aout de l’année courante ;\r\n" +
                "⦁	Etre apte physiquement ;\r\n" +
                "⦁	N’avoir aucun  antécédent judiciaire ;\r\n" +
                "⦁	Etre bachelier de l’année courante ou précédente ;\r\n" +

                "⦁	Avoir une taille minimum de 1.65m;\r\n" +
                "⦁	Etre retenu par la commission de présélection;\r\n" +
                "⦁	Sexe masculin.");
        con.setConditionsAr("");
        con.setArmee(terre);
        con.setImage("24edabd0-2286-44b0-9428-15942b2a3533.png");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
        } catch (ParseException e) {
            logger.warn("parse exception "+e);
        }
        concours.addConcours(con);
        logger.info("concours CIArt créé avec succées");

        //ERC

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des élèves Sous-Officiers de l'Armée de Terre -Spécialité Cavalerie-");
        con.setTitreAr("المدرسة الملكية للخيالة");
        con.setCategorie(sofficier);
        con.setEcoleFormation(erc);
        con.setEcoleTest(erc);
        con.setDisponible(true);
        con.setConditions("⦁	Etre de nationalité marocaine ;\r\n" +
                "⦁	Etre célibataire ;\r\n" +
                "⦁	Etre âgé de 18 au moins et 23ans au plus, au 16 aout de l’année courante ;\r\n" +
                "⦁	Etre apte physiquement ;\r\n" +
                "⦁	N’avoir aucun  antécédent judiciaire ;\r\n" +
                "⦁	Etre bachelier de l’année courante ou précédente ;\r\n" +

                "⦁	Avoir une taille minimum de 1.65m;\r\n" +
                "⦁	Etre retenu par la commission de présélection;\r\n" +
                "⦁	Sexe masculin.");
        con.setConditionsAr("");
        con.setArmee(terre);
        con.setImage("28f6c8af-e2e9-4cbd-b3e3-57e6aa5725a7.gif");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
        } catch (ParseException e) {
            logger.warn("parse exception "+e);
        }
        concours.addConcours(con);
        logger.info("concours ERC créé avec succées");

        //CIFT

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des élèves Sous-Officiers de l'Armée de Terre -Spécialité Transport-");
        con.setTitreAr("");
        con.setCategorie(sofficier);
        con.setEcoleFormation(cift);
        con.setEcoleTest(cift);
        con.setDisponible(true);
        con.setConditions("⦁	Etre de nationalité marocaine ;\r\n" +
                "⦁	Etre célibataire ;\r\n" +
                "⦁	Etre âgé de 18 au moins et 23ans au plus, au 16 aout de l’année courante ;\r\n" +
                "⦁	Etre apte physiquement ;\r\n" +
                "⦁	N’avoir aucun  antécédent judiciaire ;\r\n" +
                "⦁	Etre bachelier de l’année courante ou précédente ;\r\n" +

                "⦁	Avoir une taille minimum de 1.65m;\r\n" +
                "⦁	Etre retenu par la commission de présélection;\r\n"+
                "⦁	Sexe masculin.");
        con.setConditionsAr("");
        con.setArmee(terre);
        con.setImage("74a4a782-d974-45da-b873-3b2f18995f58.gif");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
        } catch (ParseException e) {
            logger.warn("parse exception "+e);
        }
        concours.addConcours(con);
        logger.info("concours CIFT créé avec succées");

        //CIG

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des élèves Sous-Officiers de l'Armée de Terre -Spécialité Génie-");
        con.setTitreAr("مركز تدريب الهندسة العسكرية");
        con.setCategorie(sofficier);
        con.setEcoleFormation(cig);
        con.setEcoleTest(cig);
        con.setDisponible(true);
        con.setConditions("⦁	Etre de nationalité marocaine ;\r\n" +
                "⦁	Etre célibataire ;\r\n" +
                "⦁	Etre âgé de 18 au moins et 23ans au plus, au 16 aout de l’année courante ;\r\n" +
                "⦁	Etre apte physiquement ;\r\n" +
                "⦁	N’avoir aucun  antécédent judiciaire ;\r\n" +
                "⦁	Etre bachelier de l’année courante ou précédente ;\r\n" +

                "⦁	Avoir une taille minimum de 1.65m;\r\n" +
                "⦁	Etre retenu par la commission de présélection;\r\n"+
                "⦁	Sexe masculin.");
        con.setConditionsAr("");
        con.setArmee(terre);
        con.setImage("15961afa-e171-4a08-badd-fc5bd8c120f1.gif");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
        } catch (ParseException e) {
            logger.warn("parse exception "+e);
        }
        concours.addConcours(con);
        logger.info("concours CIG créé avec succées");

        //CIT

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des élèves Sous-Officiers de l'Armée de Terre -Spécialité Transmissions-");
        con.setTitreAr("مركز تدريب سلاح الإشارة");
        con.setCategorie(sofficier);
        con.setEcoleFormation(cit);
        con.setEcoleTest(cit);
        con.setDisponible(true);
        con.setConditions("⦁	Etre de nationalité marocaine ;\r\n" +
                "⦁	Etre célibataire ;\r\n" +
                "⦁	Etre âgé de 18 au moins et 23ans au plus, au 16 aout de l’année courante ;\r\n" +
                "⦁	Etre apte physiquement ;\r\n" +
                "⦁	N’avoir aucun  antécédent judiciaire ;\r\n" +
                "⦁	Etre bachelier de l’année courante ou précédente ;\r\n" +

                "⦁	Avoir une taille minimum de 1.65m pour les garçons et 1.60m pour les filles des autres spécialités;\r\n" +
                "⦁	Etre retenu par la commission de présélection.");
        con.setConditionsAr("");
        con.setArmee(terre);
        con.setImage("423f15e3-667a-4a5f-9f3e-cc43ee92fc49.gif");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
        } catch (ParseException e) {
            logger.warn("parse exception "+e);
        }
        concours.addConcours(con);
        logger.info("concours CIT créé avec succées");

        //ERM

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des élèves Sous-Officiers de l'Armée de Terre -Spécialité Matériel-");
        con.setTitreAr("المدرسة الملكية للعتاد");
        con.setCategorie(sofficier);
        con.setEcoleFormation(erm);
        con.setEcoleTest(erm);
        con.setDisponible(true);
        con.setConditions("⦁	Etre de nationalité marocaine ;\r\n" +
                "⦁	Etre célibataire ;\r\n" +
                "⦁	Etre âgé de 18 au moins et 23ans au plus, au 16 aout de l’année courante ;\r\n" +
                "⦁	Etre apte physiquement ;\r\n" +
                "⦁	N’avoir aucun  antécédent judiciaire ;\r\n" +
                "⦁	Etre bachelier de l’année courante ou précédente ;\r\n" +

                "⦁	Avoir une taille minimum de 1.65m;\r\n" +
                "⦁	Etre retenu par la commission de présélection;\r\n" +
                "⦁	Sexe masculin.");
        con.setConditionsAr("");
        con.setArmee(terre);
        con.setImage("61c875cb-1fb3-4262-9133-b781e991a16b.gif");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
        } catch (ParseException e) {
            logger.warn("parse exception "+e);
        }
        concours.addConcours(con);
        logger.info("concours ERM créé avec succées");

        //CII

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des élèves Sous-Officiers de l'Armée de Terre -Spécialité Intendance-");
        con.setTitreAr("مركز التدريب التموين");
        con.setCategorie(sofficier);
        con.setEcoleFormation(cii);
        con.setEcoleTest(cii);
        con.setDisponible(true);
        con.setConditions("⦁	Etre de nationalité marocaine ;\r\n" +
                "⦁	Etre célibataire ;\r\n" +
                "⦁	Etre âgé de 18 au moins et 23ans au plus, au 16 aout de l’année courante ;\r\n" +
                "⦁	Etre apte physiquement ;\r\n" +
                "⦁	N’avoir aucun  antécédent judiciaire ;\r\n" +
                "⦁	Etre bachelier de l’année courante ou précédente ;\r\n" +

                "⦁	Avoir une taille minimum de 1.65m pour les garçons et 1.60m pour les filles des autres spécialités;\r\n" +
                "⦁	Etre retenu par la commission de présélection.");
        con.setConditionsAr("");
        con.setArmee(terre);
        con.setImage("58b5e170-91cc-49ac-9539-6b9acddc0eba.gif");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
        } catch (ParseException e) {
            logger.warn("parse exception "+e);
        }
        concours.addConcours(con);
        logger.info("concours CII créé avec succées");

        //CISS

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des élèves Sous-Officiers de l'Armée de Terre -Service Social-");
        con.setTitreAr("مركز تعليم الخدمات الاجتماعية");
        con.setCategorie(sofficier);
        con.setEcoleFormation(ciss);
        con.setEcoleTest(ciss);
        con.setDisponible(true);
        con.setConditions("⦁	Etre de nationalité marocaine ;\r\n" +
                "⦁	Etre célibataire ;\r\n" +
                "⦁	Etre âgé de 18 au moins et 23ans au plus, au 16 aout de l’année courante ;\r\n" +
                "⦁	Etre apte physiquement ;\r\n" +
                "⦁	N’avoir aucun  antécédent judiciaire ;\r\n" +
                "⦁	Etre bachelier de l’année courante ou précédente ;\r\n" +

                "⦁	Avoir une taille minimum de 1.60m;\r\n" +
                "⦁	Etre retenu par la commission de présélection;\r\n" +
                "⦁	Sexe féminin.");
        con.setConditionsAr("");
        con.setArmee(terre);
        con.setImage("189e891c-46e6-473b-8a70-b428d16293b8.gif");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
        } catch (ParseException e) {
            logger.warn("parse exception "+e);
        }
        concours.addConcours(con);
        logger.info("concours CISS créé avec succées");

        //PARA

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des élèves Sous-Officiers de l'Armée de Terre -Spécialité Toupes Aéroportées-");
        con.setTitreAr("مركز تدريب المشات المظليين");
        con.setCategorie(sofficier);
        con.setEcoleFormation(cita);
        con.setEcoleTest(cita);
        con.setDisponible(true);
        con.setConditions("⦁	Etre de nationalité marocaine ;\r\n" +
                "⦁	Etre célibataire ;\r\n" +
                "⦁	Etre âgé de 18 au moins et 23ans au plus, au 16 aout de l’année courante ;\r\n" +
                "⦁	Etre apte physiquement ;\r\n" +
                "⦁	N’avoir aucun  antécédent judiciaire ;\r\n" +
                "⦁	Etre bachelier de l’année courante ou précédente ;\r\n" +

                "⦁	Avoir une taille minimum de 1.70m;\r\n" +
                "⦁	Etre retenu par la commission de présélection;\r\n" +
                "⦁	Sexe masculin.");
        con.setConditionsAr("");
        con.setArmee(terre);
        con.setImage("035e2dae-1843-4e09-8d8f-d3e29dd37c4b.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
        } catch (ParseException e) {
            logger.warn("parse exception "+e);
        }
        concours.addConcours(con);
        logger.info("concours PARA créé avec succées");

        //ERPPLM

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des élèves Sous-Officiers de l'Armée de Terre -Spécialité Santé militaire-");
        con.setTitreAr("");
        con.setCategorie(sofficier);
        con.setEcoleFormation(erpplm);
        con.setEcoleTest(erpplm);
        con.setDisponible(true);
        con.setConditions("⦁	Etre de nationalité marocaine ;\r\n" +
                "⦁	Etre célibataire ;\r\n" +
                "⦁	Etre âgé de 18 au moins et 23ans au plus, au 16 aout de l’année courante ;\r\n" +
                "⦁	Etre apte physiquement ;\r\n" +
                "⦁	N’avoir aucun  antécédent judiciaire ;\r\n" +
                "⦁	Etre bachelier de l’année courante ou précédente ;\r\n" +

                "⦁	Avoir une taille minimum de 1.65m pour les garçons et 1.60m pour les filles des autres spécialités;\r\n" +
                "⦁	Etre retenu par la commission de présélection.");
        con.setConditionsAr("");
        con.setArmee(sante);
        con.setImage("68a97a4f-748c-4e20-a0ed-c79b9eb3a1c4.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
        } catch (ParseException e) {
            logger.warn("parse exception "+e);
        }
        concours.addConcours(con);
        logger.info("concours ERPPLM créé avec succées");


        //Sport

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des élèves Sous-Officiers de l'Armée de Terre -spécialité sport militaire-");
        con.setTitreAr("المركز الرياضي للقوات المسلحة الملكية");
        con.setCategorie(sofficier);
        con.setEcoleFormation(csfar);
        con.setEcoleTest(csfar);
        con.setDisponible(true);
        con.setConditions("⦁	Etre de nationalité marocaine ;\r\n" +
                "⦁	Etre célibataire ;\r\n" +
                "⦁	Etre âgé de 18 au moins et 23ans au plus, au 16 aout de l’année courante ;\r\n" +
                "⦁	Etre apte physiquement ;\r\n" +
                "⦁	N’avoir aucun  antécédent judiciaire ;\r\n" +
                "⦁	Etre bachelier de l’année courante ou précédente ;\r\n" +

                "⦁	Avoir une taille minimum de 1.65m;\r\n" +
                "⦁	Etre retenu par la commission de présélection.");
        con.setConditionsAr("");
        con.setArmee(terre);
        con.setImage("37cfca0f-a2a6-4d3e-bdfc-f9bedd7a464f.gif");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
        } catch (ParseException e) {
            logger.warn("parse exception "+e);
        }
        concours.addConcours(con);
        logger.info("concours SPORT créé avec succées");

        //EFSNO

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des élèves Sous-Officiers des Forces Royale Air -Personnel non navigant-");
        con.setTitreAr("");
        con.setCategorie(sofficier);
        con.setEcoleFormation(cfsno);
        con.setEcoleTest(cfsno);
        con.setDisponible(true);
        con.setConditions("⦁	Etre de nationalité marocaine ;\r\n" +
                "⦁	Etre célibataire ;\r\n" +
                "⦁	Etre âgé de 18 au moins et 23ans au plus, au 16 aout de l’année courante ;\r\n" +
                "⦁	Etre apte physiquement ;\r\n" +
                "⦁	N’avoir aucun  antécédent judiciaire ;\r\n" +
                "⦁	Etre titulaire d'un baccalauréat scientifique de l’année courante ou précédente ;\r\n" +

                "⦁	Avoir une taille minimum de 1.65m pour les garçons et 1.60m pour les filles.");
        con.setConditionsAr("");
        con.setArmee(aviation);
        con.setImage("a9c854cd-3b6a-46c8-a04e-13fa677ec1b3.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
        } catch (ParseException e) {
            logger.warn("parse exception "+e);
        }
        concours.addConcours(con);
        logger.info("concours personnel non navigant créé avec succées");

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des élèves Sous-Officiers des Forces Royale Air -Fusiliers commandos de l'air-");
        con.setTitreAr("");
        con.setCategorie(sofficier);
        con.setEcoleFormation(cfsno);
        con.setEcoleTest(cfsno);
        con.setDisponible(true);
        con.setConditions("⦁	Etre de nationalité marocaine ;\r\n" +
                "⦁	Etre célibataire ;\r\n" +
                "⦁	Etre de sexe masculin ;\r\n" +
                "⦁	Etre âgé de 18 au moins et 23ans au plus, au 16 aout de l’année courante ;\r\n" +
                "⦁	Etre apte physiquement ;\r\n" +
                "⦁	N’avoir aucun  antécédent judiciaire ;\r\n" +
                "⦁	Etre titulaire d'un baccalauréat scientifique de l’année courante ou précédente ;\r\n" +

                "⦁	Avoir une taille minimum de 1.70m.");
        con.setConditionsAr("");
        con.setArmee(aviation);
        con.setImage("423f15e3-667a-4a5f-9f3e-cc43ee92fc49.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
        } catch (ParseException e) {
            logger.warn("parse exception "+e);
        }
        concours.addConcours(con);
        logger.info("concours fusiliers commandos de l'air créé avec succées");

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des élèves Sous-Officiers des Forces Royale Air -Techniciens spécialisés lauréats des ISTA-");
        con.setTitreAr("");
        con.setCategorie(sofficier);
        con.setEcoleFormation(cfsno);
        con.setEcoleTest(cfsno);
        con.setDisponible(true);
        con.setConditions("⦁	Etre de nationalité marocaine ;\r\n" +
                "⦁	Etre célibataire ;\r\n" +
                "⦁	Etre de sexe masculin ;\r\n" +
                "⦁	Etre âgé de 25 ans maximum au 16 aout de l'année courante;\r\n" +
                "⦁	Etre apte physiquement ;\r\n" +
                "⦁	N’avoir aucun  antécédent judiciaire ;\r\n" +
                "⦁	Avoir une taille minimum de 1.65m pour les garçons et 1.60m pour les filles ;\r\n" +

                "⦁	Etre titulaire du diplome 'technicien spécialisé' des ISTA de l'OFPPT, dans les filiéres ci-après:\r\n"+
                "⦁	Techniques de secrétariat de Direction;\r\n" +
                "⦁	Gestion Hôtelière : Option Restauration et Hébergement ;\r\n" +
                "- Techniques de développement informatique ;\r\n" +
                "- Electricité Bâtiment ;\r\n" +
                "- Conducteur de travaux : Travaux public ;\r\n" +
                "- Responsable d’exploitation logistique ;\r\n" +
                "- Electromécanique des systèmes automatisés ;\r\n" +
                "- Bureau d’études : Option Automobile ;\r\n" +
                "- Génie Climatique.");
        con.setConditionsAr("");
        con.setArmee(aviation);
        con.setImage("47e360bf-c5f6-4b63-b537-af32133dce3b.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
        } catch (ParseException e) {
            logger.warn("parse exception "+e);
        }
        concours.addConcours(con);
        logger.info("concours technicien FRA créé avec succées");

        //CIMR

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des élèves Sous-Officiers  de la Marine Royale -Personnel navigant-");
        con.setTitreAr("");
        con.setCategorie(sofficier);
        con.setEcoleFormation(cimr);
        con.setEcoleTest(cimr);
        con.setDisponible(true);
        con.setConditions("⦁	Etre de nationalité marocaine ;\r\n" +
                "⦁	Etre célibataire ;\r\n" +
                "⦁	Etre âgé de 18 au moins et 23ans au plus, au 16 aout de l’année courante ;\r\n" +
                "⦁	Etre apte physiquement ;\r\n" +
                "⦁	N’avoir aucun  antécédent judiciaire ;\r\n" +
                "⦁	Etre titulaire d'un baccalauréat scientifique de l’année courante ou précédente ;\r\n" +

                "⦁	Avoir une taille minimum de 1.65m pour les garçons et 1.60m pour les filles.");
        con.setConditionsAr("");
        con.setArmee(marine);
        con.setImage("ce180b2b-2c9a-4832-bfe3-5f2c9b4214ca.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
        } catch (ParseException e) {
            logger.warn("parse exception "+e);
        }
        concours.addConcours(con);
        logger.info("concours personnel navigant marine créé avec succées");

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des élèves Sous-Officiers  de la Marine Royale -Fusiliers Marins-");
        con.setTitreAr("");
        con.setCategorie(sofficier);
        con.setEcoleFormation(cimr);
        con.setEcoleTest(cimr);
        con.setDisponible(true);
        con.setConditions("⦁	Etre de nationalité marocaine ;\r\n" +
                "⦁	Etre célibataire ;\r\n" +
                "⦁	Etre de sexe masculin ;\r\n" +
                "⦁	Etre âgé de 18 au moins et 23ans au plus, au 16 aout de l’année courante ;\r\n" +
                "⦁	Etre apte physiquement ;\r\n" +
                "⦁	N’avoir aucun  antécédent judiciaire ;\r\n" +
                "⦁	Etre titulaire d'un baccalauréat scientifique de l’année courante ou précédente ;\r\n" +

                "⦁	Avoir une taille minimum de 1.70m.");
        con.setConditionsAr("");
        con.setArmee(marine);
        con.setImage("b1d4a537-b04b-4d1f-b858-d11559278da0.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
        } catch (ParseException e) {
            logger.warn("parse exception "+e);
        }
        concours.addConcours(con);
        logger.info("concours fusiliers marins");

        con = new Concours();
        con.setTitre("Concours d'admission au cycle des élèves Sous-Officiers  de la Marine Royale -Techniciens spécialisés lauréats des ISTA-");
        con.setTitreAr("");
        con.setCategorie(sofficier);
        con.setEcoleFormation(cimr);
        con.setEcoleTest(cimr);
        con.setDisponible(true);
        con.setConditions("⦁	Etre de nationalité marocaine ;\r\n" +
                "⦁	Etre célibataire ;\r\n" +
                "⦁	Etre âgé de 25 ans maximum au 16 aout de l'année courante;\r\n" +
                "⦁	Etre apte physiquement ;\r\n" +
                "⦁	N’avoir aucun  antécédent judiciaire ;\r\n" +
                "⦁	Etre apte au service à la mer;\r\n"+
                "⦁	Avoir une taille minimum de 1.65m pour les garçons et 1.60m pour les filles ;\r\n" +
                "⦁	Etre titulaire du diplome 'technicien spécialisé' des ISTA de l'OFPPT, dans les filiéres ci-après:\r\n"+
                "- Réseaux Informatique ;\r\n" +
                "- Développement Informatique ;\r\n" +
                "- Génie Climatique ;\r\n" +
                "- Hydraulique ;\r\n" +
                "- Construction métallique ;\r\n" +
                "- Automatisation et Instrumentation Industrielle.\r\n");
        con.setConditionsAr("");
        con.setArmee(marine);
        con.setImage("25309bbf-4903-4db3-b65f-9a78ee5aac62.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
        } catch (ParseException e) {
            logger.warn("parse exception "+e);
        }
        concours.addConcours(con);
        logger.info("concours technicien MARINE créé avec succées");


        //1CRFI

        con = new Concours();
        con.setTitre("1°Centre de Recrutement et de Formation Interarmes à El-hajeb");
        con.setTitreAr("");
        con.setCategorie(militaire);
        con.setEcoleFormation(crfi1);
        con.setEcoleTest(crfi1);
        con.setDisponible(true);
        con.setConditions("⦁	Etre de nationalité marocaine ;\r\n" +
                "⦁	Etre âgé de 18 au moins et 22ans au plus, à la fin de l’année en cours ;\r\n" +
                "⦁	 Avoir au minimum le niveau de la 9eme année fondamentale révolue sans être bachelier ;\r\n" +
                "⦁	Etre apte physiquement et psychiquement ;\r\n" +
                "⦁	Etre célibataire et le demeurer durant la période de la formation ;\r\n" +
                "⦁	Avoir au minimum une taille de 1.70m ;\r\n" +
                "⦁	N’avoir aucun antécédent judiciaire ;\r\n" +
                "⦁	Etre titulaire de la carte nationale d’identité (CNI) ;\r\n" +
                "⦁	Etre retenu par la commission de présélection.");
        con.setConditionsAr("– الجنسية مغربية ؛\r\n" +
                "– أن يكون ذكرا ؛\r\n" +
                "– السن ما بين 18 سنة على الأقل و22 سنة على الأكثر في تاريخ 31-12-2016؛\r\n" +
                "– المستوى الدراسي السنة الثالثة من التعليم الثانوي الإعدادي كاملة على الاقل وغير حاصل على شهادة الباكالوريا؛\r\n" +
                "– التمتع بصحة جسمانية ونفسية جيدة؛\r\n" +
                "– الحالة العائلية عازب قبل وأثناء فترة التكوين؛\r\n" +
                "– القامة  1.70m على الأقل؛\r\n" +
                "– بدون سوابق عدلية؛\r\n" +
                "– حامل للبطاقة الوطنية للتعريف؛\r\n" +
                "– أن يتم قبوله من طرف لجنة الانتقاء .");
        con.setArmee(terre);
        con.setImage("285c74a2-4d74-48df-a476-b41cdeddbf29.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
        } catch (ParseException e) {
            logger.warn("parse exception "+e);
        }
        concours.addConcours(con);
        logger.info("concours 1°CRFI créé avec succées");

        //2°CRFI

        con = new Concours();
        con.setTitre("2°Centre de Recrutement et de Formation Interarmes à Kasbat Tadla");
        con.setTitreAr("");
        con.setCategorie(militaire);
        con.setEcoleFormation(crfi1);
        con.setEcoleTest(crfi1);
        con.setDisponible(true);
        con.setConditions("⦁	Etre de nationalité marocaine ;\r\n" +
                "⦁	Etre âgé de 18 au moins et 22ans au plus, à la fin de l’année en cours ;\r\n" +
                "⦁	 Avoir au minimum le niveau de la 9eme année fondamentale révolue sans être bachelier ;\r\n" +
                "⦁	Etre apte physiquement et psychiquement ;\r\n" +
                "⦁	Etre célibataire et le demeurer durant la période de la formation ;\r\n" +
                "⦁	Avoir au minimum une taille de 1.70m ;\r\n" +
                "⦁	N’avoir aucun antécédent judiciaire ;\r\n" +
                "⦁	Etre titulaire de la carte nationale d’identité (CNI) ;\r\n" +
                "⦁	Etre retenu par la commission de présélection.");
        con.setConditionsAr("– الجنسية مغربية ؛\r\n" +
                "– أن يكون ذكرا ؛\r\n" +
                "– السن ما بين 18 سنة على الأقل و22 سنة على الأكثر في تاريخ 31-12-2016؛\r\n" +
                "– المستوى الدراسي السنة الثالثة من التعليم الثانوي الإعدادي كاملة على الاقل وغير حاصل على شهادة الباكالوريا؛\r\n" +
                "– التمتع بصحة جسمانية ونفسية جيدة؛\r\n" +
                "– الحالة العائلية عازب قبل وأثناء فترة التكوين؛\r\n" +
                "– القامة  1.70m على الأقل؛\r\n" +
                "– بدون سوابق عدلية؛\r\n" +
                "– حامل للبطاقة الوطنية للتعريف؛\r\n" +
                "– أن يتم قبوله من طرف لجنة الانتقاء .");
        con.setArmee(terre);
        con.setImage("e7e4f0ee-5d15-4ef8-81a5-ef405fd343d1.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
        } catch (ParseException e) {
            logger.warn("parse exception "+e);
        }
        concours.addConcours(con);
        logger.info("concours 2°CRFI créé avec succées");

        //Lycee

        con = new Concours();
        con.setTitre("Concours d'admission en première année du Baccalauréat du Lycée de l'Academie Royale Militaire");
        con.setTitreAr("");
        con.setCategorie(lycee);
        con.setEcoleFormation(lyceearm);
        con.setEcoleTest(lyceearm);
        con.setDisponible(true);
        con.setConditions("-	Etre de nationalité marocaine ;\r\n" +
                "-	Etre de sexe masculin ;\r\n" +
                "-	Avoir entre 15 et 17 ans au premier septembre de l’année en cours ;\r\n" +
                "-	Etre apte physiquement ;\r\n" +
                "-	Etre actuellement en tronc commun sciences de l’enseignement secondaire qualifiant ;\r\n" +
                "-	Avoir de bonnes notes scolaires ;\r\n" +
                "-	Avoir l’anglais comme 2eme langue étrangère ; \r\n" +
                "-	Etre retenu par la commission de présélection.\r\n");
        con.setConditionsAr("– الجنسية مغربية ؛\r\n" +
                "– أن يكون ذكرا ؛\r\n" +
                "– السن ما بين 18 سنة على الأقل و22 سنة على الأكثر في تاريخ 31-12-2016؛\r\n" +
                "– المستوى الدراسي السنة الثالثة من التعليم الثانوي الإعدادي كاملة على الاقل وغير حاصل على شهادة الباكالوريا؛\r\n" +
                "– التمتع بصحة جسمانية ونفسية جيدة؛\r\n" +
                "– الحالة العائلية عازب قبل وأثناء فترة التكوين؛\r\n" +
                "– القامة  1.70m على الأقل؛\r\n" +
                "– بدون سوابق عدلية؛\r\n" +
                "– حامل للبطاقة الوطنية للتعريف؛\r\n" +
                "– أن يتم قبوله من طرف لجنة الانتقاء .");
        con.setArmee(terre);
        con.setImage("310d8a03-6a0a-4592-a1ac-ba062f734af9.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
        } catch (ParseException e) {
            logger.warn("parse exception "+e);
        }
        concours.addConcours(con);
        logger.info("concours Lycee ARM créé avec succées");

        //college royale

        con = new Concours();
        con.setTitre("Concours d'admission en prmière année du Baccalauréat sciences mathématiques du Collège Royal Préparatoire aux Techniques Aéronautiques");
        con.setTitreAr("");
        con.setCategorie(lycee);
        con.setEcoleFormation(crpta);
        con.setEcoleTest(crpta);
        con.setDisponible(true);
        con.setConditions("-	Etre de nationalité marocaine ;\r\n" +
                "-	Etre agé de 16ans au minimum et 18 ans au maximum au 31 decembre de l'année courante ;\r\n" +
                "-	Etre apte physiquement ;\r\n" +
                "-	Etre actuellement en tronc commun,orienté en premiére année du baccalauréat Sciences Mathématiques;\r\n" +
                "-	Etre célibataire ;\r\n" +
                "-	N'avoir pas avoir redoublé le Tronc Commun.\r\n");
        con.setConditionsAr("– الجنسية مغربية ؛\r\n" +
                "– أن يكون ذكرا ؛\r\n" +
                "– السن ما بين 18 سنة على الأقل و22 سنة على الأكثر في تاريخ 31-12-2016؛\r\n" +
                "– المستوى الدراسي السنة الثالثة من التعليم الثانوي الإعدادي كاملة على الاقل وغير حاصل على شهادة الباكالوريا؛\r\n" +
                "– التمتع بصحة جسمانية ونفسية جيدة؛\r\n" +
                "– الحالة العائلية عازب قبل وأثناء فترة التكوين؛\r\n" +
                "– القامة  1.70m على الأقل؛\r\n" +
                "– بدون سوابق عدلية؛\r\n" +
                "– حامل للبطاقة الوطنية للتعريف؛\r\n" +
                "– أن يتم قبوله من طرف لجنة الانتقاء .");
        con.setArmee(terre);
        con.setImage("f299fff1-e87f-43df-a248-9b5bb2f54466.jpg");
        try {
            con.setDateConcours(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
            con.setDebut(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_DEBUT));
            con.setFin(new SimpleDateFormat("dd/MM/yyyy").parse(DATE_FIN));
        } catch (ParseException e) {
            logger.warn("parse exception "+e);
        }
        concours.addConcours(con);
        logger.info("concours Lycee ERA créé avec succées");

        users.reSave(user2);
        users.reSave(user3);
        users.reSave(user4);
        users.reSave(user5);
    }
}

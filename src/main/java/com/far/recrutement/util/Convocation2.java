package com.far.recrutement.util;

import com.far.recrutement.dao.InscriptionRepository;
import com.far.recrutement.models.Inscription;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


@Service
public class Convocation2 {

    @Autowired
    InscriptionRepository inscriptionRepository;

    Logger logger = LoggerFactory.getLogger(Convocation2.class);
    String html = "ERROR 500";

    public Convocation2() {
        try {
            html = new String(Files.readAllBytes(Paths.get("files/format/convocation-2.html")), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void genererConvocation(Inscription inscription, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=Convocation");

        String out = html;
        out = out.replace("{ecole}", inscription.getConcours().getEcoleTest().getNomEcole().toUpperCase());
        out = out.replace("{numConvocation}", inscription.getNumeroConvocation().toString());
        out = out.replace("{nomprenom}", inscription.getCandidat().getNom() + " " + inscription.getCandidat().getPrenom());
        out = out.replace("{cin}", inscription.getCandidat().getCin());
        out = out.replace("{addresse}", inscription.getCandidat().getAdresse());
        out = out.replace("{telephone}", inscription.getCandidat().getTelephone());
        DateFormat df = new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH);
        out = out.replace("{dateheure}", df.format(inscription.getDateConcours()) + " à " + inscription.getHeure());
        out = out.replace("{addresseEcole}", inscription.getConcours().getEcoleTest().getAddresse());
        out = out.replace("{concours}", inscription.getConcours().getTitre());
        out = out.replace("{ville}", inscription.getConcours().getEcoleTest().getVille());
        Date date = new Date();
        if (date.after(inscription.getDateConcours())) {
            date = inscription.getDateConcours();
        }
        out = out.replace("{date}", df.format(date));
        String directeur1 = inscription.getConcours().getEcoleTest().getDirecteur().split(", ")[0];
        String directeur2 = inscription.getConcours().getEcoleTest().getDirecteur().split(", ")[1];
        out = out.replace("{directeur1}", directeur1);
        out = out.replace("{directeur2}", directeur2);
        String doc1 = "Une copie intégrale extraite du registre de l’état civil ;";
        String doc2 = "L'attestation originale du Baccalauréat ;";
        String doc3 = ";";
        if (inscription.getConcours().getCategorie().getCategorieID() == 4L) {
            doc1 = "Attestation de réussite au Tronc Commun ;";
            doc2 = "Une Attestation de scolarité avec photo mentionnant la date de naissance et le Code MASAR du candidat et précisant l’anglais comme deuxième langue étrangère suivie.";
            doc3 = "(Eventuellement) ;";
        }
        out = out.replace("{doc1}", doc1);
        out = out.replace("{doc2}", doc2);
        out = out.replace("{eventuellement}", doc3);
        out = out.replace("{insigne}", "http://localhost" + inscription.getConcours().getEcoleTest().getInsigne());
        out = out.replace("{code}", "http://localhost/barcode/" + inscription.getInscriptionID());


        ConverterProperties converterProperties = new ConverterProperties();
        HtmlConverter.convertToPdf(out,
                response.getOutputStream());

        inscription.setDownloaded(true);
        inscriptionRepository.save(inscription);
    }

}

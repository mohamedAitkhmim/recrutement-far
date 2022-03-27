package com.far.recrutement.restcontroller;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import com.far.recrutement.util.Convocation2;
import com.far.recrutement.util.Convocation3;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code39Writer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.far.recrutement.metier.ICandidatMetier;
import com.far.recrutement.metier.IInscriptionMetier;
import com.far.recrutement.models.Candidat;
import com.far.recrutement.models.Inscription;
import com.far.recrutement.util.InscriptionValidation;

import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class PdfRestController {

    @Autowired
    IInscriptionMetier inscriptionMetier;
    @Autowired
    ICandidatMetier candidatMetier;
    @Autowired
    Convocation2 convocation;
    @Autowired
    Convocation3 convocation3;
    @Autowired
    InscriptionValidation inscriptionValidation;

    Logger logger = LoggerFactory.getLogger(PdfRestController.class);

    static final String REFERER = "Referer";
    static final String MESSAGE = "message";


    @RequestMapping(value = "/user/convocation/{id}")
    public void convocation(HttpServletResponse response, Model model, @PathVariable(value = "id") final Long id) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Candidat candidat = candidatMetier.getCandidatByEmail(auth.getName());
        Inscription inscription = inscriptionMetier.getInscriptionById(id);
        if (inscription.getConcours().getConcoursID() <= 21) {
            return;
        }
        if (inscription.getStatut().getNomStatut().startsWith("Conv") & inscription.getCandidat().equals(candidat)) {
            convocation.genererConvocation(inscription, response);
        }
    }

    @RequestMapping(value = "/user/convocation3/{id}")
    public void convocation3(HttpServletResponse response, Model model, @PathVariable(value = "id") final Long id) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Candidat candidat = candidatMetier.getCandidatByEmail(auth.getName());
        Inscription inscription = inscriptionMetier.getInscriptionById(id);
        if (inscription.getConcours().getConcoursID() <= 21) {
            return;
        }
        if (inscription.getStatut().getNomStatut().startsWith("Adm") & inscription.getCandidat().equals(candidat)) {
            convocation3.genererConvocation(inscription, response);
        }
    }

    @GetMapping("/barcode/{code}")
    public void barcode(@PathVariable("code") String code, HttpServletResponse response) throws Exception {
        Code39Writer barcodeWriter = new Code39Writer();
        BitMatrix bitMatrix = barcodeWriter.encode(code, BarcodeFormat.CODE_39, 300, 150);
        BufferedImage img = MatrixToImageWriter.toBufferedImage(bitMatrix);
        response.setContentType("image/jpg");
        // Write to output stream
        ImageIO.write(img, "jpg", response.getOutputStream());
    }

}

package com.far.recrutement.util;

import com.far.recrutement.dao.InscriptionRepository;
import com.far.recrutement.models.Inscription;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;


@Service
public class Convocation {

    @Autowired
    InscriptionRepository inscriptionRepository;

    static final String SOURCE = "files/format/convocation.pdf";
    BaseFont bf;
    BaseFont bf2;

    Logger logger = LoggerFactory.getLogger(Convocation.class);

    public Convocation() {
        try {
            bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            bf2 = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

        } catch (Exception e) {
            logger.warn("constructor Convocation Exception ", e);
        }
    }

    public void genererConvocation(Inscription inscription, HttpServletResponse response) {
        String url = "";
        try {
            PdfReader reader = new PdfReader(SOURCE);
            url = UUID.randomUUID().toString().toUpperCase();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=Convocation");
            PdfStamper stamper = new PdfStamper(reader, response.getOutputStream());
            PdfContentByte over = stamper.getOverContent(1);

            writeLine15(inscription.getInscriptionID().toString(), 370, 685, over);
            int x = 0;
            if (inscription.getConcours().getEcoleTest().getNomEcole().toUpperCase().length() > 20) {
                x = (inscription.getConcours().getEcoleTest().getNomEcole().toUpperCase().length() - 20) * 3;
            }
            writeTitle(inscription.getConcours().getEcoleTest().getNomEcole().toUpperCase(), 387 - x, 755, over);
            writeLine(inscription.getCandidat().getNom() + " " + inscription.getCandidat().getPrenom(), 270, 630, over);
            writeLine(inscription.getCandidat().getCin(), 230, 602, over);
            writeLine(inscription.getCandidat().getAdresse(), 60, 574, over);
            writeLine(inscription.getCandidat().getTelephone(), 120, 547, over);

            DateFormat df = new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH);
            writeLine(df.format(inscription.getDateConcours()) + " à 5H 00 du Matin " + inscription.getConcours().getEcoleTest().getAddresse(), 60, 520, over);

            writeLine(inscription.getConcours().getTitre().replace("Concours d'admission au ", ""), 60, 464, over);

            String doc = "- Une copie intégrale extraite du registre de l’état civil ;";
            String doc2 = "- L'attestation originale du Baccalauréat ;";
            String doc3 = "";
            String doc4 = "";
            String doc5 = ";";
            if (inscription.getConcours().getCategorie().getCategorieID() == 4L) {
                doc = "- Attestation de réussite au Tronc Commun ;";
                doc2 = "- Une Attestation de scolarité avec photo mentionnant la date de naissance";
                doc3 = "et le Code MASAR du candidat et précisant l’anglais comme deuxième";
                doc4 = "langue étrangère suivie.";
                doc5 = "(Eventuellement) ;";
            }
            writeLine(doc, 105, 315, over);
            writeLine(doc2, 105, 295, over);
            writeLine(doc3, 105, 280, over);
            writeLine(doc4, 105, 265, over);
            writeLine(doc5, 225, 383, over);
            writeLine(doc5, 285, 360, over);

            writeLine(inscription.getConcours().getEcoleTest().getVille(), 340, 245, over);
            writeLine(df.format(new Date()), 465, 245, over);

            String directeur1 = inscription.getConcours().getEcoleTest().getDirecteur().split(", ")[0] + ",";
            String directeur2 = inscription.getConcours().getEcoleTest().getDirecteur().split(", ")[1];
            writeLine(directeur1, 300, 220, over);
            writeLine(directeur2, 290 - x, 200, over);

            URL u = new URL("http://localhost" + inscription.getConcours().getEcoleTest().getInsigne());
            Image image = Image.getInstance(u);
            image.setAbsolutePosition(30, 700);
            image.scaleToFit(120, 120);
            over.addImage(image);

            //QR
//            BarcodeQRCode barcodeQRCode = new BarcodeQRCode(url, 1000, 1000, null);
//            Image codeQrImage = barcodeQRCode.getImage();

            Barcode39 barcode39 = new Barcode39();
            barcode39.setChecksumText(true);
            barcode39.setCode(inscription.getInscriptionID().toString());
            Image codeQrImage = barcode39.createImageWithBarcode(over, null, null);
            codeQrImage.scaleAbsolute(120, 50);
            codeQrImage.setAbsolutePosition(40, 120);
            over.addImage(codeQrImage);
            stamper.close();

            inscription.setDownloaded(true);
            inscriptionRepository.save(inscription);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.warn("erreur de génération de convocation " + ex.toString());
        }
    }

    private void writeLine(String line, int x, int y, PdfContentByte over) {
        over.beginText();
        over.setFontAndSize(bf, 13);
        over.setTextMatrix(x, y);
        over.showText(line);
        over.endText();
    }

    private void writeLine15(String line, int x, int y, PdfContentByte over) {
        over.beginText();
        over.setFontAndSize(bf, 15);
        over.setTextMatrix(x, y);
        over.showText(line);
        over.endText();
    }

    private void writeTitle(String line, int x, int y, PdfContentByte over) {
        ColumnText ct = new ColumnText(over);
        ct.setSimpleColumn(x, y - 10, x + 1000, y + 22);
        Paragraph paragraph = new Paragraph(line);
        paragraph.setFont(new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD | Font.UNDERLINE));
        ct.addElement(paragraph
        );
        try {
            ct.go();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

}

package com.far.recrutement.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Random;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "session")
@Component
public class CaptchaImage {
	private String captchaString = "";
	private String captchaImageBase64= "";
	private String errorMessage= "";
	private String userString="";
	
	Logger logger = LoggerFactory.getLogger(CaptchaImage.class);
	
	public CaptchaImage() {
		super();
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void reload() {
		BufferedImage bufferedImage = null;
		try {
			Random random = new Random();
            Color backgroundColor = Color.white;
            Color borderColor = Color.black;
            Color textColor = Color.black;
            Color circleColor = new Color(190, 160, 150);
            Font textFont = new Font("Verdana", Font.BOLD, 20);
            int charsToPrint = 6;
            int width = 160;
            int height = 50;
            int circlesToDraw = 25;
            float horizMargin = 10.0f;
            double rotationRange = 0.7; 
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
            g.setColor(backgroundColor);
            g.fillRect(0, 0, width, height);

            // lets make some noisey circles
            g.setColor(circleColor);
            for (int i = 0; i < circlesToDraw; i++) {
                int rL = (int) (((float)random.nextInt(100)/100) * height / 2.0);
                int rX = (int) (((float)random.nextInt(100)/100) * width - rL);
                int rY = (int) (((float)random.nextInt(100)/100) * height - rL);
                g.draw3DRect(rX, rY, rL * 2, rL * 2, true);
            }
            g.setColor(textColor);
            g.setFont(textFont);
            FontMetrics fontMetrics = g.getFontMetrics();
            int maxAdvance = fontMetrics.getMaxAdvance();
            int fontHeight = fontMetrics.getHeight();

            String elegibleChars = "ABCDEFGHJKLMNPQRSTUVWXYabcdefghjkmnpqrstuvwxy23456789";
            char[] chars = elegibleChars.toCharArray();
            float spaceForLetters = -horizMargin * 2 + width;
            float spacePerChar = spaceForLetters / (charsToPrint - 1.0f);
            StringBuilder finalString = new StringBuilder();
            for (int i = 0; i < charsToPrint; i++) {
                double randomValue = ((float)random.nextInt(100)/100);
                int randomIndex = (int) Math.round(randomValue * (chars.length - 1));
                char characterToShow = chars[randomIndex];
                finalString.append(characterToShow);
                int charWidth = fontMetrics.charWidth(characterToShow);
                int charDim = Math.max(maxAdvance, fontHeight);
                int halfCharDim = charDim / 2;
                BufferedImage charImage = new BufferedImage(charDim, charDim, BufferedImage.TYPE_INT_ARGB);
                Graphics2D charGraphics = charImage.createGraphics();
                charGraphics.translate(halfCharDim, halfCharDim);
                double angle = (((float)random.nextInt(100)/100) - 0.5) * rotationRange;
                charGraphics.transform(AffineTransform.getRotateInstance(angle));
                charGraphics.translate(-halfCharDim, -halfCharDim);
                charGraphics.setColor(textColor);
                charGraphics.setFont(textFont);
                int charX = (int) (0.5 * charDim - 0.5 * charWidth);
                charGraphics.drawString("" + characterToShow, charX, (int) ((charDim - fontMetrics.getAscent()) / 2 + fontMetrics.getAscent()));
                float x = horizMargin + spacePerChar * (i) - charDim / 2.0f;
                int y =  (height - charDim) / 2;
                g.drawImage(charImage, (int) x, y, charDim, charDim, null, null);
                charGraphics.dispose();
            }
            g.setColor(borderColor);
            g.drawRect(0, 0, width - 1, height - 1);
            g.dispose();
            captchaString = finalString.toString();
        } catch (Exception ioe) {
            logger.warn("Unable to build image", ioe);
        }
    	String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
 
        try {
            ImageIO.write(bufferedImage, "png", bos);
            byte[] imageBytes = bos.toByteArray();
            imageString = Base64.getEncoder().encodeToString(imageBytes);
            bos.close();
        } catch (Exception e) {
            logger.warn(e.toString());
        }
        captchaImageBase64= "data:image/png;base64,"+imageString;
	}

	public String getCaptchaImage() {
		return captchaImageBase64;
	}

	public void setUserString(String userString) {
		this.userString = userString;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Boolean isValid(String entredCaptcha)
	{
		System.out.println(entredCaptcha);
		System.out.println(captchaString);
		if(entredCaptcha.equalsIgnoreCase(captchaString))
		{
			errorMessage="";
		}
		else
		{
			errorMessage="Le text saisie ne correspond pas à l'image";
		}
		return errorMessage.equals("");
	}

	public String getUserString() {
		return userString;
	}
}

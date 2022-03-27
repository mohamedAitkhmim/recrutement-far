package com.far.recrutement.restcontroller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageRestController {
	
	Logger logger = LoggerFactory.getLogger(ImageRestController.class);
	
	@RequestMapping(value = "/image", method = RequestMethod.GET)
    public void getImage(@RequestParam(value="id", defaultValue="") String id,
    		@RequestParam(required =false, defaultValue = "-1") int h,
    		@RequestParam(required =false, defaultValue = "-1") int w
    		,HttpServletResponse response) 
	{
		if(id.matches("[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}.[a-zA-Z]{3,4}"))
		{
			File file=new File("files/images/"+id);
			try(InputStream targetStream = new FileInputStream(file)) 
			{
				response.setContentType(MediaType.IMAGE_JPEG_VALUE);
				//if(h == -1 || w == -1)
				if (true)
				{
					StreamUtils.copy(targetStream, response.getOutputStream());
				}
				else
				{
					BufferedImage inputImage = ImageIO.read(targetStream);
					BufferedImage outputImage = new BufferedImage(w,h, inputImage.getType());
					
					// scales the input image to the output image
			        Graphics2D g2d = outputImage.createGraphics();
			        g2d.drawImage(inputImage, 0, 0, w, h, null);
			        g2d.dispose();
			        
			        ByteArrayOutputStream baos = new ByteArrayOutputStream();
			        ImageIO.write( outputImage, "jpg", baos );
			        baos.flush();
			        byte[] bytes = baos.toByteArray();
			        baos.close();
			        
			        StreamUtils.copy(bytes, response.getOutputStream());
				}
			}
			catch(Exception ex)
			{
				logger.warn(ex.toString());
				ex.printStackTrace();
			}
		}
		else
		{
			logger.warn("acces interdit ("+id+")");
		}
	}

}

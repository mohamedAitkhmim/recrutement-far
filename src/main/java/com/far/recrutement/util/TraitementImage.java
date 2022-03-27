package com.far.recrutement.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class TraitementImage {
	
	static Logger logger = LoggerFactory.getLogger(TraitementImage.class);
	
	private TraitementImage()
	{
		//hide implicite constructor
	}
	
	public static String create(MultipartFile file)
	{
		String url="";
		try 
		{
			url=UUID.randomUUID().toString()+"."+file.getOriginalFilename().split("\\.")[1];
		}
		catch(Exception ex)
		{
			logger.warn("Traitement Image find file",ex);
		}
		try (FileOutputStream stream = new FileOutputStream("files\\images\\"+url))
		{
			stream.write(file.getBytes());
		} catch (Exception e) {
			logger.warn("Traitement Image open file",e);
			return "no File";
		} 
		return url;
	}
	
	public static void delete(String image)
	{
		Path path = Paths.get("files\\images\\"+image);
		try {
			Files.delete(path);
		} catch (IOException e) {
			logger.warn("file not found ",e);
		}
	}
}

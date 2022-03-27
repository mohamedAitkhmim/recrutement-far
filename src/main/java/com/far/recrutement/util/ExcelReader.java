package com.far.recrutement.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.far.recrutement.models.Massar;

public class ExcelReader {
	
	MultipartFile file;
	static final int START_ROW=1;
	static final int C_MASSAR=0;
	static final int C_NOTE=4;
	static final int C_NOTE2=5;
	static final int C_NOM=2;
	static final int C_PRENOM=3;
	static final int C_CIN=1;
	
	Logger logger = LoggerFactory.getLogger(ExcelReader.class);
	
	
	public ExcelReader(MultipartFile file)
	{
		this.file=file;
	}
	
	private Massar getEtudiatFromExcelLigne(XSSFRow ligne)
	{
		String massar="";
		String nom="";
		String prenom="";
		String cin="";
		float note2=0;
		float note=0;
		try 
		{
        	massar=ligne.getCell(C_MASSAR).getStringCellValue();
        }
		catch(Exception ex)
		{
			massar="";
		}
        try 
        {
        	nom=ligne.getCell(C_NOM).getStringCellValue();
        }
        catch(Exception ex)
        {
        	nom="";
        }
        try 
        {
        	prenom=ligne.getCell(C_PRENOM).getStringCellValue();
        }
        catch(Exception ex)
        {
        	prenom="";
        }
        try 
        {
        	cin=ligne.getCell(C_CIN).getStringCellValue();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
        }
        catch(Exception ex)
        {
        	cin="";
        }
        try 
        {
        	note=(float)ligne.getCell(C_NOTE).getNumericCellValue();
        }
        catch(Exception ex)
        {
        	note=0;
        }
		try
		{
			note2=(float)ligne.getCell(C_NOTE2).getNumericCellValue();
		}
		catch(Exception ex)
		{
			note2=0;
		}
        return new Massar(massar,nom,prenom,cin,note);
	}
	
	@SuppressWarnings("resource")
	public List<Massar> getMassar()
	{
		List<Massar> massars=new ArrayList<>();
		
		InputStream excelFileToRead;
		try {
			String url="files/images/"+TraitementImage.create(file);
			excelFileToRead = new FileInputStream(url);
	        XSSFSheet sheet = new XSSFWorkbook(excelFileToRead).getSheetAt(0);
            for(int k=START_ROW;k<=sheet.getLastRowNum();k++)
            {
            	XSSFRow ligne = sheet.getRow(k);
            	massars.add(getEtudiatFromExcelLigne(ligne));
            }
		} catch (Exception e) {
			logger.warn("ExcelReader",e);
		}
		return massars;
	}

}

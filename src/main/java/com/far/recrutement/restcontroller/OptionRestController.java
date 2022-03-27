package com.far.recrutement.restcontroller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.far.recrutement.metier.EtudeMetier;
import com.far.recrutement.metier.NiveauMetier;
import com.far.recrutement.metier.OptionMetier;
import com.far.recrutement.models.Niveau;
import com.far.recrutement.models.Option;
import com.far.recrutement.util.Internationalisation;

@RestController
public class OptionRestController {
	
	@Autowired
	EtudeMetier etudeMetier;
	@Autowired
	OptionMetier optionMetier;
	@Autowired
	NiveauMetier niveauMetier;
	
	@RequestMapping("inscription/options")
    public List<Option> getOptions(HttpSession session,@RequestParam(value="niveau", defaultValue="1") Long niveau) {
		Niveau niveauO = niveauMetier.getNiveauById(niveau);
		List<Option> options=new ArrayList<>();
		if(niveauO !=null && niveauO.getEtude()!=null)
		{
			options=new ArrayList<>(niveauO.getEtude().getOptions());
		}
		options = Internationalisation.updateOptions(session, options);
		System.out.println("size "+options.size());
        return options;
    }

}

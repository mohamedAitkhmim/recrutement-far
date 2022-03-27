package com.far.recrutement.restcontroller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.far.recrutement.metier.RegionMetier;
import com.far.recrutement.models.Region;
import com.far.recrutement.models.Ville;
import com.far.recrutement.util.Internationalisation;

@RestController
public class VilleRestController {
	
	@Autowired
	RegionMetier regionMetier;
	
	@RequestMapping("inscription/villes")
    public List<Ville> getVilles(HttpSession session,@RequestParam(value="region", defaultValue="1") Long region) {
		Region regionO = regionMetier.getRegionById(region);
		List<Ville> villes=new ArrayList<>();
		if(regionO != null)
			villes = new ArrayList<>(regionO.getVilles());
		villes = Internationalisation.updateVilles(session, villes);
		return villes;
    }

}

package com.far.recrutement.metier;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.far.recrutement.dao.RegionRepository;
import com.far.recrutement.entities.RegionGraphe;
import com.far.recrutement.models.Region;

@Service
public class RegionMetier implements IRegionMetier {

	@Autowired
	RegionRepository regionRepository;
	
	Logger logger = LoggerFactory.getLogger(RegionMetier.class);
	
	@Override
	public Region addRegion(Region r) {
		return regionRepository.save(r);
	}

	@Override
	public List<Region> listRegion() {
		return regionRepository.findAll();
	}

	@Override
	public List<RegionGraphe> getRegionGraphe() {
		List<Region> regions=regionRepository.findAll();
		List<RegionGraphe> graphes=new ArrayList<>();
		try 
		{
			for(Region region : regions)
			{
				RegionGraphe graphe=new RegionGraphe();
				graphe.setRegion(region.getNomRegion());
				graphe.setCandidats(region.getCandidats().size());
				graphes.add(graphe);
			}
		}
		catch(Exception ex)
		{
			logger.warn("Région graphe Exception " + ex.toString());
		}
		return graphes;
	}

	@Override
	public Region getRegionById(Long id) {
		return regionRepository.findById(id).orElse(null);
	}
}

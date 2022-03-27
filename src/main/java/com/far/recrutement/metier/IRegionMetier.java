package com.far.recrutement.metier;

import java.util.List;

import com.far.recrutement.entities.RegionGraphe;
import com.far.recrutement.models.Region;

public interface IRegionMetier {
	
	public Region addRegion(Region r);
	public List<Region> listRegion();
	public List<RegionGraphe> getRegionGraphe();
	public Region getRegionById(Long id);

}

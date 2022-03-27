package com.far.recrutement.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.far.recrutement.models.Concours;

public interface ConcoursRepository extends PagingAndSortingRepository<Concours,Long> {
	
	//recherche parmis les concours actifs uniquement
	@Query("select c from Concours c where c.disponible='true' AND (current_date() BETWEEN c.debut AND c.fin) AND (c.titre like CONCAT('%',?1,'%') or c.descriptionConcours like CONCAT('%',?1,'%')) ORDER BY c.concoursID ASC")
	List<Concours> search(String search);
	
	@Query("select c from Concours c where c.disponible='true' AND (current_date() BETWEEN c.debut AND c.fin) AND (c.titre like CONCAT('%',?1,'%') or c.descriptionConcours like CONCAT('%',?1,'%')) AND (?2=0 or c.categorie.categorieID=?2) AND (?3=0 or c.armee.armeeID=?3) ORDER BY c.concoursID ASC")
	Page<Concours> search(String search,int categorie,int armee,Pageable pageable);
	
	@Query("select c from Concours c where  c.disponible='true' AND (current_date() BETWEEN c.debut AND c.fin) AND (c.titre like CONCAT('%',?1,'%') or c.descriptionConcours like CONCAT('%',?1,'%')) ORDER BY c.concoursID ASC")
	Page<Concours> search(String search,Pageable pageable);
	
	@Query("select c from Concours c where c.disponible='true' AND (current_date() BETWEEN c.debut AND c.fin) ORDER BY c.concoursID ASC")
	Page<Concours> getConcoursActif(Pageable pageable);
	
	@Query("select c from Concours c where c.disponible='false' OR (current_date() NOT BETWEEN c.debut AND c.fin) ORDER BY c.concoursID ASC")
	Page<Concours> getConcoursInactif(Pageable pageable);
	
	@Query("select c from Concours c where c.categorie.categorieID=?1 ORDER BY c.concoursID ASC")
	Page<Concours> getConcoursByCategorieId(Long id,Pageable pageable);
	
	@Query("select c from Concours c where c.categorie.categorieID=?1 AND c.disponible='true' AND (current_date() BETWEEN c.debut AND c.fin) ORDER BY c.concoursID ASC")
	Page<Concours> getConcoursActifByCategorieId(Long id,Pageable pageable);
}

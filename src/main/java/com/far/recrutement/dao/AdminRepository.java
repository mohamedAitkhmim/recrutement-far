package com.far.recrutement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.far.recrutement.models.Admin;

public interface AdminRepository extends JpaRepository<Admin,Long> {
	
	@Query("select a from Admin a where upper(a.email)= upper(:email)")
	public List<Admin> getAdminByEmail(@Param("email") String email);

}

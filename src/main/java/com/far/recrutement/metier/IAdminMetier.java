package com.far.recrutement.metier;

import java.util.List;

import com.far.recrutement.models.Admin;

public interface IAdminMetier {
	
	public Admin addAdmin(Admin a);
	public void delete(Long id);
	public Admin getAdminById(Long id);
	public Admin getAdminByEmail(String email);
	public List<Admin> listAdmin();
	Admin addAdminWithoutHash(Admin a);

}

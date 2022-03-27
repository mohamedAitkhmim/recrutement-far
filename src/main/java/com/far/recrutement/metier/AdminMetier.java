package com.far.recrutement.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.far.recrutement.dao.AdminRepository;
import com.far.recrutement.models.Admin;

@Service
public class AdminMetier implements IAdminMetier {

	@Autowired
	AdminRepository adminRepository;
	@Autowired
	private UserMetier userMetier;

	@Override
	public Admin addAdmin(Admin a) {
		return (Admin) userMetier.save(a);
	}
	
	@Override
	public Admin addAdminWithoutHash(Admin a) {
		return  adminRepository.save(a);
	}

	@Override
	public List<Admin> listAdmin() {
		return adminRepository.findAll();
	}

	@Override
	public Admin getAdminById(Long id) {
		return adminRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		adminRepository.deleteById(id);
	}

	@Override
	public Admin getAdminByEmail(String email) {
		return adminRepository.getAdminByEmail(email).get(0);
	}

}

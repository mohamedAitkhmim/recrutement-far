package com.far.recrutement.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.far.recrutement.dao.ArmeeRepository;
import com.far.recrutement.models.Armee;

@Service
public class ArmeeMetier implements IArmeeMetier {

	@Autowired
	ArmeeRepository armeeRepository;
	
	@Override
	public Armee addArmee(Armee armee) {
		return armeeRepository.save(armee);
	}

	@Override
	public List<Armee> listArmee() {
		return armeeRepository.findAll();
	}

}

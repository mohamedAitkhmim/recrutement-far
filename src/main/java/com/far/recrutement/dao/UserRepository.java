package com.far.recrutement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.far.recrutement.models.User;

public interface UserRepository extends JpaRepository<User,Long> {
	
	@Query("select u from User u where u.email=?1")
	public User getUserByEmail(String email);

}

package com.far.recrutement.metier;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.far.recrutement.models.User;

public interface IUserMetier {

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	User getUserByEmail(String email);
	public User save(User user);

}

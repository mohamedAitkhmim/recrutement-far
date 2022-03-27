package com.far.recrutement.metier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.far.recrutement.dao.UserRepository;
import com.far.recrutement.models.User;

@Service("userMetier")
public class UserMetier implements UserDetailsService, IUserMetier {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(UserMetier.class);

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        username = username.toLowerCase();
        User user = userRepository.getUserByEmail(username);
        UserBuilder builder = null;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.disabled(!user.isEnabled());
            builder.roles(user.getRole());
            builder.password(user.getPass());
        } else {
            logger.info("Email ou mot de passe incorrect");
            throw new UsernameNotFoundException("Email ou mot de passe incorrect");
        }
        return builder.build();
    }

    @Override
    public User save(User user) {
        user.setEmail(user.getEmail().toLowerCase());
        user.setPass(passwordEncoder.encode(user.getPass()));
        return userRepository.save(user);
    }

    public User reSave(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
}

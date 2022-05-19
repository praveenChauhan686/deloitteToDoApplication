package com.deloitte.todoapp.service.auth;

import java.util.HashSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.todoapp.model.User;
import com.deloitte.todoapp.repository.UserRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
	private static final Logger log = LogManager.getLogger(CustomerUserDetailsService.class);

	@Autowired
	private UserRepository userService;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) {
		log.info(username + " is trying to login");
		User user = userService.findByUsername(username);
		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true,
					true, true, true, new HashSet<>());
		} else
			throw new UsernameNotFoundException(String.format("User with username=%s was not found", username));
	}
}
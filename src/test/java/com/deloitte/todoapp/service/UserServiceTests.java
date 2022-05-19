package com.deloitte.todoapp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.deloitte.todoapp.model.User;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserServiceTests {

	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	private User user;

	@BeforeEach
	void beginInit() {
		String password = passwordEncoder.encode("password");
		user = new User("username", password);
		userService.save(user);
	}


	@Test
	void whenFindByNonExistUsername_thenReturnNull() {
		User testUser = userService.findByUsername("notuser");
		Assertions.assertNull(testUser);
	}

}
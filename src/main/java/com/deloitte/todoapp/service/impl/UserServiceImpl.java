package com.deloitte.todoapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.todoapp.model.User;
import com.deloitte.todoapp.repository.UserRepository;
import com.deloitte.todoapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Override
	public User findByUsername(String userName) {
		return userRepository.findByUsername(userName);
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}
}
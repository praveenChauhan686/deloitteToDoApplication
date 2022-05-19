package com.deloitte.todoapp.service;

import org.springframework.stereotype.Service;

import com.deloitte.todoapp.model.User;

@Service
public interface UserService {
    User findByUsername(String userName);
    void save(User user);
}
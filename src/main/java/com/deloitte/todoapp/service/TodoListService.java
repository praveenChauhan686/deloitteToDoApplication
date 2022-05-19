package com.deloitte.todoapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.deloitte.todoapp.model.TodoList;

@Service
public interface TodoListService {

	List<TodoList> findAllByUserId(Long UserId);

	TodoList findById(Long Id);

	void save(TodoList todoList);

	void delete(TodoList todoList);
}
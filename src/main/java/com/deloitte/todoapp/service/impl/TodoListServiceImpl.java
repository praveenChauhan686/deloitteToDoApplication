package com.deloitte.todoapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.todoapp.model.TodoList;
import com.deloitte.todoapp.repository.TodoListRepository;
import com.deloitte.todoapp.service.TodoListService;

@Service
public class TodoListServiceImpl implements TodoListService {

	@Autowired
	TodoListRepository todoListRepository;

	@Override
	public List<TodoList> findAllByUserId(Long UserId) {
		return todoListRepository.findAllByUserId(UserId);
	}

	@Override
	public TodoList findById(Long Id) {
		return todoListRepository.findById(Id).orElse(null);
	}

	@Override
	public void save(TodoList todoList) {
		todoListRepository.save(todoList);
	}

	@Override
	public void delete(TodoList todoList) {
		todoListRepository.delete(todoList);
	}
}
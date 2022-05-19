package com.deloitte.todoapp.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.deloitte.todoapp.model.TodoList;

@Component
public class TodoListValidation implements Validator {

	@Override
	public boolean supports(Class<?> supportClass) {
		return supportClass.equals(TodoList.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TodoList todoList = (TodoList) target;
		if (todoList.getHeader().length() == 0) {
			errors.rejectValue("header", "todoList.header.error", "Task name must have at least a character.");
		}
	}
}
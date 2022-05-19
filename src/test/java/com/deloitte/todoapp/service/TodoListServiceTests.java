package com.deloitte.todoapp.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.deloitte.todoapp.model.TodoList;
import com.deloitte.todoapp.model.User;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TodoListServiceTests {

	@Autowired
	private UserService userService;
	@Autowired
	private TodoListService todoListService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	private User user;
	private TodoList[] todoListArray;

	@BeforeEach
	void beginInit() {
		todoListArray = new TodoList[3];
		user = new User("username", passwordEncoder.encode("password"));

		userService.save(user);
		for (int i = 1; i < 4; i++) {
			TodoList todoList = new TodoList("task " + i, false, user, null);
			todoList.setId((long) i);
			todoListService.save(todoList);
			todoListArray[i - 1] = todoList;
		}
	}

	@Test
	void whenFindByExistIdThenReturnTodoList() {
		TodoList testTodoList = todoListService.findById(1L);
		if (testTodoList != null) {
			Assertions.assertEquals(testTodoList.toString(), todoListArray[0].toString());
			testTodoList = todoListService.findById(2L);
			Assertions.assertEquals(testTodoList.toString(), todoListArray[1].toString());
			testTodoList = todoListService.findById(3L);
			Assertions.assertEquals(testTodoList.toString(), todoListArray[2].toString());
		}
	}

	@Test
	void whenFindAllByExistUserUsingIdButNoTaskThenReturnEmptyList() {
		for (int i = 0; i < 3; i++)
			todoListService.delete(todoListArray[i]);
		List<TodoList> lists = todoListService.findAllByUserId(userService.findByUsername(user.getUsername()).getId());
		Assertions.assertEquals(lists, new ArrayList<>());
	}
}
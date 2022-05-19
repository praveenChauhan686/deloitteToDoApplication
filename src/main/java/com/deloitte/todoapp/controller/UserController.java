package com.deloitte.todoapp.controller;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.deloitte.todoapp.model.TodoList;
import com.deloitte.todoapp.model.User;
import com.deloitte.todoapp.service.TodoListService;
import com.deloitte.todoapp.service.UserService;
import com.deloitte.todoapp.validation.UserValidation;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller

public class UserController {
	private static final Logger log = LogManager.getLogger(UserController.class);

	private final UserValidation userValidation;
	private final UserService userService;
	private final TodoListService todoListService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserController(UserValidation userValidation, UserService userService, TodoListService todoListService) {
		this.userValidation = userValidation;
		this.userService = userService;
		this.todoListService = todoListService;
	}

	@GetMapping("/login")
	public String getLogin(HttpSession httpSession, Model model, Error error) {
		httpSession.invalidate();
		log.trace("GET request received for LOGIN");
		if (error != null)
			model.addAttribute("error", "The username or password you entered is incorrect.");
		return "login";
	}

	@PostMapping("/loginProcess")
	public String postLogin() {
		log.info("Login Successful");
		return "redirect:/";
	}

	@GetMapping("/register")
	public String getRegister(Model model, HttpSession httpSession) {
		httpSession.invalidate();
		log.trace("GET request received for REGISTER");
		model.addAttribute("userData", new User());
		return "register";
	}

	@PostMapping("/register")
	public String postRegister(@ModelAttribute("userData") User userData, RedirectAttributes redirectAttributes,
			BindingResult bindingResult) {
		userValidation.validate(userData, bindingResult);
		if (bindingResult.hasErrors()) {
			log.info("User not registered due to Error on validation : \n" + bindingResult.toString());
			return "register";
		}
		userData.setPassword(passwordEncoder.encode(userData.getPassword()));
		userService.save(userData);
		log.info(userData.getUsername() + " is successfully registered");
		redirectAttributes.addFlashAttribute("registered", true);
		return "redirect:/login";
	}

	@GetMapping({ "/", "", "/index" })
	public String myHome(Model model, Principal principal) {
		model.addAttribute("user", principal);
		List<TodoList> todoLists = todoListService
				.findAllByUserId(userService.findByUsername(principal.getName()).getId());
		model.addAttribute("list", todoLists);
		return "index";
	}

}
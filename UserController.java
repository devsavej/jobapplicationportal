package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.demo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

import com.demo.entity.User;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository repo;
	@PostMapping("/register")
	
	public String register( User user ) {
		
		user.setRole("USER");

		repo.save(user);
		return "redirect:/login.html";
	}
	

	@PostMapping("/login")
	public String login(String email, String password, HttpSession session) {

	    User user = repo.findByEmailAndPassword(email, password);

	    if(user != null) {

	        session.setAttribute("user", user);

	        // Redirect based on role
	        if("ADMIN".equalsIgnoreCase(user.getRole())) {
	            return "redirect:/admin-login.html";
	        } else {
	            return "redirect:/user-login.html";
	        }

	    } else {
	        return "redirect:/login.html";
	    }
	}

}

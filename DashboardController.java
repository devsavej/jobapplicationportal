package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.demo.entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
	public class DashboardController {

	    @GetMapping("/dashboard")
	    public String dashboard(HttpSession session, Model model) {

	        User user = (User) session.getAttribute("user");

	        // If user not logged in
	        if (user == null) {
	            return "redirect:/login.html";
	        }

	        
	        model.addAttribute("role", user.getRole());

	        return "dashboard";
	    }
	}



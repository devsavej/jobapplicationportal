package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.entity.Job;
import com.demo.entity.User;
import com.demo.repository.JobRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class Addjob {

	@Autowired
	private JobRepository repo;

	@PostMapping("/addjob")
	public String addJob(Job job, HttpSession session) {
		

		User user = (User) session.getAttribute("user");

		if (user != null) {
			System.out.println("ROLE =" + user.getRole());
		}

		if (user == null || !"ADMIN".equalsIgnoreCase(user.getRole())) {
			System.out.println("Access Denied");
			return "redirect:/dashboard.html?eror=accessdenied";
		}
		System.out.println("Allow");
	
		repo.save(job);
		
		return "redirect:/dashboard.html";
	}

	@GetMapping("/addjob")
	public String handleGet() {

		return "redirect:/add-job.html";
	}
	
	

}

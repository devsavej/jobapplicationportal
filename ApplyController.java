package com.demo.controller;

import com.demo.entity.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.entity.Application;
import com.demo.repository.ApplicationRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class ApplyController {
	
	
	
	

	
	@Autowired
	private ApplicationRepository appRepo;
	
	@PostMapping("/apply")
	public String applyJob(@RequestParam int jobId, HttpSession session) {
		
		System.out.println(session.getAttribute("user"));
		
	
		User user=(User) session.getAttribute("user");
		
		if(user ==null) {
			return "redirect:/login.html";
		}
		
		Application existing =appRepo.findByUserIdAndJobId(user.getId(),jobId);
		
		if(existing!=null) {
			System.out.println("Applyed Seccessfully");
			return "redirect:/viewJobs";
		}
		
		Application app=new Application();
		app.setUserId(user.getId());
		app.setJobId(jobId);
		appRepo.save(app);
		
		return "redirect:/viewJobs";
		
		
	}
	@GetMapping("/allapplications")
	@ResponseBody
	public List<Application> getAllApplications(){
		return appRepo.findAll();
	}
	
	
	
}

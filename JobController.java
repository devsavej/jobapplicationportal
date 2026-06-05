package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.entity.Application;
import com.demo.entity.Job;
import com.demo.entity.User;
import com.demo.repository.JobRepository;

import jakarta.servlet.http.HttpSession;


@Controller
public class JobController {
	@Autowired
	private JobRepository repo;
	
	
	@GetMapping("/viewJobs")
	@ResponseBody
	public String viewJobs() {

	    List<Job> jobs = repo.findAll();

	    StringBuilder sb = new StringBuilder();

	    sb.append("<html><head><style>");

	    // CSS DESIGN
	    sb.append("body { font-family: Arial; background: #f4f6f9; padding:20px; }");
	    sb.append(".container { max-width: 800px; margin:auto; }");
	    sb.append(".title { text-align:center; color:#333; }");

	    sb.append(".card { background:#fff; padding:20px; margin:15px 0; border-radius:10px;");
	    sb.append("box-shadow:0 5px 15px rgba(0,0,0,0.1); transition:0.3s; }");

	    sb.append(".card:hover { transform: translateY(-5px); }");

	    sb.append(".card h3 { margin:0; color:#667eea; }");
	    sb.append(".card p { margin:5px 0; color:#555; }");

	    sb.append(".apply-btn { background:#667eea; color:white; border:none;");
	    sb.append("padding:8px 15px; border-radius:5px; cursor:pointer; }");

	    sb.append(".apply-btn:hover { background:#5a67d8; }");

	    sb.append("</style></head><body>");

	    sb.append("<div class='container'>");
	    sb.append("<h2 class='title'>All Jobs</h2>");

	    for (Job job : jobs) {
	        sb.append("<div class='card'>");

	        sb.append("<h3>").append(job.getTitle()).append("</h3>");
	        sb.append("<p><b>Company:</b> ").append(job.getCompany()).append("</p>");
	        sb.append("<p><b>Location:</b> ").append(job.getLocation()).append("</p>");
	        sb.append("<p><b>Salary:</b> ").append(job.getSsalary()).append("</p>");

	        sb.append("<form action='/apply' method='post'>");
	        sb.append("<input type='hidden' name='jobId' value='").append(job.getId()).append("'>");
	        sb.append("<button class='apply-btn' type='submit'>Apply</button>");
	        sb.append("</form>");

	        sb.append("</div>");
	    }

	    sb.append("</div></body></html>");

	    return sb.toString();
	
	}

	
	@GetMapping("/alljobs")
	@ResponseBody
	public List<Job> getAllJobs(){
		return repo.findAll();
	}
	
	@GetMapping("/deletejob/{id}")
	@ResponseBody
	public String deleteJob(@PathVariable int id) {
		repo.deleteById(id);
		return "Deleted";
	}
	
	@GetMapping("/job/{id}")
	@ResponseBody
	public Job getJob(@PathVariable int id) {
		
		return repo.findById(id).get();
	}
	@PostMapping("/updatejob")
    @ResponseBody
	public String updateJob(@RequestBody Job job) {
		
		repo.save(job);
		
		return "Job Updated Sucessfully";
	}
	


}

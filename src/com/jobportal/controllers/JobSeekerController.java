package com.jobportal.controllers;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jobportal.configurations.WebAppInitializer;
import com.jobportal.job.AppliedJob.AppliedJobs;
import com.jobportal.job.AppliedJob.AppliedJobsService;
import com.jobportal.job.PostedJob.PostedJobs;
import com.jobportal.job.PostedJob.PostedJobsService;
import com.jobportal.userhandler.UserBean;
import com.jobportal.userhandler.UserRepository;
import org.apache.logging.log4j.*;
//this is the job controller class which takes care of redirection/controlling the job posting and applying related activities

@Controller
public class JobSeekerController {
	
	private static Logger log = LogManager.getLogger(WebAppInitializer.class.getName()); // Logger Method Object

	// Job posting service used by employer
	@Autowired
	private PostedJobsService postedJobsService;

	// Job applying service used by job seeker
	@Autowired
	private AppliedJobsService appliedJobsService;

	// User service
	@Autowired
	private UserRepository userRepository;


	// below method maps the page in which jobs can be seen by the job seeker
	@GetMapping("/joblist")
	public ModelAndView showjoblist(Authentication authentication) {

		try {
		log.info("Displaying job list");
		authentication = SecurityContextHolder.getContext().getAuthentication();

		String username = authentication.getName();
		UserBean user = userRepository.getUserByUsername(username);
		String email = user.getEmail();

		List<PostedJobs> listJobs = postedJobsService.listJobs(username);// getting all the jobs from the database

		ModelAndView mav = new ModelAndView("joblist");
		mav.addObject("listJobs", listJobs);
		mav.addObject("username", username);
		mav.addObject("email", email);
		
		
		return mav;
		}catch(Exception e) {
			log.error("Exception while fetching the job list" + e);
		}
		return null;

	}




	// Below method is responsible for storing the data "who has applied for which job in the database
	@GetMapping("/applyJob")
	public String applyJob(Map<String, Object> model, @RequestParam String username, @RequestParam String email,
			@RequestParam Long id, @RequestParam String jobName) {

		try {
			log.info("Candidate applied for a job");
		AppliedJobs appliedJobs = new AppliedJobs();

		appliedJobs.setJobid(id);
		appliedJobs.setCandidateUsername(username);

		appliedJobsService.save(appliedJobs);

		return "jobappliedsuccess";
		}catch(Exception e) {
			log.error("Exception while applying for the job" + e);
		}
		return null;

	}
	
	
	// below method maps the page in which job seeker can see their own applications
	@GetMapping("/myAppliedJobs")
	public ModelAndView myAppliedJobs(Authentication authentication) {
		
		try {
			
		authentication = SecurityContextHolder.getContext().getAuthentication();

		String username = authentication.getName();
			
		List<PostedJobs> appliedJobs= postedJobsService.getMyJobApplications(username);
		
		System.out.println(appliedJobs);
		
		ModelAndView mav = new ModelAndView("myAppliedJobs");
		mav.addObject("appliedJobs", appliedJobs);

		return mav;
		
		}catch(Exception e) {
			log.error("Exception while fetching the candidate's applied jobs "+ e);
		}
		return null;

	}
	
	
	
	// Below method is responsible for providing the revoke candidacy functionality
	@GetMapping("/revokeCandidacy")
	public String revokeMyCandidacy(@RequestParam Long jobid, Authentication authentication) {
		
		try {
			
		authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		appliedJobsService.revokeCandidacy(username,jobid);
		return "candidacy-revoked";
		}catch(Exception e) {
			log.error("Exception while revoking the candidacy "+ e);
		}
		return null;

	}
	
}

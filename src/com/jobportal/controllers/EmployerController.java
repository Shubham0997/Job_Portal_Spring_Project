package com.jobportal.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

//Controller dedicated for employer URLs
@Controller
public class EmployerController {
	
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
	
	// Below method is responsible mapping the page using which employer posts the job
	@GetMapping("/postJob")
	public String showEmployerDashboard(PostedJobs postedJobs, Authentication authentication, Model model) {
		
		
		try {
			
		log.info("Post job controller.");
		authentication = SecurityContextHolder.getContext().getAuthentication(); // getting authentication

		String username = authentication.getName();

		UserBean user = userRepository.getUserByUsername(username);

		model.addAttribute(user);

		return "postJob";
		}catch(Exception e) {
			log.error("Exception while posting the jobs  "+ e);
		}
		return null;

	}

	// Below method saves the job post posted by the employer in the database
	@RequestMapping(value = "/process-jobposting", method = RequestMethod.POST)
	public String postTheJob(@ModelAttribute("postedJobs") PostedJobs postedJobs,
			RedirectAttributes redirectAttributes) {
		try {

		log.info("Posting jobs");
		redirectAttributes.addAttribute("username", postedJobs.getEmployerUsername());
		redirectAttributes.addAttribute("email", postedJobs.getEmployerEmail());

		postedJobsService.save(postedJobs); // saving the job

		return "jobposted-success"; // redirected to success page
		}catch(Exception e) {
			log.error("Exception while saving the job post in the database " + e);
		}
		return null;
	}

	
	// below method maps the page in which employer can see their own job postings
	@GetMapping("/viewYourPostings")
	public ModelAndView showEmployersJobs(Authentication authentication) {
		
		try {
			log.info("Employer's self posting ");
		authentication = SecurityContextHolder.getContext().getAuthentication();

		String username = authentication.getName();
		List<PostedJobs> listJobs = postedJobsService.listEmployersPost(username);// getting the jobs posted by
																					// particular employer
		ModelAndView mav = new ModelAndView("EmployersPostings");
		mav.addObject("listJobs", listJobs);

		return mav;
		}catch(Exception e) {
			log.error("Exception while fetching employer particular posting " + e);
		}
		return null;

	}
	
	
	
	// Below method is reponsible for showing the applicant who has applied for a particular job.
	@GetMapping("/processapplicants")
	public ModelAndView showApplicants(@RequestParam Long jobid, Authentication authentication) {
		
		try {
			log.info("Showing applicants for a job");
		authentication = SecurityContextHolder.getContext().getAuthentication();

		List<AppliedJobs> candidates = appliedJobsService.showCandidates(jobid); // getting candidates who has applied
																					// for a job using jobid

		ModelAndView mav = new ModelAndView("Applicants");
		mav.addObject("candidatesList", candidates);

		return mav;
		}catch(Exception e) {
			log.error("Exception while fetching job candidates "+e);
		}
		return null;

	}

	// Below method is responsible for deleting a particular job post by the employer
	@GetMapping("/deletePosting")
	public ModelAndView deletePost(@RequestParam Long jobid, Authentication authentication) {
		
		try {
		log.info("Employer deleting particular post");
		authentication = SecurityContextHolder.getContext().getAuthentication();
		
		postedJobsService.deletePosting(jobid);
		appliedJobsService.deleteApplication(jobid);
		
		ModelAndView mav = new ModelAndView("deletepostsuccess");
		return mav;
		}catch(Exception e) {
			log.error("Exception while deleting the post by employer " + e);
		}
		return null;

	}
	
	
	//below method is responsible for mapping the update post page for employer
	@GetMapping("/updatePosting")
	public String updatePost(@RequestParam Long jobid,@RequestParam String jobName,@RequestParam String companyName,@RequestParam String description, Authentication authentication,Model model) {
		
	try {
		
		PostedJobs updateJob= new PostedJobs();
		updateJob.setJobid(jobid);
		updateJob.setJobName(jobName);
		updateJob.setCompanyName(companyName);
		updateJob.setDescription(description);

		model.addAttribute(updateJob);
		
		return "updateJobPosting";
	}catch(Exception e) {
		log.error("Exception while mapping the update post page  "+ e);
	}
	return null;
	}
	
	// Below method updates the job post posted by the employer in the database
	@RequestMapping(value = "/process-jobUpdating", method = RequestMethod.POST)
	public String updateTheJob(@ModelAttribute("postedJobs") PostedJobs postedJobs) {
		try {
			
		Long jobid=postedJobs.getJobid();
		String jobName=postedJobs.getJobName();
		String companyName=postedJobs.getCompanyName();
		String description = postedJobs.getDescription();
		
		postedJobsService.updatePosting(jobid,jobName,companyName,description);

		return "jobUpdate-success"; // redirected to success page
		}catch(Exception e) {
			log.error("Exception while updating the post by employer" + e);
		}
		return null;
	}
	
	

	// Below method is responsible for showing job seeker's profile to the employer who has applied for a job posted by them
	@GetMapping("/applicantprofile")
	public String applicantProfile(@RequestParam String username, Authentication authentication, Model model) {
		
		try {
			log.info("Showing applicant's profile");
		authentication = SecurityContextHolder.getContext().getAuthentication();

		UserBean user = userRepository.getUserByUsername(username);

		model.addAttribute(user);

		return "applicantProfile";
		}catch(Exception e) {
			log.error("Exception while showing the applicant's profile " + e);
		}
		return null;

	}

	
}

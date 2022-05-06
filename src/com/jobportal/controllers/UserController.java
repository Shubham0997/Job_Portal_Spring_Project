package com.jobportal.controllers;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jobportal.configurations.WebAppInitializer;
import com.jobportal.userhandler.UserBean;
import com.jobportal.userhandler.UserRegistrationService;
import com.jobportal.userhandler.UserRepository;
import org.apache.logging.log4j.*;

//this controller class is responible for signup and homepage related functionalities
@Controller
public class UserController {
	private static Logger log = LogManager.getLogger(WebAppInitializer.class.getName()); // Logger Method Object

	//Service to store the registration data in the database
	@Autowired
	private UserRegistrationService userRegistrationService;
	
	@Autowired
	private UserRepository userRepository;
	
	
	//Below method is responsible for mapping the login form
	 @RequestMapping(value = "/loginForm", method = RequestMethod.GET)
	    public String login(Model model, String error, String logout) {
		 
	        return "loginForm";
	    }
	

	//Below method is responsible for mapping the home page, i.e the page which will be loaded on startup. In this case its the registration page.
	@RequestMapping("/")
	public ModelAndView home(@ModelAttribute("registration") UserBean user ) {
		ModelAndView modelAndView = new ModelAndView("user_registration");
		return modelAndView;
	}
	
	//Below method is responsible for processing the registration request. i.e storing the registration data into the database if has no errors or username doesn't already exists.
	@RequestMapping(value = "/process-signup", method = RequestMethod.POST)
	public ModelAndView signUp(@ModelAttribute("registration") @Valid UserBean user, BindingResult result) {
		
		try {
			log.info("Registation being done");
		ModelAndView modelAndView = new ModelAndView("/registration-success");
		if(result.hasErrors()) {
			 modelAndView = new ModelAndView("/user_registration"); //if has error, redirect back to registration page

		}else {
			
			//if has no errors
			String username= user.getUsername();
			UserBean userCheck = userRepository.getUserByUsername(username); //check for existing username
			
		
			if(userCheck != null) { //if username already exists
				modelAndView = new ModelAndView("/UsernameExistsPrompt"); //show a prompt 
			}else {
			 //if all the validations are true 
			userRegistrationService.save(user); //save the registration data
			}
			
		}

		return modelAndView;
		}catch(Exception e) {
			log.error(e);
		}
		return null;
	}
	
	
	//Below method is responsible for showing the welcome page after a user logs in.
	@RequestMapping("/welcome")
	public String welcomeDasboard(Authentication authentication, Model model) {
		try {
			log.info("User Logged in ");
		authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String username = authentication.getName(); //getting the current logged in user's username

		UserBean user = userRepository.getUserByUsername(username); //getting the logged in user's data
		
		model.addAttribute(user);
		
		return "welcome";
		}catch(Exception e ) {
			log.error(e);
		}
		return null;
	}
	
	

}


/**
Simulation:
We have to make a page a registration page likenaukri.com where 2 types of users can register themselves. 
These two users will be "Candidate" (a job seeker) and other will be Employer. The registration html page will be same for them.
There will be two radio buttons to choose account type(Candidate or Employer). 
Based on account you selected, the section of the page will be shown(if candidate selected then candidate section, employer section otherwise). 
Each section will have different properties in their respective sections and if they have some same properties they will be written once and will be shared by both type of accounts. 
Like we have a property "Location" which can be shared by both and must be written once in the code. Rest of the properties can show/hide based on account type.

1. All the assignments must have proper validations.
2. Code quality (proper indentation, format, proper naming conventions, functions) will also be a good point of concern. Use all types of HTML elements, if you can.
 *
 * @author Shubham Sharma
 *
 *
 **/



package com.jobportal.configurations;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.logging.log4j.*;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

//This class initalizes the applications and tells what to load on startup
public class WebAppInitializer implements WebApplicationInitializer {
	
	private static Logger log = LogManager.getLogger(WebAppInitializer.class.getName()); // Logger Method Object
	
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
    	
    	try {
    	log.info("Setting up Dispatcher Servlet");
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(WebMvcConfig.class);
         
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
                "SpringDispatcher", new DispatcherServlet(appContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    	}catch(Exception e){
    		log.error("Exception in Web App Initializer"+ e);
    	}
         
    }
}

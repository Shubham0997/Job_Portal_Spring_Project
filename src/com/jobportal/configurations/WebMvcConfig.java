package com.jobportal.configurations;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import org.apache.logging.log4j.*;
//This class takes care of the view part, initializes the view resolver and add template handlers.

@Configuration
@EnableWebMvc
@ComponentScan("com.jobportal.*")
public class WebMvcConfig implements WebMvcConfigurer{
	
	private static Logger log = LogManager.getLogger(WebMvcConfig.class.getName()); // Logger Method Object
	
	//This method resolves the view part as in where to look for webpages and what format they are in.
	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
	    
		
		try {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	    viewResolver.setPrefix("/WEB-INF/views/");
	    viewResolver.setSuffix(".jsp");
		log.info("View resolver set");
	    return viewResolver;
		}catch(Exception e){
			log.error("Exception in Internal Resource view resolver " + e);
		}
		return null;
	}
	
	//This method is used to encrypt the password
	@Bean
	PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	//This method adds the resources folder into the project inside which the css and js files are being stored
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		log.info("added resource handlers");
	    registry.addResourceHandler("/template/**").addResourceLocations("/WEB-INF/template/");
	}
	
}

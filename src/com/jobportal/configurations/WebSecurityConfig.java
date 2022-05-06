package com.jobportal.configurations;

import org.apache.logging.log4j.*;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jobportal.userhandler.UserDetailsServiceImpl;

//This class implements the Spring Security mechanism which provides role based authentication.

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static Logger log = LogManager.getLogger(WebAppInitializer.class.getName()); // Logger Method Object
 
	//Implements the method to fetch the user details
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
     
    //bcrypt password encoder
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    //authentication manager using hibernate and JPA. Provides role based authentication.
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
    	
    	try {
    	log.info("Authentication provider called");
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    	}catch(Exception e) {
    		log.error("Exception in authentication provider "+e);
    	}
		return null;
    }
 
    //calls for authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
 
    //This method configures the permission for accessing the webpages role based.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/welcome").authenticated()
            .antMatchers("/postJob").hasAuthority("Employer")
            .and()
            .formLogin().loginPage("/loginForm").loginProcessingUrl("/process-login").permitAll().defaultSuccessUrl("/welcome")
            .and()
            .logout().permitAll();
    }
    
    @Override
	public void configure(WebSecurity web) throws Exception {
	    web
	            .ignoring()
	            .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**", "/icon/**");
	}
}

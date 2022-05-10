package com.jobportal.configurations;


import javax.persistence.EntityManagerFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = {"com.jobportal.*"})
@EnableTransactionManagement
public class JpaConfig {
	
	private static Logger log = LogManager.getLogger(WebAppInitializer.class.getName()); // Logger Method Object
	
	
	//configuring entity manager
    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
    	try {
    	
        LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
        return factoryBean;
    	}catch(Exception e) {
    		log.error("Error while initializing factory bean "+ e);
    	}
		return null;
    }
     
    //configuring transaction manager
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    	
    	try {
    		
    	
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
         
        return transactionManager;
        
    	}catch(Exception e) {
    		log.error("Error while initializing transaction manager");
    	}
		return null;
    }  
}

package com.jobportal.configurations;


import javax.persistence.EntityManagerFactory;

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
	
	
	
	//configuring entity manager
    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
    	
    	
        LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
        return factoryBean;
    }
     
    //configuring transaction manager
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
         
        return transactionManager;
    }  
}

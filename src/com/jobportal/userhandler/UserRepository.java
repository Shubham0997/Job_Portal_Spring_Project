package com.jobportal.userhandler;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<UserBean, Long> {
	

	
	
	//Query to fetch user details using username
	 @Query("SELECT user FROM UserBean user WHERE user.username = :username")
	    public UserBean getUserByUsername(@Param("username") String username);
	 

	
}

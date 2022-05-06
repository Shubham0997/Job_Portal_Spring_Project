package com.jobportal.userhandler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserRegistrationService {
	@Autowired 
	UserRepository repo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//saves the registration data
	public void save(UserBean user) {
		
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		repo.save(user);
	}
	
	
	public List<UserBean> listAll() {
		return (List<UserBean>) repo.findAll();
	}
	
	public UserBean get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	

	
}

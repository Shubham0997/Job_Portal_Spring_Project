package com.jobportal.userhandler;

import javax.persistence.Entity;
import javax.persistence.Id;

import jakarta.persistence.Table;

//This class is entity class for user data, this entity class maps the registration data.

@Entity
@Table(name="users_registration_table")
public class UserBean {
	

	public UserBean() {
	
	}


	private String name;
	

	private String email;	
	

	private String phone;
	
	private String gender;
	
	

	private String role;
	
	@Id
	private String username;
	
	
	
	private String password;

	private Boolean enabled;

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "UserBean [name=" + name + ", email=" + email + ", phone=" + phone + ", gender=" + gender
				+ ", role=" + role + ", username=" + username + ", password=" + password + "]";
	}


	public Boolean getEnabled() {
		return enabled;
	}


	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	

	
	
	

}
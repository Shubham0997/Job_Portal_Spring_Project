package com.jobportal.job.PostedJob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


//this is Posted jobs entity class which is being used to map the data of job postings done by the employers 

@Entity
@Table(name = "postedjobs")
public class PostedJobs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long jobid;
	private String employerUsername;
	private String jobName;
	private String companyName;
	private String description;
	private String employerEmail;
	
	public PostedJobs() {
	
	}
	public Long getJobid() {
		return jobid;
	}



	public void setJobid(Long jobid) {
		this.jobid = jobid;
	}


	public String getEmployerUsername() {
		return employerUsername;
	}

	public void setEmployerUsername(String employerUsername) {
		this.employerUsername = employerUsername;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmployerEmail() {
		return employerEmail;
	}

	public void setEmployerEmail(String employerEmail) {
		this.employerEmail = employerEmail;
	}
	public PostedJobs(Long jobid, String employerUsername, String jobName, String companyName, String description,
			String employerEmail) {
		super();
		this.jobid = jobid;
		this.employerUsername = employerUsername;
		this.jobName = jobName;
		this.companyName = companyName;
		this.description = description;
		this.employerEmail = employerEmail;
	}
	@Override
	public String toString() {
		return "PostedJobs [jobid=" + jobid + ", employerUsername=" + employerUsername + ", jobName=" + jobName
				+ ", companyName=" + companyName + ", description=" + description + ", employerEmail=" + employerEmail
				+ "]";
	}

	

}

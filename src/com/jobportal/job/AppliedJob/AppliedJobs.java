package com.jobportal.job.AppliedJob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import jakarta.persistence.Table;

// This is the entity class for storing the data of applied job, jobid and the username of the applicant will be stored

@Entity
@Table(name = "appliedJobs")
public class AppliedJobs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long jobid;
	private String candidateUsername;

	
	public AppliedJobs() {
	}

	public AppliedJobs(Long id, Long jobid, String candidateUsername) {
		super();
		this.id = id;
		this.jobid = jobid;
		this.candidateUsername = candidateUsername;
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getJobid() {
		return jobid;
	}

	public void setJobid(Long jobid) {
		this.jobid = jobid;
	}

	public String getCandidateUsername() {
		return candidateUsername;
	}

	public void setCandidateUsername(String candidateUsername) {
		this.candidateUsername = candidateUsername;
	}

	@Override
	public String toString() {
		return "AppliedJobs [id=" + id + ", jobid=" + jobid + ", candidateUsername=" + candidateUsername + "]";
	}


	
	

}

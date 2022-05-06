package com.jobportal.job.AppliedJob;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AppliedJobsService {
	
	@Autowired
	AppliedJobsRepository repo;
	
	//below method saves the applied jobs data
	public void save(AppliedJobs appliedjobs) {
		
		repo.save(appliedjobs);
	}

	//method to fetch candidates of a particular job posting 
	public List<AppliedJobs> showCandidates(Long jobid) {
		
		return (List<AppliedJobs>) repo.findCandidates(jobid);
	}

	public void deleteApplication(Long jobid) {
		repo.deleteApplication(jobid);
		
	}

	public void revokeCandidacy(String username, Long jobid) {
		repo.revokeMyCandidacy(username, jobid);
		
	}



}

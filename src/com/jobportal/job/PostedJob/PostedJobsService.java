package com.jobportal.job.PostedJob;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class PostedJobsService {

	@Autowired
	PostedJobsRepository repo;

	//below method saves the job posting
	public void save(PostedJobs jobs) {

		repo.save(jobs);
	}
	
	//below method returns all of the job postings
	public List<PostedJobs> listAll() {
		return (List<PostedJobs>) repo.findAll();
	}
	
	//below method lists particular employer's post
	public List<PostedJobs> listEmployersPost(String username){
		return (List<PostedJobs>) repo.findPostings(username);
	}

	//below method deletes the job posting by employer
	public void deletePosting(Long jobid) {
		repo.deleteById(jobid);
		
	}

	public void updatePosting(Long jobid, String jobName, String companyName, String description) {
		repo.updatePost(jobid,jobName,companyName,description);
	}

	public List<PostedJobs> getMyJobApplications(String username) {
		
		return (List<PostedJobs>) repo.getMyApplications(username);
	}

	public List<PostedJobs> listJobs(String username) {
		return (List<PostedJobs>) repo.getNonAppliedJobs(username);
	
	}


}

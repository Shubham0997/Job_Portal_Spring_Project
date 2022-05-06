package com.jobportal.job.PostedJob;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PostedJobsRepository extends CrudRepository<PostedJobs, Long>{

	
	//Query to find the employer's own job postings
	@Query("SELECT job FROM PostedJobs job WHERE job.employerUsername= :username")
	List<PostedJobs> findPostings(@Param("username") String username);

	
	//Query to update the job post by employer
	@Modifying(clearAutomatically = true)
	@Query("update PostedJobs job set job.jobName=:jobName, job.companyName=:companyName, job.description=:description where job.jobid=:jobid")
	void updatePost(@Param("jobid") Long jobid, @Param("jobName") String jobName, @Param("companyName") String companyName, @Param("description") String description);

	//Query to fetch the jobs job seeker has applied for
	@Query("Select jobs from PostedJobs jobs left join AppliedJobs appliedjobs on jobs.jobid = appliedjobs.jobid where appliedjobs.candidateUsername = :username")
	List<PostedJobs> getMyApplications(@Param("username") String username);

	//query to fetch the jobs job seeker hasn't applied for
	@Query("select job from PostedJobs job where job.jobid not in (select appliedjob.jobid from AppliedJobs appliedjob where appliedjob.candidateUsername = :username)")
	List<PostedJobs> getNonAppliedJobs(@Param("username") String username);

}

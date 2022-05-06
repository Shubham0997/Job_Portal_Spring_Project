package com.jobportal.job.AppliedJob;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AppliedJobsRepository extends CrudRepository<AppliedJobs, Long>{
	
	
	//Query to fetch the candidates of a particular job
	@Query("SELECT jobs FROM AppliedJobs jobs WHERE jobs.jobid= :jobid")
	List<AppliedJobs> findCandidates(@Param("jobid") Long jobid);

	//Query to delete job from applied jobs when employer deletes their job post
	@Modifying
	@Query("delete from AppliedJobs job where job.jobid= :jobid")
	void deleteApplication(@Param("jobid") Long jobid);

	//Query to delete the candidacy of a seeker from a post
	@Modifying
	@Query("delete from AppliedJobs applied where applied.jobid=:jobid AND applied.candidateUsername=:username")
	void revokeMyCandidacy(@Param("username") String username, @Param("jobid") Long jobid);

	
}

package com.ashokit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ashokit.binding.CitizenPlan;


public interface CitizenPlanRepository extends JpaRepository<CitizenPlan, Serializable>{
	
	@Query("select distinct(planName) from CitizenPlan")
	public List<String> getPlanName();


	@Query("select distinct(planStatus) from CitizenPlan")
	public List<String> getPlanStatus();
}

package com.ashokit.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import com.ashokit.binding.CitizenPlan;
import com.ashokit.binding.SearchRequest;

public interface ReportService {
	
	public List<String> getPlanName();
	public List<String> getPlanStatus();
	public List<CitizenPlan> getCitizenPlan(SearchRequest request);
	public void exportExcel(HttpServletResponse response) throws Exception;
	public void exportPdf(HttpServletResponse response) throws Exception ;
}	

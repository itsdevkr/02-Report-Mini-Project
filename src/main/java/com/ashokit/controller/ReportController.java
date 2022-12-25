package com.ashokit.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.service.ReportService;

@RestController
public class ReportController {

	@Autowired
	private ReportService repoService;
	
	@GetMapping("/excel")
	public void generateExcel(HttpServletResponse response) throws Exception {
		
		repoService.exportExcel(response);
		
	}
	
	@GetMapping("/pdf")
	public void generatePdf(HttpServletResponse response) throws Exception{
		repoService.exportPdf(response);
	}
}

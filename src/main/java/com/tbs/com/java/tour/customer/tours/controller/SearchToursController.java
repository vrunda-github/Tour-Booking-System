package com.tbs.com.java.tour.customer.tours.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tbs.com.java.tour.customer.tours.service.SearchToursService;

@Controller
public class SearchToursController {
	
	@Autowired
	SearchToursService searchToursService;
	
	@RequestMapping(value = "/searchTours")
	public String searchTours(HttpServletRequest request, HttpServletResponse response,String criteria) {
		String pageUrl = "/tourPage";
		// Criteria is based on Place, Dates, Budget, Number of Person
		//call to searchToursService
		searchToursService.searchTours(criteria);
		
		return pageUrl;
	}

}

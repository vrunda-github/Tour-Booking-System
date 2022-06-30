package com.tbs.com.java.tour.customer.tours.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tbs.com.java.tour.customer.tours.service.SearchToursService;

@Controller
public class SearchToursController {
	
	@Autowired
	SearchToursService searchToursService;
	
	public String searchTours(String criteria) {
		String pageUrl = "/tourPage";
		// Criteria is based on Place, Dates, Budget, Number of Person
		//call to searchToursService
		searchToursService.searchTours(criteria);
		
		return pageUrl;
	}

}

package com.tbs.com.java.tour.customer.tours.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tbs.com.java.tour.customer.tours.dao.SearchToursDao;

@Service
public class SearchToursService {
	
	@Autowired
	SearchToursDao searchToursDao;
	
	public String searchTours(String criteria) {
		// Validation of search Fields
		//Sanitizing input Fields
		return searchToursDao.searchTours(criteria);
	}

}

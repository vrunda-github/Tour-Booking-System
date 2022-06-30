package com.tbs.com.java.tour.customer.tours.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tbs.com.java.tour.customer.tours.pojo.User;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDao userDao;

	@Transactional
	public void insertData(User user) {
		/// TO insert data into user table
		userDao.insertData(user);
		
	}

	@Transactional
	public List<User> listUser() {
		//To show all user list
		List<User> userList=userDao.listUser();
		return userList;
	}

	@Transactional
	public User getUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return userDao.getUserByUserName(userName);
	}

	@Transactional
	public void editUser(User user) {
		// TODO Auto-generated method stub
		userDao.editUser(user);
		
	}

	@Transactional
	public void deleteUser(String userName) {
		userDao.deleteUser(userName);
		
	}

	@Transactional
	public User getAuthenticatedUser(String userName, String password) {
		// TODO Auto-generated method stub
		return userDao.getAuthenticatedUser(userName,password);
	}

	
	
	

}
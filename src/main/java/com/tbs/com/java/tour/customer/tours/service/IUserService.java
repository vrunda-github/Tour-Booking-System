package com.tbs.com.java.tour.customer.tours.service;

import java.util.List;

import com.tbs.com.java.tour.customer.tours.pojo.User;

public interface IUserService {
	public void insertData(User user);
	public List<User> listUser();
	public void editUser(User user);
	public void deleteUser(String userName);
	public User getUserByUserName(String userName);
	public User getAuthenticatedUser(String userName, String password);
}
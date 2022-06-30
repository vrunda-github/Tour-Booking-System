package com.tbs.com.java.tour.customer.tours.dao;

import java.util.List;

import javax.management.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tbs.com.java.tour.customer.tours.pojo.User;

@Repository
public class UserDaoImpl implements IUserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void insertData(User user) {
		// TO insert data into user table
		sessionFactory.getCurrentSession().save(user);
		System.out.println("User Successfully Inserted");
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<User> listUser() {
		//To show all user list
		Query query = sessionFactory.getCurrentSession().createQuery("from User");
		return (List<User>) query.list();
		System.out.println("vrunda listed");
	}


	@Override
	public User getUserByUserName(String userName) {
		//To get user by user name
		User user=new User();
		user=(User)sessionFactory.getCurrentSession().get(User.class,userName);
		return user;
	}


	@Override
	public void editUser(User user) {
		//To update user
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		System.out.println("User updated Succesfully!!");

	}


	@Override
	public void deleteUser(String userName) {
		// To delete user
		User user=new User();
		user=(User)sessionFactory.getCurrentSession().get(User.class,userName);
		sessionFactory.getCurrentSession().delete(user);
	}


	@SuppressWarnings("unchecked")
	@Override
	public User getAuthenticatedUser(String userName, String password) {
		// To get authenticated user
		User user = null;
		Query query=sessionFactory.getCurrentSession().createQuery("from User where userName=:userName and password=:password");
		query.setParameter("userName",userName );
		query.setParameter("password",password);
		List<User> list=query.list();
		if(list.size() > 0)
		{
			user = list.get(0);
		}
		return user;
	}




}
Foote
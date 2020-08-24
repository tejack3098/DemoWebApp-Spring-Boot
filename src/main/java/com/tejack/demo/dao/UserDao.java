package com.tejack.demo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tejack.demo.model.User;

@Repository
public class UserDao {

	@Autowired
	private SessionFactory factory;

	public void saveUser(User user) {

		getSession().save(user);
	}
	

	private Session getSession() {
		try {

			Session session = factory.getCurrentSession();

			if (session == null) {
				session = factory.openSession();
			}
			return session;

		} catch (Exception e) {

			System.out.println(e);
		}
		return null;

	}

}

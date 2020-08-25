package com.tejack.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tejack.demo.dao.UserDao;
import com.tejack.demo.model.User;

@Service
@Transactional
public class LoginService {

	@Autowired
	private UserDao dao;

	public boolean validateUser(User user) {
		Boolean login_status = false;

		try {
			login_status = dao.loginValidate(user);

		} catch (Exception e) {
			System.out.println(e);
		}
		return login_status;

	}

}

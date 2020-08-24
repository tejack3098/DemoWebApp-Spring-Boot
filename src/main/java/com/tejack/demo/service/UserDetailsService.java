package com.tejack.demo.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {

	@Autowired
	JdbcTemplate jdbc;

	public String getDetail(String email) {

		String status;

		status = jdbc.query("select  * from users where EmailID = ?", new Object[] { email },
				new ResultSetExtractor<String>() {

					@Override
					public String extractData(ResultSet rs) throws SQLException, DataAccessException {

						String name = null;
						if (rs.next()) {
							name = rs.getString(1);
						}

						return name;
					}

				});

		return status;
	}

	public Boolean chk_email(String email) {

		Boolean status;

		status = jdbc.query("select  Uname from users where EmailID=?", new Object[] { email },
				new ResultSetExtractor<Boolean>() {

					@Override
					public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {

						return rs.next();
					}

				});

		return status;
	}

}

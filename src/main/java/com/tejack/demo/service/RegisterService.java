package com.tejack.demo.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
	
	@Autowired  
    JdbcTemplate jdbc; 
	
	public boolean RegisterUser(String uname, String email, String password) {
		
		String query="insert into users values(?,?,?)"; 
		
		return jdbc.execute( query,new PreparedStatementCallback<Boolean>(){  

			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				 ps.setString(1,uname);  
        	     ps.setString(2,email);  
        	     ps.setString(3,password); 
				return ps.execute();
			}  
    	    });
	
    }

}

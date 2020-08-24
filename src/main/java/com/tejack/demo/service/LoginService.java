package com.tejack.demo.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class LoginService {
	
	@Autowired  
    JdbcTemplate jdbc; 
	
	  public boolean validateUser(String userid, String password) {
		  
		  
		  String sql = "Select * from users  where EmailID=? and Password=?";
	      Boolean status;
		
	      status = jdbc.execute( sql,new PreparedStatementCallback<Boolean>(){  

				public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
	        	     ps.setString(1,userid);  
	        	     ps.setString(2,password);  
	        	              
	        	     ResultSet rs =  ps.executeQuery();
	        	     return rs.next();
				}  
	    	    });
		  
		  
		  return status;  
		  
	    }
	  

}

package com.tejack.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
	
	@Id
	private String email;
	private String uname;
	private int age;
	private String password;
}

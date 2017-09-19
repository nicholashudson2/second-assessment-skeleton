package com.cooksys.twittr.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Profile {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String phone;
	
	
}

package com.cooksys.twittr.entity;

import java.sql.Timestamp;

import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;


public class User {

	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToOne
	private String username;
	
	private Profile profile;
	
	private Timestamp joined;
	
	@Embedded
	private Credentials credentials;
	
	private 
}

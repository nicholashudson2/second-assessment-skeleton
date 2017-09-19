package com.cooksys.twittr.entity;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Embeddable
public class Credentials {

	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToOne(mappedBy="username")
	private String username;
	
	private String password;
}

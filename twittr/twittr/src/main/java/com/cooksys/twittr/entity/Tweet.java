package com.cooksys.twittr.entity;

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Tweet {

	@Id
	@GeneratedValue
	private Integer id;
	
	private User author;
	
	private Timestamp posted;
	
	private String content;
	
	private Tweet inReplyTo;
	
	private Tweet repostOf;
	
}

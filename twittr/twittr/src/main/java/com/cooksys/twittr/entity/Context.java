package com.cooksys.twittr.entity;

import java.util.Set;

import javax.persistence.Embeddable;

@Embeddable
public class Context {
	
	private Tweet target;
	private Set<Tweet> before;
	private Set<Tweet> after;

}

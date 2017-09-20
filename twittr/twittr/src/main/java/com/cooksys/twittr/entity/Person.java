package com.cooksys.twittr.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Person {

	@Id
	@GeneratedValue
    private Integer id;

	@CreationTimestamp
	private Timestamp joined;

	@Embedded
	private Credentials credentials;
	
	@Embedded
	private Profile profile;
	
	@Column(nullable = false)
	private Boolean active = true;
	
	public Person() {
		super();
	}

	public Person(Integer id, Credentials credentials, Profile profile, Timestamp joined) {
		super();
		this.id = id;
		this.credentials = credentials;
		this.profile = profile;
		this.active = true;
		this.joined = joined;
	}

	/**
	 * @return the profile
	 */
	public Profile getProfile() {
		return profile;
	}
	
	/**
	 * @param profile the profile to set
	 */
	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the joined
	 */
	public Timestamp getJoined() {
		return joined;
	}

	/**
	 * @return the credentials
	 */
	public Credentials getCredentials() {
		return credentials;
	}

	/**
	 * @param credentials the credentials to set
	 */
	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	/**
	 * @return the isDeleted
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((credentials == null) ? 0 : credentials.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (credentials == null) {
			if (other.credentials != null)
				return false;
		} else if (!credentials.equals(other.credentials))
			return false;
		return true;
	}
	
	
}

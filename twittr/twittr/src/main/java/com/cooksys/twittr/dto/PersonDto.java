package com.cooksys.twittr.dto;

import com.cooksys.twittr.entity.Credentials;
import com.cooksys.twittr.entity.Profile;

public class PersonDto {

	private Credentials credentials;
	
	private Profile profile;
	
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

}

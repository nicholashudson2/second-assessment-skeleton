package com.cooksys.twittr.dto;

import java.sql.Timestamp;

import com.cooksys.twittr.entity.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;

public class OutputPersonDto {

	private String username;

	private Profile profile;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
	private Timestamp joined;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the profile
	 */
	public Profile getProfile() {
		return profile;
	}

	/**
	 * @param profile
	 *            the profile to set
	 */
	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	/**
	 * @return the joined
	 */
	public Timestamp getJoined() {
		return joined;
	}

	/**
	 * @param joined
	 *            the joined to set
	 */
	public void setJoined(Timestamp joined) {
		this.joined = joined;
	}

}

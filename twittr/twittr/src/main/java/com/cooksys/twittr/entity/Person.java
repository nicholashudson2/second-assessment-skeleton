package com.cooksys.twittr.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name="person")
public class Person {

	@Transient
	java.util.Date today = new java.util.Date();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="person_id")
    private Integer id;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
	private final Timestamp joined = new java.sql.Timestamp(today.getTime());

	@Embedded
	private Credentials credentials;
	
	@Embedded
	private Profile profile;
	
	@Column(nullable = false)
	private Boolean active = true;
	
	@ManyToMany
	@JoinTable(name="follower_following",
	 joinColumns=@JoinColumn(name="following_id"),
	 inverseJoinColumns=@JoinColumn(name="follower_id"))
	private List<Person> followers = new ArrayList<>();

	@ManyToMany
	@JoinTable(name="follower_following",
	 joinColumns=@JoinColumn(name="follower_id"),
	 inverseJoinColumns=@JoinColumn(name="following_id"))
	private List<Person> following = new ArrayList<>();
	
	@ManyToMany(mappedBy="likes")
	private List<Tweet> likedTweets = new ArrayList<>();

	@OneToMany(mappedBy="author")
	private List<Tweet> tweets = new ArrayList<>();
	
	@ManyToMany(mappedBy="mentions")
	private List<Tweet> mentioned = new ArrayList<>();
	
	public Person() {
		super();
	}
	
	public Person(Integer id, Credentials credentials, Profile profile) {
		super();
		this.id = id;
		this.credentials = credentials;
		this.profile = profile;
		this.active = true;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return credentials.getUsername();
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.credentials.setUsername(username);
	}

	/**
	 * @return the followers
	 */
	public List<Person> getFollowers() {
		return followers;
	}

	/**
	 * @param followers the followers to set
	 */
	public void setFollowers(List<Person> followers) {
		this.followers = followers;
	}

	/**
	 * @return the following
	 */
	public List<Person> getFollowing() {
		return following;
	}

	/**
	 * @param following the following to set
	 */
	public void setFollowing(List<Person> following) {
		this.following = following;
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
	
	/**
	 * @return the tweets
	 */
	public List<Tweet> getTweets() {
		return tweets;
	}

	/**
	 * @param tweets the tweets to set
	 */
	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}
	
	

	/**
	 * @return the likedTweets
	 */
	public List<Tweet> getLikedTweets() {
		return likedTweets;
	}

	/**
	 * @param likedTweets the likedTweets to set
	 */
	public void setLikedTweets(List<Tweet> likedTweets) {
		this.likedTweets = likedTweets;
	}

	/**
	 * @return the mentioned
	 */
	public List<Tweet> getMentioned() {
		return mentioned;
	}

	/**
	 * @param mentioned the mentioned to set
	 */
	public void setMentioned(List<Tweet> mentioned) {
		this.mentioned = mentioned;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}


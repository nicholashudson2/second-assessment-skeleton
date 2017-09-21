package com.cooksys.twittr.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Tweet {

	@Transient
	java.util.Date today = new java.util.Date();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tweet_id")
	private Integer id;

	@ManyToOne
	private Person author;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
	private final Timestamp posted = new java.sql.Timestamp(today.getTime());

	private String content;

	@ManyToOne
	private Tweet inReplyTo;
	
	@OneToMany(mappedBy="inReplyTo")
	private List<Tweet> replies;

	@ManyToOne
	private Tweet repostOf;
	
	@OneToMany(mappedBy="repostOf")
	private List<Tweet> reposts;

	@ManyToMany
	private List<Person> likes;

	private Boolean active;
	
	@ManyToMany
	private List<Person> mentions = new ArrayList<>();
	
	@ManyToMany(cascade=CascadeType.ALL)
	private List<Hashtag> hashtags = new ArrayList<>();

	public Tweet() {
		super();
	}
	
	public Tweet(Integer id, Person author, String content) {
		super();
		this.id = id;
		this.author = author;
		this.content = content;
		this.mentions = new ArrayList<>();
		this.hashtags = new ArrayList<>();
	}
	
	public Tweet(Integer id, Person author, String content, Tweet inReplyTo, Tweet repostOf, List<Person> mentions, List<Hashtag> hashtags) {
		super();
		this.id = id;
		this.author = author;
		this.content = content;
		this.inReplyTo = inReplyTo;
		this.repostOf = repostOf;
		this.mentions = mentions;
		this.hashtags = hashtags;
	}

	/**
	 * @return the author
	 */
	public Person getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(Person author) {
		this.author = author;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the posted
	 */
	public Timestamp getPosted() {
		return posted;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the inReplyTo
	 */
	public Tweet getInReplyTo() {
		return inReplyTo;
	}

	/**
	 * @param inReplyTo
	 *            the inReplyTo to set
	 */
	public void setInReplyTo(Tweet inReplyTo) {
		this.inReplyTo = inReplyTo;
	}

	/**
	 * @return the repostOf
	 */
	public Tweet getRepostOf() {
		return repostOf;
	}

	/**
	 * @param repostOf
	 *            the repostOf to set
	 */
	public void setRepostOf(Tweet repostOf) {
		this.repostOf = repostOf;
	}

	/**
	 * @return the likes
	 */
	public List<Person> getLikes() {
		return likes;
	}

	/**
	 * @param likes
	 *            the likes to set
	 */
	public void setLikes(List<Person> likes) {
		this.likes = likes;
	}
	
	

	/**
	 * @return the replies
	 */
	public List<Tweet> getReplies() {
		return replies;
	}

	/**
	 * @param replies the replies to set
	 */
	public void setReplies(List<Tweet> replies) {
		this.replies = replies;
	}

	/**
	 * @return the reposts
	 */
	public List<Tweet> getReposts() {
		return reposts;
	}

	/**
	 * @param reposts the reposts to set
	 */
	public void setReposts(List<Tweet> reposts) {
		this.reposts = reposts;
	}

	/**
	 * @return the mentions
	 */
	public List<Person> getMentions() {
		return mentions;
	}

	/**
	 * @param mentions the mentions to set
	 */
	public void setMentions(List<Person> mentions) {
		this.mentions = mentions;
	}

	/**
	 * @return the hashtags
	 */
	public List<Hashtag> getHashtags() {
		return hashtags;
	}

	/**
	 * @param hashtags the hashtags to set
	 */
	public void setHashtags(List<Hashtag> hashtags) {
		this.hashtags = hashtags;
	}

	/**
	 * @return active
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		Tweet other = (Tweet) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

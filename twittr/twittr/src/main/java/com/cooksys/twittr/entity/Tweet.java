package com.cooksys.twittr.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Tweet {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tweet_id")
	private Integer id;

	@ManyToOne
	private Person author;

	@CreationTimestamp
	private Timestamp posted;

	private String content;

	@ManyToOne
	@JoinColumn(name = "in_reply_to_id")
	private Tweet inReplyTo;
	
	@OneToMany
	@JoinColumn(name = "reply_id")
	private List<Tweet> replies;

	@ManyToOne
	@JoinColumn(name = "repost_of_tweet_id")
	private Tweet repostOf;
	
	@OneToMany
	@JoinColumn(name = "reposted_tweet_id")
	private List<Tweet> reposts;

	@ManyToMany
	@JoinTable(name="likes_tweets",
	 joinColumns=@JoinColumn(columnDefinition="integer", name="liked_tweet_id"),
	 inverseJoinColumns=@JoinColumn(columnDefinition="integer", name="person_id"))
	private List<Person> likes;

	private Boolean active;
	
	@ManyToMany
	@JoinTable(name="mentioned_tweets",
	 joinColumns=@JoinColumn(columnDefinition="integer", name="mentioned_tweet_id"),
	 inverseJoinColumns=@JoinColumn(columnDefinition="integer", name="person_id"))
	private List<Person> mentions;
	
	@ManyToMany
	@JoinTable(name="tagged_tweets",
	 joinColumns=@JoinColumn(columnDefinition="integer", name="tagged_tweet_id"),
	 inverseJoinColumns=@JoinColumn(columnDefinition="integer", name="tag_id"))
	private List<Hashtag> hashtags;

	public Tweet() {
		super();
	}
	
	public Tweet(Integer id, Person author, Timestamp posted, String content) {
		super();
		this.id = id;
		this.author = author;
		this.posted = posted;
		this.content = content;
		this.mentions = new ArrayList<>();
		this.hashtags = new ArrayList<>();
	}
	
	public Tweet(Integer id, Person author, Timestamp posted, String content, Tweet inReplyTo, Tweet repostOf, List<Person> mentions, List<Hashtag> hashtags) {
		super();
		this.id = id;
		this.author = author;
		this.posted = posted;
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

package com.cooksys.twittr.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "hashtags")
public class Hashtag {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="hashtag_id")
	private Integer id;
	
	private String tagName;
	
	@CreationTimestamp
	private Timestamp created;
	
	@UpdateTimestamp
	private Timestamp lastTagged;
	
	@ManyToMany
	@JoinTable(name="tagged_tweets",
	 joinColumns=@JoinColumn(columnDefinition="integer", name="tag_id"),
	 inverseJoinColumns=@JoinColumn(columnDefinition="integer", name="tagged_tweet_id"))
	private List<Tweet> taggedTweets;
	
	

	public Hashtag() {
		super();
	}

	public Hashtag(Integer id, String title, List<Tweet> taggedTweets) {
		super();
		this.id = id;
		this.tagName = title;
		this.taggedTweets = taggedTweets;
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
	 * @return the title
	 */
	public String getTagName() {
		return tagName;
	}

	/**
	 * @param title the title to set
	 */
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	/**
	 * @return the taggedTweets
	 */
	public List<Tweet> getTaggedTweets() {
		return taggedTweets;
	}

	/**
	 * @param taggedTweets the taggedTweets to set
	 */
	public void setTaggedTweets(List<Tweet> taggedTweets) {
		this.taggedTweets = taggedTweets;
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
		Hashtag other = (Hashtag) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}

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
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "hashtag")
public class Hashtag {
	
	@Transient
	java.util.Date today = new java.util.Date();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="hashtag_id")
	private Integer id;
	
	private String label;
	
	private final Timestamp firstUsed = new java.sql.Timestamp(today.getTime());
	
	private Timestamp lastUsed = new java.sql.Timestamp(today.getTime());
	
	@ManyToMany(mappedBy="hashtags", cascade=CascadeType.ALL)
	private List<Tweet> taggedTweets = new ArrayList<>();
	
	

	public Hashtag() {
		super();
	}

	public Hashtag(String label) {
		super();
		this.label = label;
	}
	
	

	public Hashtag(Integer id, String label, Timestamp lastUsed, List<Tweet> taggedTweets) {
		super();
		this.id = id;
		this.label = label;
		this.lastUsed = lastUsed;
		this.taggedTweets = taggedTweets;
	}
	
	

	/**
	 * @return the today
	 */
	public java.util.Date getToday() {
		return today;
	}

	/**
	 * @param today the today to set
	 */
	public void setToday(java.util.Date today) {
		this.today = today;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the lastUsed
	 */
	public Timestamp getLastUsed() {
		return lastUsed;
	}

	/**
	 * @param lastUsed the lastUsed to set
	 */
	public void setLastUsed(Timestamp lastUsed) {
		this.lastUsed = lastUsed;
	}

	/**
	 * @return the firstUsed
	 */
	public Timestamp getFirstUsed() {
		return firstUsed;
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
		return label;
	}

	/**
	 * @param title the title to set
	 */
	public void setTagName(String label) {
		this.label = label;
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

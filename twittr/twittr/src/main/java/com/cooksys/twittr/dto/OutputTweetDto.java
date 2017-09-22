package com.cooksys.twittr.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OutputTweetDto {

	private Integer id;
	
	private OutputPersonDto author;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
	private Timestamp posted;
	
	private String content;
	
	private OutputTweetDto inReplyTo;
	
	private OutputTweetDto repostOf;

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
	 * @return the author
	 */
	public OutputPersonDto getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(OutputPersonDto author) {
		this.author = author;
	}

	/**
	 * @return the posted
	 */
	public Timestamp getPosted() {
		return posted;
	}

	/**
	 * @param posted the posted to set
	 */
	public void setPosted(Timestamp posted) {
		this.posted = posted;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the inReplyTo
	 */
	public OutputTweetDto getInReplyTo() {
		return inReplyTo;
	}

	/**
	 * @param inReplyTo the inReplyTo to set
	 */
	public void setInReplyTo(OutputTweetDto inReplyTo) {
		this.inReplyTo = inReplyTo;
	}

	/**
	 * @return the repostOf
	 */
	public OutputTweetDto getRepostOf() {
		return repostOf;
	}

	/**
	 * @param repostOf the repostOf to set
	 */
	public void setRepostOf(OutputTweetDto repostOf) {
		this.repostOf = repostOf;
	}
	
	
}

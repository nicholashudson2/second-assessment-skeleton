package com.cooksys.twittr.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.twittr.entity.Tweet;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Integer> {
	
	List<Tweet> findByActiveTrueOrderByPostedDesc();
	
	Tweet findById(Integer id);
	
	public default List<String> getMentions(String content) {
		String[] splitContents = content.split(" ");
		List<String> mentions = new ArrayList<>();
		for (String s : splitContents) {
			if(s.charAt(0) == '@') {
				mentions.add(s);
			}
		}
		return mentions;
	}
	
	public default List<String> getHashtags(String content) {
		String[] splitContents = content.split(" ");
		List<String> hashtags = new ArrayList<>();
		for (String s : splitContents) {
			if(s.charAt(0) == '#') {
				hashtags.add(s);
			}
		}
		return hashtags;
	}
}

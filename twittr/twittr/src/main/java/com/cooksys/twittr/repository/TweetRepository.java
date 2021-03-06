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

	List<Tweet> findByAuthorCredentialsUsernameAndActiveTrueOrderByPostedDesc(String username);
	
	List<Tweet> findTweetsByHashtagsLabelOrderByPostedDesc(String label);

	List<Tweet> findTweetsByMentionsAndActiveTrueOrderByPostedDesc(String username);
	
	public default List<String> getMentions(String content) {
		List<String> mentions = new ArrayList<>();
		for (String s : content.split(" ")) {
			if(s.charAt(0) == '@') {
				String result = s.replaceAll("@", "");
				mentions.add(result);
			}
		}
		return mentions;
	}
	
	public default List<String> getHashtags(String content) {
		String[] splitContents = content.split(" ");
		List<String> hashtags = new ArrayList<>();
		for (String s : splitContents) {
			if(s.charAt(0) == '#') {
				String result = s.replaceAll("#", "");
				hashtags.add(result);
			}
		}
		return hashtags;
	}


}

package com.cooksys.twittr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.twittr.entity.Hashtag;

public interface HashtagRepository extends JpaRepository<Hashtag, Integer> {

	List<Hashtag> findAllByOrderByLastUsedDesc();
	
	Hashtag findByLabel(String label);
	
	Hashtag findHashtagsByTaggedTweets(Integer taggedTweetsId);
	
}

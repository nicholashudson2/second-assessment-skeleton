package com.cooksys.twittr.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cooksys.twittr.dto.HashtagDto;
import com.cooksys.twittr.dto.OutputHashtagDto;
import com.cooksys.twittr.entity.Hashtag;
import com.cooksys.twittr.entity.Tweet;
import com.cooksys.twittr.mapper.HashtagMapper;
import com.cooksys.twittr.repository.HashtagRepository;
import com.cooksys.twittr.repository.TweetRepository;

@Service
public class HashtagService {

	private HashtagRepository hashtagRepository;
	private HashtagMapper hashtagMapper;
	private TweetRepository tweetRepository;

	public HashtagService(HashtagRepository hashtagRepository, HashtagMapper hashtagMapper, TweetRepository tweetRepository) {
		this.hashtagRepository = hashtagRepository;
		this.hashtagMapper = hashtagMapper;
		this.tweetRepository = tweetRepository;
	}
	
	public List<OutputHashtagDto> getHashtags() {
		return hashtagMapper.toDtos(hashtagRepository.findAllByOrderByLastUsedDesc());
	}
	
	@Transactional
	public Hashtag create(String tagName) {
		Hashtag modifiedHashtag = hashtagRepository.findByLabel(tagName);
		if(modifiedHashtag == null)
			modifiedHashtag = new Hashtag(tagName);
		return hashtagRepository.save(modifiedHashtag);
	}
	
	@Transactional
	public void addTweetToHashtag(HashtagDto hashtagDto, Integer tweetId) {
		Tweet tweet = tweetRepository.findById(tweetId);
		Hashtag modifiedHashtag = hashtagRepository.findByLabel(hashtagDto.getLabel());
		if (modifiedHashtag.getTaggedTweets().contains(tweet))
			modifiedHashtag.getTaggedTweets().add(tweet);
		hashtagRepository.saveAndFlush(modifiedHashtag);
	}

	public OutputHashtagDto findByLabel(String tagName) {
		return hashtagMapper.toDto(hashtagRepository.findByLabel(tagName));
	}
	
}

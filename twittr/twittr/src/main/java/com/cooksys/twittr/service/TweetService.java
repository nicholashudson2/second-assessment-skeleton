package com.cooksys.twittr.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.twittr.dto.TweetDto;
import com.cooksys.twittr.entity.Tweet;
import com.cooksys.twittr.mapper.TweetMapper;
import com.cooksys.twittr.repository.PersonRepository;
import com.cooksys.twittr.repository.TweetRepository;

@Service
public class TweetService {

	private TweetRepository tweetRepository;
	private TweetMapper tweetMapper;
	private PersonRepository personRepository;

	public TweetService(TweetRepository tweetRepository, TweetMapper tweetMapper, PersonRepository personRepository) {
		this.tweetRepository = tweetRepository;
		this.tweetMapper = tweetMapper;
		this.personRepository = personRepository;
	}

	public List<TweetDto> getTweets() {
		return tweetMapper.toDtos(tweetRepository.findByActiveTrueOrderByPostedDesc());
	}

	public TweetDto create(TweetDto tweetDto) {
		Tweet tweet = new Tweet();
//		tweetRepository.save(tweet);
		if (personRepository.validateCredentials(
				personRepository.findByCredentialsUsername(tweetDto.getCredentials().getUsername()).getCredentials(),
				tweetDto.getCredentials())) {
			tweet.setAuthor(personRepository.findByCredentialsUsername(tweetDto.getCredentials().getUsername()));
			tweet.setContent(tweetDto.getContent());
			tweet.setActive(true);
			personRepository.findByCredentialsUsername(tweetDto.getCredentials().getUsername()).getTweets().add(tweet);
		} 
		return tweetMapper.toDto(tweetRepository.save(tweet));
	}

	public TweetDto findById(Integer id) {
		return tweetMapper.toDto(tweetRepository.findById(id));
	}

}

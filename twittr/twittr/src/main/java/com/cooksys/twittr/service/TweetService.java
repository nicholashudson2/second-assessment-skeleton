package com.cooksys.twittr.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cooksys.twittr.dto.OutputHashtagDto;
import com.cooksys.twittr.dto.OutputTweetDto;
import com.cooksys.twittr.dto.TweetDto;
import com.cooksys.twittr.entity.Credentials;
import com.cooksys.twittr.entity.Tweet;
import com.cooksys.twittr.mapper.HashtagMapper;
import com.cooksys.twittr.mapper.TweetMapper;
import com.cooksys.twittr.repository.HashtagRepository;
import com.cooksys.twittr.repository.PersonRepository;
import com.cooksys.twittr.repository.TweetRepository;

@Service
public class TweetService {

	private TweetRepository tweetRepository;
	private TweetMapper tweetMapper;
	private PersonRepository personRepository;
	private HashtagService hashtagService;
	private HashtagRepository hashtagRepository;
	private HashtagMapper hashtagMapper;

	public TweetService(TweetRepository tweetRepository, TweetMapper tweetMapper, PersonRepository personRepository,
			HashtagService hashtagService, HashtagRepository hashtagRepository, HashtagMapper hashtagMapper) {
		this.tweetRepository = tweetRepository;
		this.tweetMapper = tweetMapper;
		this.personRepository = personRepository;
		this.hashtagService = hashtagService;
		this.hashtagRepository = hashtagRepository;
		this.hashtagMapper = hashtagMapper;
	}

	public List<OutputTweetDto> getTweets() {
		return tweetMapper.toDtos(tweetRepository.findByActiveTrueOrderByPostedDesc());
	}

	@Transactional
	public OutputTweetDto create(TweetDto tweetDto) {
		Tweet tweet = new Tweet();
		tweetRepository.save(tweet);
		if (personRepository.validateCredentials(
				personRepository.findByCredentialsUsername(tweetDto.getCredentials().getUsername()).getCredentials(),
				tweetDto.getCredentials())) {
			tweet.setAuthor(personRepository.findByCredentialsUsername(tweetDto.getCredentials().getUsername()));
			tweet.setContent(tweetDto.getContent());
			tweet.setActive(true);
			tweetRepository.save(tweet);
		}
		addMentionsAndHashtags(tweet.getId());
		return tweetMapper.toDto(tweetRepository.save(tweet));
	}

	public OutputTweetDto findById(Integer id, HttpServletResponse response) {
		if (tweetRepository.findById(id) == null || tweetRepository.findById(id).getActive() == false)
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return tweetMapper.toDto(tweetRepository.findById(id));
	}

	@Transactional
	public OutputTweetDto deleteTweet(Integer tweetId, Credentials credentials) {
		Tweet tweet = tweetRepository.findById(tweetId);
		if (tweet != null && personRepository.validateCredentials(personRepository
				.findByCredentialsUsername(tweet.getAuthor().getCredentials().getUsername()).getCredentials(),
				credentials)) {
			tweet.setActive(false);
			tweetRepository.save(tweet);
		}
		return tweetMapper.toDto(tweet);
	}

	public List<OutputTweetDto> findByUsernameAndActiveTrue(String username) {
		return tweetMapper
				.toDtos(tweetRepository.findByAuthorCredentialsUsernameAndActiveTrueOrderByPostedDesc(username));
	}

	@Transactional
	public boolean likeTweet(Integer tweetId, Credentials credentials, HttpServletResponse response) {
		Tweet modifiedTweet = tweetRepository.findById(tweetId);
		tweetRepository.saveAndFlush(modifiedTweet);
		Boolean valid = (personRepository.validateCredentials(
				personRepository.findByCredentialsUsername(credentials.getUsername()).getCredentials(), credentials)
				&& modifiedTweet.getActive());
		if (valid) {
			modifiedTweet.getLikes().add(personRepository.findByCredentialsUsername(credentials.getUsername()));
			tweetRepository.saveAndFlush(modifiedTweet);
		}
		return valid;
	}

	@Transactional
	public Tweet addMentionsAndHashtags(Integer tweetId) {
		Tweet modifiedTweet = tweetRepository.findById(tweetId);
		for (String s : tweetRepository.getMentions(modifiedTweet.getContent())) {
			modifiedTweet.getMentions().add(personRepository.findByCredentialsUsername(s));
		}
		for (String s : tweetRepository.getHashtags(modifiedTweet.getContent())) {
			if (hashtagService.findByLabel(s) == null)
				modifiedTweet.getHashtags().add(hashtagRepository.save(hashtagService.create(s)));
		}
		return modifiedTweet;
	}

	@Transactional
	public OutputTweetDto createReply(Integer id, TweetDto tweetDto) {
		Tweet tweet = tweetRepository.findById(create(tweetDto).getId());
		tweet.setInReplyTo(tweetRepository.findById(id));
		return tweetMapper.toDto(tweetRepository.save(tweet));
	}
	
	@Transactional
	public OutputTweetDto createRepost(Integer id, Credentials credentials) {
		Tweet tweet = new Tweet();
		tweet.setAuthor(personRepository.findByCredentialsUsername(credentials.getUsername()));
		tweet.setContent(tweetRepository.findById(id).getContent());
		tweet.setRepostOf(tweetRepository.findById(id));
		return tweetMapper.toDto(tweetRepository.save(tweet));
	}

	public List<OutputTweetDto> findTweetsByTagName(String tagName) {
		return tweetMapper.toDtos(tweetRepository.findTweetsByHashtagsLabelOrderByPostedDesc(tagName));
	}
	
	public List<OutputTweetDto> findTweetsByMentions(String username) {
		return tweetMapper.toDtos(tweetRepository.findTweetsByMentionsAndActiveTrueOrderByPostedDesc(username));
	}
	
	public List<OutputHashtagDto> findById(Integer id) {
		return hashtagMapper.toDtos(tweetRepository.findById(id).getHashtags());
	}

}

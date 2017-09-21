package com.cooksys.twittr.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.twittr.dto.OutputHashtagDto;
import com.cooksys.twittr.dto.OutputTweetDto;
import com.cooksys.twittr.dto.TweetDto;
import com.cooksys.twittr.entity.Credentials;
import com.cooksys.twittr.service.HashtagService;
import com.cooksys.twittr.service.TweetService;

@RestController
@RequestMapping
public class TweetController {

	private TweetService tweetService;
	private HashtagService hashtagService;

	public TweetController(TweetService tweetService, HashtagService hashtagService) {
		this.tweetService = tweetService;
		this.hashtagService = hashtagService;
	}
	
	@GetMapping("tags")
	public List<OutputHashtagDto> getHashtags() {
		return hashtagService.getHashtags();
	}
	
	@GetMapping("tags/{label}")
	public List<OutputTweetDto> getByTagName(@PathVariable String label) {
		return tweetService.findTweetsByTagName(label);
	}
	
	@GetMapping("tweets")
	public List<OutputTweetDto> getTweets() {
		return tweetService.getTweets();
	}
	
	@PostMapping("tweets")
	public OutputTweetDto createTweet(@RequestBody TweetDto tweet) {
		return tweetService.create(tweet);
	}
	
	@GetMapping("tweets/{id}")
	public OutputTweetDto getTweetById(@PathVariable Integer id) {
		return tweetService.findById(id);
	}
	
	@DeleteMapping("tweets/{id}")
	public OutputTweetDto deleteTweet(@PathVariable Integer id, @RequestBody Credentials credentials) {
		return tweetService.deleteTweet(id, credentials);
	}
	
	@PostMapping("tweets/{id}/like")
	public void likeTweet(@PathVariable Integer id, @RequestBody Credentials credentials, HttpServletResponse response) {
		tweetService.likeTweet(id, credentials, response);
	}
}

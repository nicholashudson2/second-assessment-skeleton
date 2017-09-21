package com.cooksys.twittr.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.twittr.dto.TweetDto;
import com.cooksys.twittr.service.TweetService;

@RestController
@RequestMapping
public class TweetController {

	private TweetService tweetService;

	public TweetController(TweetService tweetService) {
		this.tweetService = tweetService;
	}
	
	@GetMapping("tweets")
	public List<TweetDto> getTweets() {
		return tweetService.getTweets();
	}
	
	@PostMapping("tweets")
	public TweetDto createTweet(@RequestBody TweetDto tweet) {
		return tweetService.create(tweet);
	}
	
	@GetMapping("tweets/{id}")
	public TweetDto getTweetById(@PathVariable Integer id) {
		return tweetService.findById(id);
	}
}

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
import com.cooksys.twittr.dto.OutputPersonDto;
import com.cooksys.twittr.dto.OutputTweetDto;
import com.cooksys.twittr.dto.TweetDto;
import com.cooksys.twittr.entity.Credentials;
import com.cooksys.twittr.service.HashtagService;
import com.cooksys.twittr.service.PersonService;
import com.cooksys.twittr.service.TweetService;

@RestController
@RequestMapping
public class TweetController {

	private TweetService tweetService;
	private HashtagService hashtagService;
	private PersonService personService;

	public TweetController(TweetService tweetService, HashtagService hashtagService, PersonService personService) {
		this.tweetService = tweetService;
		this.hashtagService = hashtagService;
		this.personService = personService;
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
	public OutputTweetDto getTweetById(@PathVariable Integer id, HttpServletResponse response) {
		return tweetService.findById(id, response);
	}
	
	@DeleteMapping("tweets/{id}")
	public OutputTweetDto deleteTweet(@PathVariable Integer id, @RequestBody Credentials credentials) {
		return tweetService.deleteTweet(id, credentials);
	}
	
	@PostMapping("tweets/{id}/like")
	public void likeTweet(@PathVariable Integer id, @RequestBody Credentials credentials, HttpServletResponse response) {
		tweetService.likeTweet(id, credentials, response);
	}
	
	@PostMapping("tweets/{id}/reply")
	public OutputTweetDto createReply(@PathVariable Integer id, @RequestBody TweetDto tweetDto, HttpServletResponse response) {
		if(!personService.validateCredentials(tweetDto.getCredentials()))
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		return tweetService.createReply(id, tweetDto);
	}
	
	@PostMapping("tweets/{id}/repost")
	public OutputTweetDto createRepost(@PathVariable Integer id, @RequestBody Credentials credentials, HttpServletResponse response) {
		if(!personService.validateCredentials(credentials))
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		return tweetService.createRepost(id, credentials);
	}
	
	@GetMapping("tweets/{id}/tags")
	public List<OutputHashtagDto> getHashtags(@PathVariable Integer id, HttpServletResponse response) {
		return tweetService.findById(id);
	}
	
	@GetMapping("tweets/{id}/likes")
	public List<OutputPersonDto> getTweetLikes(@PathVariable Integer id, HttpServletResponse response) {
		return tweetService.getTweetLikes(id, response);
	}
	
	@GetMapping("tweets/{id}/replies")
	public List<OutputTweetDto> getTweetReplies(@PathVariable Integer id, HttpServletResponse response) {
		return tweetService.getTweetReplies(id, response);
	}
	
	@GetMapping("tweets/{id}/reposts")
	public List<OutputTweetDto> getTweetReposts(@PathVariable Integer id, HttpServletResponse response) {
		return tweetService.getTweetReposts(id, response);
	}
	
	@GetMapping("tweets/{id}/mentions")
	public List<OutputPersonDto> getTweetMentions(@PathVariable Integer id, HttpServletResponse response) {
		return tweetService.getTweetMentions(id, response);
	}
}

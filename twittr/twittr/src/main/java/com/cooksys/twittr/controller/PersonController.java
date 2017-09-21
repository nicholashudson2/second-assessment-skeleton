package com.cooksys.twittr.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.twittr.dto.OutputPersonDto;
import com.cooksys.twittr.dto.OutputTweetDto;
import com.cooksys.twittr.dto.PersonDto;
import com.cooksys.twittr.entity.Credentials;
import com.cooksys.twittr.service.PersonService;
import com.cooksys.twittr.service.TweetService;

@RestController
@RequestMapping
public class PersonController {

	private PersonService personService;
	private TweetService tweetService;

	public PersonController(PersonService personService, TweetService tweetService) {
		this.personService = personService;
		this.tweetService = tweetService;
	}

	@GetMapping("users")
	public List<OutputPersonDto> users() {
		return personService.getActiveUsers();
	}

	@PostMapping("users")
	public OutputPersonDto createPerson(@RequestBody PersonDto person) {
		personService.create(person);
		return personService.findByUsername(person.getCredentials().getUsername());
	}

	@DeleteMapping("users/@{username}")
	public OutputPersonDto deleteByUsername(@PathVariable String username, @RequestBody Credentials credentials, HttpServletResponse response) {
		if (!validateExistingUsername(username) || !personService.validateCredentials(credentials))
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		return personService.deactivateUser(username, credentials);

	}

	@PatchMapping("users/@{username}")
	public OutputPersonDto updateUser(@PathVariable String username, @RequestBody PersonDto person, HttpServletResponse response) {
		if (!validateExistingUsername(username) || !personService.validateCredentials(person.getCredentials()))
			response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
		return personService.updateUser(username, person);
	}

	@GetMapping("users/@{username}")
	public OutputPersonDto findByUsername(@PathVariable String username, HttpServletResponse response) {
		return personService.findByUsername(username);
	}

	@GetMapping("validate/username/exists/@{username}")
	public Boolean validateExistingUsername(@PathVariable String username) {
		return (personService.findByUsername(username) != null && personService.checkIfActive(username)) ? true : false;
	}

	@GetMapping("validate/username/available/@{username}")
	public Boolean validateAvailableUsername(@PathVariable String username) {
		return (personService.findByUsername(username) != null) ? false : true;
	}

	@PostMapping("users/@{username}/follow")
	public void followUser(@PathVariable String username, @RequestBody Credentials credentials, HttpServletResponse response) {
		if (!validateExistingUsername(username) || !personService.validateCredentials(credentials))
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		personService.followUser(username, credentials);
	}

	@PostMapping("users/@{username}/unfollow")
	public void unfollowUser(@PathVariable String username, @RequestBody Credentials credentials, HttpServletResponse response) {
		if (!validateExistingUsername(username) || !personService.validateCredentials(credentials) || !username.equals(credentials.getUsername())) 
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		personService.unfollowUser(username, credentials);
	}

	@GetMapping("users/@{username}/tweets")
	public List<OutputTweetDto> getTweetsByAuthor(@PathVariable String username) {
		return tweetService.findByUsernameAndActiveTrue(username);
	}

	@GetMapping("users/@{username}/followers")
	public List<OutputPersonDto> getFollowers(@PathVariable String username, HttpServletResponse response) {
		List<OutputPersonDto> followers = personService.findFollowers(username);
		if (followers.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		} 
		return followers;
	}

	@GetMapping("users/@{username}/following")
	public List<OutputPersonDto> getFollowing(@PathVariable String username, HttpServletResponse response) {
		List<OutputPersonDto> following = personService.findFollowing(username);
		if (following.isEmpty())
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		return following;
	}

}

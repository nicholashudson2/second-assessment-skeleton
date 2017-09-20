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

import com.cooksys.twittr.dto.PersonDto;
import com.cooksys.twittr.entity.Credentials;
import com.cooksys.twittr.service.PersonService;

@RestController
@RequestMapping
public class PersonController {

	private PersonService personService;

	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping("users")
	public List<PersonDto> users() {
		return personService.getActiveUsers();
	}

	@PostMapping("users")
	public PersonDto createPerson(@RequestBody PersonDto person) {
		return personService.create(person);
	}

	@DeleteMapping("users/@{username}")
	public PersonDto deleteByUsername(@PathVariable String username, @RequestBody Credentials credentials) {
		return personService.deactivateUser(username, credentials);
	}

	@PatchMapping("users/@{username}")
	public PersonDto updateUser(@PathVariable String username, @RequestBody PersonDto person) {
		return personService.updateUser(username, person);
	}

	@GetMapping("users/@{username}")
	public PersonDto findByUsername(@PathVariable String username) {
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
		if (validateExistingUsername(username) && personService.validateCredentials(credentials)) {
			personService.followUser(username, credentials);
		} else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
	}

	@PostMapping("users/@{username}/unfollow")
	public void unfollowUser(@PathVariable String username, @RequestBody Credentials credentials,
			HttpServletResponse response) {
		if (validateExistingUsername(username) && personService.validateCredentials(credentials)) {
			personService.unfollowUser(username, credentials);
		} else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
	}
	
	@GetMapping("users/@{username}/followers")
	public List<PersonDto> getFollowers(@PathVariable String username, HttpServletResponse response) {
		List<PersonDto> followers = personService.findFollowers(username);
		if(followers.isEmpty())
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		return followers;
	}
	
	@GetMapping("users/@{username}/following")
	public List<PersonDto> getFollowing(@PathVariable String username, HttpServletResponse response) {
		List<PersonDto> following = personService.findFollowing(username);
		if(following.isEmpty())
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		return following;
	}

}

package com.cooksys.twittr.controller;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.twittr.dto.PersonDto;
import com.cooksys.twittr.service.PersonService;

@RestController
@RequestMapping
public class PersonController {

	private PersonService personService;
	
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	@GetMapping("users")
	@Query("GET * FROM User WHERE isActive = true")
	public List<PersonDto> users() {
		return personService.getActiveUsers();
	}
	
	@PostMapping("users")
	public PersonDto createPerson(@RequestBody PersonDto person) {
		return personService.create(person);
	}
	
	@DeleteMapping("users/@{username}")
	public PersonDto deleteByUsername(@PathVariable String username, @PathVariable String password) {
		if(personService.findByUsername(username) != null) {
			
		}
	}
	
	@GetMapping("users/@{username}")
	public PersonDto findByUsername(@PathVariable String username) {
		return personService.findByUsername(username);
	}
	
	@GetMapping("validate/username/exists/@{username}")
	public Boolean validateExistingUsername(@PathVariable String username) {
		return (personService.findByUsername(username) != null) ? true : false;
	}
	
	@GetMapping("validate/username/available/@{username}")
	public Boolean validateAvailableUsername(@PathVariable String username) {
		return (personService.findByUsername(username) != null) ? false : true;
	}
	
	
	
}

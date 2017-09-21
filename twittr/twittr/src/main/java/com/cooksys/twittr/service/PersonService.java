package com.cooksys.twittr.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cooksys.twittr.dto.OutputPersonDto;
import com.cooksys.twittr.dto.PersonDto;
import com.cooksys.twittr.entity.Credentials;
import com.cooksys.twittr.entity.Person;
import com.cooksys.twittr.mapper.PersonMapper;
import com.cooksys.twittr.repository.PersonRepository;

@Service
public class PersonService {

	private PersonRepository personRepository;
	private PersonMapper personMapper;

	public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
		this.personRepository = personRepository;
		this.personMapper = personMapper;
	}

	public List<OutputPersonDto> getActiveUsers() {
		return personMapper.toOutputDtos(personRepository.findByActiveTrue());
	}

	public Boolean validateCredentials(Credentials credentials) {
		return personRepository.validateCredentials(
			personRepository.findByCredentialsUsername(credentials.getUsername()).getCredentials(), credentials);
	}

	public OutputPersonDto findByUsername(String username) {
		return personMapper.toOutputDto(personRepository.findByCredentialsUsername(username));
	}

	@Transactional
	public OutputPersonDto create(PersonDto person) {
		Person modifiedUser = personRepository.findByCredentialsUsername(person.getCredentials().getUsername());
		if (modifiedUser != null) {
			if (modifiedUser.getActive() == false) {
				modifiedUser.setActive(true);
				modifiedUser.setUsername(modifiedUser.getCredentials().getUsername());
				personRepository.save(modifiedUser);
			}
		} else {
			personRepository.save(personMapper.fromDto(person));
		}
		return findByUsername(person.getCredentials().getUsername());
	}

	@Transactional
	public OutputPersonDto deactivateUser(String username, Credentials credentials) {
		Person modifiedUser = personRepository.findByCredentialsUsername(credentials.getUsername());
		if (modifiedUser != null && personRepository.validateCredentials(personRepository.findByCredentialsUsername(username).getCredentials(), credentials)) {
			modifiedUser.setActive(false);
			personRepository.save(modifiedUser);
		}
		return personMapper.toOutputDto(modifiedUser);
	}

	public Boolean checkIfActive(String username) {
		return (personRepository.findByCredentialsUsername(username)).getActive();
	}

	@Transactional
	public OutputPersonDto reactivateUser(String username, Credentials credentials) {
		Person modifiedUser = personRepository.findByCredentialsUsername(credentials.getUsername());
		if (modifiedUser != null && personRepository.validateCredentials(
				personRepository.findByCredentialsUsername(username).getCredentials(), credentials)) {
			modifiedUser.setActive(true);
			personRepository.save(modifiedUser);
		}
		return personMapper.toOutputDto(modifiedUser);
	}

	@Transactional
	public OutputPersonDto updateUser(String username, PersonDto person) {
		Person modifiedUser = personRepository.findByCredentialsUsername(username);
		if (modifiedUser != null) {
			if (personRepository.validateCredentials(personRepository.findByCredentialsUsername(username).getCredentials(), person.getCredentials())) {
				if (modifiedUser.getActive()) {
					modifiedUser.setProfile(person.getProfile());
				}
				modifiedUser.setActive(true);
				modifiedUser.setProfile(person.getProfile());
			}
			personRepository.save(modifiedUser);
		}
		return personMapper.toOutputDto(modifiedUser);
	}

	@Transactional
	public void followUser(String username, Credentials credentials) {
		Person personFollowing = personRepository.findByCredentialsUsername(credentials.getUsername());
		Person personFollowed = personRepository.findByCredentialsUsername(username);
		if(!personFollowing.getFollowing().contains(personFollowed) && personRepository.validateCredentials(personFollowing.getCredentials(), credentials))
			personFollowing.getFollowing().add(personFollowed);
		personRepository.save(personFollowing);
	}

	@Transactional
	public void unfollowUser(String username, Credentials credentials) {
		Person personFollowing = personRepository.findByCredentialsUsername(credentials.getUsername());
		Person personFollowed = personRepository.findByCredentialsUsername(username);
		if(personFollowing.getFollowing().contains(personFollowed) && personRepository.validateCredentials(personFollowing.getCredentials(), credentials))
			personFollowing.getFollowing().remove(personFollowed);
		personRepository.save(personFollowing);
	}
	
	public List<OutputPersonDto> findFollowers(String username) {
		List<OutputPersonDto> followers = new ArrayList<>();
		for(Person p : personRepository.findByCredentialsUsername(username).getFollowers()) {
			if(p.getActive())
				followers.add(personMapper.toOutputDto(p));
		}
		return followers;
	}
	
	public List<OutputPersonDto> findFollowing(String username) {
		List<OutputPersonDto> following = new ArrayList<>();
		for(Person p : personRepository.findByCredentialsUsername(username).getFollowing()) {
			if(p.getActive())
				following.add(personMapper.toOutputDto(p));
		}
		return following;
	}

}

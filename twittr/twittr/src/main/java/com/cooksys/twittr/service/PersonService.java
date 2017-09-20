package com.cooksys.twittr.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.twittr.dto.PersonDto;
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

	public List<PersonDto> getActiveUsers() {
		return personMapper.toDtos(personRepository.findByActiveTrue());
	}

	public PersonDto findByUsername(String username) {
		return personMapper.toDto(personRepository.findByCredentialsUsername(username));
	}

	public PersonDto create(PersonDto person) {
		return personMapper.toDto(personRepository.save(personMapper.fromDto(person)));
	}
	
	
	
}

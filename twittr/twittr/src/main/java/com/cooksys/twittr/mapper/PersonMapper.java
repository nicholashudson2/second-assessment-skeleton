package com.cooksys.twittr.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.twittr.dto.PersonDto;
import com.cooksys.twittr.entity.Person;

@Mapper(componentModel="spring")
public interface PersonMapper {

	Person fromDto(PersonDto dto);
	
	PersonDto toDto(Person user);
	
	List<Person> fromDtos(List<PersonDto> dtos);
	
	List<PersonDto> toDtos(List<Person> users);
}

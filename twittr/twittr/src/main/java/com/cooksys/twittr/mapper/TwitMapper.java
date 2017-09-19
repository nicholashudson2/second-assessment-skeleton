package com.cooksys.twittr.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.twittr.dto.TwitDto;
import com.cooksys.twittr.entity.Twit;

@Mapper(componentModel="spring")
public interface TwitMapper {

	Twit fromDto(TwitDto dto);
	
	TwitDto toDto(Twit user);
	
	List<Twit> fromDtos(List<TwitDto> dtos);
	
	List<TwitDto> toDtos(List<Twit> users);

}

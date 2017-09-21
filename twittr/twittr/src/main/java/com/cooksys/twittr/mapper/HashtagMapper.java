package com.cooksys.twittr.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.twittr.dto.HashtagDto;
import com.cooksys.twittr.entity.Hashtag;

@Mapper(componentModel="spring")
public interface HashtagMapper {

	Hashtag fromDto(HashtagDto dto);
	
	HashtagDto toDto(Hashtag user);
	
	List<Hashtag> fromDtos(List<HashtagDto> dtos);
	
	List<HashtagDto> toDtos(List<Hashtag> users);
	
}

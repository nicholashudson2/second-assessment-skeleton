package com.cooksys.twittr.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.twittr.dto.HashtagDto;
import com.cooksys.twittr.dto.OutputHashtagDto;
import com.cooksys.twittr.entity.Hashtag;

@Mapper(componentModel="spring")
public interface HashtagMapper {

	Hashtag fromDto(HashtagDto dto);
	
	OutputHashtagDto toDto(Hashtag hashtag);
	
	Hashtag fromOutputDto(OutputHashtagDto dto);
	
	List<Hashtag> fromDtos(List<HashtagDto> dtos);
	
	List<OutputHashtagDto> toDtos(List<Hashtag> hashtags);
	
}

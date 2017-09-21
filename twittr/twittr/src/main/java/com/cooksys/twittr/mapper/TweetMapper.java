package com.cooksys.twittr.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.twittr.dto.TweetDto;
import com.cooksys.twittr.entity.Tweet;

@Mapper(componentModel="spring")
public interface TweetMapper {

	Tweet fromDto(TweetDto dto);
	
	TweetDto toDto(Tweet tweet);
	
	List<Tweet> fromDtos(List<TweetDto> dtos);
	
	List<TweetDto> toDtos(List<Tweet> tweets);
	
}

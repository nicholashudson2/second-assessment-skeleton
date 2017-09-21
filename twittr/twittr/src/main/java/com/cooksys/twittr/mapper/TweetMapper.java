package com.cooksys.twittr.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.twittr.dto.OutputTweetDto;
import com.cooksys.twittr.dto.TweetDto;
import com.cooksys.twittr.entity.Tweet;

@Mapper(componentModel="spring")
public interface TweetMapper {

	Tweet fromDto(TweetDto dto);
	
	OutputTweetDto toDto(Tweet tweet);
	
	List<Tweet> fromDtos(List<TweetDto> dtos);
	
	List<OutputTweetDto> toDtos(List<Tweet> tweets);
	
}

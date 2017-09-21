package com.cooksys.twittr.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.twittr.dto.HashtagDto;
import com.cooksys.twittr.mapper.HashtagMapper;
import com.cooksys.twittr.repository.HashtagRepository;

@Service
public class HashtagService {

	private HashtagRepository hashtagRepository;
	private HashtagMapper hashtagMapper;

	public HashtagService(HashtagRepository hashtagRepository, HashtagMapper hashtagMapper) {
		this.hashtagRepository = hashtagRepository;
		this.hashtagMapper = hashtagMapper;
	}
	
	public List<HashtagDto> getHashtags() {
		return hashtagMapper.toDtos(hashtagRepository.findAllByOrderByLastTaggedDesc());
	}
}

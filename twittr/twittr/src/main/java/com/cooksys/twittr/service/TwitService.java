package com.cooksys.twittr.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.twittr.dto.TwitDto;
import com.cooksys.twittr.entity.Twit;
import com.cooksys.twittr.mapper.TwitMapper;
import com.cooksys.twittr.repository.TwitRepository;

@Service
public class TwitService {

	private TwitRepository userRepository;
	private TwitMapper userMapper;
	public List<Twit> getActiveUsers;
	
	public TwitService(TwitRepository userRepository, TwitMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	public List<TwitDto> getActiveUsers() {
		return userMapper.toDtos(userRepository.getActiveUsers());
	}

	public TwitDto get(String username) {
		return userMapper.toDto(userRepository.get(username));
	}

	public TwitDto create(TwitDto user) {
		return userMapper.toDto(userRepository.create(userMapper.fromDto(user)));
	}

}

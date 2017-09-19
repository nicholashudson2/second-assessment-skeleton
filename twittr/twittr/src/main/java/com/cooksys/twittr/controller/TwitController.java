package com.cooksys.twittr.controller;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.twittr.entity.Twit;
import com.cooksys.twittr.service.TwitService;

@RestController
@RequestMapping("user")
public class TwitController {

	private TwitService userService;
	
	public TwitController(TwitService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	@Query("SELECT * FROM User WHERE User.isActive = true")
	public List<Twit> users() {
		return userService.getActiveUsers;
	}
}

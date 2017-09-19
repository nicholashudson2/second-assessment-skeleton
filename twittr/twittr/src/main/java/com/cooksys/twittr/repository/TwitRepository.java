package com.cooksys.twittr.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.cooksys.twittr.entity.Twit;
import com.cooksys.twittr.repository.TwitRepository;

@Repository
public class TwitRepository {

	private EntityManager entityManager;
	
	public TwitRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<Twit> getAllUsers() {
		return entityManager.createQuery("FROM User", Twit.class).getResultList();
	}
	
	public List<Twit> getActiveUsers() {
		return entityManager.createQuery("FROM User", Twit.class).getResultList();
	}
	
	public Twit get(String username) {
		return entityManager.find(Twit.class, username);
	}
	
	@Transactional
	public Twit create(Twit user) {
		entityManager.persist(user);
		return user;
	}
	
}

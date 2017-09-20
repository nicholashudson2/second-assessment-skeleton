package com.cooksys.twittr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.twittr.entity.Credentials;
import com.cooksys.twittr.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

	Person findByCredentialsUsername(String username);
	
	List<Person> findByActiveTrue();
	
	public default Boolean validateCredentials(Credentials validate, Credentials credentials) {
		return credentials.getPassword().equals(validate.getPassword());
	}
	
}

//public PersonRepository(EntityManager entityManager) {
//	this.entityManager = entityManager;
//}
//
//public List<Person> getAllUsers() {
//	return entityManager.createQuery("FROM User", Person.class).getResultList();
//}
//
//public List<Person> getActiveUsers() {
//	List<Person> activeUsers = entityManager.createQuery("SELECT a FROM person where a.active = true").getResultList();
//	return activeUsers;
//}
//
//public Person get(String username) {
//	return (Person)entityManager.createQuery("SELECT * FROM Person where username = :username").getSingleResult();
//}
//
//@Transactional
//public Person create(Person user) {
//	entityManager.persist(user);
//	return user;
//}

package com.example.jmsintegration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.jmsintegration.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query(" from User u where u.name=?1")
	User findByName(String name);
}

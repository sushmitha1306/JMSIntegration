package com.example.jmsintegration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.jmsintegration.entity.UserDTO;

public interface UserRepository extends JpaRepository<UserDTO, Long> {

	@Query(" from UserDTO u where u.name=?1")
	UserDTO findByName(String name);
}

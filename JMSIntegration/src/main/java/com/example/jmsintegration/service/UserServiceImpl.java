package com.example.jmsintegration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.jmsintegration.entity.User;
import com.example.jmsintegration.repository.UserRepository;
@Component
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repo;
	
	@Override
	public User addUser(User user) {
		return repo.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return repo.findAll();
	}

	@Override
	public User updateUser(User user) {
		return repo.save(user);
	}

	@Override
	public void deleteUser(String name) {
		User user=repo.findByName(name);
		repo.delete(user);
	}

	@Override
	public User findUser(String name) {
	    return repo.findByName(name);	
	}
	

}

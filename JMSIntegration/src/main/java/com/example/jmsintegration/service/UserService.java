package com.example.jmsintegration.service;

import java.util.List;

import com.example.jmsintegration.entity.User;

public interface UserService {

	public User addUser(User user);
	
	public List<User> getAllUsers();
	
	public User updateUser(User user);
	
	public void deleteUser(String name);

	public User findUser(String name);
}

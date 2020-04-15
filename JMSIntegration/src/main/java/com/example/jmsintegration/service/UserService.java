package com.example.jmsintegration.service;

import java.util.List;

import com.example.jmsintegration.entity.User;
import com.example.jmsintegration.entity.UserDTO;

public interface UserService {

	public User addUser(UserDTO user);
	
	public List<User> getAllUsers();
	
	public User updateUser(UserDTO user, String name);
	
	public void deleteUser(String name);

	public User findUser(String name);
}

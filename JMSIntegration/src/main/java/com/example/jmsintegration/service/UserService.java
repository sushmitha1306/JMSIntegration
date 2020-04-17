package com.example.jmsintegration.service;

import java.util.List;

import com.example.jmsintegration.entity.UserDTO;

public interface UserService {

	public UserDTO addUser(UserDTO user);
	
	public List<UserDTO> getAllUsers();
	
	public UserDTO updateUser(UserDTO user, String name);
	
	public void deleteUser(String name);

	public UserDTO findUser(String name);
}

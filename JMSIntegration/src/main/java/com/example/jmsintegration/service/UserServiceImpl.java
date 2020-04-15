package com.example.jmsintegration.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.jmsintegration.entity.UserDTO;
import com.example.jmsintegration.repository.UserRepository;
@Component
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repo;

	@Override
	public List<UserDTO> getAllUsers() {
		return repo.findAll();
	}

	@Override
	public UserDTO updateUser(UserDTO u1) {
   		return repo.save(u1);
	}

	@Override
	public void deleteUser(String name) {
		UserDTO user=repo.findByName(name);
		repo.delete(user);
	}

	@Override
	public UserDTO findUser(String name) {
	    return repo.findByName(name);	
	}

	@Override
	public UserDTO addUser(UserDTO user) {
		return repo.save(user);
	}
	

}

package com.example.jmsintegration.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.jmsintegration.entity.User;
import com.example.jmsintegration.entity.UserDTO;
import com.example.jmsintegration.repository.UserRepository;
@Component
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repo;
	
	@Autowired
    private ModelMapper modelMapper;


	@Override
	public List<User> getAllUsers() {
		return repo.findAll();
	}

	@Override
	public User updateUser(UserDTO user,String name) {
		User u1=repo.findByName(name);
		User u=modelMapper.map(user, User.class);
        u1.setPassword(u.getPassword());
        u1.setAddress(u.getAddress());
		return repo.save(u1);
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

	@Override
	public User addUser(UserDTO user) {
		User u=modelMapper.map(user, User.class);
		return repo.save(u);
	}
	

}

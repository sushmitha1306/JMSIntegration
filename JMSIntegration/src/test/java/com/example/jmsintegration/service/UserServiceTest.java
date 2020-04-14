package com.example.jmsintegration.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jmsintegration.entity.Address;
import com.example.jmsintegration.entity.User;
import com.example.jmsintegration.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceTest {

	@InjectMocks
	UserServiceImpl service;
	
	@Mock
	UserRepository repo;
	
	@Test
	public void getAllUsersTest() {
		List<User> users=new ArrayList<User>();
		Address a1=new Address("Hyderabad","Bangalore");
		Address a2=new Address("Chennai","Bangalore");
		Set<Address> s1=new HashSet<>();
		s1.add(a1);
		s1.add(a2);
		User u1=new User("smith@gmail.com","smith123","smith","student",s1);
		User u2=new User("john@gmail.com","john123","John","teacher",s1);
		users.add(u1);
		users.add(u2);
		when(repo.findAll()).thenReturn(users);
		List<User> list=repo.findAll();
		assertEquals(2,list.size());
	}
	
	@Test
	public void createUserTest() {
		Address a1=new Address("Hyderabad","Bangalore");
		Address a2=new Address("Chennai","Bangalore");
		Set<Address> s1=new HashSet<>();
		s1.add(a1);
		s1.add(a2);
		User u1=new User("smith@gmail.com","smith123","smith","student",s1);
		repo.save(u1);
		verify(repo, times(1)).save(u1);
	}

}

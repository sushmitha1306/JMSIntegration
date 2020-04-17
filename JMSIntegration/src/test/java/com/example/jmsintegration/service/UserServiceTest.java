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
import com.example.jmsintegration.entity.UserDTO;
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
		List<UserDTO> users=new ArrayList<UserDTO>();
		Address a1=new Address(1,"Home","southblock");
		Address a2=new Address(2,"office","southblock");
		Set<Address> s1=new HashSet<>();
		s1.add(a1);
		s1.add(a2);
		UserDTO u1=new UserDTO("smith@gmail.com","smith123","smith","student",s1);
		UserDTO u2=new UserDTO("john@gmail.com","john123","John","teacher",s1);
		users.add(u1);
		users.add(u2);
		when(repo.findAll()).thenReturn(users);
		List<UserDTO> list=repo.findAll();
		assertEquals(2,list.size());
	}
	
	@Test
	public void createUserTest() {
		
		Address a1=new Address(3,"Home","southblock");
		Address a2=new Address(4,"office","southblock");
		Set<Address> s1=new HashSet<>();
		s1.add(a1);
		s1.add(a2);
		UserDTO u1=new UserDTO("smith@gmail.com","smith123","smith","student",s1);
		repo.save(u1);
		verify(repo, times(1)).save(u1);
	}
	
	@Test
	public void deleteUserTest() {
		Address a1=new Address(1,"Home","southblock");
		Address a2=new Address(2,"office","southblock");
		Set<Address> s1=new HashSet<>();
		Set<Address> s2=new HashSet<>();
		s1.add(a1);
		s2.add(a2);
		UserDTO u1=new UserDTO("smith@gmail.com","smith123","smith","student",s1);
		UserDTO u2=new UserDTO("john@gmail.com","john123","John","teacher",s2);
        repo.save(u1);
		repo.save(u2);
		repo.delete(u2);
		verify(repo, times(1)).save(u1);
		
	}
	    

}

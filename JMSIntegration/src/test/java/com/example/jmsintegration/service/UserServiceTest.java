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

import com.example.jmsintegration.entity.AddressDTO;
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
		AddressDTO a1=new AddressDTO(1,"Home","southblock");
		AddressDTO a2=new AddressDTO(2,"office","southblock");
		Set<AddressDTO> s1=new HashSet<>();
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
		AddressDTO a1=new AddressDTO(3,"Home","southblock");
		AddressDTO a2=new AddressDTO(4,"office","southblock");
		Set<AddressDTO> s1=new HashSet<>();
		s1.add(a1);
		s1.add(a2);
		UserDTO u1=new UserDTO("smith@gmail.com","smith123","smith","student",s1);
		repo.save(u1);
		verify(repo, times(1)).save(u1);
	}

}

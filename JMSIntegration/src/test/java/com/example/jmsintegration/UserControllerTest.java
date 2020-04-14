package com.example.jmsintegration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.jmsintegration.entity.Address;
import com.example.jmsintegration.entity.User;
import com.example.jmsintegration.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {

	@MockBean
	UserService service;
	@Autowired
	private MockMvc mvc;
	
	
	@Test
	public void createUserTest() throws Exception {
		Address a1=new Address("Hyderabad","Bangalore");
		Set<Address> s1=new HashSet<>();
		s1.add(a1);
		User u1=new User("smith@gmail.com","smith123","smith","student",s1);
		String inputJson=this.mapToJson(u1);
		System.out.println(inputJson);
		Mockito.when(service.addUser(Mockito.any(User.class))).thenReturn(u1);
		RequestBuilder req= MockMvcRequestBuilders.get("/users");
		MvcResult result=mvc.perform(req).andExpect(status().isOk()).andReturn();
		assertEquals(false,result.getResponse().getContentAsString().isEmpty());
	}
	
	private String mapToJson(Object object)throws JsonProcessingException {
		ObjectMapper omap=new ObjectMapper();
		return omap.writeValueAsString(object);
	}

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
		when(service.getAllUsers()).thenReturn(Stream.of(new User("smith@gmail.com","smith123","smith","student",s1),new User("john@gmail.com","john123","John","teacher",s1)).collect(Collectors.toList()));
		assertEquals(2,service.getAllUsers().size());

	}
	
	@Test
	public void deleteUserTest() throws Exception {
		RequestBuilder req= MockMvcRequestBuilders.get("/users/smith").accept(MediaType.TEXT_PLAIN);
		mvc.perform(req).andExpect(status().isMethodNotAllowed()).andReturn();
		
	}

}

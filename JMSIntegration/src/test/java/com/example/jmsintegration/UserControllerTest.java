package com.example.jmsintegration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.jmsintegration.entity.Address;
import com.example.jmsintegration.entity.UserDTO;
import com.example.jmsintegration.service.KafkaConsumer;
import com.example.jmsintegration.service.KafkaSender;
import com.example.jmsintegration.service.UserService;
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
@DirtiesContext
@EmbeddedKafka(partitions = 1,
    topics = {UserControllerTest.HELLOWORLD_TOPIC})

public class UserControllerTest {

	static final String HELLOWORLD_TOPIC = "exam";

	@MockBean
	UserService service;

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	KafkaSender kafkaSender;
	@MockBean
	KafkaConsumer consumer;
	
	public UserControllerTest() {
		
	}
	@Test
	public void createUserTest() throws Exception {
		Address a2=new Address(1,"home","southblock");
		Set<Address> s2=new HashSet<>();
		s2.add(a2);
        UserDTO u2=new UserDTO("smith@gmail.com","smith123","smith","student",s2);
		Mockito.when(service.addUser(u2)).thenReturn(u2);
		RequestBuilder req= MockMvcRequestBuilders.get("/users");
		MvcResult result=mvc.perform(req).andExpect(status().isOk()).andReturn();
		assertEquals(false,result.getResponse().getContentAsString().isEmpty());
	}
	
	@Test
	public void getAllUsersTest() {
		List<UserDTO> users=new ArrayList<UserDTO>();
		Address a1=new Address(2,"home","northblock");
		Address a2=new Address(3,"office","southblock");
		Set<Address> s1=new HashSet<>();
		s1.add(a1);
		s1.add(a2);
		UserDTO u1=new UserDTO("smith@gmail.com","smith123","smith","student",s1);
		UserDTO u2=new UserDTO("john@gmail.com","john123","John","teacher",s1);
		users.add(u1);
		users.add(u2);
		when(service.getAllUsers()).thenReturn(Stream.of(new UserDTO("smith@gmail.com","smith123","smith","student",s1),new UserDTO("john@gmail.com","john123","John","teacher",s1)).collect(Collectors.toList()));
		assertEquals(2,service.getAllUsers().size());

	}
	
	@Test
	public void deleteUserTest() throws Exception {
		RequestBuilder req= MockMvcRequestBuilders.get("/users/smith").accept(MediaType.TEXT_PLAIN);
		mvc.perform(req).andExpect(status().isMethodNotAllowed()).andReturn();
		
	}


}

package com.example.jmsintegration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.jmsintegration.entity.Address;
import com.example.jmsintegration.entity.Exam;
import com.example.jmsintegration.entity.User;
import com.example.jmsintegration.service.KafkaConsumer;
import com.example.jmsintegration.service.KafkaSender;
import com.example.jmsintegration.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
@DirtiesContext
@EmbeddedKafka(partitions = 1,
    topics = {UserControllerTest.HELLOWORLD_TOPIC})
class UserControllerTest {

	static final String HELLOWORLD_TOPIC = "exam";
	@MockBean
	UserService service;
	@Autowired
	private MockMvc mvc;
	@MockBean
	KafkaSender kafkaSender;
	@MockBean
	KafkaConsumer consumer;
	
	@Test
	public void testReceive() throws Exception {
//	    kafkaSender.send(new Exam("java","www.java.com"));
//	    consumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
//	    assertThat(consumer.getLatch().getCount()).isEqualTo(0);
	  }

	@Test
	public void createUserTest() throws Exception {
		Address a1=new Address("Hyderabad","Bangalore");
		Set<Address> s1=new HashSet<>();
		s1.add(a1);
		User u1=new User("smith@gmail.com","smith123","smith","student",s1);
		Mockito.when(service.addUser(Mockito.any(User.class))).thenReturn(u1);
		RequestBuilder req= MockMvcRequestBuilders.get("/users");
		MvcResult result=mvc.perform(req).andExpect(status().isOk()).andReturn();
		assertEquals(false,result.getResponse().getContentAsString().isEmpty());
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

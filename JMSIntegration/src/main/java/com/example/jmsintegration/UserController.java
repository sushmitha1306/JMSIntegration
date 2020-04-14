package com.example.jmsintegration;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.jmsintegration.entity.Exam;
import com.example.jmsintegration.entity.RestClass;
import com.example.jmsintegration.entity.User;
import com.example.jmsintegration.service.KafkaConsumer;
import com.example.jmsintegration.service.KafkaSender;
import com.example.jmsintegration.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserController {

	@Autowired
	UserService service;
	
	@Autowired
	KafkaSender kafkaSender;

	@Autowired
	KafkaConsumer consumer;
    
	private RestTemplate template=new RestTemplate();
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/template")
	@ResponseBody
	@ApiOperation(value="Fetching data using RestTemplate")
	public RestClass getItems() {
		logger.info("Acccessing items using RestTemplate");
		String uri="https://api.github.com/search/users?q=followers:0&sort=joined&order=asc";
		return template.getForObject(uri, RestClass.class);
	}
	
	@GetMapping("/users")
	@ResponseBody
	@ApiOperation(value="Get All Users")
	public ResponseEntity<List<User>> getAllUsers(){
		logger.info("Retrieving all users: {}", service.getAllUsers());
		List<User> users=service.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@PostMapping("/users")
	@ResponseBody
	@ApiOperation(value="Create New User")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		logger.info("adding new user");
		service.addUser(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/users/{name}")
	@ResponseBody
	@ApiOperation("Update User Details like password")
	public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable(name="name")String name) {
		User u1=service.findUser(name);
		logger.info("updating user");
		u1.setPassword(user.getPassword());
		u1.setAddress(user.getAddress());
		service.updateUser(u1);
		return new ResponseEntity<>(u1, HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{name}")
	@ResponseBody
	@ApiOperation(value="Delete User data")
	public ResponseEntity<Void> deleteUser(@PathVariable(name="name")String name) {
	    logger.warn("deleting user"); 
		service.deleteUser(name);
	    return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/examproducer")
	@ApiOperation(value="Publish Message to Consumer")
    public String producer(@RequestBody Exam exam) {
        kafkaSender.send(exam);
        return "Exam details sent Successfully";
    }
	
	 @GetMapping("/student")
	 @ApiOperation(value="Consumer Receiving Message")
	 public Exam getMessage()
	 {               
	    return consumer.getConsumedmessage(); 
	 }
}

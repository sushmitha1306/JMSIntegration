package com.example.jmsintegration;

import java.util.List;
import org.modelmapper.ModelMapper;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.jmsintegration.entity.Exam;
import com.example.jmsintegration.entity.RestClass;
import com.example.jmsintegration.entity.User;
import com.example.jmsintegration.entity.UserDTO;
import com.example.jmsintegration.service.KafkaConsumer;
import com.example.jmsintegration.service.KafkaSender;
import com.example.jmsintegration.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserController {

	@Autowired
	UserService service;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	KafkaSender kafkaSender;

	@Autowired
	KafkaConsumer consumer;
    
	private RestTemplate template=new RestTemplate();
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/template")
	@ApiOperation(value="Fetching data using RestTemplate")
	public RestClass getItems() {
		logger.info("Acccessing items using RestTemplate");
		String uri="https://api.github.com/search/users?q=followers:0&sort=joined&order=asc";
		return template.getForObject(uri, RestClass.class);
	}
	
	@GetMapping("/users")
	@ApiOperation(value="Get All Users")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		logger.info("Retrieving all users: {}", service.getAllUsers());
		List<UserDTO> users=service.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@PostMapping("/users")
	@ApiOperation(value="Create New User")
	public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
		logger.info("adding new user");
		UserDTO u2=new UserDTO();
		modelMapper.map(user, u2);
		service.addUser(u2);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/users/{name}")
	@ApiOperation("Update User Details like password")
	public ResponseEntity<UserDTO> updateUser(@RequestBody User user,@PathVariable(name="name")String name) {
		logger.info("updating user");
		UserDTO user1=new UserDTO();
		modelMapper.map(user, user1);
		UserDTO u1=service.updateUser(user1,name);
		return new ResponseEntity<>(u1,HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{name}")
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

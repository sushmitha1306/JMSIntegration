package com.example.jmsintegration.repository;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.jmsintegration.entity.AddressDTO;
import com.example.jmsintegration.entity.UserDTO;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
class UserRepositoryTest {
    @Autowired
    private UserRepository repo;
	
	@Test
	public void getAllUsersTest() {
		AddressDTO a1=new AddressDTO(1,"Home","southblock");
		AddressDTO a2=new AddressDTO(2,"office","northblock");
		Set<AddressDTO> s1=new HashSet<>();
		s1.add(a1);
		Set<AddressDTO> s2=new HashSet<>();
		s2.add(a2);
		UserDTO u1=new UserDTO("smith@gmail.com","smith123","smith","student",s1);
		UserDTO u2=new UserDTO("john@gmail.com","john123","John","teacher",s2);
	    repo.save(u1);
	    repo.save(u2);
	    List<UserDTO> users=repo.findAll();
	    assertThat(users.size()).isEqualTo(2);
	}
	
	@Test
	public void deleteUserTest() {
		AddressDTO a1=new AddressDTO(3,"Home","southblock");
		AddressDTO a2=new AddressDTO(4,"office","southblock");
		Set<AddressDTO> s1=new HashSet<>();
		s1.add(a1);
		Set<AddressDTO> s2=new HashSet<>();
		s2.add(a2);
		UserDTO u1=new UserDTO("smith@gmail.com","smith123","smith","student",s1);
		UserDTO u2=new UserDTO("john@gmail.com","john123","John","teacher",s2);
	    repo.save(u1);
	    repo.save(u2);
	    repo.delete(u2);
	    List<UserDTO> users=repo.findAll();
	    assertThat(users.size()).isEqualTo(1);
	}
	
	@Test
	public void createUserTest() {
		AddressDTO a1=new AddressDTO(6,"Home","southblock");
		Set<AddressDTO> s1=new HashSet<>();
		s1.add(a1);
		UserDTO u1=new UserDTO("smith@gmail.com","smith123","smith","student",s1);
		repo.save(u1);
		List<UserDTO> users=repo.findAll();
		assertThat(users.isEmpty()).isEqualTo(false);
	}
}

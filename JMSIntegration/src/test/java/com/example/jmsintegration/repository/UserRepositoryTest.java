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

import com.example.jmsintegration.entity.Address;
import com.example.jmsintegration.entity.User;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
class UserRepositoryTest {
    @Autowired
    private UserRepository repo;
	
	@Test
	public void getAllUsersTest() {
		Address a1=new Address(1,"Home","southblock");
		Address a2=new Address(2,"office","northblock");
		Set<Address> s1=new HashSet<>();
		s1.add(a1);
		Set<Address> s2=new HashSet<>();
		s2.add(a2);
		User u1=new User("smith@gmail.com","smith123","smith","student",s1);
		User u2=new User("john@gmail.com","john123","John","teacher",s2);
	    repo.save(u1);
	    repo.save(u2);
	    List<User> users=repo.findAll();
	    assertThat(users.size()).isEqualTo(2);
	}
	
	@Test
	public void deleteUserTest() {
		Address a1=new Address(3,"Home","southblock");
		Address a2=new Address(4,"office","southblock");
		Set<Address> s1=new HashSet<>();
		s1.add(a1);
		Set<Address> s2=new HashSet<>();
		s2.add(a2);
		User u1=new User("smith@gmail.com","smith123","smith","student",s1);
		User u2=new User("john@gmail.com","john123","John","teacher",s2);
	    repo.save(u1);
	    repo.save(u2);
	    repo.delete(u2);
	    List<User> users=repo.findAll();
	    assertThat(users.size()).isEqualTo(1);
	}
	
	@Test
	public void createUserTest() {
		Address a1=new Address(6,"Home","southblock");
		Set<Address> s1=new HashSet<>();
		s1.add(a1);
		User u1=new User("smith@gmail.com","smith123","smith","student",s1);
		repo.save(u1);
		List<User> users=repo.findAll();
		assertThat(users.isEmpty()).isEqualTo(false);
	}
}

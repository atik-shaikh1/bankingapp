package com.cts.login.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cts.login.model.User;
import com.cts.login.repository.UserRepository;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class UserServiceTest {

	@MockBean
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@Test
	void createUserTest() {

		User user = new User(0l, "atikshaikh", "pass", "atikshaikh", "ROLE_USER", "shaikhatikrajjak@gmail.com",
				"215461654651321654", "9420626475", "03/06/1998", "Savings", "wakadi", "maharashtra", "India");

		Mockito.when(userRepository.save(user)).thenReturn(Mono.just(user));

		Mono<User> createdUserMono = userService.createUser(user);

		StepVerifier.create(createdUserMono).expectSubscription().expectNext(user).verifyComplete();

	}

	@Test
	void findByUsername() {
		User user = new User(0l, "atikshaikh", "pass", "atikshaikh", "ROLE_USER", "shaikhatikrajjak@gmail.com",
				"215461654651321654", "9420626475", "03/06/1998", "Savings", "wakadi", "maharashtra", "India");

		Mockito.when(userRepository.findByUsername("atikshaikh")).thenReturn(Mono.just(user));

		StepVerifier.create(userService.findByUsername("atikshaikh")).expectSubscription().expectNext(user)
				.verifyComplete();

	}

	@Test
	void findById() {
		User user = new User(0l, "atikshaikh", "pass", "atikshaikh", "ROLE_USER", "shaikhatikrajjak@gmail.com",
				"215461654651321654", "9420626475", "03/06/1998", "Savings", "wakadi", "maharashtra", "India");

		Mockito.when(userRepository.findById(0l)).thenReturn(Mono.just(user));

		StepVerifier.create(userService.findById(0l)).expectSubscription().expectNext(user).verifyComplete();

	}
}

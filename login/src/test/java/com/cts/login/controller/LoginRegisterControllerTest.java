package com.cts.login.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cts.login.model.LoginRequest;
import com.cts.login.model.ResponseMessage;
import com.cts.login.model.User;
import com.cts.login.service.UserService;

import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoginRegisterControllerTest {

	@Autowired
	WebTestClient webTestClient;

	@MockBean
	UserService userService;

	@Test
	void registerTest() {
		User user = new User(0l, "atikshaikh", "pass", "atikshaikh", "ROLE_USER", "shaikhatikrajjak@gmail.com",
				"215461654651321654", "9420626475", "03/06/1998", "Savings", "wakadi", "maharashtra", "India");

		Mockito.when(userService.createUser(user)).thenReturn(Mono.just(user));

		webTestClient.post().uri("/register").contentType(MediaType.APPLICATION_JSON).body(Mono.just(user), User.class)
				.exchange().expectStatus().is5xxServerError();
	}

	@Test
	void loginTest() {
		User user = new User(0l, "atikshaikh", "pass", "atikshaikh", "ROLE_USER", "shaikhatikrajjak@gmail.com",
				"215461654651321654", "9420626475", "03/06/1998", "Savings", "wakadi", "maharashtra", "India");

		Mockito.when(userService.createUser(user)).thenReturn(Mono.just(user));
		Mockito.when(userService.findByUsername(Mockito.anyString())).thenReturn(Mono.just(user));

		LoginRequest loginRequest = new LoginRequest("atikshaikh", "pass");

		webTestClient.post().uri("/login").contentType(MediaType.APPLICATION_JSON)
				.body(Mono.just(loginRequest), LoginRequest.class).exchange().expectStatus().isOk();

	}

}

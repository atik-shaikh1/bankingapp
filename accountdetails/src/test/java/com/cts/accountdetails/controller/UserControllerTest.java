package com.cts.accountdetails.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cts.accountdetails.model.User;
import com.cts.accountdetails.service.UserService;

import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

	@Autowired
	WebTestClient webTestClient;

	@MockBean
	UserService userService;

	@Test
	public void updateAccountDetailsTest() throws InterruptedException {

		User user = new User(0l, "atikshaikh", "pass", "atikshaikh", "ROLE_USER", "shaikhatikrajjak@gmail.com",
				"215461654651321654", "9420626475", "03/06/1998", "Savings", "wakadi", "maharashtra", "India");

		Mockito.when(userService.updateAccountDetails(Mockito.any(), Mockito.any())).thenReturn(true);

		webTestClient
			.put().uri("/update-account/{id}", 0l)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.body(Mono.just(user), User.class)
			.exchange()
			.expectStatus().isOk()
			.expectBody();

	}

}

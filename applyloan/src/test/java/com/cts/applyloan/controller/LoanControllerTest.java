package com.cts.applyloan.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cts.applyloan.model.Loan;

import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoanControllerTest {

	@Autowired
	WebTestClient webTestClient;
	
	@Test
	void applyLoan() {
		
		Loan loan = new Loan(0l, "Car loan", 20000.0, "20/08/2021", 7, 10, 0);
		
		webTestClient
			.post().uri("/apply-loan/{userId}", 1l)
	        .contentType(MediaType.APPLICATION_JSON)
	        .body(Mono.just(loan), Loan.class)
			.exchange()
			.expectStatus().isOk();
	}

	@Test
	void applyLoan_notCreated() {
		
		Loan loan = new Loan(0l, "Car loan", 20000.0, "20/08/2021", 7, 10, 0);
		
		webTestClient
			.post().uri("/apply-loan/{userId}", 1l)
			.contentType(MediaType.APPLICATION_JSON)
			.body(Mono.just(loan), Loan.class)
			.exchange()
			.expectStatus().isOk();
		
		
	}

}

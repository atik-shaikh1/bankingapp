package com.cts.login.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.cts.login.model.Loan;
import com.cts.login.model.ResponseMessage;
import com.cts.login.model.User;
import com.cts.login.service.UserService;

import reactor.core.publisher.Mono;

@RestController
@Api(value = "Client controller", description="Operations for applying a loan and updating account details")
public class ClientController {

	@Autowired
	private UserService userService;

	WebClient loanWebClient = WebClient.create("http://localhost:8082");
	WebClient accountWebClient = WebClient.create("http://localhost:8083");

	@ApiOperation("updating account details")
	@PreAuthorize("hasRole('USER')")
	@PutMapping("/update-account/{id}")
	public Mono<ResponseEntity<?>> updateAccountDetails(@RequestBody User user, @PathVariable Long id)
			throws InterruptedException {

		System.out.println(user);

		Mono<Boolean> updatedMono = accountWebClient
				.put().uri("/update-account/{id}", id)
				.body(Mono.just(user), User.class)
				.retrieve()
				.bodyToMono(Boolean.class);

		return updatedMono.map(updated -> {
			if (updated) {
				return ResponseEntity.ok()
						.body(ResponseMessage.getResponse(id, HttpStatus.OK, "Account Updated!"));
			}
			return ResponseEntity.badRequest()
					.body(ResponseMessage.getResponse(id, HttpStatus.BAD_REQUEST, "Account update failed!"));
		});

	}

	@ApiOperation("applying a loan")
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/apply-loan/{userId}")
	public Mono<ResponseEntity<?>> applyLoan(@RequestBody Loan loan, @PathVariable Long userId)
			throws InterruptedException {

		Mono<User> userMono = userService.findById(userId);

		User user = new User();

		StringBuilder str = new StringBuilder();
		
		userMono.subscribe(u -> str.append(u.getId()));

		Thread.sleep(50);

		if (str.length() > 0) {
			Mono<Loan> loanMono = loanWebClient.post().uri("/apply-loan/{userId}", userId)
					.body(Mono.just(loan), Loan.class)
					.retrieve()
					.bodyToMono(Loan.class);

			return loanMono.map(l -> {
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(ResponseMessage.getResponse(userId, HttpStatus.CREATED, "Loan Applied"));
			});
		}

		return Mono.just(ResponseEntity.badRequest()
				.body(ResponseMessage.getResponse(userId, HttpStatus.BAD_REQUEST, "Loan application failed!")));

	}

}

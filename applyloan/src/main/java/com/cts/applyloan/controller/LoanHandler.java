package com.cts.applyloan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.cts.applyloan.model.Loan;
import com.cts.applyloan.service.LoanService;

import reactor.core.publisher.Mono;

@Component
public class LoanHandler {

	@Autowired
	private LoanService loanService;

	public Mono<ServerResponse> applyLoan(ServerRequest serverRequest) {

		Mono<Loan> loanMono = serverRequest.bodyToMono(Loan.class);
		String userIdString = serverRequest.pathVariable("userId");
		Long userId = Long.parseLong(userIdString);

		return loanMono.flatMap(loan -> {
			System.out.println(loan);
			loan.setUserId(userId);
			return ServerResponse.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(loanService.applyLoan(loan), Loan.class);
		});

	}

}

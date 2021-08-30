package com.cts.applyloan.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class LoanRouter {

	@Bean
	public RouterFunction<ServerResponse> userRoute(LoanHandler loanHandler) {

		return RouterFunctions.route(POST("/fn/apply-loan/{userId}").and(accept(APPLICATION_JSON)), loanHandler::applyLoan);
	}

}

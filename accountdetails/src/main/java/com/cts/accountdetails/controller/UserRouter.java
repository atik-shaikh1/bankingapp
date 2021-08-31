package com.cts.accountdetails.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class UserRouter {

	@Bean
	public RouterFunction<ServerResponse> userRoute(UserHandler userHandler) {

		return RouterFunctions.route(PUT("/update-account/{id}").and(accept(APPLICATION_JSON)),
				userHandler::updateAccountDetails);
	}

}

package com.cts.login.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class LoginRouter {

	@Bean
	public RouterFunction<ServerResponse> userRoute(LoginHandler userHandler) {

		return RouterFunctions.route(POST("/fn/register").and(accept(APPLICATION_JSON)),
				userHandler::register)
				.andRoute(POST("/fn/login").and(accept(APPLICATION_JSON)),
				userHandler::login);
	}

}

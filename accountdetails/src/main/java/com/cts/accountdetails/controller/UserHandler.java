package com.cts.accountdetails.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.cts.accountdetails.model.ResponseMessage;
import com.cts.accountdetails.model.User;
import com.cts.accountdetails.service.UserService;

import reactor.core.publisher.Mono;

import java.sql.SQLOutput;

@Component
@Slf4j
public class UserHandler {

	
	@Autowired
	private UserService userService;
	
	public Mono<ServerResponse> updateAccountDetails(ServerRequest serverRequest) {

		Long id = Long.parseLong(serverRequest.pathVariable("id"));

		Mono<User> userMono = serverRequest.bodyToMono(User.class);

        return userMono.flatMap(user ->
                {
					try {
						return ServerResponse.ok()
						        .contentType(MediaType.APPLICATION_JSON)
						        .body(Mono.just(userService.updateAccountDetails(user, id)), User.class);
					} catch (InterruptedException e) {
						e.printStackTrace();
						return ServerResponse.badRequest()
						        .contentType(MediaType.APPLICATION_JSON)
						        .body(Mono.just("Account update failed!"), Boolean.class);
						
					}
				});
		
    }
	
	
}

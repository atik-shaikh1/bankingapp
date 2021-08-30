package com.cts.login.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
public class UserResource {

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public Mono<ResponseEntity<?>> publicUser(Principal principal) {
		return Mono.just(ResponseEntity.ok(principal.toString()));
	}

	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/resource/user", method = RequestMethod.GET)
	public Mono<ResponseEntity<?>> user(Principal principal) {
		return Mono.just(ResponseEntity.ok(principal.toString()));
	}
}

package com.cts.login.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.login.model.LoginRequest;
import com.cts.login.model.LoginResponse;
import com.cts.login.model.User;
import com.cts.login.service.UserService;
import com.cts.login.utils.JwtUtil;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@Validated
@Api(value = "Loan controller", description="Operations for applying a loan")
public class LoginRegisterController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserService userService;

	@ApiOperation("registering a new user")
	@PostMapping("/register")
	public Mono<ResponseEntity<?>> register(@RequestBody User user) {
		return Mono.just(ResponseEntity.ok(userService.createUser(user)));
	}

	@ApiOperation("to login a user")
	@PostMapping("/login")
	public Mono<ResponseEntity<?>> login(@RequestBody LoginRequest request) {
		return userService
				.findByUsername(request.getUsername())
				.map((user) -> {
						if (request.getPassword().equals(user.getPassword())) {
							return ResponseEntity.ok(new LoginResponse(jwtUtil.generateToken(user)));
						} else {
							log.info("password not matching");
							return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
						}
				})
				.defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

}

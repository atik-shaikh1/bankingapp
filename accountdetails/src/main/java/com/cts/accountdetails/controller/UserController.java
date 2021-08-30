package com.cts.accountdetails.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.accountdetails.model.ResponseMessage;
import com.cts.accountdetails.model.User;
import com.cts.accountdetails.service.UserService;

import reactor.core.publisher.Mono;

@RestController
@Api(value = "User controller", description="Operations for updating account details")
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation("update account")
	@PutMapping("/update-account/{id}")
	public Mono<Boolean> updateAccountDetails(@RequestBody User user, @PathVariable Long id)
			throws InterruptedException {

		return Mono.just(userService.updateAccountDetails(user, id));

//		if (!updated) {
//			return Mono.just(
//					ResponseEntity.badRequest().body(userService.getResponse(id, HttpStatus.BAD_REQUEST, "Account update failed!")));
//		}
//
//		return Mono.just(ResponseEntity.ok().body(userService.getResponse(id, HttpStatus.OK, "Account Updated")));
	}

	
}

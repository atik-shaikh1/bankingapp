package com.cts.accountdetails.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cts.accountdetails.dao.UserRepository;
import com.cts.accountdetails.model.ResponseMessage;
import com.cts.accountdetails.model.User;

import reactor.core.publisher.Mono;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public boolean updateAccountDetails(User user, Long id) throws InterruptedException {
		user.setId(id);
		
		Mono<User> updatedUserMono = userRepository.findById(id)
			.flatMap(u -> {
				return userRepository.save(user);
			});
		
		List<Boolean> updated = new ArrayList<>();
		updated.add(false);
		
		updatedUserMono.subscribe(u -> updated.add(0, true));
		
		Thread.sleep(2000);
		
		return updated.get(0);
	}
	
}

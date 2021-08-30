package com.cts.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.login.model.User;
import com.cts.login.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Mono<User> createUser(User userdata) {
    	return userRepository.save(userdata);
    }

    public Mono<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

	public Mono<User> findById(Long userId) {
		return userRepository.findById(userId);
	}

}

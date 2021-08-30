package com.cts.accountdetails.dao;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.cts.accountdetails.model.User;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

	
}

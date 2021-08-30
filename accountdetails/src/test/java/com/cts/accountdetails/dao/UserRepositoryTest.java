package com.cts.accountdetails.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.cts.accountdetails.model.User;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DirtiesContext
@SpringBootTest
public class UserRepositoryTest {
	
	@Autowired
	UserRepository userRepository;
	
	@Test
    public void updateUser() {
		
//		User user = new User(1l, "atik shaikh", "atikshaikh", "pass", "wakadi", "maharashtra", "India",
//				"shaikhatikrajjak@gmail.com", "215461654651321654", "9420626475", "03/06/1998", "Savings");
		
        String newAccountType = "Current";
        Mono<User> updatedUser = userRepository.findById(1l)
                .map(user1 -> {
                    user1.setAccountType(newAccountType);
                    return user1;
                })
                .flatMap((user1) -> {
                    return userRepository.save(user1);
                });

        StepVerifier.create(updatedUser)
                .expectSubscription()
                .expectNextMatches(user1 -> user1.getAccountType().equals(newAccountType))
                .verifyComplete();


    }

}

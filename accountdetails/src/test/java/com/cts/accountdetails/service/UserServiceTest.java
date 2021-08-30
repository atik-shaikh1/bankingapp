package com.cts.accountdetails.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cts.accountdetails.dao.UserRepository;
import com.cts.accountdetails.model.User;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class UserServiceTest {

	@MockBean
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@Test
	void testUpdateAccountDetails() throws InterruptedException {
		User user = new User(0l, "atikshaikh", "pass", "atikshaikh", "ROLE_USER", "shaikhatikrajjak@gmail.com",
				"215461654651321654", "9420626475", "03/06/1998", "Savings", "wakadi", "maharashtra", "India");

		Mockito.when(userRepository.findById(0l)).thenReturn(Mono.just(user));
		Mockito.when(userRepository.save(user)).thenReturn(Mono.just(user));
		
		boolean updatedUserMono = userService.updateAccountDetails(user, 0l);
		
		assertTrue(updatedUserMono);
		
//		StepVerifier.create(updatedUserMono)
//					.expectNext(user)
//					.expectComplete()
//					.verify();
		
	}
	@Test
	void testUpdateAccountDetails_UpdateFailed() throws InterruptedException {
		User user = new User(0l, "atikshaikh", "pass", "atikshaikh", "ROLE_USER", "shaikhatikrajjak@gmail.com",
				"215461654651321654", "9420626475", "03/06/1998", "Savings", "wakadi", "maharashtra", "India");

		Mockito.when(userRepository.findById(10l)).thenReturn(Mono.empty());
//		Mockito.when(userRepository.save(user)).thenReturn(Mono.empty());
		
		boolean updatedUserMono = userService.updateAccountDetails(user, 10l);
		
		assertFalse(updatedUserMono);
		
//		StepVerifier.create(updatedUserMono)
//					.expectNext(user)
//					.expectComplete()
//					.verify();
		
	}

}

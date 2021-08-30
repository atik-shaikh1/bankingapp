package com.cts.applyloan.service;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cts.applyloan.dao.LoanRepository;
import com.cts.applyloan.model.Loan;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class LoanServiceTest {

	@Autowired
	LoanService loanService;
	
	@MockBean
	LoanRepository loanRepository;
	
	@Test
	void applyLoanTest() {
		Loan loan = new Loan(0l, "Car loan", 20000.0, "20/2/08/2021", 7, 10, 0);

		when(loanRepository.save(loan)).thenReturn(Mono.just(loan));
		
		Mono<Loan> applyLoanMono = loanService.applyLoan(loan);
		
		StepVerifier.create(applyLoanMono)
			.expectSubscription()
			.expectNext(loan)
			.verifyComplete();
		
		
	}

}

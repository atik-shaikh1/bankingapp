package com.cts.applyloan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.applyloan.dao.LoanRepository;
import com.cts.applyloan.model.Loan;

import reactor.core.publisher.Mono;

@Service
public class LoanService {

	@Autowired
	private LoanRepository loanRepository;

	public Mono<Loan> applyLoan(Loan loan) {
		return loanRepository.save(loan);
	}

}

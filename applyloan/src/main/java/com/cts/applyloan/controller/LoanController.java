package com.cts.applyloan.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.applyloan.model.Loan;
import com.cts.applyloan.model.ResponseMessage;
import com.cts.applyloan.service.LoanService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@Api(value = "Loan controller", description="Operations for applying a loan")
public class LoanController {
	
	@Autowired
	private LoanService loanService;

	@ApiOperation("apply loan")
	@PostMapping("/apply-loan/{userId}")
	public Mono<Loan> applyLoan(@RequestBody Loan loan, @PathVariable Long userId) {
		loan.setUserId(userId);
		return loanService.applyLoan(loan);
	}
	

}

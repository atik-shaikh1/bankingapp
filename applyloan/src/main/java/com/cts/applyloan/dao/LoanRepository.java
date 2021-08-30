package com.cts.applyloan.dao;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.cts.applyloan.model.Loan;

@Repository
public interface LoanRepository extends ReactiveCrudRepository<Loan, Long>{

}

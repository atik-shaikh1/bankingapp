package com.cts.applyloan.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Component
public class Loan {

	@Id
	@ApiModelProperty(notes = "The Loan id")
	private long id;
	@ApiModelProperty(notes = "The type of loan")
	private String loanType;
	@ApiModelProperty(notes = "The total loan amount")
	private double loanAmount;
	@ApiModelProperty(notes = "The loan application date")
	private String date;
	@ApiModelProperty(notes = "The interest rate of loan")
	private double rateOfInterest;
	@ApiModelProperty(notes = "The duration of loan")
	private double durationOfLoan;
	@ApiModelProperty(notes = "The User id of the applicant")
	private long userId;
}

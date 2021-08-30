package com.cts.accountdetails.model;

import java.time.LocalDate;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@ApiModelProperty(notes = "The User id")
	private Long id;
	@ApiModelProperty(notes = "The username of the user")
	@NotBlank(message = "Username should not be empty")
	private String username;
	@ApiModelProperty(notes = "The password of the user")
	@NotBlank(message = "Password should not be empty")
	private String password;
	@ApiModelProperty(notes = "The name of the user")
	@NotBlank(message = "Name should not be empty")
	private String name;
	@ApiModelProperty(notes = "The security privilege roles of the user")
	@NotBlank(message = "Role should not be empty")
	private String roles;
	@ApiModelProperty(notes = "The email address of the user")
	@Email(message = "Invalid email address")
	private String emailAddress;
	@ApiModelProperty(notes = "The PAN number of the user")
	@NotBlank(message = "PAN should not be empty")
	private String PAN;
	@ApiModelProperty(notes = "The contact number of the user")
	@NotBlank(message = "ContactNumber should not be empty")
	private String contactNumber;
	@NotBlank(message = "dob should not be empty")
	@ApiModelProperty(notes = "The date of birth of the user")
	private String dob;
	@ApiModelProperty(notes = "The account type of the user")
	@NotBlank(message = "AccountType should not be empty")
	private String accountType;
	@ApiModelProperty(notes = "The residential address of the user")
	@NotBlank(message = "Address should not be empty")
	private String address;
	@ApiModelProperty(notes = "The state of the user")
	@NotBlank(message = "State should not be empty")
	private String state;
	@ApiModelProperty(notes = "The Country of the user")
	@NotBlank(message = "Country should not be empty")
	private String country;

}

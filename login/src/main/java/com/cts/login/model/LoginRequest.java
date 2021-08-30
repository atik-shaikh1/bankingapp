package com.cts.login.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
	@ApiModelProperty(notes = "The username for authentication")
	private String username;
	@ApiModelProperty(notes = "The password for authentication")
	private String password;
}

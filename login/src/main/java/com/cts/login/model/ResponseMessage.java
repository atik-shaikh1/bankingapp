package com.cts.login.model;


import org.springframework.http.HttpStatus;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseMessage {
    @ApiModelProperty(notes = "The status code of the response")
    private Integer statusCode;
    @ApiModelProperty(notes = "The status of the response")
    private String status;
    @ApiModelProperty(notes = "The id of the response")
    private long id;
    @ApiModelProperty(notes = "The message of the response")
    private String message;
    
	public static ResponseMessage getResponse(long id, HttpStatus httpStatus, String message) {
		ResponseMessage response = new ResponseMessage();
		response.setId(id);
		response.setStatus(httpStatus.name());
		response.setStatusCode(httpStatus.value());
		response.setMessage(message);
		return response;
	}
}


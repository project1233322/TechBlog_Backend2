package com.example.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class AnswerDTO {
private long id;
	
	private String body;
	private long userId;
	private long questionId;
	private String username;
	private Date createdDate;


}

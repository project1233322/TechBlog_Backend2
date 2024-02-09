package com.example.dtos;

import java.util.Date;
import java.util.List;

import lombok.Data;

 @Data
public class QuestionDTO {
 
	private long id;
	
	private String title;
	
	private String body;
	
	private List<String> tags;
	
	private long userId;
	private Date createdDate;
	private String username;
		
}

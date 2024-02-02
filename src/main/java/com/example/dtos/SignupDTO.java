package com.example.dtos;

import lombok.Data;

@Data
public class SignupDTO {
	
	private long id;
	private String name;
	private  String email;
	private String password;
}

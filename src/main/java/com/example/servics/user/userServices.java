package com.example.servics.user;


import com.example.dtos.SignupDTO;
import com.example.dtos.UserDTO;

public interface userServices {

	UserDTO createUser(SignupDTO signupDTO) throws Exception;

	boolean hasUserWithEmail(String email);
	

}

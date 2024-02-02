package com.example.conttroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.dtos.SignupDTO;
import com.example.dtos.UserDTO;
import com.example.servics.user.userServices;

@RestController
public class SignupController {
	
	@Autowired
	private userServices userservice;
	
	
	@PostMapping("/signup")
	public ResponseEntity<?> createUser(@RequestBody(required = true) SignupDTO signupDTO) throws Exception{

		if(userservice.hasUserWithEmail(signupDTO.getEmail()))
			return new ResponseEntity<>("user already exist with this "+signupDTO.getEmail(),HttpStatus.NOT_ACCEPTABLE);  //for check if email is exist or not
		
		UserDTO createdUser=userservice.createUser(signupDTO);
		if(createdUser == null)
			return new ResponseEntity<>("user not created,come again later" ,HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
		
		
		
	}
}

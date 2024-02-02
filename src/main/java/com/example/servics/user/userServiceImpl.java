package com.example.servics.user;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dtos.SignupDTO;
import com.example.dtos.UserDTO;
import com.example.entities.User;
import com.example.repositories.UserRepository;

@Service
public class userServiceImpl implements userServices{
	
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	@Override
	public UserDTO  createUser(SignupDTO signupDTO) {
		// TODO Auto-generated method stub
		User user=new User();
		user.setEmail(signupDTO.getEmail());
		user.setName(signupDTO.getName());
		user.setPassword(new BCryptPasswordEncoder().encode(signupDTO.getPassword()));
		User createduser=userRepository.save(user);
		UserDTO createdUserDTO =new UserDTO();
		createdUserDTO.setId(createduser.getId());
		return createdUserDTO;
		
		
	}
	
	@Override
	public boolean hasUserWithEmail(String email) {
		
		return userRepository.findFirstByEmail(email).isPresent();
	}
	
	
	
	
	
	
	
	
	
	
	
	
}

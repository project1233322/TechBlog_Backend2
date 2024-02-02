package com.example.conttroller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Utils.JwtUtils;
import com.example.dtos.AuthenticationRequest;
import com.example.dtos.AuthenticationResponse;
import com.example.entities.User;
import com.example.repositories.UserRepository;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@RestController
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserRepository userRepository;
	
	public static final String TOKEN_PREFIX = "Bearer";
	
	public static final String HEADER_STRING = "Authorization";
	
	@PostMapping("/authentication")
	public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest ,HttpServletResponse response) throws IOException, JSONException
	{
		try
		{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));			
		}
		catch(BadCredentialsException e)
		{
			throw new BadCredentialsException("Incorrect Email or Password");
		}
		catch(DisabledException e)
		{
			response.sendError(HttpServletResponse.SC_NOT_FOUND,"user is not created");
			return;
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		
		Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
		
		final  String Jwt = jwtUtils.generateToken(userDetails.getUsername());
		
		if(optionalUser.isPresent()) {
			response.getWriter().write(new JSONObject()
					.put("userId", optionalUser.get().getId())
					.toString()
			);
		}
		
		
		response.addHeader("Access-Control-Expose-Headers", "Authorization");
		response.setHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, X-Requested-With, Content-Type, Accept, X-Custom-header");
		response.setHeader(HEADER_STRING, TOKEN_PREFIX + Jwt);
		
		
	}
}

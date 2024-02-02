package com.example.dtos;

public class AuthenticationResponse {
	
	private String JwtToken;

	public String getJwtToken() {
		return JwtToken;
	}

	public void setJwtToken(String jwtToken) {
		JwtToken = jwtToken;
	}

	public AuthenticationResponse(String jwtToken) {
		
		JwtToken = jwtToken;
	}

}

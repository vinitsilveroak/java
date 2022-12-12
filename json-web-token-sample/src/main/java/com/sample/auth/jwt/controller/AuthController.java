package com.sample.auth.jwt.controller;

import java.text.ParseException;
import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.auth.jwt.dto.LoginDTO;
import com.sample.auth.jwt.dto.LoginResponseDTO;
import com.sample.auth.jwt.util.TokenManager;


@RestController
@RequestMapping("api/v1/")
public class AuthController {
	
	@Autowired
	private TokenManager tokenManager;

	@PostMapping("login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDto) throws ParseException {	
		
		LoginResponseDTO jwtResponseModel = null;
		
		if(!loginDto.getUsername().equals("admin") || !loginDto.getPassword().equals("pass") ) {
		    return new ResponseEntity<LoginResponseDTO>(jwtResponseModel, HttpStatus.UNAUTHORIZED);
		}
		
		final UserDetails userDetails = 
				new User(loginDto.getUsername(),
				loginDto.getPassword(), new ArrayList<>());
		//I don't understand
				
		final String jwtToken = tokenManager.generateJwtToken(userDetails);
		
	    return new ResponseEntity<LoginResponseDTO>(new LoginResponseDTO(jwtToken), HttpStatus.OK);
	}
	
	
}

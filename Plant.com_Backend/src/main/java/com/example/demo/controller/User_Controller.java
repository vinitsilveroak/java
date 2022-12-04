package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SignDto;
import com.example.demo.dto.SignInReponseDto;
import com.example.demo.dto.SignupDto;
import com.example.demo.service.User_service;

@RestController
@RequestMapping("User")
public class User_Controller {
	
	@Autowired
	User_service userservice;

	@PostMapping("singup")
	public String signup(@RequestBody SignupDto signupDto) {
		return userservice.signUp(signupDto);
		
	}
	
	 @PostMapping("/signin")
	    public SignInReponseDto signIn(@RequestBody SignDto signInDto) {
	        return userservice.signIn(signInDto);
	    } 
}

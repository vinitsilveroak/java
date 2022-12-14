package com.example.demo.controller;

import java.text.ParseException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginResponseDTO;
import com.example.demo.dto.SignDto;
import com.example.demo.dto.SignInReponseDto;
import com.example.demo.service.User_service;
import com.example.demo.util.TokenManager;

@RestController
@RequestMapping("api/v1/")
//@PropertySource("classpath:application.properties")
public class AuthController {

	@Autowired
	private TokenManager tokenManager;

	@Autowired
	User_service service;

	@PostMapping("/signin")
	public String signIn(@RequestBody SignDto signInDto) {
		return service.signIn(signInDto);
	}

	@GetMapping("privateapi")
	public ResponseEntity<String> privateapi(@RequestHeader(value = "authorization") String token) {

		try {
			if (!tokenManager.validateJwtToken(token)) {
				return new ResponseEntity<String>("Not a valid token", HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Invalid Token", HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<String>("Executed Private API", HttpStatus.OK);

	}

}

package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.User_service;
import com.sample.auth.jwt.dto.LoginDTO;
import com.sample.auth.jwt.util.TokenManager;

@RestController
@RequestMapping("/api/v1/")
public class AuthController {

	@Autowired
	private TokenManager tokenManager;

	@Autowired
	User_service userservice;
	
	@GetMapping("1")
	public String name() {
		System.out.println("received");
		return "done";
	}

	@PostMapping("login")
	public ResponseEntity loginUsingCreds(@RequestBody LoginDTO dto) {
		if ("admin".equals(dto.getUsername()) && "pass".equals(dto.getPassword())) {
			System.out.println("invalid user : " + dto.getUsername());
			return new ResponseEntity("not valid user", HttpStatus.UNAUTHORIZED);
		}
//		dto.setUsername(dto.getUsername());
//		dto.setPassword(dto.getPassword());
		System.out.println("user validated");
		String tokenManagerToken = (tokenManager.generateJwtToken(dto));
		System.out.println("token generated sucessfully");
		return new ResponseEntity<>(tokenManagerToken, HttpStatus.OK);
	}

	@GetMapping("validate")
	public ResponseEntity loginUsingToken(@RequestHeader("authorization") String htoken,
			@RequestParam("authorization") String ptoken) {
		System.out.println("token received : ");
		System.out.println("header token : " + htoken);
		System.out.println("param token : " + ptoken);
		if (!tokenManager.validateJwtToken(ptoken)) {
			return new ResponseEntity("not valid user", HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity("valid user", HttpStatus.OK);
	}

}

package com.example.demo.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Objects;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SignDto;
import com.example.demo.dto.SignInReponseDto;
import com.example.demo.dto.SignupDto;
import com.example.demo.entity.AuthenticationToken;
import com.example.demo.entity.User;
import com.example.demo.exception.CustomException;
import com.example.demo.repository.UserRepo;
import com.example.demo.util.TokenManager;

@Service
public class User_service {

	@Autowired
	UserRepo userRepository;
	
	@Autowired
	TokenManager manager;

	@Autowired
	Authentication_service authenticationService;

	public String signUp(SignupDto signupDto) {
		// check if user is already present
		if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))) {
			// we have an user
			throw new CustomException("user already present");
		}

		// hash the password

		String encryptedpassword = signupDto.getPassword();

		try {
			encryptedpassword = hashPassword(signupDto.getPassword());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		User user = new User(signupDto.getFirstName(), signupDto.getLastName(), signupDto.getEmail(),
				encryptedpassword);

		userRepository.save(user);

		// save the user

		// create the token

		final AuthenticationToken authenticationToken = new AuthenticationToken(user);

		authenticationService.saveConfirmationToken(authenticationToken);

		// ResponseDto responseDto = new ResponseDto("success", "user created
		// succesfully");
		return "user created succesfully";
	}

	private String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] digest = md.digest();
		String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		return hash;
	}

	public String signIn(SignDto signInDto) {
		// find user by email

		User user = userRepository.findByEmail(signInDto.getEmail());

		if (Objects.isNull(user)) {
			throw new com.example.demo.exception.AuthenticationFailException("user is not valid");
		}

		// hash the password

		try {
			if (!user.getPasswoprd().equals(hashPassword(signInDto.getPassword()))) {
				throw new com.example.demo.exception.AuthenticationFailException("wrong password");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// compare the password in DB

		// if password match

		AuthenticationToken token = authenticationService.getToken(user);

		// retrive the token

		final UserDetails userDetails = new org.springframework.security.core.userdetails.User(signInDto.getEmail(), signInDto.getPassword(), new ArrayList<>());

		final String jwtToken = manager.generateJwtToken(userDetails);
		return jwtToken;

		// return response
	}

}

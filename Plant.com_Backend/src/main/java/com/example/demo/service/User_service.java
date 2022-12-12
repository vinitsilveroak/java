package com.example.demo.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Objects;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SignDto;
import com.example.demo.dto.SignupDto;
import com.example.demo.entity.AuthenticationToken;
import com.example.demo.entity.Users;
//import com.example.demo.entity.User;
import com.example.demo.exception.CustomException;
import com.example.demo.repository.UserRepo;
import com.sample.auth.jwt.util.TokenManager;

@Service
public class User_service {

	@Autowired
	UserRepo userRepository;

	@Autowired
	Authentication_service authenticationService;

	@Autowired
	private TokenManager tokenManager;

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

		Users user = new Users(signupDto.getFirstName(), signupDto.getLastName(), signupDto.getEmail(),
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

	public com.example.demo.dto.SignInReponseDto signIn(SignDto loginDto) {
		// find user by email

		com.example.demo.entity.Users user = userRepository.findByEmail(loginDto.getEmail());

		if (Objects.isNull(user)) {
			throw new com.example.demo.exception.AuthenticationFailException("user is not valid");
		}

		// hash the password

		try {
			if (!user.getPasswoprd().equals(hashPassword(loginDto.getPassword()))) {
				throw new com.example.demo.exception.AuthenticationFailException("wrong password");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		final UserDetails userDetails = new User(loginDto.getEmail(), loginDto.getPassword(), new ArrayList<>());

		System.out.println("hii");
		@SuppressWarnings("unused")
		final String jwtToken = tokenManager.generateJwtToken(userDetails);
		return null;

	}

}

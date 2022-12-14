package com.example.demo.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AuthenticationToken;
import com.example.demo.entity.User;
import com.example.demo.repository.TokenRepository;

@Service
public class Authentication_service {
	@Autowired
	TokenRepository tokenRepository;

	public void saveConfirmationToken(com.example.demo.entity.AuthenticationToken authenticationToken) {
		tokenRepository.save(authenticationToken);
	}

	public AuthenticationToken getToken(User user) {
		return tokenRepository.findByUser(user);
	}

	public User getUser(String tokens) {
		System.out.println("token from db ...");
		AuthenticationToken authenticationToken = tokenRepository.findByToken(tokens);
		System.out.println("user with token : " + authenticationToken.toString());
		if (Objects.isNull(tokens)) {
			System.out.println("token is null in getUser");
			return null;
		}
		return authenticationToken.getUser();
	}

	public boolean authentication(String token) {
		if (Objects.isNull(token)) {
			throw new AuthenticationFailException("token not present");

		}
		if (!Objects.isNull(token)) {
			System.out.println("token not null");
			return true;
			// throw new AuthenticationFailException("AUTH_TOEKN_NOT_VALID");
		}
		return true;
	}
}

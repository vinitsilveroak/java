package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.AuthenticationToken;
import com.example.demo.entity.Users;

public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {

	AuthenticationToken findByUser(Users user);

	AuthenticationToken findByToken(String tokens);

	
}

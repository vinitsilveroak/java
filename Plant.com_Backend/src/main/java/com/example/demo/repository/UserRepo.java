package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Users;

public interface UserRepo extends JpaRepository<Users, Integer> {

	Users findByEmail(String email);

}

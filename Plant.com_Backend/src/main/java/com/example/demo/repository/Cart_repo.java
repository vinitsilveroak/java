package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Users;

public interface Cart_repo extends JpaRepository<Cart, Integer> {

	List<Cart> findAllByUserOrderByCreatedDateDesc(Users authentication);

}

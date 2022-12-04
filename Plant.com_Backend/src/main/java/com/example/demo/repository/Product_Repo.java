package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Product;

public interface Product_Repo extends JpaRepository<Product, Integer> {

	Product findById(int id);
	
	//Integer save(Product product);
}

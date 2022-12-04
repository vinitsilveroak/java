package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Product_image;

public interface Image_Repo extends JpaRepository<Product_image, Integer> {

}

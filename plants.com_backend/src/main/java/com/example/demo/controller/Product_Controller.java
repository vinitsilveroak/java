package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.ApiResponse;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductlisstDto;
import com.example.demo.entity.Product;
import com.example.demo.service.Product_service;

@RestController
@RequestMapping("product")
public class Product_Controller {

	@Autowired
	Product_service product_service;

	//add product
	@PostMapping("addproduct")
	public ResponseEntity<ApiResponse> createproduct(@RequestBody ProductDto productDto) {
		product_service.addplant(productDto);
		return new ResponseEntity<>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
	}

	//get product list
	@GetMapping("getlist")
	public List<ProductlisstDto> getproductList(ProductlisstDto productlisstDto) {
		System.out.println("hey");
		List<Product> allProduct = product_service.getAllProduct();
		List<ProductlisstDto> productList = new ArrayList<>();
		allProduct.forEach(a -> {
			productList.add(product_service.productEntityToListDto(a));
		});
		return productList;
	}

	//get product by id
	@GetMapping("getproduct")
	public ProductDto getProduct(@RequestParam int id) {
		Product product = product_service.getproduct(id);
		ProductDto product1 = product_service.productEntityToDto(product);
		return product1;
	}

}

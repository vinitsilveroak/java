package com.example.demo.controller;

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
import com.example.demo.dto.AddToCartDto;
import com.example.demo.dto.GetDto;
import com.example.demo.entity.Users;
import com.example.demo.exception.productNotExistExce;
import com.example.demo.service.Authentication_service;
import com.example.demo.service.CartService;

@RestController
@RequestMapping("cart")
public class CartController {

	@Autowired
	Authentication_service authentication_service;

	@Autowired
	CartService cartService;

	@PostMapping("ADDTOCART")
	public ResponseEntity<ApiResponse> addtocart(@RequestBody AddToCartDto addToCart, @RequestParam("token") String token)
			throws productNotExistExce {
		Users user = authentication_service.getUser(token);

		cartService.addtocart(addToCart, user);
		return new ResponseEntity<>(new ApiResponse(true, "ITEM HAS BEEN ADDED"), HttpStatus.CREATED);
	}

	@GetMapping("getCart")
	public ResponseEntity<GetDto> getcartitem(@RequestParam("token") String token) {
		Users authentication = authentication_service.getUser(token);

		authentication_service.authentication(token);
		GetDto dto = cartService.listcartItem(authentication);
		return new ResponseEntity<>(dto, HttpStatus.OK);

	}
}

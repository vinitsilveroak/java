package com.example.demo.dto;

import java.util.List;

import com.example.demo.service.cartItemDto;


public class GetDto {

	List<cartItemDto> cartItem;
	private double totalCost;

	public GetDto() {

	}

	public List<cartItemDto> getCartItem() {
		return cartItem;
	}

	public void setCartItem(List<cartItemDto> cartItem) {
		this.cartItem = cartItem;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
}

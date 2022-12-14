package com.example.demo.service;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Product;

public class cartItemDto {

	private Integer id;
	private Integer quantity;
	private Product product;

	public cartItemDto() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public cartItemDto(Cart cart) {
		this.id = cart.getId();
		this.quantity = cart.getQuantity();
		this.setProduct(cart.getProduct());
	}
}

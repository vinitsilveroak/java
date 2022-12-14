package com.example.demo.dto;

public class AddToCartDto {

	private Integer id;
	private Integer quantity;
	private Integer product_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuality(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public AddToCartDto(Integer id, Integer quality, Integer product_id) {
		super();
		this.id = id;
		this.quantity = quality;
		this.product_id = product_id;
	}

	public AddToCartDto() {

	}

}

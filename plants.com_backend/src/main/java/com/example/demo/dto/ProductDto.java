package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.enumm.Size;

public class ProductDto {

	private Integer id;

	private String image_thumbnail;

	private String product_description;

	private Integer product_price;

	private String product_name;

	private String care;

	private String light;

	private Size size;

	private List<String> image_url= new ArrayList<>();

	public String getImage_thumbnail() {
		return image_thumbnail;
	}

	public void setImage_thumbnail(String image_thumbnail) {
		this.image_thumbnail = image_thumbnail;
	}

	public String getProduct_description() {
		return product_description;
	}

	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getCare() {
		return care;
	}

	public void setCare(String care) {
		this.care = care;
	}

	public String getLight() {
		return light;
	}

	public void setLight(String light) {
		this.light = light;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProduct_price() {
		return product_price;
	}

	public void setProduct_price(Integer product_price) {
		this.product_price = product_price;
	}

	public List<String> getImage_url() {
		return image_url;
	}

	public void setImage_url(List<String> image_url) {
		this.image_url = image_url;
	}

	public ProductDto() {

	}

}

package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "Quantity")
	private Integer quantity;

	@Column(name = "createdDate")
	private Date createdDate;

	@ManyToOne
	@JoinColumn(name = "productid")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quality) {
		this.quantity = quality;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	

	public Cart(Integer id, Integer quality, Date createdDate, Product product, User user) {
		super();
		this.id = id;
		this.quantity = quality;
		this.createdDate = createdDate;
		this.product = product;
		this.user = user;
	}

	public Cart() {

	}

}

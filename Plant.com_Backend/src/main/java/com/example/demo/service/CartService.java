package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AddToCartDto;
import com.example.demo.dto.GetDto;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.exception.productNotExistExce;
import com.example.demo.repository.Cart_repo;

@Service

public class CartService {

	@Autowired
	Product_service product_service;

	@Autowired
	Cart_repo cart_repRepo;

	public void addtocart(AddToCartDto addToCart, User user) throws productNotExistExce {

		Product product = product_service.findById(addToCart.getProduct_id());

		Cart cart = new Cart();
		cart.setProduct(product);
		cart.setUser(user);
		cart.setQuantity(addToCart.getQuantity());
		cart.setCreatedDate(new Date());
		
		cart_repRepo.save(cart);
	}

	public GetDto listcartItem(User authentication) {
		List<Cart> carts = cart_repRepo.findAllByUserOrderByCreatedDateDesc(authentication);
		List<cartItemDto> dtos = new ArrayList<>();

		double totalcost = 0;
		for (Cart cart : carts) {
			cartItemDto cartItemDto = new cartItemDto(cart);
			totalcost += cartItemDto.getQuantity() * cart.getProduct().getProduct_price();
			dtos.add(cartItemDto);

		}

		GetDto dto = new GetDto();
		dto.setTotalCost(totalcost);
		dto.setCartItem(dtos);

		return dto;

	}

}

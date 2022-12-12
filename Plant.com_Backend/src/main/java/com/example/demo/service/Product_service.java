package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductlisstDto;
import com.example.demo.entity.Product;
import com.example.demo.entity.Product_image;
import com.example.demo.exception.productNotExistExce;
import com.example.demo.repository.Image_Repo;
import com.example.demo.repository.Product_Repo;
import com.example.demo.repository.Type_repo;
import com.example.demo.repository.product_typeRepo;

@Service
public class Product_service {

	@Autowired
	Product_Repo product_Repo;

	@Autowired
	Image_Repo product_image;

	@Autowired
	Type_repo type_repo;

	@Autowired
	product_typeRepo product_typeRepo;

	@Autowired
	Image_Repo image_Repo;

	// add product

	public Product addplant(ProductDto productDto) {
		Product product = productDtoToEntity(productDto);

		// return
		int as = product_Repo.save(product).getId();
		// Product id = as.getId();
		System.out.println(as);
		Product_image image;
		for (int i = 0; i < productDto.getImage_url().size(); i++) {
			image = new Product_image();
			image.setProduct(as);

			// image.set(productDto.getImage_url().get(i));
			image.setImage(productDto.getImage_url().get(i));
			System.out.println(i);

			image_Repo.save(image);
		}

		return null;

		// product_id, product_type
		// productDtoToEntity

	}

	// get productlist
	public List<Product> getAllProduct() {

		return product_Repo.findAll();
	}

	// get product by id
	public ProductDto getproduct(int id) {
		ProductDto productDto = productEntityToDto(product_Repo.findById(id));
		List<Product_image> findByProduct = image_Repo.findByProduct(id);

		List<String> list = new ArrayList<>();
		for (int t = 0; t < findByProduct.size(); t++) {

			list.add(findByProduct.get(t).getImage());
		}
		productDto.setImage_url(list);
		return productDto;
	}

	public ProductlisstDto productEntityToListDto(Product product) {

		ProductlisstDto productDto = new ProductlisstDto();
		productDto.setId(product.getId());
		productDto.setImage_url(product.getImage_thumbnail());
		productDto.setPrice(product.getProduct_price());
		productDto.setPlantName(product.getProduct_name());

		return productDto;
	}

	public ProductDto productEntityToDto(Product product) {

		ProductDto productDto = new ProductDto();
		productDto.setId(product.getId());
		productDto.setProduct_description(product.getProduct_description());
		productDto.setProduct_price(product.getProduct_price());
		productDto.setProduct_name(product.getProduct_name());
		productDto.setImage_thumbnail(product.getImage_thumbnail());
		productDto.setCare(product.getCare());
		productDto.setLight(product.getLight());
		productDto.setSize(product.getSize());
		return productDto;
	}

	public Product productDtoToEntity(ProductDto productDto) {

		Product product = new Product();
		product.setId(productDto.getId());
		product.setProduct_description(productDto.getProduct_description());
		product.setImage_thumbnail(productDto.getImage_thumbnail());
		product.setCare(productDto.getCare());
		product.setProduct_price(productDto.getProduct_price());
		product.setLight(productDto.getLight());
		product.setSize(productDto.getSize());
		product.setProduct_name(productDto.getProduct_name());
		return product;
	}

	public Product findById(Integer productid) throws productNotExistExce {
		Optional<Product> product = product_Repo.findById(productid);
		if (product.isEmpty()) {
			throw new productNotExistExce("product id is invalid :" + productid);
		}
		return product.get();
	}

}

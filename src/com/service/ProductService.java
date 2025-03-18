package com.service;

import java.util.List;

import com.model.Product;

public interface ProductService {

	void addProduct(Product p);

	void deleteProduct(int index);

	List<Product> getAllProducts();
	
	void updateProduct(Product p);
	
	List<Product> searchProduct(String sdata);
}

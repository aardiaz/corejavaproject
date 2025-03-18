package com.view;

import java.util.List;
import java.util.Scanner;

import com.model.Product;
import com.service.ProductService;
import com.service.ProductServiceImpl;

public class Test {

	public static void main(String[] args) {
		add();
		//getAll();
		//delete();
	}

	// add product
	static void add() {

		ProductService service = new ProductServiceImpl();
		char flag = 'y';
		Scanner sc = new Scanner(System.in);

		do {

			Product p = new Product();

			System.out.println("Enter id ");
			p.setId(sc.nextInt());

			System.out.println("Enter name ");
			p.setName(sc.next());

			p.setPrice(800000);
			p.setCompany("Apple");

			service.addProduct(p);

			System.out.println("do you want to add more[y/n]");
			flag = sc.next().charAt(0);

		} while (flag == 'y');
	}

	// get all products
	static void getAll() {

		ProductService service = new ProductServiceImpl();

		List<Product> plist = service.getAllProducts();
		System.out.println(plist);
	}

	// delete product
	static void delete() {
		ProductService service = new ProductServiceImpl();
		service.deleteProduct(0);// index = 0

		// show all products after deleted
		//getAll();
	}
	
	/*
	 *  1. Student
	 *  2. Employee
	 *  3. Dog
	 */

}

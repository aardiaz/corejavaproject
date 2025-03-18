package com.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.model.Product;

public class ProductServiceImpl implements ProductService {

	@Override
	public void addProduct(Product p) {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "1234");

			String sql = "insert into product(name,company,price)values('" + p.getName() + "','" + p.getCompany()
					+ "','" + p.getPrice() + "')";
			Statement stmt = con.createStatement();

			int res = stmt.executeUpdate(sql);
			if (res > 0) {
				JOptionPane.showMessageDialog(null, "Product added successfully");
			} else {
				JOptionPane.showMessageDialog(null, "Failed");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

//		plist.add(p);
//		System.out.println("---- added success---size= " + plist.size());
	}

	@Override
	public void deleteProduct(int id) {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "1234");

			String sql = "delete from product where id =  " + id;
			Statement stmt = con.createStatement();

			stmt.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Product> getAllProducts() {

		List<Product> plist = new ArrayList<>();

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "1234");

			String sql = "select * from product ";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				Product p = new Product();

				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setCompany(rs.getString("company"));
				p.setPrice(rs.getInt("price"));

				plist.add(p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return plist;
	}

	@Override
	public void updateProduct(Product p) {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "1234");

			String sql = "update product set name='" + p.getName() + "',company='" + p.getCompany() + "',price='"
					+ p.getPrice() + "' where id = " + p.getId();
			Statement stmt = con.createStatement();

			stmt.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Product> searchProduct(String sdata) {

		List<Product> plist = new ArrayList<>();

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "1234");

			String sql = "select * from product where name like '%"+sdata+"%' OR company like '%"+sdata+"%'  ";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				Product p = new Product();

				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setCompany(rs.getString("company"));
				p.setPrice(rs.getInt("price"));

				plist.add(p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return plist;

	}

}

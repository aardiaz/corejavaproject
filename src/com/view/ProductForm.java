package com.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.model.Product;
import com.service.ProductService;
import com.service.ProductServiceImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameTxt;
	private JTextField priceTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductForm frame = new ProductForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProductForm() {
		setTitle("AddProduct ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 606, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(101, 112, 103, 22);
		contentPane.add(lblNewLabel);
		
		nameTxt = new JTextField();
		nameTxt.setBounds(214, 96, 200, 57);
		contentPane.add(nameTxt);
		nameTxt.setColumns(10);
		
		JLabel lblCompany = new JLabel("Company");
		lblCompany.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCompany.setBounds(101, 194, 103, 22);
		contentPane.add(lblCompany);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPrice.setBounds(101, 278, 103, 22);
		contentPane.add(lblPrice);
		
		priceTxt = new JTextField();
		priceTxt.setColumns(10);
		priceTxt.setBounds(214, 264, 200, 57);
		contentPane.add(priceTxt);
		
		JComboBox companyCmb = new JComboBox();
		companyCmb.setModel(new DefaultComboBoxModel(new String[] {"select", "CG", "DELL", "SAMSUNG", "APPLE"}));
		companyCmb.setBounds(214, 176, 200, 59);
		contentPane.add(companyCmb);
		
		JButton btnNewButton = new JButton(" Add Product");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//form validation
				
				if (nameTxt.getText().isBlank()) {
					JOptionPane.showMessageDialog(nameTxt, "Please enter name");
					return;
				}
				
				if (companyCmb.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(companyCmb, "Please select company");
					return;
				}
				
				if (priceTxt.getText().isBlank()) {
					JOptionPane.showMessageDialog(priceTxt, "Please enter price");
					return;
				}
				
				//get data from product form and set to product object
				Product p = new Product();
				
				p.setName(nameTxt.getText());
				p.setCompany(companyCmb.getSelectedItem().toString());
				p.setPrice(Integer.parseInt(priceTxt.getText()));
				
				ProductService service = new ProductServiceImpl();
				service.addProduct(p);
				
				//clear data from product form
				nameTxt.setText("");
				companyCmb.setSelectedIndex(0);
				priceTxt.setText("");
				
			}
		});
		btnNewButton.setBounds(246, 348, 129, 51);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Product Details");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel_1.setBounds(138, 11, 333, 57);
		contentPane.add(lblNewLabel_1);
	}
}

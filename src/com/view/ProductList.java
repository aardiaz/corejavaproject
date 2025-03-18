package com.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.model.Product;
import com.service.ProductService;
import com.service.ProductServiceImpl;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;

public class ProductList extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField nameTxt;
	private JTextField priceTxt;
	private JComboBox companyCmb;
	private int pid=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductList frame = new ProductList();
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
	public ProductList() {
		setTitle("ProductList");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 788, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Product List");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblNewLabel.setBounds(262, 0, 191, 41);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(281, 74, 481, 307);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Name", "Company", "Price"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(table.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(null, "select any row");
					return;
				}
				
				int srow = table.getSelectedRow();
				int id = (int) table.getModel().getValueAt(srow, 0);
				
				ProductService ps = new ProductServiceImpl();
				ps.deleteProduct(id);
				
				displayData();//show data after deleted
				JOptionPane.showMessageDialog(null, "product deleted success");
			}
		});
		btnNewButton.setBounds(525, 392, 110, 41);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(table.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(null, "select any row");
					return;
				}
				
				int srow = table.getSelectedRow();
				  pid = (int) table.getModel().getValueAt(srow, 0);
				
				nameTxt.setText(table.getModel().getValueAt(srow, 1).toString());
				companyCmb.setSelectedItem(table.getModel().getValueAt(srow, 2));
				priceTxt.setText(table.getModel().getValueAt(srow, 3).toString());
				
			}
		});
		btnNewButton_1.setBounds(396, 392, 110, 41);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Print");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					table.print();
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(652, 392, 110, 41);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Add New Product");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new ProductForm().setVisible(true);
			}
		});
		btnNewButton_3.setBounds(54, 34, 141, 31);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel_1 = new JLabel("Search");
		lblNewLabel_1.setBounds(588, 32, 46, 31);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String sdata = textField.getText().trim();
				
				ProductService service = new ProductServiceImpl();
				List<Product> plist = service.searchProduct(sdata);
				
				DefaultTableModel  tmodel = (DefaultTableModel) table.getModel();
				tmodel.setRowCount(0);//reset table
				
				for(Product p : plist) {
					tmodel.addRow(new Object[] {p.getId(),p.getName(),p.getCompany(),p.getPrice()});
				}
				
			}
		});
		textField.setBounds(639, 32, 123, 31);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setBounds(28, 113, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		nameTxt = new JTextField();
		nameTxt.setBounds(95, 97, 161, 46);
		contentPane.add(nameTxt);
		nameTxt.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Company");
		lblNewLabel_2_1.setBounds(28, 175, 46, 14);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Price");
		lblNewLabel_2_2.setBounds(28, 236, 46, 14);
		contentPane.add(lblNewLabel_2_2);
		
		priceTxt = new JTextField();
		priceTxt.setColumns(10);
		priceTxt.setBounds(95, 220, 161, 46);
		contentPane.add(priceTxt);
		
		companyCmb = new JComboBox();
		companyCmb.setModel(new DefaultComboBoxModel(new String[] {"select", "CG", "DELL", "SAMSUNG", "APPLE"}));
		companyCmb.setBounds(95, 163, 161, 46);
		contentPane.add(companyCmb);
		
		JButton btnNewButton_4 = new JButton("Update Product");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Product p = new Product();
				
				p.setId(pid);
				p.setName(nameTxt.getText());
				p.setPrice(Integer.parseInt(priceTxt.getText()));
				p.setCompany(companyCmb.getSelectedItem().toString());
				
			  ProductService  service= new ProductServiceImpl();
			  service.updateProduct(p);
			  JOptionPane.showMessageDialog(null, "update success");
			  
			  displayData();
			  
				//clear data from product form
				nameTxt.setText("");
				companyCmb.setSelectedIndex(0);
				priceTxt.setText("");
			  
			}
		});
		btnNewButton_4.setBounds(41, 297, 215, 53);
		contentPane.add(btnNewButton_4);
		
		displayData();
	}
	
	//display product data in JTable 
	private void displayData() {
		
		ProductService ps = new ProductServiceImpl();
		List<Product> plist = ps.getAllProducts();
		
		DefaultTableModel  tmodel = (DefaultTableModel) table.getModel();
		tmodel.setRowCount(0);//reset table
		
		for(Product p : plist) {
			tmodel.addRow(new Object[] {p.getId(),p.getName(),p.getCompany(),p.getPrice()});
		}
		
	}
}

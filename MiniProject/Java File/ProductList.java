package com.miniproject.ecommerce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ProductList {

	Scanner scanner = new Scanner(System.in);
	ConnectSql connect = new ConnectSql();
	Connection connection = null;
	PreparedStatement preparedstatement = null;
	ResultSet rs = null;
	Map<Integer, Integer> items = new HashMap<Integer, Integer>();

	public Map<Integer, Integer> productlist() {

		int choice, count = 0, quantity;
		int option;
		String ans = null;

		/* Arraylist 'computerproduct' holding the items under category 'Computer' */
		List<String> computerproduct = new ArrayList<String>();
		computerproduct.add("Laptop");
		computerproduct.add("Mouse");
		computerproduct.add("Keyboard");

		/* Arraylist 'appliancesproduct' holding the items under category 'Appliance' */
		List<String> appliancesproduct = new ArrayList<String>();
		appliancesproduct.add("Fridge");
		appliancesproduct.add("Television");

		/* Arraylist 'mobileproduct' holding the items under category 'Mobile' */
		List<String> mobileproduct = new ArrayList<String>();
		mobileproduct.add("SmartPhone");

		/* Arraylist 'clothingproduct' holding the items under category 'Clothing' */
		List<String> clothingproduct = new ArrayList<String>();
		clothingproduct.add("Jeans");
		clothingproduct.add("Shirt");
		clothingproduct.add("TShirt");

		/*
		 * TreeMap 'Category' is the key and Arraylist
		 * 'computerproduct','appliancesproduct','mobileproduct','clothingproduct' under
		 * respective category
		 */
		Map<String, List<String>> Category = new TreeMap<String, List<String>>();
		Category.put("Computer", computerproduct);
		Category.put("Appliance", appliancesproduct);
		Category.put("Mobile", mobileproduct);
		Category.put("Clothing", clothingproduct);

		/*
		 * TreeMap 'EShop' is the key and 'List' holding category and their respective
		 * values
		 */
		Map<String, Map<String, List<String>>> EShop = new TreeMap<String, Map<String, List<String>>>();
		EShop.put("EShop", Category);

		/*
		 * List of the items displayed to user to among sort as per category and there
		 * item
		 */
		
		System.out.println("Welcome to Eshop\n" + "Shop By Category\n");
		System.out.println(EShop);
		System.out.println("Select by category\n" + "Choose '1' for Appliance\n" + "Choose '2' for Computer\n"
				+ "Choose '3' for Clothing\n" + "Choose '4' for Mobile\n");
		choice = scanner.nextInt();
		
		/* User will select category and later select the product user want to buy and enter its quantity */
		
		switch (choice) {
		case 1:
			System.out.println(appliancesproduct);
			System.out.println(
					"Select the product you want to buy\n" + "Choose 4 for Fridge\n" + "Choose 5 for Television\n");
			option = scanner.nextInt();
			System.out.println("Enter the quantity for the product");
			quantity = scanner.nextInt();
			try {

				connection = connect.getconnectiondetail();
				preparedstatement = connection.prepareStatement("select Quantity from productlist where Productid = ?");
				preparedstatement.setInt(1, option);
				rs = preparedstatement.executeQuery();
				while (rs.next()) {
					 
					count = rs.getInt(1);
					
					if (count == 0) {
						throw new Stock("The product is out of stock");
					} /* If product is out stock it throw an customized exception with a 'The product is out of stock' */

				}

				if (quantity > count) {
					System.out.println("Quantity is more than stock/n" + "Please enter the correct quantity");
					quantity = scanner.nextInt();
				} else if (quantity < count) {
					count = count - quantity;
					preparedstatement = connection
							.prepareStatement("update productlist set Quantity = ? where Productid = ? ");
					preparedstatement.setInt(1, count);
					preparedstatement.setInt(2, option);
					int no = preparedstatement.executeUpdate();
					System.out.println("Stock updated >>" + no);
					
					/* To ask the user if the user wants to shop more if 'y' will store the list again and add the selected items in HashMap "items" along with quantity */
					/*If no 'n' it return the object 'items' with the product added by the user along with the quantities*/
					System.out.println("Do you want shop more(y/n)"); 
					ans = scanner.next();								
					if (ans.equals("y")) {
						items.put(option, quantity);
						ProductList productList = new ProductList();
						productList.productlist();
					} else if (ans.equals("n")) {
						System.out.println("Thank you for shopping");
						items.put(option, quantity);
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			break;
		case 2:
			System.out.println(computerproduct);
			System.out.println("Select the product you want to buy\n" + "Choose 1 for Laptop\n" + "Choose 2 for Mouse\n"
					+ " Choose 3  for Keyboard");
			option = scanner.nextInt();
			System.out.println("Enter the quantity for the product");
			quantity = scanner.nextInt();
			try {

				connection = connect.getconnectiondetail();
				preparedstatement = connection.prepareStatement("select Quantity from productlist where Productid = ?");
				preparedstatement.setInt(1, option);
				rs = preparedstatement.executeQuery();
				while (rs.next()) {

					count = rs.getInt(1);
					if (count == 0) {
						throw new Stock("The product is out of stock");

					}

				}

				if (quantity > count) {
					System.out.println("Quantity is more than stock\n" + "Please enter the correct quantity");
					quantity = scanner.nextInt();
				} else if (quantity <= count) {
					count = count - quantity;
					preparedstatement = connection
							.prepareStatement("update productlist set Quantity = ? where Productid = ? ");
					preparedstatement.setInt(1, count);
					preparedstatement.setInt(2, option);
					int no = preparedstatement.executeUpdate();
					System.out.println("Stock updated >>" + no);

					System.out.println("Do you want shop more(y/n)");
					ans = scanner.next();
					if (ans.equals("y")) {
						items.put(option, quantity);
						ProductList productList = new ProductList();
						productList.productlist();
					} else if (ans.equals("n")) {
						System.out.println("Thank you for shopping");
						items.put(option, quantity);
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			}

			break;
		case 3:
			System.out.println(clothingproduct);
			System.out.println("Select the product you want to buy\n" + "Choose 7 for Jeans\n" + "Choose 8 for Shirt\n"
					+ "Choose 9 for T-Shirt");
			option = scanner.nextInt();
			System.out.println("Enter the quantity for the product");
			quantity = scanner.nextInt();
			try {

				connection = connect.getconnectiondetail();
				preparedstatement = connection.prepareStatement("select Quantity from productlist where Productid = ?");
				preparedstatement.setInt(1, option);
				rs = preparedstatement.executeQuery();
				while (rs.next()) {

					count = rs.getInt(1);
					if (count == 0) {
						throw new Stock("The product is out of stock");
					}

				}

				if (quantity > count) {
					System.out.println("Quantity is more than stock\n" + "Please enter the correct quantity");
					quantity = scanner.nextInt();
				} else if (quantity <= count) {
					count = count - quantity;
					preparedstatement = connection
							.prepareStatement("update productlist set Quantity = ? where Productid = ? ");
					preparedstatement.setInt(1, count);
					preparedstatement.setInt(2, option);
					int no = preparedstatement.executeUpdate();
					System.out.println("Stock updated >>" + no);

					System.out.println("Do you want shop more(y/n)");
					ans = scanner.next();
					if (ans.equals("y")) {
						items.put(option, quantity);
						ProductList productList = new ProductList();
						productList.productlist();
					} else if (ans.equals("n")) {
						System.out.println("Thank you for shopping");
						items.put(option, quantity);
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			break;
		case 4:
			System.out.println(mobileproduct);
			System.out.println("Select the product you want to buy " + "Choose 6 for Phone\n");
			option = scanner.nextInt();
			System.out.println("Enter the quantity for the product");
			quantity = scanner.nextInt();
			try {

				connection = connect.getconnectiondetail();
				preparedstatement = connection.prepareStatement("select Quantity from productlist where Productid = ?");
				preparedstatement.setInt(1, option);
				rs = preparedstatement.executeQuery();
				while (rs.next()) {

					count = rs.getInt(1);
					if (count == 0) {
						throw new Stock("The product is out of stock");
					}

				}

				if (quantity > count) {
					System.out.println("Quantity is more than stock/n" + "Please enter the correct quantity");
					quantity = scanner.nextInt();
				} else if (quantity <= count) {
					count = count - quantity;
					preparedstatement = connection
							.prepareStatement("update productlist set Quantity = ? where Productid = ? ");
					preparedstatement.setInt(1, count);
					preparedstatement.setInt(2, option);
					int no = preparedstatement.executeUpdate();
					System.out.println("Stock updated >>" + no);
					System.out.println("Do you want shop more(y/n)");
					ans = scanner.next();
					if (ans.equals("y")) {
						items.put(option, quantity);
						ProductList productList = new ProductList();
						productList.productlist();
					} else if (ans.equals("n")) {
						System.out.println("Thank you for shopping");
						items.put(option, quantity);
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			break;
		default:
			System.out.println("Invaild input");

		}
		return items;

	}

}

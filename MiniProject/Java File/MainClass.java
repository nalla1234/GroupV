package com.miniproject.ecommerce;

import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

public class MainClass {

	Scanner scanner = new Scanner(System.in);
	static AdminOperation admin = new AdminOperation();
	NewUser newuser = new NewUser();

	Cart cart = new Cart();

	public static void main(String[] args) {
		Map<Integer, Integer> items = null;

		String ans, ans1;
		int choice = 0;
		int amount = 0;
		System.out.println("Are you a new user(y/n)");
		Scanner scanner = new Scanner(System.in);
		ans = scanner.next();
		if (ans.equalsIgnoreCase("y")) {

			System.out.println("Please register yourself first");
			NewUser newuser = new NewUser();
			NewUser usernew = newuser.newregistration();
			try {
				ProductList productlist = new ProductList();
				items = productlist.productlist();

			} catch (Exception e) {

				e.printStackTrace();
			}
			Cart cart = new Cart();
			amount = cart.cart(newuser, items);
			System.out.println("The amount to be paid\n" + amount);
			PurchaseDetails purchasedetails = new PurchaseDetails();
			purchasedetails.updatepurchasehistory(usernew, items, amount);

		} else if (ans.equalsIgnoreCase("n")) {

			System.out.println("Are the admin(y/n)");
			ans1 = scanner.next();
			if (ans1.equalsIgnoreCase("y")) {
				System.out.println("Please select the data you want check\n"
						+ "Choose '1' for displaying the registered user list.\n"
						+ "Choose '2' for check the quantity of each product.\n"
						+ "Choose '3' for checking particular user history for product purchase details."
						+ "\nPlease select the data you want to fetch:");
				choice = scanner.nextInt();
				switch (choice) {

				case 1:
					admin.getregistereduserinformation();
					break;

				case 2:
					admin.productinformation();
					break;
				case 3:
					admin.getpurchaseinformation();
					break;
				default:
					System.out.println("Incorrect Input");
					break;

				}
			}

		}
	}
}

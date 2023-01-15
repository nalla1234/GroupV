package com.miniproject.ecommerce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class AdminOperation {

	Scanner scanner = new Scanner(System.in);
	ConnectSql connect = new ConnectSql();
	Connection connection = null;
	PreparedStatement preparedStatement;
	ResultSet resultset = null;
	int Userid;

	public void getpurchaseinformation() {
		/* Method will provide purchase history of the user by entering the 'userid' of that user and fetch all the data of purchase and display*/
		
		
		System.out.println("Enter the userid of the user to check the purchase history");
		Userid = scanner.nextInt();
		try {
			connection = connect.getconnectiondetail();
			preparedStatement = connection.prepareStatement("select * from purchasedetails where userid =?");
			preparedStatement.setInt(1, Userid);
			resultset = preparedStatement.executeQuery();

			while (resultset.next()) {

				System.out.println("\nUserID\n" + resultset.getInt(1));
				System.out.println("\nUsername\n" + resultset.getString(2));
				// Need to check the correct output
				System.out.println("\nProductID\n" + resultset.getInt(3));
				System.out.println("\nPurchased Quantity\n" + resultset.getInt(4));
				System.out.println("\nAmount\n" + resultset.getInt(5));
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void getregistereduserinformation() {
		/* Method will fetch all the data  of the registered user */
		
		
		try {
			connection = connect.getconnectiondetail();
			preparedStatement = connection.prepareStatement("Select * from userdetails");
			resultset = preparedStatement.executeQuery();

			while (resultset.next()) {

				System.out.println("\nThe user id is:\n" + resultset.getInt("UserId"));
				System.out.println("\nThe username is:\n" + resultset.getString("Username"));
				System.out.println("\nThe email is:\n" + resultset.getString("Email"));
				System.out.println("\nThe password is:\n" + resultset.getString("Password"));
			}
		} catch (Exception e) {

			System.out.println(e);

		}

	}

	public void productinformation() {
		/* Method will fetch the quantity of the product by providing the 'productID' of the */
		
		int productid = 0;
		System.out.println("Enter the product id to check the quantity");
		productid = scanner.nextInt();
		try {
			connection = connect.getconnectiondetail();
			preparedStatement = connection.prepareStatement("Select quantity from productlist where ProductId = ?");
			preparedStatement.setInt(1, productid);
			resultset = preparedStatement.executeQuery();

			while (resultset.next()) {

				System.out.println("The stock left are:\n" + resultset.getInt(1));

			}
		} catch (Exception e) {

			System.out.println(e);

		}

	}

}

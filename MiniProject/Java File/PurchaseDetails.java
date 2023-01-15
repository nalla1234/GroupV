package com.miniproject.ecommerce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Set;

public class PurchaseDetails {

	ConnectSql connect = new ConnectSql();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultset = null;
	int Userid, Productid, Quantity, Amount;
	String Username;

	public void updatepurchasehistory(NewUser user, Map<Integer, Integer> items, int amount) {

		/* Method will get 'Username','Userid', 'Productid'.'Quantity', 'Payment amount' and pass to method name 'getpurchaseinformation' to update the details of purchase in the database */
		
		try {
			connection = connect.getconnectiondetail();

			preparedStatement = connection.prepareStatement("select UserId from userdetails where Username = ?");
			preparedStatement.setString(1, user.getName());
			resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				Userid = resultset.getInt(1);
			}

			Set<Integer> s = items.keySet();

			for (int i : s) {

				Productid = i;
				Quantity = items.get(i);

			}

			Username = user.getName();
			Amount = amount;

			System.out.println("The name of the purchaser is:\n" + Username);
			System.out.println("UserId of purchaser:\n" + Userid);
			System.out.println("Product ID:\n" + Productid);
			System.out.println("Quantity purchased:\n" + Quantity);

			getpurchaseinformation(Userid, Username, Productid, Quantity, amount);

		} catch (Exception e) {

			e.getMessage();

		}

	}

	private void getpurchaseinformation(int puserid, String pusername, int pproductid, int pquantity, int pamount) {
		/* Method will update the 'Username','Userid', 'Productid'.'Quantity', 'Payment amount' in the table 'purchasedetails'*/
		
		
		try {

			preparedStatement = connection.prepareStatement(
					"insert into purchasedetails(UserId,Username,ProductId,purchasequantity,Amount) values(?,?,?,?,?)");

			preparedStatement.setInt(1, puserid);
			preparedStatement.setString(2, pusername);
			preparedStatement.setInt(3, pproductid);
			preparedStatement.setInt(4, pquantity);
			preparedStatement.setInt(5, pamount);

			int q = preparedStatement.executeUpdate();
			System.out.println("Updated purchased details" + q);

		} catch (Exception e) {
			System.out.println(e);

		}
	}

}

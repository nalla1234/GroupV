package com.miniproject.ecommerce;

import java.util.Map;
import java.util.Scanner;

public class Cart extends Payment {

	Scanner scanner = new Scanner(System.in);
	/*Cart will fetch the amount the user needs to pay by send the  Hashmap 'items' and provide the amount which the user to main function */
	public int cart(NewUser user, Map<Integer, Integer> items) {
		String choice;
		int amount = 0;

		Cart cart = new Cart();

		System.out.println("Do you want to payment do (y/n)");
		choice = scanner.next();
		if (choice.equals("y")) {

			amount = cart.payment(items);
		} else {

			System.out.println("Payment denied");
		}
		return amount;

	}

}

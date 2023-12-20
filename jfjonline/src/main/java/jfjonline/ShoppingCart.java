package jfjonline;
/**


	* class ShoppingCart, a class that stores methods for the backend management of anything shopping cart related.
	* @author Franklin Bai
	* @version 17.0.3
	*/

import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class ShoppingCart {
	
 /**
	 * A void method with 1 parameter, the account checking out, that verifys the
	 * credit card number and pin, and makes sure they meet the input requirements.
	 * @param account , a CustomerAccount object, that is the account checking out.
	 */
	public static void creditCardCheckOut(CustomerAccount account) {
		
		int validGo = 0;
		String cardNum = "";
		String pin = "";
		do {
			try {
				System.out.println("Type in your credit card number. Must be between 12 to 16 digits.");
				cardNum = Main.read.nextLine();

				if (cardNum.matches("-?\\d+(\\.\\d+)?") && cardNum.length() >= 12 && cardNum.length() <= 16) {
					System.out.println("Type in your 4 digit pin. ");
					pin = Main.read.nextLine();
					
					if (pin.matches("-?\\d+(\\.\\d+)?") && pin.length() == 4) {
						System.out.println("Purchase successful.");
						clearCart(account);
						validGo = 1;
					} else {
						System.out.println("Make sure your pin is 4 digits long, and is only numbers.");
					}
				} else {
				System.out.println ("Check that your card number is between 12 to 16 digits, and only numbers.");
				}
			} catch (Exception e) {
				System.out.println("Please enter only numbers.");
				Main.read.nextLine();
			}
		} while (validGo != 1);
	}

	/**
	 * A void method with 1 parameter, the account, and clears the shopping cart of that account.
	 * @param account , a CustomerAccount object, that determines the account shopping cart being cleared.
	 */
	public static void clearCart(CustomerAccount account) {
		try {
			PrintWriter clearfile = new PrintWriter(account.getUsername() + "Cart.txt");
			clearfile.close();
		} catch (IOException e) {
			System.out.println ("Something went wrong while clearing cart the cart. Try again later. Here is the error code.");
			System.out.println(e);
		}
	}

 /**
	 * A void method with 1 parameter, the account, and prints out the shopping cart of that account.
	 * @param account , a CustomerAccount object, that determines the account shopping cart being viewed.
	 * @throws throws an IO Exception.
	 */
	public static void viewShoppingCart(CustomerAccount account) throws IOException {
		Main.clearScreen();
		System.out.println("---------- Your cart ----------");
		Item[] cartItems = getShoppingCart(account);
		double totalPrice = 0.00;
		if (cartItems.length > 0) {
		for (int i = 0; i < cartItems.length; i ++) {
			totalPrice += cartItems[i].getPrice();
			System.out.println(cartItems[i].getBrand() + " " + cartItems[i].getItemModel() + " " + cartItems[i].getPrice());
		}
		DecimalFormat format = new DecimalFormat("0.00");
		System.out.println("--------- Summary ---------");
		System.out.println("Price before tax: $" + totalPrice);
		System.out.println("Price after tax 13%: $" + format.format((double)totalPrice*1.13));
		} else {
			System.out.println("There is nothing in your cart!");
		}
	}
	
	/**
	 * A method with 1 parameter, the account, and grabs and returns the shoppingCart of that account in an Item array.
	 * @param account , a CustomerAccount object, that determines the account shopping cart being returned.
	 * @return returns an Item array of all items in the shopping cart.
	 * @throws throws an IO Exception.
	 */
	public static Item [] getShoppingCart(CustomerAccount account) throws IOException {
		Scanner line_counter = null;
		Scanner line_reader = null;
		String lines[] = {};
		Item[] originalItems = ShopManager.grabItems("storeInventory.txt");
		
		Item[] cartItems = {};
		int cartItemID[] = {};
		int numOfItems = 0;
		try {
			// This file reader is used to count the number of lines inside the file, so
			// that we can prepare the array of items in cart.
			line_counter = new Scanner(new BufferedReader(new FileReader(account.getUsername()+"Cart.txt")));
			
			// counts the number of lines in the file. The num of lines will be the number
			// of items in the cart.
			while (line_counter.hasNextLine()) {
				numOfItems++;
				line_counter.nextLine();
			}
			lines = new String[numOfItems];
			cartItemID = new int[numOfItems];
			cartItems = new Item[numOfItems];
			
			// Since the previous reader already reached the end of file, a new reader is
			// made, so that we can go through the file again and actually take in information this time.
			// This stores each line in the file in a String array.
			line_reader = new Scanner(new BufferedReader(new FileReader(account.getUsername()+"Cart.txt")));
			int count = 0;
			while (line_reader.hasNextLine()) {
				lines[count] = line_reader.nextLine();
				count++;
			}
			// Getting the item code from each line.
			for (int i = 0; i < lines.length; i++) {
				cartItemID[i] = Integer.parseInt(lines[i]);
			}

			// comparing each itemID in the cart to the original storeInventory itemIDs, and filling the
			// object array, cartItems, with the items that passed the comparison.
			for (int k = 0; k < ShopManager.grabItems("storeInventory.txt").length;k++) {
				for (int i = 0; i < lines.length; i++) {
					if (cartItemID[i] == originalItems[k].getItemID()) {
						cartItems[i] = originalItems[k];
					}
				}	
			}
		} catch (IOException e) {
			System.out.println(e);
			
		} finally {
			if (line_reader != null) {
				line_reader.close();
				line_counter.close();
			}
		}
		return cartItems;
	}

	/**
	 * A void method with 3 parameters, the item, item count, and the account. The item will be added to the cart
	 * of the account, the same number of times as item count.
	 *
	 * @param newItem , the item being added to cart.
	 * @param itemCount , an integer that stores the number of times the item will be added to cart.
	 * @param account , a CustomerAccount object, that determines the shopping cart of the account being added to.
	 * @throws throws an IO Exception.
	 */
	public static void addToShoppingCart (Item newItem, int itemCount, CustomerAccount account) throws IOException{
		//using arraylists, because of the .add function. Easier to use, and simpler compared to arrays.
		ArrayList<Integer> itemIDList = new ArrayList<Integer>();
	
		Item[] cartItems = getShoppingCart(account);
		
		//using the getters for the itemID.
		for (int i = 0; i < cartItems.length; i++) {
			itemIDList.add(cartItems[i].getItemID());
		}
		
		//adding the new itemID to the array, the same amount of times as the number of that item bought.
		for (int i = 0; i < itemCount; i++) {
			itemIDList.add(newItem.getItemID());
		}
		
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter(account.getUsername() + "Cart.txt", true));
			
			// a new print writer that essentially clears the file, so that we can write new
			// values to it, since all information is stored temporarily in the arraylists.
			PrintWriter clearfile = new PrintWriter(account.getUsername() + "Cart.txt");
			clearfile.close();
			
			// writing to file using the array we created.
			for (int i = 0; i < itemIDList.size(); i++) {
				pw.println(itemIDList.get(i));
			}
		} catch (IOException e) {
			System.out.println ("Something went wrong while adding to the cart. Try again later. Here is the error code.");
			System.out.println(e);
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}
}




		
	
	
package jfjonline;
/**
	* class Main, that puts all the classes of the store together and helps run them.
	* @author Franklin Bai
	* @version 17.0.3
	*/

import java.util.Scanner;
import java.io.*;

public class Main {
	
	// a static CustomerAccount object that determines the current logged in account.
	static CustomerAccount loggedInAccount;
	
	// A static scanner that can be called anywhere in the Main class to read input.
	static Scanner read = new Scanner(System.in);

	// main method, that essentially brings all the classes together and helps them run. It is the driver code.
	public static void main(String[] args) throws IOException {
		boolean start = true;
		while (start == true) {
			System.out.println("Welcome to JFJ Online. Please log in, or create an account to continue."
												+ "\n[0] Log in"
												+ "\n[1] Create a new account"
												+ "\n[2] Exit");
			
			int input = loginMenuInputCheck();
			read.nextLine();

			// If input 0, log in to account.
			if (input == 0) {

				//loop for logging in to account.
				boolean loggingIn = true;
				while (loggingIn) {
					clearScreen();
					System.out.println("Log in to your JFJ account.");
					System.out.println("Username: ");
					String username = read.nextLine();
					
					System.out.println("Password: ");
					String password = read.nextLine();

					//limbo account is the account information being checked, using accountValidate from LogInManager.
					// if account is null value, it means it does not exist.
					CustomerAccount limboAccount = LogInManager.accountValidate(username, password);
					if (limboAccount == null) {
						System.out.println("Your username or password is incorrect, or you do not have an account.");
						System.out.println("[Press enter to try again.]");
						read.nextLine();
						
					} else {
						System.out.println("Login successful. Welcome to JFJ Online!");
						// since validation provided an actual object and not a null value, it means account exists.
						// Set current logged in account to the limboAccount.
						loggedInAccount = limboAccount;
						loggingIn = false;
						System.out.println("[Press enter to continue.]");
						read.nextLine();
						clearScreen();
						// grant access to main menu.
						loggedInMainMenu();
					}
				}	
				
			// If input is 1, create a new account.
			} else if (input == 1) {
				clearScreen();
				//check if user and password are valid.
				String username = LogInManager.usernameValidate();
				String password = LogInManager.passwordValidate();
				LogInManager.createNewAccount(username, password, 1);
				
				System.out.println("Your account has been created. Welcome to JFJ Online!");
				System.out.println("[Press enter to go back to main menu, to log in.]");
			  read.nextLine();

			// If input is 2, exit code, by setting loop condition to false.
			} else if (input == 2) {
				clearScreen();
				System.out.println("Thank you for shopping at JFJ. Come back soon!");
				start = false;
				System.out.println("press enter to exit.");
				read.nextLine();
			}
			clearScreen();
		}
	}

	
/**
	* A void method with no parameters, that runs the menu AFTER the user is logged in.
	* This is similar to the menu when the user first starts the program, but with more functionality.
	*
	* @throws throws an IOException if there are any problems that arise from methods with IO functionality.
	*/
	public static void loggedInMainMenu() throws IOException {
		boolean loggedIn = true;
		while (loggedIn == true) {
			System.out.println("Welcome to JFJ Online, " + loggedInAccount.getUsername() + "!"
												+ "\n[0] Browse the store"
												+ "\n[1] View your cart"
												+ "\n[2] Change account username"
												+ "\n[3] Log out"
												+ "\n\n *If your username is not updated correctly, please relog.");
			
			int input = loggedinMenuInputCheck();
			read.nextLine();

			// If logged in menu input 0, browse items in store.
			if (input == 0) {
				boolean itemListView = true;
				
				//sorting method defaults to the original order of items in the file.
				int sortInput = 1;
				int lastSortingStyle = sortInput;
				
				//loops viewing items.
				while (itemListView == true) {
					
					ShopManager.printItemList(sortInput);
					System.out.println("[0] View specific item.\n" +
														 "[1] Sort by default order.\n" +
														 "[2] Sort by cheapest price first.\n" +
														 "[3] Sort by most expensive price first.\n" +
														 "[4] Go back to menu.");
					sortInput = loggedinMenuInputCheck2();

					// This is done so that the last menu input will not equal 0, which is a null value
					// when passed to printItemList on the next loop.
					if (sortInput != 0) {
						lastSortingStyle = sortInput;
					}
					//if input is zero, then pass the store inventory of items, into the view item method.
					if (sortInput == 0) {
						viewItem(ShopManager.grabItems("storeInventory.txt"));
						sortInput = lastSortingStyle;
						
					} else if (sortInput == 4) {
						// reset sorting style.
						sortInput = 1;
						itemListView = false;
					}
				}
				read.nextLine();

			// if logged in menu input is 1, then pass the current logged in account
			// to viewShoppingCart method in ShoppingCart class.
			} else if (input == 1) {
				ShoppingCart.viewShoppingCart(loggedInAccount);
				System.out.println("[0] Check out\n" +
													 "[1] Clear cart\n" +
													 "[2] Go back to main menu.\n");
				int cartOptions = loginMenuInputCheck();
				read.nextLine();
				//if cart options is 0, check out.
				if (cartOptions == 0) {
					clearScreen();
					ShoppingCart.creditCardCheckOut(loggedInAccount);

					//clear cart method.
				} else if (cartOptions == 1) {
					clearScreen();
					if (ShoppingCart.getShoppingCart(loggedInAccount).length == 0){
						System.out.println("You cant buy nothing!");
					} else {
						ShoppingCart.clearCart(loggedInAccount);
					}
				
				//do nothing to reach end of loop.
				} else if (cartOptions == 2) {
					// do nothing.
				}
				System.out.println("[Press enter to continue.]");
				read.nextLine();

			//if logged in menu input was 2, then change username, if name changes left is greater than 0.
			} else if (input == 2) {
				clearScreen();
				
				if (loggedInAccount.getNameChangesLeft() > 0) {
						System.out.println("Type in your new username.");
						String newName = LogInManager.usernameValidate();
					
						LogInManager.editAccountList(loggedInAccount, true, newName);
						System.out.println("Name change successful.");
		
				} else {
					System.out.println("You have no name changes left!");
				}
				System.out.println("[Press enter to return to menu.]");
				read.nextLine();
				
			//if logged in menu input is 3, return nothing, to exit this method.
			} else if (input == 3) {
				clearScreen();
				return;
			}
			clearScreen();
		}
	}

/**
	* A void method that runs the view item menu, with 1 parameter, an Item array.
	* It calls buyItem method in Main, if item is in stock.
	*
	*	@param sortedItems , an array of Items that are sorted.
	* @throws throws an IOException if anything goes wrong while using methods with IO functionality.
	*/
	public static void viewItem(Item[] sortedItems) throws IOException {
		Item currentItem = itemIDLocator(sortedItems);
		read.nextLine();
		System.out.println(currentItem.toString());
		System.out.println("-----------------------------------" +
											 "\n[0] Add this item to cart.\n" +
											 "[1] Go back to browsing.\n");

		int decision = loginMenuInputCheck();
		read.nextLine();

		//if view item decision is 0, check if in stock, then add to cart using buyItem(item) method.
		if (decision == 0) {
			if (currentItem.getAmtInStock() == 0) {
				System.out.println("There are none left. come back next time.");
				System.out.println("[Press enter to continue.]");
				read.nextLine();
			} else {
				buyItem(currentItem);	
				read.nextLine();
			}
			
		// if decision was 1, then return back to menu.
		} else if (decision == 1) {
			clearScreen();
			return;
		}
	}

	/**
	 * A void method with 1 parameter, that gets how many items the user wants to buy, and runs the 
	 * addToShoppingCart method, declared in ShoppingCart class.
	 * 
	 * @param item , the current item that is being viewed.
	 * @throws throws an IO Exception.
	 */
	public static void buyItem(Item item) throws IOException{
		int validGo = 0;
		int decision = 0;
		do {
			try {
				System.out.println("How many do you want to buy?");
				decision = read.nextInt();
				if (decision < 0 || decision > item.getAmtInStock()) {
					System.out.println("There are not that many in stock!");
				} else {
					ShoppingCart.addToShoppingCart(item, decision, loggedInAccount);
					item.setAmtInStock(item.getAmtInStock() - decision);
					System.out.println("Add to cart successful.");
					System.out.println("[Press enter to go back.]");
					read.nextLine();
					validGo = 1;
				}
			} catch (Exception e) {
				System.out.println("Please enter only whole numbers. Or, you are seeing this error because" +
													 " this is your first time adding to cart. Dont worry.");
				read.nextLine();
			}
		} while (validGo != 1);
	}
	
	/**
	 * A method with 1 parameter, that searches the given array of items for the itemID the user is looking for.
	 * If found, then return that Item.
	 *
	 * @param items , the array of Items being searched through.
	 * @return returns the user input, an Item, if it was found in the array of items
	 * @throws throws an IOException.
	 */
	public static Item itemIDLocator (Item[] items) throws IOException {
		int validGo = 0;
		int decision = 0;
		int indexOfItem = 0;
		int itemCount = items.length;
		do {
			try {
				System.out.println("Type in the item ID for the item you want to view.");
				decision = read.nextInt();
				boolean found = false;
				//search through item array, flip boolean if found.
				for (int i = 0; i < itemCount; i ++) {
					if (decision == items[i].getItemID()){
						indexOfItem = i;
						found = true;
					}	
				}
				//if boolean not flipped, then item not in array.
				if (found == false) {
					System.out.println("That item does not exist in our inventory! Please try again.");
				} else {
					validGo = 1;
				}
			} catch (Exception e) {
				System.out.println("Please enter only numbers.");
				read.nextLine();
			}
		} while (validGo != 1);
		
		return items[indexOfItem];
	}

	/**
	 * A method with no parameters, that gets user input and makes sure the decision
	 * is an integer between 0 and 4, inclusive.
	 * If the input is valid, return it. This method will be used for checking
	 * menu decisions for when the user is logged in already, or for inputs from 0 to 4.
	 * 
	 * @return returns the user input, an integer, if it was valid.
	 */
	public static int loggedinMenuInputCheck2() {
		int validGo = 0;
		int decision = 0;
		do {
			try {
				decision = read.nextInt();
				if (decision < 0 || decision > 4) {
					System.out.println("Please enter a number between 0 and 4 inclusive.");
				} else {
					validGo = 1;
				}
			} catch (Exception e) {
				System.out.println("Please enter only numbers.");
				read.nextLine();
			}
		} while (validGo != 1);
	
		return Integer.valueOf(decision);
	}
	/**
	 * A method with no parameters, that gets user input and makes sure the decision
	 * is an integer between 0 and 3, inclusive.
	 * If the input is valid, return it. This method will be used for checking
	 * menu decisions for when the user is logged in already, or for any inputs from 0 to 3.
	 * 
	 * @return returns the user input, an integer, if it was valid.
	 */
	public static int loggedinMenuInputCheck() {
		int validGo = 0;
		int decision = 0;
		do {
			try {
				decision = read.nextInt();
				if (decision < 0 || decision > 3) {
					System.out.println("Please enter a number between 0 and 3 inclusive.");
				} else {
					validGo = 1;
				}
			} catch (Exception e) {
				System.out.println("Please enter only numbers.");
				read.nextLine();
			}
		} while (validGo != 1);
	
		return Integer.valueOf(decision);
	}

 /**
	 * A method with no parameters, that gets user input and makes sure the decision
	 * is an integer between 0 and 2, inclusive.
	 * If the input is valid, return it. This method will be used for checking the initial 
	 	* user menu decisions, BEFORE logging in, or check any input that requires input from 0 to 2.
	 * 
	 * @return returns the user input, an integer, if it was valid.
	 */
	public static int loginMenuInputCheck() {
		int validGo = 0;
		int decision = 0;
		do {
			try {
				decision = read.nextInt();
				if (decision < 0 || decision > 2) {
					System.out.println("Please enter a number between 0 and 2 inclusive.");
				} else {
					validGo = 1;
				}
			} catch (Exception e) {
				System.out.println("Please enter only numbers.");
				read.nextLine();
			}
		} while (validGo != 1);
	
		return Integer.valueOf(decision);
	}

/**
	* A void method with no parameters, that clears the output in the terminal.
	* Only for UI improvement.
	*/
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.print(String.format("\033[2J"));
		System.out.flush();
	}
}
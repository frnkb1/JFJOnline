package jfjonline;
/**

	* class LogInManager, a class that manages most of the backend account modifying actions.
	* @author Franklin Bai
	* @version 17.0.3
	*/

import java.io.*;
import java.util.*;
public class LogInManager {

	// a static scanner that can be used anywhere in this class.
	static Scanner read = new Scanner (System.in);

/**
	* A void method that creates a new account using the given username, password, and nameChanges parameters,
	* by calling the constructor of CustomerAccount. That account is then written to the account database file.
	* @param username , a String that determines the username of the account. 
	* @param password , a String that determines the password of the account.
	* @param nameChanges , a integer that determines the number of name changes left.
	* @throws throws an IOException when writing to the accountDatabase goes wrong.
	*/
	public static void createNewAccount (String username, String password, int nameChanges) throws IOException{
		CustomerAccount newAccount = new CustomerAccount (username, password, nameChanges);
		editAccountList(newAccount, false, null);
	}
	
/**
	* A void method that takes in 3 parameters, a CustomerAccount object, a nameChange boolean,
	* and the desired newName. The newName field will essentially be ignored if nameChange boolean
	* is not true. If not true, then simply just add the given CustomerAccounts' attributes to the accountDatabase file.
	* 
	* @param newAccount , a CustomerAccount object that should be the account being created and added to the file.
	* @param nameChange , a boolean that determines the mode of the accountList method. If true, then change name. If false, add account to list.
	* @param nameChange , a String that stores the desired new name.
	* @throws throws an IOException, when there is something wrong reading from the accountDatabase file.
	*/
	public static void editAccountList (CustomerAccount newAccount, boolean nameChange, String newName) throws IOException{
		//using arraylists, because of the .add function. Easier to use, and simpler compared to arrays.
		ArrayList<String> accountNameList = new ArrayList<String>();
		ArrayList<String> accountPasswordList = new ArrayList<String>();
		ArrayList<Integer> accountNameChangesList = new ArrayList<Integer>();
		CustomerAccount[] accounts = getAccounts();

		// if nameChange boolean is true, then change the name of the corresponding account.
		if (nameChange == true) {
			for (int i = 0; i < accounts.length; i++) {
				if (newAccount.getUsername().equals(accounts[i].getUsername())) {	
					// store the old file name.
					File oldFile = new File(newAccount.getUsername() + "Cart.txt");
					
					//change username
					accounts[i].setUsername(newName);
					
					//reame old file name, to new account name.
					File rename = new File(accounts[i].getUsername() + "Cart.txt");
					oldFile.renameTo(rename);
					accounts[i].setNameChangesLeft(accounts[i].getNameChangesLeft() - 1);
					break;
				}
			}
		}
		//using the getters for the CustomerAccount object, and putting them into string arraylists.
		for (int i = 0; i < accounts.length; i++) {
			accountNameList.add(accounts[i].getUsername());
			accountPasswordList.add(accounts[i].getPassword());
			accountNameChangesList.add(accounts[i].getNameChangesLeft());
		}
		//only adding the new account information to the arrays, if method not in nameChange mode.
		if (nameChange == false) {
			accountNameList.add(newAccount.getUsername());
			accountPasswordList.add(newAccount.getPassword());
			accountNameChangesList.add(newAccount.getNameChangesLeft());
		}
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter("accountDatabase.txt", true));
			
			// a new print writer that essentially clears the file, so that we can write new
			// values to it, since all information is stored temporarily in the arraylists.
			PrintWriter clearfile = new PrintWriter("accountDatabase.txt");
			clearfile.close();
			
			// writing to file using the arrays we created.
			for (int i = 0; i < accountNameList.size(); i++) {
				pw.println(accountNameList.get(i) + " " + accountPasswordList.get(i) + " " + accountNameChangesList.get(i));
			}
			
		} catch (IOException e) {
			System.out.println ("Something went wrong while creating your account. Try again later. Here is the error code.");
			System.out.println(e);
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}
	
/**
	* A method with no parameters, that returns an array of all existing CustomerAccount objects stored.
	* This reads all the information in the database file, and sends it to the constructor of CustomerAccount.
	*	
	* @return returns an array of CustomerAccount objects.
	* @throws throws an IOException when something goes wrong while reading from the file.
	*/
	public static CustomerAccount[] getAccounts() throws IOException{
		Scanner line_counter = null;
		Scanner line_reader = null;
		
		CustomerAccount[] accounts = {};
		String[] lines = {};
		int numOfAccounts = 0;
		try {
			// This file reader is used to count the number of lines inside the file, so
			// that we can prepare the array of accounts.
			line_counter = new Scanner(new BufferedReader(new FileReader("accountDatabase.txt")));
			
			// counts the number of lines in the file. The num of lines will be the number
			// of accounts on the file.
			while (line_counter.hasNextLine()) {
				numOfAccounts++;
				line_counter.nextLine();
			}
			lines = new String[numOfAccounts];
			accounts = new CustomerAccount[numOfAccounts];
			
			// Since the previous reader already reached the end of file, a new reader is
			// made, so that we can go through the file again and actually take in information this time.
			// This stores each line in the file in a String array.
			line_reader = new Scanner(new BufferedReader(new FileReader("accountDatabase.txt")));
			int count = 0;
			while (line_reader.hasNextLine()) {
				lines[count] = line_reader.nextLine();
				count++;
			}
			// This seperates the lines taken in from the file by the spaces. The left side is the username,
			// the middle is the password, and the right is the number of name changes left.
			// Using the constructor for the CustomerAccount object, we can create an array of CustomerAccount
			// objects which will be easier to manipulate, since there are methods for getting and setting values for the object.
			for (int i = 0; i < accounts.length; i++) {
				int seperator1 = lines[i].indexOf(" ");
				int seperator2 = lines[i].lastIndexOf(" ");
				String accountName = (lines[i].substring(0, seperator1));
				String accountPassword = (lines[i].substring(seperator1 + 1, seperator2));
				int accountNameChanges = Integer.parseInt((lines[i].substring (seperator2 + 1)));
				CustomerAccount account = new CustomerAccount (accountName, accountPassword, accountNameChanges);
				accounts[i] = account;
			}
			
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (line_reader != null) {
				line_reader.close();
				line_counter.close();
			}
		}
		return accounts;
	}


/**
	*	A method with 2 parameters, that checks that the credentials given, username and password,
	* match that of an account in the accoundDatabase. If matches, then return that specific account.
	* Otherwise, return a null value.
	*
	* @param username , a String that stores the username of the account.
	* @param password , a String that stores the password of the account.
	* @return returns an object of type CustomerAccount, if it exists in the database. Otherwise, return null.
	* @throws throws an IOException when there is an error getting the array of CustomerAccounts.
	*/
	public static CustomerAccount accountValidate(String username, String password) throws IOException{
		CustomerAccount [] accounts = getAccounts();
		
		//compare the username and password given, to each account in the account array. 
		// If found, return that account.
		for (int i = 0; i < accounts.length; i++) {
			if (username.equals(accounts[i].getUsername()) && password.equals(accounts[i].getPassword())) {
				return accounts[i];
			}
		}
		//this will only run if the account locator above does not find an account.
		return null;
	}

/** 
	* A static method with no parameters, that checks if the username already exists. If it is unique,
	* that username is returned. If it already exists, the user will be prompted to try a different one.
	*
	* @return Returns the valid username in a String.
	*	@throws Throws an IO Exception when there is an error getting CustomerAccounts.
	*/
	public static String usernameValidate () throws IOException {
		boolean invalid = true;
		boolean unique = true;
		String username = "";
		CustomerAccount[] accounts = getAccounts();

		//looping checking input
		while (invalid == true) {
			unique = true;
			System.out.println("Create a username. Any spaces will be automatically removed.");
			username = read.nextLine().replaceAll("\\s+", "");

			//searching for username. If not found, then it is unique.
			for (int i = 0; i < accounts.length; i++) {
				if (username.equals(accounts[i].getUsername()))  {
					unique = false;
				}
			}
			if (unique == true) {
				System.out.println("Valid username.");
				invalid = false;
			} else {
				System.out.println("Username has been taken. Try a different one.");	
				System.out.println("[Press enter to try again.]");
				clearScreen();
			}
		}
		return username;
	}
	
	/** 
	* A static method with no parameters, that checks asks for input for a password,
	* and checks that it meets the password requirements. If yes, return that String.
	*
	* @return Returns the valid password in a String.
	*/
	public static String passwordValidate () {
		boolean invalid = true;
		String pass = "";
		
		while (invalid) {
			System.out.println("Create a password. Any spaces will be automatically removed.");
			System.out.println("Your password needs at least 6 characters in length. " 
												+ "it must contain 2 lowercase letters, "
												+ "at least 2 uppercase letters, and at least 1 number. ");
			
			pass = read.nextLine().replaceAll("\\s+", "");

			//password too short
			if (pass.length() < 6) {
				System.out.println("Your password is too short.");

				//Checking everything else, using ascii values.
			} else {
				int upperCount = 0;
				int lowerCount = 0;
				int numCount = 0;
				for (int i = 0; i < pass.length(); i ++  ) {
					if (pass.charAt(i) >= 'A' && pass.charAt(i) <= 'Z') {
						upperCount ++;
					} else if (pass.charAt(i) >= 'a' && pass.charAt(i) <= 'z') {
						lowerCount ++;
					} else if (pass.charAt(i) >= 48 && pass.charAt(i) <= 57) {
						numCount ++;
					}
				}
				if (upperCount >= 2 && lowerCount >= 2 && numCount >= 1) {
					System.out.println("This is a strong password. Success.");
					invalid = false;
				} else {
					System.out.println("Make sure your password meets the requirements to be secure.");
					System.out.println("[Press enter to try again.]");
					read.nextLine();
					clearScreen();
				}
			}
		}
		return pass;
	}

 /**
	 * A void method with no parameters, that clears the output in the terminal.
	 * Only for UI improvement.
	 */
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
}
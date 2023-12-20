/**
	* class ShopManager, that stores methods for the backend management of anything shop related.
	* @author Franklin Bai
	* @version 17.0.3
	*/
import java.io.*;
import java.util.Scanner;
class ShopManager {

/**
	* A method with 1 parameter, the file name, that grabs all items from the given database, and compiles it into an array, then returns it.
	* @param filename , a String that stores the filename that is being accessed. 
	* @return returns an array of Items from the file.
	* @throws throws an IO Exception.
	*/
	public static Item[] grabItems(String filename) throws IOException {
		Scanner line_counter = null;
		Scanner line_reader = null;
		
		Item[] items = {};
		String[] lines = {};
		int numOfItems = 0;
		try {
			// This file reader is used to count the number of lines inside the file, so
			// that we can prepare the array of items.
			line_counter = new Scanner(new BufferedReader(new FileReader(filename)));
			
			// counts the number of lines in the file. The num of lines will be the number
			// of items on the file.
			while (line_counter.hasNextLine()) {
				numOfItems++;
				line_counter.nextLine();
			}
			lines = new String[numOfItems];
			items = new Item[numOfItems];
			
			// Since the previous reader already reached the end of file, a new reader is
			// made, so that we can go through the file again and actually take in information this time.
			// This stores each line in the file in a String array.
			line_reader = new Scanner(new BufferedReader(new FileReader(filename)));
			int count = 0;
			while (line_reader.hasNextLine()) {
				lines[count] = line_reader.nextLine();
				count++;
			}

			for (int i = 0; i < items.length; i++) {
				String seperator = "\\|";
				// splitting each line by the seperator into an array, and putting common attributes into variables.
				String[] itemAttributes = lines[i].split(seperator);
				int itemID = Integer.parseInt(itemAttributes[0]);
				String itemType = itemAttributes[1];
				String brand = itemAttributes[2];
				String itemModel = itemAttributes[3];
				int amtInStock = Integer.parseInt(itemAttributes[4]);
				double price = Double.parseDouble(itemAttributes[5]);
				
				// check what item type is found and run the corresponding constructor.
				if (itemType.equals("CPU")) {
					//run CPU object constructor
					items[i] = new CPU(itemID, itemType, brand, itemModel, amtInStock, price, 
														 itemAttributes[6], itemAttributes[7], itemAttributes[8]);
					
				} else if (itemType.equals("GPU")) {
					///  Run GPU object constructor.
					items[i] = new GPU (itemID, itemType, brand, itemModel, amtInStock,
															price, itemAttributes[6], itemAttributes[7], itemAttributes[8]);
					
				} else if (itemType.equals("RAM")) {
					// Run RAM object constructor.
					items[i] = new RAM (itemID, itemType, brand, itemModel, amtInStock,
															price, itemAttributes[6], itemAttributes[7], itemAttributes[8], itemAttributes[9]);
				}
			}
		} catch (IOException e) {
			System.out.println("error while grabbing items from file. " + e);
		} finally {
			if (line_reader != null) {
				line_reader.close();
				line_counter.close();
			}
		}
		return items;
	}

/**
	* A void method with 1 parameter, that prints out the item list depending on the sort method chosen, using the input given.
	* @param input , an integer that is sent to getSortedItems to pick a specific sort method.
	* @throws throws an IO Exception.
	*/
	public static void printItemList(int input) throws IOException {
		Main.clearScreen();
		System.out.println("Items: ");
		
		//Get list of items from ShopManager class, and the desired sort method.
		Item[] items = getSortedItems(input, ShopManager.grabItems("storeInventory.txt"));
		
		// Print out main attributes of each item.
		for (int i = 0; i < items.length; i++){
			System.out.println("[item ID: " +items[i].getItemID() + "] " + items[i].getItemType() + ", " + items[i].getBrand() 
								 + " " + items[i].getItemModel() + ", price: " + items[i].getPrice()
		 + "$. There are " + items[i].getAmtInStock() + " left in stock.\n");
		}
	}

/**
	* A method with 2 parameters, for the user input and the given Item array to be sorted. It will return
	* the sorted item array, with the sort method decided by the input.
	* @param input , an integer from user input, that determines the sort method.
	* @param items , an Item array that is going to be sorted.
	* @return returns the sorted array of Items.
	* @throws throws an IO Exception.
	*/
	public static Item[] getSortedItems(int input, Item[] items) throws IOException {
		if (input == 1){
			System.out.println("Sorting by default.");
			return items;
		} else if (input == 2){
			System.out.println("Sorting by cheapest first.");
			return sortByPriceAscending(items);
		} else if (input == 3) {
			System.out.println("Sorting by most expensive first.");
			return sortByPriceDescending(items);
		} else if (input == 4) {
		}
		return null;
	}
	//selection sort
	
	/**
	* A method with 1 parameter, the Item array to be sorted. It will return
	* the item array sorted by price descending, using selection sort.
	* @param items , an Item array that is going to be sorted.
	* @return returns the sorted array of Items.
	*/
	public static Item[] sortByPriceDescending(Item[] items) {

		//get price of each item in the given item array.
		double[] prices = new double [items.length];
		for (int i = 0; i < items.length; i ++) {
			prices[i] = items[i].getPrice();
		}
		for (int i = 0; i < prices.length; i++) {
				int max = i;
				for (int k = i + 1; k < prices.length; k++) {
					if (prices[k] > (prices[max])) {
						max = k;
					}
				}
				double tempPrice = prices[i];
				Item tempItem = items[i];
			
				prices[i] = prices[max];
				items[i] = items[max];
			
				prices[max] = tempPrice;
				items[max] = tempItem;
			}
		return items;
	} 

	/**
	* A method with 1 parameter, the Item array to be sorted. It will return
	* the item array sorted by price ascending, using selection sort.
	* @param items , an Item array that is going to be sorted.
	* @return returns the sorted array of Items.
	*/
	public static Item[] sortByPriceAscending(Item[] items) {
		
		//get price of each item in the given item array.
		double[] prices = new double [items.length];
		for (int i = 0; i < items.length; i ++) {
			prices[i] = items[i].getPrice();
		}
		for (int i = 0; i < prices.length; i++) {
				int min = i;
				for (int k = i + 1; k < prices.length; k++) {
					if (prices[k] < (prices[min])) {
						min = k;
					}
				}
				double tempPrice = prices[i];
				Item tempItem = items[i];
			
				prices[i] = prices[min];
				items[i] = items[min];
			
				prices[min] = tempPrice;
				items[min] = tempItem;			
			}
		return items;
	}
}
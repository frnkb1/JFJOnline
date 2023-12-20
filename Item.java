/**
* An abstract class, Item, that is used to create subclasses of it self, which then create objects.
*/

public abstract class Item {

  //instance variables
	int item_ID;
	String item_type;
	String item_model;
	String brand;
	int amt_in_stock;
	double price;

 
/**
  * A constructor for the abstract class Item, with parameters for the item identification code,
	* item type, item name, amount in stock, and the price.
	*
	* @param item_ID - An integer that stores the Item identification code.
	* @param item_type - A string that stores the type of item.
	* @param brand - A string that stores the brand of the item.
  * @param item_model - A String variable that stores the Item's model
  * @param amt_in_stock - A int variable that stores Item's amount in stock
  * @param price - A double variable that stores the Item's price
  */
	public Item (int item_ID, String item_type, String brand, String item_model, int amt_in_stock, double price) {
		this.item_ID = item_ID;
		this.item_type = item_type;
		this.brand = brand;
		this.item_model = item_model;
		this.amt_in_stock = amt_in_stock;
		this.price = price;
	}

  //getters
/**
  * A getter that returns the item name, with no parameters.
  * @return returns the name of the item as a String.
  */
	public int getItemID() {
		return this.item_ID;
	}

/**
  * A getter that returns the item type, with no parameters.
  * @return returns the type of the item as a String.
  */
	public String getItemType(){
		return this.item_type;
	}

/**
  * A getter that returns the item brand, with no parameters.
  * @return returns the brand of the item as a String.
  */
	public String getBrand() {
		return this.brand;
	}

/**
  * A getter that returns the item model, with no parameters.
  * @return returns the model of the item as a String.
  */
	public String getItemModel() {
		return this.item_model;
	}

/**
  * A getter that returns the item's amount in stock, with no parameters.
  * @return returns the amount of stock of the item as a integer.
  */
	public int getAmtInStock() {
		return this.amt_in_stock;
	}

/**
  * A getter that returns the price of the item, with no parameters.
  * @return returns the price of the item as a double.
  */
	public double getPrice() {
		return this.price;
	}

/**
  * A void setter method that sets the amount in stock, with 1 parameter that determines the the desired stock count.
  * @param desiredStockCount , a int that determines the new stock count.
  */
	public void setAmtInStock(int desiredStockCount) {
		this.amt_in_stock = desiredStockCount;
	}

/**
	* A method with no parameters that forces the subclass to inherit this. It must return a type String.
	* It should contain a description of the item.
	*	@return returns the description of item in a String.
	*/
	@Override
	public abstract String toString();
}
/**
	* class RAM, that extends the abstract Item class, to create a RAM object.
	* @author Franklin Bai
	* @version 17.0.3
	*/

class RAM extends Item {
	
	//instance variables
	String memory_amt = "GB";
	String memory_speed = "mhz";
	String memory_type = "DDR(num)";
	String pin_config = "xxx pin";

	//Constructor
  /**
  * A constructor for RAM that creates a RAM object, using the super constructor from its inherited class, Item.
	* It has parameters for name, amount in stock, price, memory amount, memory speed, memory type, and pin configuration.
	* @param item_ID - An integer variable that stores the item indentification number.
	* @param brand - A String variable that stores the brand of the RAM.
  * @param item_model - A String variable that stores the RAM model.
  * @param amt_in_stock - A int variable that stores RAM's in stock.
  * @param price - A double variable that stores the RAM's price.
  * @param memory_amt - A String variable that stores the Memory amount.
  * @param memory_speed - A String variable that stores the Memory speed.
  * @param memory_type - A String variable that stores the Memory type.
  * @param pin_config - A String variable that stores the RAM's pin configuration.
  */
	public RAM (int item_ID, String item_type, String brand, String item_model, int amt_in_stock, 
							double price, String memory_amt, String memory_speed, String memory_type, String pin_config) {
    //Super constructor
		super (item_ID, item_type, brand, item_model, amt_in_stock, price);
		this.memory_amt = memory_amt;
		this.memory_speed = memory_speed;
		this.memory_type = memory_type;
		this.pin_config = pin_config;
	}

	//getters
/**
  * A getter that returns the memory amount, with no parameters.
  * @return returns the amount of memory in stock.
  */
	public String getMemoryAmt() {
		return this.memory_amt;
	}

/**
  * A getter that returns the memory speed, with no parameters.
  * @return returns the speed of the memory.
  */
	public String getMemorySpeed(){
		return this.memory_speed;
	}

/**
  * A getter that returns the memory type, with no parameters.
  * @return returns the type of memory
  */
	public String getMemoryType () {
		return this.memory_type;
	}

/**
  * A getter that returns the memory's pin configuration, with no parameters.
  * @return returns the pin figuration of the memory.
  */
	public String getPinConfig() {
		return this.pin_config;
	}

//setters
/**
  * A void setter method, that sets the memory amount with 1 parameter that determines the desired memory amount.
  * @param memory_amt, a String that determines the memory amount.
  */  
	public void setMemoryAmt(String memory_amt) {
		this.memory_amt = memory_amt;
	}

/**
  * A void setter method, that sets the memory speed with 1 parameter that determinesthe desired memory speed.
  * @param memory_speed, a String that determines the memory speed.
  */  
	public void setMemorySpeed(String memory_speed){
		this.memory_speed = memory_speed;
	}

/**
  * A void setter method, that sets the memory type with 1 parameter that determines the desired memory type.
  * @param memory_type, a String that determines the memory type.
  */  
	public void setMemoryType (String memory_type) {
		this.memory_type = memory_type;
	}

  /**
  * A void setter method that sets the memory pin configuration with 1 parameter that determines the the desired pin configuration.
  * @param pin_config, a String that determines the pin configuration.
  */  
	public void setPinConfig (String pin_config) {
		this.pin_config = pin_config;
	}
	
/**
	*	A method that returns the details of the RAM in a description format, as a String. No parameters.
	* This overrides the native java toString method.
	* @return returns a description of the RAM.
	*/
	@Override
	public String toString() {
		Main.clearScreen();
		String description = this.brand + " " + this.item_type + " " + this.item_model + "\n" +
													"Price: " + this.price + "$ \n" +
													"Amount left in stock: " + this.amt_in_stock + " items\n" +
													"\n------------ Specifications ------------\n" +
													"RAM memory capacity: " + this.memory_amt +
													"\nRAM clock speed: " + this.memory_speed +
													"\nMemory type: " + this.memory_type +
													"\nRAM pin configuration: " + this.pin_config +
													"\nItem ID: " + this.item_ID;
													
		return description;
	}
}



	

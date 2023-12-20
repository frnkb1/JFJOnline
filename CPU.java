/**
	* class CPU, that extends the abstract Item class, to create a CPU object.
	* @author Franklin Bai
	* @version 17.0.3
	*/

class CPU extends Item {

	//instance variables
	String cpu_base_speed = "GHz";
  String cpu_boost_speed = "GHz";
  String core_count = "x cores";
	
	//Constructor
  /**
  * A constructor for CPU that creates a CPU object, using the super constructor from its inherited class, Item.
	* It has parameters for name, amount in stock, price, cpu cache, cpu clock speed and core count.
  * 
	* @param item_ID - An int variable that stores the identification number of the item.
	* @param item_type - A String that stores the specific subclass type of Item.
	* @param brand - A String that stores the brand of the item.
	* @param item_model - A String variable that stores the CPU name
  * @param amt_in_stock - A int variable that stores CPU's in stock
  * @param price - A double variable that stores the CPU's price
  * @param cpu_base_speed - A String variable that stores the CPU base clock speed
  * @param cpu_boost_speed - A String variable that stores the CPU boost clock speed
  * @param core_count - A String variabe that stores the core count
  */
	public CPU (int item_ID, String item_type, String brand, String item_model, int amt_in_stock,
							double price, String cpu_base_speed, String cpu_boost_speed, String core_count) {
		super (item_ID,item_type, brand, item_model, amt_in_stock, price);
		this.cpu_base_speed = cpu_base_speed;
    this.cpu_boost_speed = cpu_boost_speed;
		this.core_count = core_count;
	}
	
	//getters
/**
  * A getter that returns the base clock speed of the cpu, with no parameters.
  * @return returns the base clock speed of the cpu in a String.
  */
	public String getCpuBaseSpeed(){
		return this.cpu_base_speed;
	}

/**
  * A getter that returns the boost clock speed of the cpu, with no parameters.
  * @return returns the boost clock speed of the cpu in a String.
  */
  public String getCpuBoostSpeed(){
		return this.cpu_boost_speed;
	}

/**
  * A getter that returns the core count of the cpu, with no parameters.
  * @return returns the core count of the cpu in a String.
  */
	public String getCoreCount() {
		return this.core_count;
	}

//setters
/**
	* A void setter method that sets cpu clock speed, with 1 parameter that determines the base clock speed.
	* @param cpu_clock_speed , a String that determines the cpu clock speed.
	*/  
  public void setCpuBaseSpeed(String cpu_base_speed) {
		this.cpu_base_speed = cpu_base_speed;
	}
/**
	* A void setter method that sets cpu boost speed, with 1 parameter that determines the the desired stock count.
	* @param cpu_clock_speed , a String that determines the cpu boost speed.
	*/  
	public void setCpuBoostSpeed(String cpu_boost_speed) {
		this.cpu_boost_speed = cpu_boost_speed;
	}
  
/**
	* A void setter method that sets cpu core count, with 1 parameter that determines the the desired stock count.
	* @param core_count , a String that determines the cpu core count.
	*/  
	public void setCoreCount (String core_count) {
		this.core_count = core_count;
	}

/**
	*	A method that returns the details of the CPU in a description format, as a String. No parameters.
	* This overrides the native java toString method.
	* @return returns a description of the CPU.
	*/
	@Override
	public String toString () {
		Main.clearScreen();
		String description = this.brand + " " + this.item_type + " " + this.item_model + "\n" +
													"Price: " + this.price + "$ \n" +
													"Amount left in stock: " + this.amt_in_stock + " items\n" +
													"\n------------ Specifications ------------\n" +
													"CPU Base clock speed: " + this.cpu_base_speed +
													"\nCPU Boost clock speed: " + this.cpu_boost_speed +
													"\nNumber of cores: " + this.core_count +
													"\nItem ID: " + this.item_ID;
		
		return description;
	}
}
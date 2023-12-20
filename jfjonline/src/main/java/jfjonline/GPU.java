package jfjonline;
/**
	* class GPU, that extends the abstract Item class, to create a GPU object.
	* @author Franklin Bai
	* @version 17.0.3
	*/
public class GPU extends Item {

	//instance variables
	String vram_amt = "GB";
	String gpu_clock_speed = "mhz";
	String fan_count = "0";

/**
  * A constructor for GPU that creates a GPU object, using the super constructor from its inherited class, Item.
	* It has parameters for name, amount in stock, price, vram amount, gpu clock speed, and fan count.
	*
  * @param item_name - A String variable that stores the GPU name
  * @param amt_in_stock - An int variable that stores GPU's in stock
  * @param price - A double variable that stores the GPU's price
  * @param vram_amt - A String variabe that stores the Vram amount
  * @param gpu_clock_speed - A String variabe that stores the GPU clock speed
  * @param fan_count - A String variabe that stores the fan count
  */
	public GPU (int item_ID, String item_type,String brand, String item_model, int amt_in_stock, 
							double price, String vram_amt, String gpu_clock_speed, String fan_count) {
		super (item_ID, item_type, brand, item_model, amt_in_stock, price);
		this.vram_amt = vram_amt;
		this.gpu_clock_speed = gpu_clock_speed;
		this.fan_count = fan_count;
	}

	//getters
/**
  * A getter that returns the amount of the Vram, with no parameters.
  * @return returns the amount of the Vram in a String.
  */
	public String getVramAmt() {
		return this.vram_amt;
	}

/**
  * A getter that returns the clock speed of the gpu, with no parameters.
  * @return returns the clock speed of the gpu in a String.
  */
	public String getGpuClockSpeed(){
		return this.gpu_clock_speed;
	}

/**
  * A getter that returns the fan count of the gpu, with no parameters.
  * @return returns the fan count of the gpu in a String.
  */
	public String getFanCount () {
		return this.fan_count;
	}

//setters
/**
  * A void setter method that sets the amount of Vram, with 1 parameter that determines the desired vram.
  * @param vram_amt , a String that determines the vram amount.
  */
	public void setVramAmt(String vram_amt) {
		this.vram_amt = vram_amt;
	}

/**
  * A void setter method that sets the gpu clock speed, with 1 parameter that determines the desired GPU clock speed.
  * @param gpu_clock_speed, a String that determines the gpu clock speed.
  */
	public void setGpuClockSpeed(String gpu_clock_speed){
		this.gpu_clock_speed = gpu_clock_speed;
	}

/**
  * A void setter method that sets the fan count, with 1 parameter that determines the desired fan number of fans.
  * @param fan_count , a String that determines the new fan count.
  */
	public void setFanCount (String fan_count) {
		this.fan_count = fan_count;
	}
	
/**
	*	A method that returns the details of the GPU in a description format, as a String. No parameters.
	* This overrides the native java toString method.
	* @return returns a description of the GPU.
	*/
	@Override
	public String toString() {
		Main.clearScreen();
		String description = this.brand + " " + this.item_type + " " + this.item_model + "\n" +
													"Price: " + this.price + "$ \n" +
													"Amount left in stock: " + this.amt_in_stock + " items\n" +
													"\n------------ Specifications ------------\n" +
													"GPU VRAM amount: " + this.vram_amt +
													"\nGPU clock speed: " + this.gpu_clock_speed +
													"\nNumber of fans on GPU: " + this.fan_count +
													"\nItem ID: " + this.item_ID;
													
		return description;
	}
}

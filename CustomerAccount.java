// allows them to create an account with a username and password

class CustomerAccount {

  //instance variables
	String username;
	private String password;
	int name_changes_left = 0;

  //Constructor
  /**
  * A constructor for creating a customer acoount, with parameters for a username, password, and amount of name changes left.
  * @param username - A String variable that stores the customer's username
  * @param password - A String variable that stores the customer's password
	* @param name_changes_left - A integer 
  */
  public CustomerAccount(String username, String password, int name_changes_left) {
		this.username = username;
		this.password = password;
		this.name_changes_left = name_changes_left;
	}

  //getters
  /**
  * A getter that returns the username of the customer, with no parameters.
  * @return returns the username of the customer in a String.
  */
	public String getUsername() {
		return this.username;
	}

  /**
  * A getter that returns the password of the customer, with no parameters.
  * @return returns the password of the customer in a String.
  */
	public String getPassword() {
		return this.password;
	}

  /**
  * A getter that returns the remaining name changes of the customer, with no parameters.
  * @return returns remaining name changes of the customer in a int.
  */
	// change password, requires original password.
	public int getNameChangesLeft() {
		return this.name_changes_left;
	}


/**
	* A void setter method with 1 parameter, that sets the original username to the new username given.
  * @param newUsername - A String variable that determines the customer's new username
  */
	public void setUsername(String newUsername) {
		this.username = newUsername;
	}

/**
	* A void setter method with 1 parameter, that sets the amount of name changes left.
  * @param name_changes_left - A int variable that determines the customer's amount changes left
  */
	public void setNameChangesLeft(int name_changes_left) {
		this.name_changes_left = name_changes_left;
	}
}
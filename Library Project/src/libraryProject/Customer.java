package libraryProject;

import java.text.DecimalFormat;

public class Customer {
	private long customerID;
	private String customerName;
	private Address customerAddress = new Address();
	private long customerPhone = 0;
	private String customerEmail;
	private Book[] customerBooksBorrowed = new Book[0];
	private double fines = 0.0;
	private boolean canCheckOut = true;
	
	/** Constructor to build a Customer with only a customerID number
	 * @param customerID
	 */
	public Customer (long customerID) {
		this.customerID = customerID;
	}
	
	/** Constructor to build a Customer with all customer data and an Address object without customerID and the 
	 * customerBooksBorrowed array.
	 * @param customerName
	 * @param customerAddress

	 * @param customerPhone
	 * @param customerEmail
	 * @param fines
	 * @param canCheckOut
	 */
	public Customer(String customerName, Address customerAddress, long customerPhone, String customerEmail,
			double fines, boolean canCheckOut) {
		super();
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.customerPhone = customerPhone;
		this.customerEmail = customerEmail;
		this.fines = fines;
		this.canCheckOut = canCheckOut;
	}
	
	/** Constructor to build a Customer with all customer and address data without customerID and the 
	 * customerBooksBorrowed array.
	 * @param customerName
	 * @param customerStreet1
	 * @param customerStreet2
	 * @param customerCity
	 * @param customerState
	 * @param customerZip
	 * @param customerZipPlus4
	 * @param customerPhone
	 * @param customerEmail
	 * @param fines
	 * @param canCheckOut
	 */
	public Customer(String customerName, String customerStreet1, String customerStreet2, String customerCity, 
			String customerState, int customerZip, int customerZipPlus4, long customerPhone, String customerEmail,
			double fines, boolean canCheckOut) {
		super();
		this.customerName = customerName;
		this.customerAddress.setAddressStreet1(customerStreet1);
		this.customerAddress.setAddressStreet2(customerStreet2);
		this.customerAddress.setAddressCity(customerCity);
		this.customerAddress.setAddressState(customerState);
		this.customerAddress.setAddressZip(customerZip);
		this.customerAddress.setAddressZipPlus4(customerZipPlus4);
		this.customerAddress.setAddressStreet1(customerStreet1);
		this.customerPhone = customerPhone;
		this.customerEmail = customerEmail;
		this.fines = fines;
		this.canCheckOut = canCheckOut;
	}
	
	/** Constructor to build a Customer with all customer data and an Address object
	 * @param customerID
	 * @param customerName
	 * @param customerStreet1
	 * @param customerStreet2
	 * @param customerCity
	 * @param customerState
	 * @param customerZip
	 * @param customerZipPlus4
	 * @param customerPhone
	 * @param customerEmail
	 * @param customerBooksBorrowed
	 * @param fines
	 * @param canCheckOut
	 */
	public Customer(long customerID, String customerName, Address customerAddress, long customerPhone, 
			String customerEmail, Book[] customerBooksBorrowed, double fines, boolean canCheckOut) {
		super();
		this.customerID = customerID;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.customerPhone = customerPhone;
		this.customerEmail = customerEmail;
		this.customerBooksBorrowed = customerBooksBorrowed;
		this.fines = fines;
		this.canCheckOut = canCheckOut;
	}

	/** Constructor to build a Customer with all customer data
	 * @param customerID
	 * @param customerName
	 * @param customerStreet1
	 * @param customerStreet2
	 * @param customerCity
	 * @param customerState
	 * @param customerZip
	 * @param customerZipPlus4
	 * @param customerPhone
	 * @param customerEmail
	 * @param customerBooksBorrowed
	 * @param fines
	 * @param canCheckOut
	 */
	public Customer(long customerID, String customerName, String customerStreet1, String customerStreet2, 
			String customerCity, String customerState, int customerZip, int customerZipPlus4, long customerPhone, 
			String customerEmail, Book[] customerBooksBorrowed, double fines, boolean canCheckOut) {
		super();
		this.customerID = customerID;
		this.customerName = customerName;
		this.customerAddress.setAddressStreet1(customerStreet1);
		this.customerAddress.setAddressStreet2(customerStreet2);
		this.customerAddress.setAddressCity(customerCity);
		this.customerAddress.setAddressState(customerState);
		this.customerAddress.setAddressZip(customerZip);
		this.customerAddress.setAddressZipPlus4(customerZipPlus4);
		this.customerAddress.setAddressStreet1(customerStreet1);
		this.customerPhone = customerPhone;
		this.customerEmail = customerEmail;
		this.customerBooksBorrowed = customerBooksBorrowed;
		this.fines = fines;
		this.canCheckOut = canCheckOut;
	}

	public long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public long getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(long customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public Book[] getCustomerBooksBorrowed() {
		return customerBooksBorrowed;
	}

	public void setCustomerBooksBorrowed(Book[] customerBooksBorrowed) {
		this.customerBooksBorrowed = customerBooksBorrowed;
	}

	public double getFines() {
		return fines;
	}

	public void setFines(double fines) {
		this.fines = fines;
	}

	public boolean isCanCheckOut() {
		return canCheckOut;
	}

	public void setCanCheckOut(boolean canCheckOut) {
		this.canCheckOut = canCheckOut;
	}
	
	/** Method promptUserForCustomerInfo uses dialog boxes to prompt the user for the customer name, 
	 * 	street number and name, additional street info (if needed), city, state, zip code, zip code+4 (if known),
	 * 	customer e-mail, and if the new customer can check out books right away. 
	 * 
	 * @param Customer newCustomer - new customer object shell with customerID assigned
	 */
	public boolean promptUserForCustomerInfo() {
		// Skip the function if the newCustomer.customerID is not assigned.
		if (this.getCustomerID() != 0) {
			userCancelled:
			do {
				String firstName = DialogInput.getInputString("Please enter the customer's first name:");
				// If DialogInput return is null, then the user pressed Cancel, so break out of the loop.
				if (firstName == null) {
					break userCancelled;
				}
				// Remove all white space from the string
				firstName = firstName.replaceAll("\\s", "");
				String lastName = DialogInput.getInputString("Please enter the customer's last name:");
				// If DialogInput return is null, then the user pressed Cancel, so break out of the loop.
				if (lastName == null) {
					break userCancelled;
				}
				// Remove all white space from the string
				lastName = lastName.replaceAll("\\s", "");
				this.customerName = firstName + " " + lastName;
				
				if (!this.customerAddress.promptUsertoUpdateAddressInfo()) {
					return false;
				}
				
				Long phone = DialogInput.getInputLong("Please enter the customer's phone number:", 10);
				// If DialogInput return is null, then the user pressed Cancel, so break out of the loop.
				if (phone == null) {
					break userCancelled;
				}
				this.customerPhone = phone;
				
				String email = DialogInput.getInputString("Please enter the customer's email address:");
				// If DialogInput return is null, then the user pressed Cancel, so break out of the loop.
				if (email == null) {
					break userCancelled;
				}
				this.customerEmail = email;
								
				this.fines = 0.0;
				this.canCheckOut = DialogInput.getInputBoolean("Is this user allowed to check out books right away?");
				System.out.println("promptUserForCustomerInfo - customer information updated");	
				// All inputs received without user canceling.
				return true;
//TODO display customer information and re-prompt if the information is incorrect
			} while (false);	
		}
		System.out.println("promptUserForCustomerInfo - customer information invalid");
		this.customerID = 0;
		return false;
	}
	
	/** Method generateNewCustomer randomly generates a new customer with an address and phone number from 
	 * text files containing first and last names, street names, cities, zip codes, and associated area codes.
	 * @return
	 */
	public boolean generateNewCustomer() {
		CustomerInfoGenerator gen = new CustomerInfoGenerator();
		if (gen.isValid()) {
			this.customerName = (gen.getFirstName() + " " + gen.getLastName());
			this.customerAddress.setAddressStreet1(gen.getStreetNumberName());
			this.customerAddress.setAddressCity(gen.getCity());
			this.customerAddress.setAddressState(gen.getState());
			this.customerAddress.setAddressZip(Integer.parseInt(gen.getZipCode()));
			this.customerPhone = Long.parseLong(gen.getPhoneNumber());
			this.customerEmail = gen.getEmail();
			
			return true;
		}
		return false;
	}
	
	/** Method isValidCustomer checks the current Customer object to ensure all properties have non-blank or null values.
	 * 	It prints the customer to the console and if invalid, the first reason why it was found to be invalid.
	 * @param isNewCustomer - true if the Customer is new so it does not check for a customer ID.
	 * @return boolean - true if the customer information is valid, otherwise false.
	 */
	public boolean isValidCustomer(boolean isNewCustomer) {
		// Print this customer information to the console.
		System.out.println(this.toString());
		if (!isNewCustomer) {
			if ((Long)this.customerID == null || this.customerID == 0) {
				System.out.println("isValidCustomer - existing customer has no customerID.");
				return false;
			}
		}
		if (this.customerName.isBlank() || this.customerName.isEmpty()) {
			System.out.println("isValidCustomer - customer has no name.");
			return false;
		}
		if (!this.customerAddress.isValidAddress(isNewCustomer)) {
			return false;
		}
		if (this.customerPhone == 0L || Long.toString(this.customerPhone).length() != 10) {
			System.out.println("isValidCustomer - customer phone number information is invalid.");
			return false;
		}
		if (this.customerEmail.isBlank() || this.customerEmail.isEmpty()) {
			System.out.println("isValidCustomer - customer has no e-mail address information.");
			return false;
		}
		System.out.println("isValidCustomer - customer is valid.");
		return true;
	}
	
	/** Method addCheckedOutBook adds currentBook to the end of the customerBooksBorrowed array and returns true.
	 * 	If the Book is not set as checked out or the dueDate is not set, then it does not add the book and returns false.
	 * @param currentBook
	 * @return boolean - true if the book was added, false if it was not.
	 */
	public boolean addCheckedOutBook(Book currentBook) {
		if (currentBook.isCheckedOut() || currentBook.getDueDate() != null) {
			customerBooksBorrowed = resizeArray(customerBooksBorrowed, customerBooksBorrowed.length + 1);
			customerBooksBorrowed[customerBooksBorrowed.length - 1] = currentBook;
			return true;
		}
		return false;
	}

	/** Method resizeArray takes an array and re-sizes the array to the desiredLength.
	 * 	It discards any elements beyond the desired length.
	 * 
	 * @param Book[] inputArray, int desiredLength
	 * @return Book[]
	 */
	private static Book[] resizeArray(Book[] inputArray, int desiredLength) {
		// Create a new array of the desired length
		Book[] resizedArray = new Book[desiredLength];
		// Step through the input array and assign input values to the resized array.
		for (int index = 0; index < desiredLength; index++) {
			// If the desired length is longer than the input array, put nulls in the remaining elements of the resized array.
			if (index < inputArray.length) {
				resizedArray[index] = inputArray[index];
			} else {
				resizedArray[index] = null;
			}
		}
		return resizedArray;
	}
	
	/** Method numberOfBooks searches the customerBooksBorrowed Array and returns the numberOfBooks 
	 * @return Integer the requested number of books this customer has checked out
	 */
	public Integer numberOfBooks() {
		return customerBooksBorrowed.length;
	}
	
	/** Method toString overrides the default Object.toString to summarize the customer.
	 * @return String with the salient details of the customer
	 */
	@Override
	public String toString() {
		String outputString = "Customer #" + this.customerID + " " + this.customerName + "\n";
		outputString += this.customerAddress.toString();
		String phoneString = Long.toString(this.customerPhone);
		if (phoneString.length() < 10) {
			DecimalFormat paddedPhone = new DecimalFormat("0000000000");
			phoneString = paddedPhone.format(this.customerPhone);
		} 
		if (phoneString.length() == 10) {
			phoneString = "(" + phoneString.subSequence(0, 3) + ") " + phoneString.subSequence(3, 6) + "-" + phoneString.subSequence(6, 10);
		} 
		outputString += " Phone " + phoneString;
		outputString += "\nE-mail  " + this.customerEmail + "\nThey have " 
			+ this.customerBooksBorrowed.length + " books checked out.\n";
		if (this.canCheckOut) {
			outputString += "They are allowed to check out books. \n";
		} else {
			outputString += "They are not allowed to check out books. \n";
		}
		outputString += "They have the following books checked out:";
		for (int index = 0; index < this.customerBooksBorrowed.length; index++) {
			outputString += "\n" + customerBooksBorrowed[index].toString();
		}
		return outputString;
	}

	public Address getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(Address customerAddress) {
		this.customerAddress = customerAddress;
	}
}

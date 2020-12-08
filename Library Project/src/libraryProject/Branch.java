package libraryProject;

public class Branch {
	private String branchName;
	private Address branchAddress = new Address();
	private String associatedLibrary;
	private Book[] booksAssignedHere = new Book[0];

	/**
	 * Constructor to build Branch with just the name.
	 * 
	 * @param branchName
	 */
	public Branch(String branchName, String associatedLibrary) {
		this.branchName = branchName;
		this.associatedLibrary = associatedLibrary;
	};

	/**
	 * Constructor to build Branch with branch name, associated library and an
	 * Address object without the list of booksAssignedHere
	 * 
	 * @param branchName
	 * @param Address
	 * @param associatedLibrary
	 */
	public Branch(String branchName, Address branchAddress, String associatedLibrary) {
		this.branchName = branchName;
		this.branchAddress.setAddressStreet1(branchAddress.getAddressStreet1());
		this.branchAddress.setAddressStreet2(branchAddress.getAddressStreet2());
		this.branchAddress.setAddressCity(branchAddress.getAddressCity());
		this.branchAddress.setAddressState(branchAddress.getAddressState());
		this.branchAddress.setAddressZip(branchAddress.getAddressZip());
		this.branchAddress.setAddressZipPlus4(branchAddress.getAddressZipPlus4());
		this.associatedLibrary = associatedLibrary;
	}

	/**
	 * Constructor to build Branch with all but the list of booksAssignedHere
	 * 
	 * @param branchName
	 * @param branchStreet1
	 * @param branchStreet2
	 * @param branchCity
	 * @param branchState
	 * @param branchZip
	 * @param branchZipPlus4
	 * @param associatedLibrary
	 */
	public Branch(String branchName, String branchStreet1, String branchStreet2, String branchCity, String branchState,
			int branchZip, int branchZipPlus4, String associatedLibrary) {
		this.branchName = branchName;
		this.branchAddress.setAddressStreet1(branchStreet1);
		this.branchAddress.setAddressStreet2(branchStreet2);
		this.branchAddress.setAddressCity(branchCity);
		this.branchAddress.setAddressState(branchState);
		this.branchAddress.setAddressZip(branchZip);
		this.branchAddress.setAddressZipPlus4(branchZipPlus4);
		this.associatedLibrary = associatedLibrary;
	}

	/**
	 * Constructor to build Branch with all properties including the array of
	 * booksAssigned here.
	 * 
	 * @param branchName
	 * @param branchStreet1
	 * @param branchStreet2
	 * @param branchCity
	 * @param branchState
	 * @param branchZip
	 * @param branchZipPlus4
	 * @param associatedLibrary
	 * @param booksAssignedHere
	 */
	public Branch(String branchName, String branchStreet1, String branchStreet2, String branchCity, String branchState,
			int branchZip, int branchZipPlus4, String associatedLibrary, Book[] booksAssignedHere) {
		super();
		this.branchName = branchName;
		this.branchAddress.setAddressStreet1(branchStreet1);
		this.branchAddress.setAddressStreet2(branchStreet2);
		this.branchAddress.setAddressCity(branchCity);
		this.branchAddress.setAddressState(branchState);
		this.branchAddress.setAddressZip(branchZip);
		this.branchAddress.setAddressZipPlus4(branchZipPlus4);
		this.associatedLibrary = associatedLibrary;
		this.booksAssignedHere = booksAssignedHere;
	}

	/**
	 * Constructor to build Branch with branch name, associated library
	 * booksAssignedHere array and an Address object
	 * 
	 * @param branchName
	 * @param Address
	 * @param associatedLibrary
	 * @param booksAssignedHere
	 */
	public Branch(String branchName, Address branchAddress, String associatedLibrary, Book[] booksAssignedHere) {
		this.branchName = branchName;
		this.branchAddress.setAddressStreet1(branchAddress.getAddressStreet1());
		this.branchAddress.setAddressStreet2(branchAddress.getAddressStreet2());
		this.branchAddress.setAddressCity(branchAddress.getAddressCity());
		this.branchAddress.setAddressState(branchAddress.getAddressState());
		this.branchAddress.setAddressZip(branchAddress.getAddressZip());
		this.branchAddress.setAddressZipPlus4(branchAddress.getAddressZipPlus4());
		this.associatedLibrary = associatedLibrary;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getAssociatedLibrary() {
		return associatedLibrary;
	}

	public void setAssociatedLibrary(String associatedLibrary) {
		this.associatedLibrary = associatedLibrary;
	}

	public Book[] getBooksAssignedHere() {
		return booksAssignedHere;
	}

	public void setBooksAssignedHere(Book[] booksAssignedHere) {
		this.booksAssignedHere = booksAssignedHere;
	}

	/**
	 * Method promptUserForBranchInfo uses dialog boxes to prompt the user for the
	 * book title, author, ISBN, number of pages, genre, assign a branch where the
	 * book is held.
	 * 
	 * @param Customer newCustomer - new customer object shell with customerID
	 *                 assigned
	 */
	public boolean promptUserForBranchInfo() {
		userCancelled: do {
			String branchName = DialogInput.getInputString("Please enter the name of this branch of the library:");
			// If DialogInput return is null, then the user pressed Cancel, so break out of
			// the loop.
			if (branchName == null) {
				break userCancelled;
			}
			this.branchName = branchName;

			String streetName1 = DialogInput.getInputString("Please enter the branch's street number and street name:");
			// If DialogInput return is null, then the user pressed Cancel, so break out of
			// the loop.
			if (streetName1 == null) {
				break userCancelled;
			}
			streetName1 = streetName1.toUpperCase();
			this.branchAddress.setAddressStreet1(streetName1);

			String streetName2 = DialogInput.getInputString("Please enter the branch's additional street information "
					+ "(e.g. Building#, etc.):\n" + "Click Cancel if there is no additional street information.");
			// If DialogInput return is null, then the user pressed Cancel, so break out of
			// the loop.
			if (streetName2 == null) {
				streetName2 = "";
			}
			streetName2 = streetName2.toUpperCase();
			this.branchAddress.setAddressStreet2(streetName2);

			String city = DialogInput.getInputString("Please enter the branch's city:");
			// If DialogInput return is null, then the user pressed Cancel, so break out of
			// the loop.
			if (city == null) {
				break userCancelled;
			}
			city = city.toUpperCase();
			this.branchAddress.setAddressCity(city);

			String state = DialogInput
					.getInputState("Please enter the two characters of the US State, Commonwealth, or Territory \n"
							+ "(e.g. IL for Illinois):");
			// If DialogInput return is null, then the user pressed Cancel, so break out of
			// the loop.
			if (state == null) {
				break userCancelled;
			}
			this.branchAddress.setAddressState(state);

			Integer zip = DialogInput.getInputInt("Please enter the branch's 5 digit zip code:", 5);
			// If DialogInput return is null, then the user pressed Cancel, so break out of
			// the loop.
			if (zip == null) {
				break userCancelled;
			}
			this.branchAddress.setAddressZip(zip);

			Integer zipPlusFour = DialogInput.getInputInt("Please enter the branch's 4 digit zip+4:", 4);
			// If DialogInput return is null, then the user pressed Cancel, so break out of
			// the loop.
			if (zipPlusFour == null) {
				zipPlusFour = 0;
			}
			this.branchAddress.setAddressZipPlus4(zipPlusFour);
			System.out.println("promptUserForBranchInfo - branch information updated");
			// All inputs received without user canceling.
			return true;
		} while (false);
		System.out.println("promptUserForBranchInfo - branch information invalid");
		this.branchName = "";
		return false;
	}
	
	/** Method generateNewBranch randomly generates a new branch with an address from text files 
	 * containing first and last names, street names, cities, zip codes, and associated area codes.
	 * If successful, returns true, otherwise returns false.
	 * @return
	 */
	public boolean generateNewBranch() {
		BranchInfoGenerator gen = new BranchInfoGenerator();
		if (gen.isValid()) {
			this.branchName = gen.getBranchName();
			this.branchAddress.setAddressStreet1(gen.getStreetNumberName());
			this.branchAddress.setAddressCity(gen.getCity());
			this.branchAddress.setAddressState(gen.getState());
			this.branchAddress.setAddressZip(Integer.parseInt(gen.getZipCode()));
			return true;
		}
		return false;
	}

	/**
	 * Method addBook adds the bookID to the branch's array of BookIDs assigned here
	 * as long as it is not in the list already.
	 * 
	 * @param long bookID
	 * @return boolean - true if the bookID was added successfully, false if it was
	 *         not.
	 */
	public boolean addBook(Book newBook) {
		if (isBookAlreadyAssigned(newBook)) {
			return false;
		}
		// If there are no duplicates, then expand the booksAssignedHere array, assign
		// the newBook to the last
		// element and then bubble sort the list.
		this.booksAssignedHere = resizeArray(this.booksAssignedHere, this.booksAssignedHere.length + 1);
		this.booksAssignedHere[this.booksAssignedHere.length - 1] = newBook;
		this.booksAssignedHere = bubbleSortByBookID(this.booksAssignedHere, true);
		// newBook successfully added to this Branch.
		return true;
	}
	
	/** Method isBookAlreadyAssigned takes a book and compares it to the bookIDs stored in the booksAssignedHere array
	 * and returns true if it is found, false if it is not found.
	 * @param bookToFind
	 */
	public boolean isBookAlreadyAssigned (Book bookToFind) {
		if (this.booksAssignedHere.length != 0) {
			// Search for duplicate bookIDs in the list of books assigned to the branch
			for (int index = 0; index < this.booksAssignedHere.length; index++) {
				// If the new bookID is a duplicate, the parameter is invalid, so return false.
				if (this.booksAssignedHere[index].getBookID() == bookToFind.getBookID()) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Method numberOfBooks searches the booksAssignedHere and returns the
	 * numberOfBooks determined by totalOutOrIn
	 * 
	 * @param int totalOutOrIn- 0 for all books in the branch, 1 for all books
	 *            checked out, 2 for all books checked in
	 * @return Integer the requested number of books in this Branch, otherwise
	 *         returns null
	 */
	public Integer numberOfBooks(int totalOutOrIn) {
		switch (totalOutOrIn) {
		case 0:
			return booksAssignedHere.length;
		case 1:
			int numCheckedOut = 0;
			for (int index = 0; index < this.booksAssignedHere.length; index++) {
				if (booksAssignedHere[index].isCheckedOut()) {
					numCheckedOut++;
				}
			}
			return numCheckedOut;
		case 2:
			int numCheckedIn = 0;
			for (int index = 0; index < this.booksAssignedHere.length; index++) {
				if (!booksAssignedHere[index].isCheckedOut()) {
					numCheckedIn++;
				}
			}
			return numCheckedIn;
		default:
			return (Integer) null;
		}
	}

	/**
	 * Method resizeArray takes an array and re-sizes the array to the
	 * desiredLength. It discards any elements beyond the desired length.
	 * 
	 * @param Book[] inputArray, int desiredLength
	 * @return Book[]
	 */
	private static Book[] resizeArray(Book[] inputArray, int desiredLength) {
		// Create a new array of the desired length
		Book[] resizedArray = new Book[desiredLength];
		// Step through the input array and assign input values to the resized array.
		for (int index = 0; index < desiredLength; index++) {
			// If the desired length is longer than the input array, put nulls in the
			// remaining elements of the resized array.
			if (index < inputArray.length) {
				resizedArray[index] = inputArray[index];
			} else {
				resizedArray[index] = null;
			}
		}
		return resizedArray;
	}

	/**
	 * Method bubbleSort sorts an array of Book in order of depending on
	 * leastToGreatest. If leastToGreatest is true: Bubble sort from least to
	 * greatest. If leastToGreatest is false: Bubble sort from greatest to least.
	 */
	private Book[] bubbleSortByBookID(Book[] inputArray, boolean leastToGreatest) {

		for (int out = 0; out < inputArray.length; out++) {
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputArray.length - 1; in++) {
				// Depending on direction, check to see if a swap is necessary.
				if ((leastToGreatest && inputArray[in - 1].getBookID() > inputArray[in].getBookID())
						|| (!leastToGreatest && inputArray[in - 1].getBookID() < inputArray[in].getBookID())) {
					Book temp = inputArray[in - 1];
					inputArray[in - 1] = inputArray[in];
					inputArray[in] = temp;
				}
			}
		}
		return inputArray;
	}

	/**
	 * Method isValidBranch checks the current Branch object to ensure all
	 * properties have non-blank or null values. It prints the branch to the console
	 * and if invalid, the first reason why it was found to be invalid.
	 * 
	 * @return boolean - true if the branch information is valid, otherwise false.
	 */
	public boolean isValidBranch() {
		// Print this branch information to the console.
		System.out.println(this.toString());
		if (this.branchName.isBlank() || this.branchName.isEmpty()) {
			System.out.println("isValidBranch - branch has no name.");
			return false;
		}
		if (!this.branchAddress.isValidAddress(false)) {
			return false;
		}
		System.out.println("isValidBranch - branch is valid.");
		return true;
	}

	
	/**
	 * Method toString overrides the default Object.toString to summarize the
	 * library.
	 * 
	 * @return String with the salient details of the library
	 */
	@Override
	public String toString() {
		String outputString = this.branchName + "\n" + this.branchAddress.toString();
		outputString += "\nAssociated with " + this.associatedLibrary + "\n";
		return outputString;
	}


}

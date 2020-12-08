package libraryProject;

import java.time.LocalDate;
import java.util.Random;

import javax.swing.JOptionPane;

public class Library {
	
	private String libraryName;
	private Book[] libraryBookArray = new Book[0];
	private Customer[] libraryCustomerArray = new Customer[0];
	private Branch[] libraryBranchArray = new Branch[0];
	private long nextCustomerID = 1; 
	private int daysUntilDueDate = 14;
	
	/** Constructor to build Library with just the name. Initializes nextCustomerID to 1.
	 * @param libraryName
	 */
	public Library(String libraryName) {
		this.libraryName = libraryName;
	}
	
	/** Constructor to build Library with a name, an array of Books and Customers. 
	 * 	It sorts the Books by bookID and Customers by customerID;
	 * 	Initializes nextCustomerID to the highest customerID in the array + 1.
	 * @param libraryName
	 * @param libraryBookArray
	 * @param libraryCustomerArray
	 */
	public Library(String libraryName, Book[] libraryBookArray, Customer[] libraryCustomerArray, 
			Branch[] libraryBranchArray) {
		super();
		this.libraryName = libraryName;
		// Ensure the Books and Customers added are sorted by BookID and CustomerID respectively
		this.libraryBookArray = bubbleSortByBookID(libraryBookArray, true);
		this.libraryCustomerArray = bubbleSortByCustomerID(libraryCustomerArray, true);
		this.libraryBranchArray = bubbleSortByBranchName(libraryBranchArray, true);
		// Assign the nextCustomerID to the last sorted CustomerID + 1
		this.nextCustomerID = libraryCustomerArray[libraryCustomerArray.length - 1].getCustomerID() + 1;  
	}

	public String getLibraryName() {
		return libraryName;
	}

	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	public Book[] getLibraryBookArray() {
		return libraryBookArray;
	}

	public void setLibraryBookArray(Book[] libraryBookArray) {
		this.libraryBookArray = libraryBookArray;
	}

	public Customer[] getLibraryCustomerArray() {
		return libraryCustomerArray;
	}

	public void setLibraryCustomerArray(Customer[] libraryCustomerArray) {
		this.libraryCustomerArray = libraryCustomerArray;
	}
	
	
	public Branch[] getLibraryBranchArray() {
		return libraryBranchArray;
	}

	public void setLibraryBranchArray(Branch[] libraryBranchArray) {
		this.libraryBranchArray = libraryBranchArray;
	}

	/** Method addCustomer prompts the user to provide all the necessary information to create a new customer
	 *  or generates it randomly. It then assigns the next CustomerNumber, and updates the LibraryCustomerArray
	 *   with the new customer included as the last element. fromUser - true: prompt the user for the required 
	 *   information, false: generate the customer randomly
	 *  @param fromUser 
	 *  @return boolean - true if the customer is added, false if the customer was not added successfully.
	 */
	public boolean addCustomer(boolean fromUser) {
		Customer newCustomer = new Customer(nextCustomerID);
		boolean wasSuccessful = false;
		if (fromUser) {
			wasSuccessful = newCustomer.promptUserForCustomerInfo();
		} else {
			wasSuccessful = newCustomer.generateNewCustomer();
		}
		// If wasSuccessful is true, then the new customer information was valid,
		//  so add the new customer and update this.libraryCustomerArray with the new customer list.
		if (wasSuccessful) {
			// Set the customerID and increment nextCustomerID
			newCustomer.setCustomerID(this.nextCustomerID);
			this.nextCustomerID++;
			// Create updatedCustomers with one more element at the end and add the new Customer
			Customer[] updatedCustomers = resizeArray(this.libraryCustomerArray, this.libraryCustomerArray.length + 1);
			updatedCustomers[updatedCustomers.length - 1] = newCustomer;
			this.libraryCustomerArray = updatedCustomers;
		} 
		// Ensure the customers are sorted by CustomerID in ascending order
		this.libraryCustomerArray = bubbleSortByCustomerID(libraryCustomerArray, true);
		// If wasSuccessful is false, then the new customer information was not valid,
		//  so leave this.libraryCustomerArray array unchanged.
		return wasSuccessful;
	}
	
	/** Method addCustomer takes a Customer object, validates the information is correct usable, then assigns the 
	 * 	next CustomerNumber, and updates LibraryCustomerArray
	 *   with the new customer included as the last element.
	 *  @param Customer - the Customer object to be added to the library.
	 *  @return boolean - true if the customer is added, false if the customer was not added successfully.
	 */
	public boolean addCustomer(Customer newCustomer) {
		boolean wasSuccessful = newCustomer.isValidCustomer(true);
		// If wasSuccessful is true, then the new customer information was valid,
		//  so add the new customer and update this.libraryCustomerArray with the new customer list.
		if (wasSuccessful) {
			// Set the customerID and increment nextCustomerID
			newCustomer.setCustomerID(this.nextCustomerID);
			this.nextCustomerID++;
			// Create updatedCustomers with one more element at the end and add the new Customer
			Customer[] updatedCustomers = resizeArray(this.libraryCustomerArray, this.libraryCustomerArray.length + 1);
			updatedCustomers[updatedCustomers.length - 1] = newCustomer;
			this.libraryCustomerArray = updatedCustomers;
		} 
		// Ensure the customers are sorted by CustomerID in ascending order
		this.libraryCustomerArray = bubbleSortByCustomerID(libraryCustomerArray, true);
		// If wasSuccessful is false, then the new customer information was not valid,
		//  so leave this.libraryCustomerArray array unchanged.
		return wasSuccessful;
	}
	
	/** Method addBook prompts the user to provide all the necessary information to create a new book
	 *  or generates it randomly. It then assigns the proper BookID, and sorts the LibraryBookArray
	 *  by BookID and with the new book included. fromUser - true: prompt the user for the required 
	 *  information, false: generate the book randomly
	 *  @param fromUser
	 *  @return boolean - true if the book is added, false if the book was not added successfully.
	 */
	public boolean addBook(boolean fromUser) {
		Book newBook = new Book(0);
		// Indicates whether the Book object has been updated correctly
		boolean bookUpdated = false;
		// Indicates whether the Branch object has been updated correctly with the new book.
		boolean branchUpdated = false;
		if (fromUser) {
			bookUpdated = newBook.promptUserForBookInfo();
			// If the book information was updated successfully, then assign it a bookID and prompt the user for
			//  which branch it is assigned to.
			if (bookUpdated) {
				// Generate a bookID based on the new book's ISBN.
				newBook.setBookID(generateBookID(newBook.getIsbn()));
				// Prompt the user to assign a branch to the book.
				newBook = assignBookToBranch(newBook, true);
				// If the return value is not null, then the branch was updated successfully.
				if (newBook != null) {
					branchUpdated = true;				
				}
			}
		} else {
			bookUpdated = newBook.generateNewBook();
			if (bookUpdated) {
				// Generate a bookID based on the new book's ISBN.
				newBook.setBookID(generateBookID(newBook.getIsbn()));
				// Randomly select a branch and assign it to the book.
				newBook = assignBookToBranch(newBook, false);
				// If the return value is not null, then the branch was updated successfully.
				if (newBook != null) {
					branchUpdated = true;				
				}
			}
		}
		// If the book and the branch were updated, then the new book information was valid,
		//  so add the new book and update this.libraryBookArray with the new book list.
		if (bookUpdated && branchUpdated) {
			// Create updatedBooks with one more element at the end and add the new Book
			Book[] updatedBooks = resizeArray(this.libraryBookArray, this.libraryBookArray.length + 1);
			// Put the new book at the end of the libraryBookArray
			updatedBooks[updatedBooks.length - 1] = newBook;
			// Sort by BookID before overwriting the libraryBookArray
			updatedBooks = bubbleSortByBookID(updatedBooks, true);
			this.libraryBookArray = updatedBooks;
			// Ensure the books are sorted by BookID in ascending order
			this.libraryBookArray = bubbleSortByBookID(libraryBookArray, true);
			return true;
		} else {
			// If either the book or the branch were not updated then the new book information was not valid,
			//  so leave this.libraryCustomerArray array unchanged.
			return false;
		}
	}
	
	/** Method addBook adds an object Book to the library. It then assigns the proper bookID, and sorts the LibraryBookArray
	 *   by BookID and with the new book included.
	 *  @param Book newBook - the Book object to be added to the library.
	 *  @return boolean - true if the book is added, false if the book was not added successfully.
	 */
	public boolean addBook(Book newBook) {
		// Indicates whether the Book object has been updated correctly
		boolean bookValid = newBook.isValidBook(true);
		// Indicates whether the Branch object has been updated correctly with the new book.
		boolean branchUpdated = false;
		if (bookValid) {
			// If the book information is valid for a new book, assign it a bookID 
			newBook.setBookID(generateBookID(newBook.getIsbn()));
			// If the book's assigned branch string does not match a library branch, prompt the user for a branch to which it is assigned.
			if (isBranchName(newBook.getAssignedBranch())) {
				// If the book's assigned branch is valid, check if the book is assigned to that branch. 
				Branch assignedBranch = findBranchByName(newBook.getAssignedBranch());
				// If the book is already assigned to assignedBranch no need to update the Branch.
				if (assignedBranch.isBookAlreadyAssigned(newBook)) {
					branchUpdated = true;
				// If the book is not already assigned, then add the book to assignedBranch
				} else {
					branchUpdated = assignedBranch.addBook(newBook);
				}
				
			} else {
				// Prompt the user to assign a branch to the book.
				newBook = assignBookToBranch(newBook, true);
			}
			// If the return value is not null, then the branch was updated successfully.
			if (newBook != null) {
				branchUpdated = true;				
			}
		} 
		// If the book and the branch were updated, then the new book information was valid,
		//  so add the new book and update this.libraryBookArray with the new book list.
		if (bookValid && branchUpdated) {
			// Create updatedBooks with one more element at the end and add the new Book
			Book[] updatedBooks = resizeArray(this.libraryBookArray, this.libraryBookArray.length + 1);
			// Put the new book at the end of the libraryBookArray
			updatedBooks[updatedBooks.length - 1] = newBook;
			// Sort by BookID before overwriting the libraryBookArray
			updatedBooks = bubbleSortByBookID(updatedBooks, true);
			this.libraryBookArray = updatedBooks;
			// Ensure the books are sorted by BookID in ascending order
			this.libraryBookArray = bubbleSortByBookID(libraryBookArray, true);
			return true;
		} else {
			// If either the book or the branch were not updated then the new book information was not valid,
			//  so leave this.libraryCustomerArray array unchanged.
			return false;
		}
	}
	
	/** Method assignBookToBranch updates Book toBeAssigned by calling userChooseBranch to prompt the user to 
	 * 	select a branch or randomly choose a branch, depending on promptUser. The method adds the book to the 
	 *  Branch and updates the book's assignedBranch before returning it to the calling method. 
	 * @param Book toBeAssigned
	 * @param promptUser - true will prompt the user to choose a branch, false will randomly select a branch.
	 * @return Book - updated with the user specified assignedBranch or null if the user clicked cancel.
	 */
	private Book assignBookToBranch(Book toBeAssigned, boolean promptUser) {
		Branch branchChosen;
		if (promptUser) {
			branchChosen = userChooseBranch();
		} else {
			branchChosen = addBookToRandomBranch();
		}
		// If the user chose a branch, set the book's assigned branch to the user selected branch and
		// 	add that Book to the Branch's array of assigned books.
		if (branchChosen != null) {
			boolean branchUpdated = branchChosen.addBook(toBeAssigned);
			// If addBook was successful, return Book
			if (branchUpdated) {
				toBeAssigned.setAssignedBranch(branchChosen.getBranchName());
				return toBeAssigned;
			}
		}
		//User cancelled in userChooseBranch so return null
		return null;
	}
	
	/** Method assignBookToBranch checks to see if the the Book toBeAssigned and the Branch toThisBranch are valid.
	 *  If so, updates Book toBeAssigned by calling userChooseBranch to prompt the user to 
	 * 	select a branch or randomly choose a branch, depending on promptUser. The method adds the book to the 
	 *  Branch and updates the book's assignedBranch before returning it to the calling method. 
	 * @param Book toBeAssigned
	 * @param promptUser - true will prompt the user to choose a branch, false will randomly select a branch.
	 * @return Book - updated with the user specified assignedBranch or null if the user clicked cancel.
	 */
	private Book assignBookToBranch(Book toBeAssigned, Branch toThisBranch) {
		if (toBeAssigned.isValidBook(false) && toThisBranch.isValidBranch()) {
			boolean branchUpdated = toThisBranch.addBook(toBeAssigned);
			// If addBook was successful, return Book
			if (branchUpdated) {
				toBeAssigned.setAssignedBranch(toThisBranch.getBranchName());
				return toBeAssigned;
			}
		}
		//User cancelled in userChooseBranch so return null
		return null;
	}
	
	/** Method userChooseBranch prompts the user to select a branch from the libraryBranchArray. The method
	 * 	 returns the  
	 * @return Branch - returns the selected Branch or null if the user clicked cancel.
	 */
	public Branch userChooseBranch() {
		boolean branchUpdated = false;
		userCancelled:
		while (!branchUpdated) {
			// Build a string with a numbered list of branches from which the user can choose by typing an integer.
			String userPrompt = "Please select a branch for the book(s):";
//System.out.println("userChooseBranch - there are " + libraryBranchArray.length + " branches.");
			for (int index = 0; index < this.libraryBranchArray.length; index++) {
				userPrompt += "\n" +(index + 1) + ". "+ this.libraryBranchArray[index];
			}
			Integer branchSelected = DialogInput.getInputChoice(userPrompt, libraryBranchArray.length);
			// Check if the integer returned is a valid choice, otherwise advise the user and re-prompt for input.
			if (branchSelected != null) {
				if (branchSelected <= this.libraryBranchArray.length) {
					return this.libraryBranchArray[branchSelected - 1];
				} else {
					// Advise the user the input is invalid and continue the loop
					JOptionPane.showMessageDialog(null, "You did not select a valid branch. \nPlease try again.", 
							"Invalid Input", JOptionPane.WARNING_MESSAGE);
				} 
			} else {
				// If branchSelected is null, the user pressed cancel and wants to exit, so exit the loop.
				branchUpdated = false;
				break userCancelled;
			}
		}
		return null;
	}
	
	/** Method randomBranch randomly selects a branch from the libraryBranchArray and adds the book to the branch. 
	 * @return Branch - returns the selected Branch or null if there was an error.
	 */
	public Branch addBookToRandomBranch() {
		if (this.libraryBranchArray.length == 0) {
			return null;
		}
		Random rand = new Random();
		int branchIndex = rand.nextInt(this.libraryBranchArray.length);
		return this.libraryBranchArray[branchIndex];
	}
	
	/** Method isBranchName compares the String branchName to the names of the branches of the library.
	 * 	 The comparison removes whitespace before and after the string and is not case sensitive.
	 * @param String branchName
	 * @return boolean - true if it is the name of one of the branches of the library.
	 */
	public boolean isBranchName(String branchName) {
		// Remove any whitespace before or after the characters of the string to avoid a false negative result.
		branchName.trim();
		// Step through the array of Branches in the Library and compare the branchNames with String branchName.
		for (int index = 0; index < this.libraryBranchArray.length; index++) {
			if (this.libraryBranchArray[index].getBranchName().equalsIgnoreCase(branchName)) {
				return true;
			}
		}
		// No matches found, so branchName is not a Branch in this Library.
		return false;
	}
	
	/** Method addBranch prompts the user to provide all the necessary information to create a new branch of the library
	 *  or generates it randomly. It then appends the new branch name to the libraryBranchArray and sorts it.
	 *  @param boolean fromUser - true: prompt the user for the required information, false: generate the branch randomly
	 *  @return boolean - true if the branch is added successfully, false if the branch was not added successfully.
	 */
	public boolean addBranch(boolean fromUser) {
		Branch newBranch = new Branch("", this.libraryName);
		boolean wasSuccessful = false;
		if (fromUser) {
			wasSuccessful = newBranch.promptUserForBranchInfo();
		} else {
			wasSuccessful = newBranch.generateNewBranch();
		}
		// If wasSuccessful is true, then the new branch information was valid,
		//  so add the new branch and update this.libraryBranchArray with the new branch list.
		if (wasSuccessful) {
			// Create updatedCustomers with one more element at the end and add the new Customer
			Branch[] updatedBranches = resizeArray(this.libraryBranchArray, this.libraryBranchArray.length + 1);
			updatedBranches[updatedBranches.length - 1] = newBranch;
			this.libraryBranchArray = updatedBranches;
//System.out.println("addBranch - after adding branch " + newBranch.toString());
//System.out.println("addBranch - the last BranchArray element is " +
//		this.libraryBranchArray[this.libraryBranchArray.length - 1].toString());
			// Ensure the branches are sorted by Branch name in ascending order
			this.libraryBranchArray = bubbleSortByBranchName(this.libraryBranchArray, true);
		} 
		// If wasSuccessful is false, then the new branch information was not valid,
		//  so leave this.libraryBranchArray array unchanged.
		return wasSuccessful;
	}
	
	/** Method addBranch takes a Branch, checks to see if it is valid and if so adds the new branch of the library
	 *  It then assigns the libraryName to the branch associated library, appends the new branch name to the 
	 *  libraryBranchArray and sorts it.
	 *  @param Branch newBranch 
	 *  @return boolean - true if the branch is added successfully, false if the branch was not added successfully.
	 */
	public boolean addBranch(Branch newBranch) {
System.out.println("addBranch - check if valid: " + newBranch.toString());
		boolean wasSuccessful = newBranch.isValidBranch();
		if (wasSuccessful) {
System.out.println("addBranch - was valid: " + newBranch.toString());
			// If wasSuccessful is true, then the new branch information was valid,
			//  so add the new branch and update this.libraryBranchArray with the new branch list.
			// Create updatedBranches with one more element at the end and add the new Branch
			Branch[] updatedBranches = resizeArray(this.libraryBranchArray, this.libraryBranchArray.length + 1);
			updatedBranches[updatedBranches.length - 1] = newBranch;
			this.libraryBranchArray = updatedBranches;
			// Ensure the branches are sorted by Branch name in ascending order
			this.libraryBranchArray = bubbleSortByBranchName(libraryBranchArray, true);
		}
		// If wasSuccessful is false, then the new branch information was not valid,
		//  so leave this.libraryBranchArray array unchanged.
		return wasSuccessful;
	}
	
	/** Method checkOutBook takes a Customer, checks if they are part of the Library and if they are in good standing
	 *  (allowed to check out books), and takes a Book, checks if it is part of the library, and if it is already checked out.
	 *  If any of these are true, the Customer cannot check out the Book. 
	 *  If all of these are false, mark the Book as checked out and assign a due date. Then add the book to the customer's array
	 *  	of checked out Books.
	 * @param currentCustomer
	 * @param currentBook
	 * @return
	 */
	public boolean checkOutBook(Customer currentCustomer, Book currentBook) {
		if (!isCustomerInLibrary(currentCustomer)) {
			System.out.println("checkOutBook - customer " + currentCustomer.getCustomerID() + " is not in the library.");
			return false;
		}
		if (!isBookInLibrary(currentBook)) {
			System.out.println("checkOutBook - book " + currentBook.getBookID() + "is not in the library");
			return false;
		}
		if (!currentCustomer.isCanCheckOut()) {
			System.out.println("checkOutBook - customer " + currentCustomer.getCustomerName() + " is not allowed to borrow books.");
			return false;
		}
		if (currentBook.isCheckedOut()) {
			System.out.println("checkOutBook - book " + currentBook.getTitle() + " is already checked out.");
			return false;
		}
		// Update the current book to be checked out
		currentBook.setCheckedOut(true);
		// Set the current book's due date to the current date plus the global variable daysUntilDueDate
		LocalDate dueDate = LocalDate.now();
		dueDate = dueDate.plusDays(this.daysUntilDueDate);
		currentBook.setDueDate(dueDate);
		// Find the index of the currentBook in the libraryBookArray
		Integer indexInBookArray = findBookIndex(currentBook.getBookID());
		Integer indexInCustomerArray = findCustomerIndex(currentCustomer.getCustomerID());
		/* If the book and customer indices are valid and addCheckedOutBook is successful for currentCustomer, 
		 * update the library. */
		if (indexInBookArray != null  && indexInCustomerArray != null && currentCustomer.addCheckedOutBook(currentBook)) {
			this.libraryBookArray[indexInBookArray] = currentBook;
			this.libraryCustomerArray[indexInCustomerArray] = currentCustomer;
System.out.println(this.libraryCustomerArray[indexInCustomerArray].toString());
		} else {
			// The current book and/or current customer was not found in the library.
			return false;
		}
		return true;
	}
	
	/** Method findBookIndex searches for a bookID in the array of library books.
	 * @param bookID
	 * @return index - If found, return the index of the Book with that bookID, otherwise return null.
	 */
	private Integer findBookIndex(long bookID) {
		for (int index = 0; index < libraryBookArray.length; index++) {
			if (libraryBookArray[index].getBookID() == bookID) {
				return index;
			}
		}
		return null;
	}

	/** Method findCustomerIndex searches the libraryCustomerArray for matching customer ID and return 
	 * the matching index. Customer ID is a unique identifier.
	 * @param searchID - the phone number to search for stored as a long integer
	 * @return Integer[] - an array of the indices of all matches
	 */
	private Integer findCustomerIndex(long searchID) {
		for (int index = 0; index < this.libraryCustomerArray.length; index++) {
			if (libraryCustomerArray[index].getCustomerID() == searchID) {
				return index;
			}
		}
		return null;
	}

	/** Method isCustomerInLibrary searches the library customer array for the currentCustomer and returns true if it is found.
	 * @param Customer currentCustomer
	 * @return boolean - true if the currentCustomer is found, otherwise false.
	 */
	private boolean isCustomerInLibrary(Customer currentCustomer) {
		for (Customer existingCustomer:libraryCustomerArray) {
			if (currentCustomer.getCustomerID() == existingCustomer.getCustomerID()) {
				return true;
			}	
		}
		return false;
	}
	
	/** Method isBookInLibrary searches the library book array for the currentBook and returns true if it is found.
	 * @param Book currentBook
	 * @return boolean - true if the currentBook is found, otherwise false.
	 */
	private boolean isBookInLibrary(Book currentBook) {
		for (Book existingBook:libraryBookArray) {
			if (currentBook.getBookID() == existingBook.getBookID()) {
				return true;
			}
		}
		return false;
	}

	/** Method generateBookID generates a bookID given an ISBN. 
	 * Book ID is IIIIIIIIIIIIINNN where I is a digit of the ISBN and N is a digit of the number of 
	 * 	copies of this book in the libraryBookArray. 
	 * @param long isbn - 13 digit ISBN. NOTE: The method only uses the first 13 digits.
	 * @return long
	 */
	public long generateBookID(long isbn) {
		// Find the indices of all the books which match the ISBN provided
		Integer[] indexArray = findBookIndicesByISBN(isbn);
		if (indexArray.length > 0) {
			// Matching ISBNs found, so take the last one (highest in sorted array) and add one.
			int highestMatch = indexArray[indexArray.length - 1];
			return this.libraryBookArray[highestMatch].getBookID() + 1;
		} else {
			// No matching ISBNs found, so this must be the first, so make it ISBN followed by 001.
			return (isbn * 1000) + 1;
		}
	}
	
	/** Method findBooks returns an array of Books based on the integer search criteria:
	 * 		1. Title
	 * 		2. Author
	 * 		3. Genre
	 * @param String searchString
	 * @param int searchType - specify what type of property searchString is looking for:
	 * 		
	 * @return Book[]
	 */
	public Book[] findBooks(String searchString, int searchType) {
		Book[] bookArray = new Book[0];
		Integer[] indices = new Integer[0];
		switch (searchType) {
		case 1:
			indices = findBookIndicesByTitle(searchString);
			break;
		case 2:
			indices = findBookIndicesByAuthor(searchString);
			break;
		case 3:
			indices = findBookIndicesByGenre(searchString);
			break;
		default: 
			System.out.println("findBooks - not a valid search type");
		}
		if (indices.length > 0) {
			bookArray = resizeArray(bookArray, indices.length);
			for (int index = 0; index < indices.length; index++) {
				bookArray[index] = libraryBookArray[indices[index]];
			}
			return bookArray;
		}
		return null;
	}
	
	/** Method findBooks returns an array of Books based on the ISBN.
	 * @param Long searchISBN
	 * @return Book[]
	 */
	public Book[] findBooks(Long searchISBN) {
		Book[] bookArray = new Book[0];
		Integer[] indices = findBookIndicesByISBN(searchISBN);
		if (indices.length > 0) {
			bookArray = resizeArray(bookArray, indices.length);
			for (int index = 0; index < indices.length; index++) {
				bookArray[index] = libraryBookArray[indices[index]];
			}
			return bookArray;
		}
		return null;
	}
	
	/** Method findBookIndicesByISBN searches the libraryBookArray for matching book ISBN and returns 
	 * the indices of all matches.
	 * @param long ISBN - the ISBN to search for
	 * @return int[] - an array of the indices of all matches
	 */
	private Integer[] findBookIndicesByISBN(long isbn) {
		Integer[] indexArray = new Integer[0];
		for (int index = 0; index < this.libraryBookArray.length; index++) {
			if (libraryBookArray[index].getIsbn() == isbn) {
				indexArray = resizeArray(indexArray, indexArray.length + 1);
				indexArray[indexArray.length - 1] = index;
			}
		}
		return indexArray;
	}
	
	/** Method findBookIndicesByAuthor searches the libraryBookArray for matching book author and returns 
	 * the indices of all matches. Match is not case sensitive.
	 * @param String author - the author to search for
	 * @return Integer[] - an array of the indices of all matches
	 */
	private Integer[] findBookIndicesByAuthor(String author) {
		author = author.trim().toLowerCase();
		Integer[] indexArray = new Integer[0];
		String tempString = "";
		for (int index = 0; index < this.libraryBookArray.length; index++) {
			tempString = libraryBookArray[index].getAuthor().toLowerCase();
			if (tempString.contains(author)) {
				indexArray = resizeArray(indexArray, indexArray.length + 1);
				indexArray[indexArray.length - 1] = index;
			}
		}
		return indexArray;
	}
	
	/** Method findBookIndicesByTitle searches the libraryBookArray for matching book title and returns 
	 * the indices of all matches. Match is not case sensitive.
	 * @param String title - the title to search for
	 * @return Integer[] - an array of the indices of all matches
	 */
	private Integer[] findBookIndicesByTitle(String title) {
		title = title.trim().toLowerCase();
		Integer[] indexArray = new Integer[0];
		String tempString = "";
		for (int index = 0; index < this.libraryBookArray.length; index++) {
			tempString = libraryBookArray[index].getTitle().toLowerCase();
			if (tempString.contains(title)) {
				indexArray = resizeArray(indexArray, indexArray.length + 1);
				indexArray[indexArray.length - 1] = index;
			}
		}
		return indexArray;
	}
	
	/** Method findBookIndicesByGenre searches the libraryBookArray for matching book genre and returns 
	 * the indices of all matches. Match is not case sensitive.
	 * @param genre - the genre to search for
	 * @return Integer[] - an array of the indices of all matches
	 */
	private Integer[] findBookIndicesByGenre(String genre) {
		genre = genre.trim().toLowerCase();
		Integer[] indexArray = new Integer[0];
		String tempString = "";
		for (int index = 0; index < this.libraryBookArray.length; index++) {
			tempString = libraryBookArray[index].getGenre();
			if (tempString.contains(genre)) {
				indexArray = resizeArray(indexArray, indexArray.length + 1);
				indexArray[indexArray.length - 1] = index;
			}
		}
		return indexArray;
	}
	
	/** Method findCustomers returns an array of Customers based on the integer search criteria:
	 * 		1. Name
	 * 		2. Street number and/or street name
	 * 		3. City
	 * 		4. State
	 * 		5. Email Address
	 * @param String searchString
	 * @param int searchType - specify what type of property searchString is looking for
	 * 		
	 * @return Customer[]
	 */
	public Customer[] findCustomers(String searchString, int searchType) {
		Customer[] customerArray = new Customer[0];
		Integer[] indices = new Integer[0];
		switch (searchType) {
		case 1:
			indices = findCustomerIndicesByName(searchString);
			break;
		case 2:
			indices = findCustomerIndicesByStreet(searchString);
			break;
		case 3:
			indices = findCustomerIndicesByCity(searchString);
			break;
		case 4:
			indices = findCustomerIndicesByState(searchString);
			break;
		case 5:
			indices = findCustomerIndicesByEmail(searchString);
			break;
		default: 
			System.out.println("findCustomer - not a valid search type");
		}
		if (indices.length > 0) {
			customerArray = resizeArray(customerArray, indices.length);
			for (int index = 0; index < indices.length; index++) {
				customerArray[index] = libraryCustomerArray[indices[index]];
			}
			return customerArray;
		}
		return null;
	}
	
	/** Method findCustomers returns an array of Customers based on their phone number stored as a long integer.
	 * @param long searchPhone
	 * return Customer[]
	 */
	public Customer[] findCustomers(long searchPhone) {
		Customer[] customerArray = new Customer[0];
		Integer[] indices = new Integer[0];
		indices = findCustomerIndicesByPhone(searchPhone);
		if (indices.length > 0) {
			customerArray = resizeArray(customerArray, indices.length);
			for (int index = 0; index < indices.length; index++) {
				customerArray[index] = libraryCustomerArray[indices[index]];
			}
			return customerArray;
		}
		return null;
	}
	
	
	/** Method findCustomers returns an array of Customers based on their zip code stored as an integer.
	 * @param int searchZip
	 * return Customer[]
	 */
	public Customer[] findCustomers(int searchZip) {
		Customer[] customerArray = new Customer[0];
		Integer[] indices = new Integer[0];
		indices = findCustomerIndicesByZip(searchZip);
		if (indices.length > 0) {
			customerArray = resizeArray(customerArray, indices.length);
			for (int index = 0; index < indices.length; index++) {
				customerArray[index] = libraryCustomerArray[indices[index]];
			}
			return customerArray;
		}
		return null;
	}
	
	/** Method findCustomerIndicesByName searches the libraryCustomerArray for matching full name and returns 
	 * the indices of all matches. Match is not case sensitive.
	 * @param fullName - the full name to search for
	 * @return Integer[] - an array of the indices of all matches
	 */
	private Integer[] findCustomerIndicesByName(String name) {
		name = name.trim().toLowerCase();
		Integer[] indexArray = new Integer[0];
		String tempString = "";
		for (int index = 0; index < this.libraryCustomerArray.length; index++) {
			tempString = libraryCustomerArray[index].getCustomerName().toLowerCase();
			if (tempString.contains(name)) {
				indexArray = resizeArray(indexArray, indexArray.length + 1);
				indexArray[indexArray.length - 1] = index;
			}
		}
		return indexArray;
	}
	
	/** Method findCustomerIndicesByStreet searches the libraryCustomerArray for matching full name and returns 
	 * the indices of all matches. Match is not case sensitive.
	 * @param street1 - the street number and street name to search for
	 * @return Integer[] - an array of the indices of all matches
	 */
	private Integer[] findCustomerIndicesByStreet(String street) {
		street = street.trim().toLowerCase();
		Integer[] indexArray = new Integer[0];
		String tempString = "";
		for (int index = 0; index < this.libraryCustomerArray.length; index++) {
			tempString = libraryCustomerArray[index].getCustomerAddress().getAddressStreet1().toLowerCase() + " " +
					libraryCustomerArray[index].getCustomerAddress().getAddressStreet2().toLowerCase();
			if (tempString.contains(street)) {
				indexArray = resizeArray(indexArray, indexArray.length + 1);
				indexArray[indexArray.length - 1] = index;
			}
		}
		return indexArray;
	}
	
	/** Method findCustomerIndicesByCity searches the libraryCustomerArray for matching full name and returns 
	 * the indices of all matches. Match is not case sensitive.
	 * @param city - the city to search for
	 * @return Integer[] - an array of the indices of all matches
	 */
	private Integer[] findCustomerIndicesByCity(String city) {
		city = city.trim().toLowerCase();
		Integer[] indexArray = new Integer[0];
		String tempString = "";
		for (int index = 0; index < this.libraryCustomerArray.length; index++) {
			tempString = libraryCustomerArray[index].getCustomerAddress().getAddressCity().toLowerCase();
			if (tempString.contains(city)) {
				indexArray = resizeArray(indexArray, indexArray.length + 1);
				indexArray[indexArray.length - 1] = index;
			}
		}
		return indexArray;
	}
	
	/** Method findCustomerIndicesByState searches the libraryCustomerArray for matching full name and returns 
	 * the indices of all matches. Match is not case sensitive.
	 * @param state - the state to search for
	 * @return Integer[] - an array of the indices of all matches
	 */
	private Integer[] findCustomerIndicesByState(String state) {
		state = state.trim().toLowerCase();
		Integer[] indexArray = new Integer[0];
		String tempString = "";
		for (int index = 0; index < this.libraryCustomerArray.length; index++) {
			tempString = libraryCustomerArray[index].getCustomerAddress().getAddressState().toLowerCase();
			if (tempString.contains(state)) {
				indexArray = resizeArray(indexArray, indexArray.length + 1);
				indexArray[indexArray.length - 1] = index;
			}
		}
		return indexArray;
	}
	
	/** Method findCustomerIndicesByZip searches the libraryCustomerArray for matching zip code and returns 
	 * the indices of all matches.
	 * @param zip - the zip code integer to search for
	 * @return Integer[] - an array of the indices of all matches
	 */
	private Integer[] findCustomerIndicesByZip(int zip) {
		Integer[] indexArray = new Integer[0];
		for (int index = 0; index < this.libraryCustomerArray.length; index++) {
			if (libraryCustomerArray[index].getCustomerAddress().getAddressZip() == zip) {
				indexArray = resizeArray(indexArray, indexArray.length + 1);
				indexArray[indexArray.length - 1] = index;
			}
		}
		return indexArray;
	}
	
	/** Method findCustomerIndicesByPhone searches the libraryCustomerArray for matching phone number and returns 
	 * the indices of all matches.
	 * @param searchPhone - the phone number to search for stored as a long integer
	 * @return Integer[] - an array of the indices of all matches
	 */
	private Integer[] findCustomerIndicesByPhone(long searchPhone) {
		Integer[] indexArray = new Integer[0];
		for (int index = 0; index < this.libraryCustomerArray.length; index++) {
			if (libraryCustomerArray[index].getCustomerPhone() == searchPhone) {
				indexArray = resizeArray(indexArray, indexArray.length + 1);
				indexArray[indexArray.length - 1] = index;
			}
		}
		return indexArray;
	}
	
	/** Method findCustomerIndicesByEmail searches the libraryCustomerArray for matching full name and returns 
	 * the indices of all matches. Match is not case sensitive.
	 * @param email - the full name to search for
	 * @return Integer[] - an array of the indices of all matches
	 */
	private Integer[] findCustomerIndicesByEmail(String email) {
		email = email.trim().toLowerCase();
		Integer[] indexArray = new Integer[0];
		String tempString = "";
		for (int index = 0; index < this.libraryCustomerArray.length; index++) {
			tempString = libraryCustomerArray[index].getCustomerEmail().toLowerCase();
			if (tempString.contains(email)) {
				indexArray = resizeArray(indexArray, indexArray.length + 1);
				indexArray[indexArray.length - 1] = index;
			}
		}
		return indexArray;
	}
	
	/** Method findBranchByName searches the libraryBranchArray for matching name and returns 
	 * the Branch object if found. Returns null otherwise. Match is not case sensitive.
	 * @param name - the branch name to search for
	 * @return Branch - the first Branch found with a matching name
	 */
	private Branch findBranchByName(String name) {
		name = name.trim().toLowerCase();
		if (!isBranchName(name)) {
			return null;
		}
		String tempString = "";
		for (int index = 0; index < this.libraryBranchArray.length; index++) {
			tempString = libraryBranchArray[index].getBranchName().toLowerCase();
			if (tempString.contains(name)) {
				return libraryBranchArray[index];
			}
		}
		return null;
	}
	
	
	/** Method numberOfBooks searches the libraryBookArray returns the numberOfBooks determined by totalOutOrIn
	 * @param int totalOutOrIn- 0 for all books in the library, 1 for all books checked out, 2 for all books checked in
	 * @return Integer the requested number of books in this Branch, otherwise returns null
	 */
	public Integer numberOfBooks(int totalOutOrIn) {
		switch (totalOutOrIn) {
		case 0:
			return libraryBookArray.length;
		case 1:
			int numCheckedOut = 0;
			for (int index = 0; index < this.libraryBookArray.length; index++) {
				if (libraryBookArray[index].isCheckedOut()) {
					numCheckedOut++;
				}
			}
			return numCheckedOut;
		case 2:
			int numCheckedIn = 0;
			for (int index = 0; index < this.libraryBookArray.length; index++) {
				if (!libraryBookArray[index].isCheckedOut()) {
					numCheckedIn++;
				}
			}
			return numCheckedIn;
		default:
			return (Integer)null;
		}
	}
	
	/** Method numberOfCustomers searches the libraryBookArray returns the numberOfBooks determined by totalOutOrIn
	 * @param int totalActiveInactive - 0 for all customers in the library, 1 for all customers who can check out books (active), 
	 * 	2 for all customers who cannot check out books (inactive)
	 */
	public Integer numberOfCustomers(int totalActiveInactive) {
		switch (totalActiveInactive) {
		case 0:
			return libraryCustomerArray.length;
		case 1:
			int numCheckedOut = 0;
			for (int index = 0; index < this.libraryCustomerArray.length; index++) {
				if (libraryCustomerArray[index].isCanCheckOut()) {
					numCheckedOut++;
				}
			}
			return numCheckedOut;
		case 2:
			int numCheckedIn = 0;
			for (int index = 0; index < this.libraryCustomerArray.length; index++) {
				if (!libraryCustomerArray[index].isCanCheckOut()) {
					numCheckedIn++;
				}
			}
			return numCheckedIn;
		default:
			return (Integer)null;
		}
	}
	
	/** Method bubbleSort sorts an array of Customer by CustomerID depending on leastToGreatest.
	 * 	If leastToGreatest is true: Bubble sort from least to greatest.
	 * 	If leastToGreatest is false: Bubble sort from greatest to least.
	 */
	private Customer[] bubbleSortByCustomerID(Customer[] inputArray, boolean leastToGreatest) {

		for (int out = 0; out < inputArray.length; out++) {
			boolean anySwaps = false;
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputArray.length - 1; in++) {
				// Depending on direction, check to see if a swap is necessary.
				if ( (leastToGreatest && inputArray[in-1].getCustomerID() > inputArray[in].getCustomerID()) || 
						(!leastToGreatest && inputArray[in-1].getCustomerID() < inputArray[in].getCustomerID())) {
					Customer temp = inputArray[in-1];
					inputArray[in-1] = inputArray[in];
					inputArray[in] = temp;
					anySwaps = true;
				}
			}
			if (!anySwaps) {
				// Exit the loop if the last search through the array did not result in any swaps.
				break;
			}
		}
		return inputArray;
	}
	
	/** Method bubbleSort sorts an array of Book in order of depending on leastToGreatest.
	 * 	If leastToGreatest is true: Bubble sort from least to greatest.
	 * 	If leastToGreatest is false: Bubble sort from greatest to least.
	 */
	private Book[] bubbleSortByBookID(Book[] inputArray, boolean leastToGreatest) {

		for (int out = 0; out < inputArray.length; out++) {
			boolean anySwaps = false;
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputArray.length - 1; in++) {
				// Depending on direction, check to see if a swap is necessary.
				if ( (leastToGreatest && inputArray[in-1].getBookID() > inputArray[in].getBookID()) || 
						(!leastToGreatest && inputArray[in-1].getBookID() < inputArray[in].getBookID())) {
					Book temp = inputArray[in-1];
					inputArray[in-1] = inputArray[in];
					inputArray[in] = temp;
					anySwaps = true;
				}
			}
			if (!anySwaps) {
				// Exit the loop if the last search through the array did not result in any swaps.
				break;
			}
		}
		return inputArray;
	}
	
	/** Method bubbleSort sorts an array of Branches by name in order as specified by alphabeticalOrder.
	 * 	If alphabeticalOrder is true: Bubble sort from in alphabetical order.
	 * 	If alphabeticalOrder is false: Bubble sort from in reverse alphabetical order.
	 */
	private Branch[] bubbleSortByBranchName(Branch[] inputArray, boolean alphabeticalOrder) {

		for (int out = 0; out < inputArray.length; out++) {
			boolean anySwaps = false;
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputArray.length - 1; in++) {
				// Depending on direction, check to see if a swap is necessary.
				String lastBranch = inputArray[in-1].getBranchName();
				String currentBranch = inputArray[in].getBranchName();
				if ( (alphabeticalOrder && lastBranch.compareToIgnoreCase(currentBranch) < 0) || 
						(!alphabeticalOrder && lastBranch.compareToIgnoreCase(currentBranch) > 0) ) {
					Branch temp = inputArray[in-1];
					inputArray[in-1] = inputArray[in];
					inputArray[in] = temp;
					anySwaps = true;
				}
			}
			if (!anySwaps) {
				// Exit the loop if the last search through the array did not result in any swaps.
				break;
			}
		}
		return inputArray;
	}
	
	/** Method bubbleSort sorts an array of longs depending on leastToGreatest.
	 * 	If leastToGreatest is true: Bubble sort from least to greatest.
	 * 	If leastToGreatest is false: Bubble sort from greatest to least.
	 */
	private Long[] bubbleSort(Long[] inputArray, boolean leastToGreatest) {

		for (int out = 0; out < inputArray.length; out++) {
			boolean anySwaps = false;
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputArray.length - 1; in++) {
				// Depending on direction, check to see if a swap is necessary.
				if ( (leastToGreatest && inputArray[in-1] > inputArray[in]) || 
						(!leastToGreatest && inputArray[in-1] < inputArray[in])) {
					long temp = inputArray[in-1];
					inputArray[in-1] = inputArray[in];
					inputArray[in] = temp;
					anySwaps = true;
				}
			}
			if (!anySwaps) {
				// Exit the loop if the last search through the array did not result in any swaps.
				break;
			}
		}
		return inputArray;
	}
	
	/** Method resizeArray takes an array and re-sizes the array to the desiredLength.
	 * 	It discards any elements beyond the desired length.
	 * 
	 * @param Customer[] inputArray, int desiredLength
	 * @return Customer[]
	 */
	private static Customer[] resizeArray(Customer[] inputArray, int desiredLength) {
		// Create a new array of the desired length
		Customer[] resizedArray = new Customer[desiredLength];
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
	
	/** Method resizeArray takes an array and re-sizes the array to the desiredLength.
	 * 	It discards any elements beyond the desired length.
	 * 
	 * @param String[] inputStringArray, int desiredLength
	 * @return String[]
	 */
	private static Branch[] resizeArray(Branch[] inputBranchArray, int desiredLength) {
		// Create a new array of the desired length
		Branch[] resizedArray = new Branch[desiredLength];
		// Step through the input array and assign input values to the resized array.
		for (int index = 0; index < desiredLength; index++) {
			// If the desired length is longer than the input array, put nulls in the remaining elements of the resized array.
			if (index < inputBranchArray.length) {
				resizedArray[index] = inputBranchArray[index];
			} else {
				resizedArray[index] = null;
			}
		}
		return resizedArray;
	}
	
	/** Method resizeArray takes an array and re-sizes the array to the desiredLength.
	 * 	It discards any elements beyond the desired length.
	 * 
	 * @param int[] inputIntArray, int desiredLength
	 * @return Integer[]
	 */
	private static Integer[] resizeArray(Integer[] inputIntArray, int desiredLength) {
		// Create a new array of the desired length
		Integer[] resizedArray = new Integer[desiredLength];
		// Step through the input array and assign input values to the resized array.
		for (int index = 0; index < desiredLength; index++) {
			// If the desired length is longer than the input array, put nulls in the remaining elements of the resized array.
			if (index < inputIntArray.length) {
				resizedArray[index] = inputIntArray[index];
			} else {
				resizedArray[index] = (Integer)null;
			}
		}
		return resizedArray;
	}
	
	/** Method resizeArray takes an array and re-sizes the array to the desiredLength.
	 * 	It discards any elements beyond the desired length.
	 * 
	 * @param Long[] inputLongArray, int desiredLength
	 * @return Long[]
	 */
	private static Long[] resizeArray(Long[] inputLongArray, int desiredLength) {
		// Create a new array of the desired length
		Long[] resizedArray = new Long[desiredLength];
		// Step through the input array and assign input values to the resized array.
		for (int index = 0; index < desiredLength; index++) {
			// If the desired length is longer than the input array, put nulls in the remaining elements of the resized array.
			if (index < inputLongArray.length) {
				resizedArray[index] = inputLongArray[index];
			} else {
				resizedArray[index] = (Long)null;
			}
		}
		return resizedArray;
	}
	
	/** Method toString overrides the default Object.toString to summarize the library.
	 * @return String with the salient details of the library
	 */
	@Override
	public String toString() {
		String outputString = this.libraryName + " has the following branches\n";
		for (int index = 0; index < libraryBranchArray.length; index++) {
			outputString += libraryBranchArray[index].toString() + "\n";
		}
		outputString += "\nIt has " + numberOfBooks(0) + " total books and\n" + numberOfCustomers(0) + " total customers.";
		return outputString;
	}
}

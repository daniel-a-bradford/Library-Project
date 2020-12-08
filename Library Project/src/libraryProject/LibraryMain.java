package libraryProject;

import javax.swing.JOptionPane;

public class LibraryMain {

	private static Book currentBook = new Book(0);
	private static Customer currentCustomer = new Customer(0L);

	public static void main(String[] args) {
		/*
		 * Library: Write a method to prompt the user with a menu to perform various
		 * functions within a virtual library. The library is distributed between
		 * multiple branches, each branch has customers, and each book is related to a
		 * customer. The customer is one of the branches if the book is not checked out.
		 * 1. Search for a book and display its status. 2. Search for a customer and
		 * display their status (including all books checked out). 3. Add a new book. 4.
		 * Add a new customer. 5. Add a new library branch 6. Display how many books are
		 * in the library. 7. Display how many books are in a branch. 8. Randomly
		 * generate a specified number of books. 9. Randomly generate a specified number
		 * of customers. 10. Randomly generate a specified number of branches.
		 */

		boolean userExit = false;
		/*
		 * TODO Use currentBook and currentCustomer to allow a user to search for a book
		 * and check it out with currentCustomer (if designated) or search for a
		 * customer and have them check out currentBook (if designated).
		 */
		String libraryName = "Illinois Random Virtual Library";
		while (!userExit) {
			String mainMenuPrompt = "Welcome to the " + libraryName + "! \n";
			if (currentBook.getBookID() != 0) {
				mainMenuPrompt += "The currently selected book is " + currentBook.getTitle() + " copy number "
						+ currentBook.getCopyNum() + "\n";
			}
			if (currentCustomer.getCustomerID() != 0) {
				mainMenuPrompt += "The currently selected customer is " + currentCustomer.getCustomerID() + " "
						+ currentCustomer.getCustomerName() + "\n" + currentCustomer.getCustomerAddress() + "\n";
			}
			mainMenuPrompt += "Please enter the number of what you would like to do: \n"
					+ "1. Search for a book and display its status.\n"
					+ "2. Search for a customer and display their status (including all books checked out).\n"
					+ "3. Add a new book.\n" + "4. Add a new customer.\n" + "5. Add a new library branch.\n"
					+ "6. Display how many books are in the library.\n" + "7. Display how many books are in a branch.\n"
					+ "8. Randomly generate a specified number of books.\n"
					+ "9. Randomly generate a specified number of customers.\n"
					+ "10. Randomly generate a specified number of branches.\n" + "Click Cancel to exit the library.";
			// Use getInputChoice to display the main menu and have the user choose an
			// option.
			Integer userInputInt = DialogInput.getInputChoice(mainMenuPrompt, 10);

			// If null is returned, skip the remaining instructions since the user wants to
			// exit.
			if (userInputInt != null) {

				// The user wants to access the library, so create one
				Library ourLibrary = new Library(libraryName);
				ourLibrary = setDefaultBranchesBooksCustomers(ourLibrary);
				// Start building the outputMessage
				String outputMessage = "You selected " + userInputInt;
				// Determine the method to run
				switch (userInputInt) {
				case 1:
					ourLibrary = bookSearch(ourLibrary);
					break;
				case 2:
					ourLibrary = customerSearch(ourLibrary);
					break;
				case 3:
					ourLibrary = bookAdd(ourLibrary);
					break;
				case 4:
					ourLibrary = customerAdd(ourLibrary);
					break;
				case 5:
					ourLibrary = branchAdd(ourLibrary);
					break;
				case 6:
					displayNumLibraryBooks(ourLibrary);
					break;
				case 7:
					displayNumBranchBooks(ourLibrary);
					break;
				case 8:
					generateBooks(ourLibrary);
					break;
				case 9:
					generateCustomers(ourLibrary);
					break;
				case 10:
					generateBranches(ourLibrary);
					break;
				default:
					outputMessage += " which is not a valid menu option.";
					// Show the output string in a dialog box.
					displayError(outputMessage, "Invalid Choice");
				}
			} else {
				userExit = true;
			}
		}
	}

	/**
	 * Method displayMessage is the class-wide standard way to display an message
	 * specified by messageString. If blank, the message will display Success. If
	 * applicable, the standard header Information will be used.
	 * 
	 * @param errorString
	 * @param headerString
	 */
	private static void displayMessage(String messageString) {
		displayMessage(messageString, "");
	}

	/**
	 * Method displayMessage is the class-wide standard way to display an message
	 * specified by messageString. If blank, the message will display Success. If
	 * applicable, the header of the window will display headerString. If blank, the
	 * window header will display Information.
	 * 
	 * @param errorString
	 * @param headerString
	 */
	private static void displayMessage(String messageString, String headerString) {
		if (messageString.isBlank()) {
			messageString = "Success.";
		}
		if (headerString.isBlank()) {
			headerString = "Information";
		}
		JOptionPane.showMessageDialog(null, messageString, headerString, JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Method displayError is the class-wide standard way to display an error
	 * message specified by errorString. If errorString is blank, display the
	 * standard error message. If applicable, the standard header Error will be
	 * used.
	 * 
	 * @param errorString
	 */
	private static void displayError(String errorString) {
		displayError(errorString, "");
	}

	/**
	 * Method displayError is the class-wide standard way to display an error
	 * message specified by errorString. If errorString is blank, display the
	 * standard error message. If applicable, the header of the window will display
	 * headerString. If blank, the window header will display Error.
	 * 
	 * @param errorString
	 * @param headerString
	 */
	private static void displayError(String errorString, String headerString) {
		if (errorString.isBlank()) {
			errorString = "An error occured.";
		}
		if (headerString.isBlank()) {
			headerString = "Error";
		}
		JOptionPane.showMessageDialog(null, errorString, headerString, JOptionPane.ERROR_MESSAGE);
	}

	public static Library bookSearch(Library currentLibrary) {
		System.out.println("bookSearch");
		Integer searchType = DialogInput
				.getInputChoice("Book Search\nFor what book information would you like to search?\n" + "1. Title\n"
						+ "2. Author\n" + "3. Genre\n" + "4. ISBN\n" + "Please enter the number of your selection:", 4);
		// Check if the user clicked cancel and if so, return to the main menu.
		if (searchType != null) {
			String searchString = "";
			Book[] booksFound = new Book[0];
			switch (searchType) {
			case 1:
				searchString = DialogInput.getInputString("Please enter part or all of the book's title.");
				if (searchString == null) {
					System.out.println("Book search cancelled by the user.");
					break;
				}
				booksFound = currentLibrary.findBooks(searchString, searchType);
				break;
			case 2:
				searchString = DialogInput.getInputString("Please enter part or all of the book's author.");
				if (searchString == null) {
					System.out.println("Book search cancelled by the user.");
					break;
				}
				booksFound = currentLibrary.findBooks(searchString, searchType);
				break;
			case 3:
				searchString = DialogInput.getInputString("Please enter part or all of the book's genre.");
				if (searchString == null) {
					System.out.println("Book search cancelled by the user.");
					break;
				}
				booksFound = currentLibrary.findBooks(searchString, searchType);
				break;
			case 4:
				Long searchISBN = DialogInput.getInputLong("Please enter the book's 13 digit ISBN.", 13);
				if (searchISBN == null) {
					System.out.println("Book search cancelled by the user.");
					break;
				}
				booksFound = currentLibrary.findBooks(searchISBN);
				break;
			default:
				displayError("Your selection was invalid.", "Input Invalid");
				break;
			}
			String searchResults = "";
			if (booksFound != null && booksFound.length > 0) {
				searchResults = "Your search returned: \n";
				for (int index = 0; index < booksFound.length; index++) {
					searchResults += (index + 1) + ". " + booksFound[index].toString() + "\n";
				}
				Integer userChoice = DialogInput
						.getInputChoice(searchResults + "If you would like to set a book as the current selection, "
								+ "please enter its number, or cancel if not.", booksFound.length);
				if (userChoice != null) {
					currentBook = booksFound[userChoice - 1];
					currentLibrary = checkoutMenu(currentLibrary);
				}
			} else {
				displayError("Your search did not find any books.", "Nothing Found");
			}
		} else {
			System.out.println("Customer search cancelled by the user.");
		}
		return currentLibrary;
	}

	/**
	 * Method checkoutMenu checks to see if the currentCustomer and currentBook
	 * attributes have been set. If so, it prompts to see if the currentCustomer
	 * would like to check out the currentBook. If so, it checks if currentCustomer
	 * is allowed to check out books and if the current book is checked out. If so,
	 * it modifies the currentLibrary with the currentBook checked out to the
	 * currentCustomer. If the book cannot be checked out it displays appropriate
	 * notifications.
	 * 
	 * @param currentLibrary
	 * @return
	 */
	private static Library checkoutMenu(Library currentLibrary) {
		if (currentBook.getBookID() != 0) {
			if (currentCustomer.getCustomerID() != 0) {

				if (currentCustomer.isCanCheckOut()) {
					if (!currentBook.isCheckedOut()) {
						String checkoutPrompt = "Would customer " + currentCustomer.getCustomerID() + " "
								+ currentCustomer.getCustomerName() + "\n" + currentCustomer.getCustomerAddress()
								+ "\nlike to check out the follow book?\n" + currentBook.getTitle() + " copy number "
								+ currentBook.getCopyNum();
						if (!currentBook.isCheckedOut()) {
							checkoutPrompt += " not ";
						}
						checkoutPrompt += " checked out?";
						Boolean checkBookOut = DialogInput.getInputBoolean(checkoutPrompt);
						if (checkBookOut) {
							currentLibrary.checkOutBook(currentCustomer, currentBook);
							currentBook = new Book(0);
						}
					} else {
						displayError(currentBook.getTitle() + " copy number " + currentBook.getCopyNum()
								+ " is already checked out.");
					}
				} else {
					displayError(currentCustomer.getCustomerName() + " is not allowed to check out books.");
				}
			} else {
				displayMessage("To check out a book, please set a current customer using the customer search.");
			}
		} else {
			displayMessage("To check out a book, please set a current book using the book search.");
		}
		return currentLibrary;
	}

	private static Library customerSearch(Library currentLibrary) {
		Integer searchType = DialogInput
				.getInputChoice("Book Search\nFor what customer information would you like to search?\n" + "1. Name\n"
						+ "2. Street Address\n" + "3. City\n" + "4. State\n" + "5. Zip Code\n" + "6. Phone Number\n"
						+ "7. Email Address\n" + "Please enter the number of your selection:", 7);
		// Check if the user clicked cancel and if so, return to the main menu.
		if (searchType != null) {
			String searchString = "";
			Customer[] customersFound = new Customer[0];
			switch (searchType) {
			case 1:
				searchString = DialogInput.getInputString("Please enter part or all of the customer's name.");
				if (searchString == null) {
					System.out.println("Customer search cancelled by the user.");
					break;
				}
				customersFound = currentLibrary.findCustomers(searchString, searchType);
				break;
			case 2:
				searchString = DialogInput.getInputString("Please enter part or all of the customer's street address.");
				if (searchString == null) {
					System.out.println("Customer search cancelled by the user.");
					break;
				}
				customersFound = currentLibrary.findCustomers(searchString, searchType);
				break;
			case 3:
				searchString = DialogInput.getInputString("Please enter part or all of the customer's city.");
				if (searchString == null) {
					System.out.println("Customer search cancelled by the user.");
					break;
				}
				customersFound = currentLibrary.findCustomers(searchString, searchType);
				break;
			case 4:
				searchString = DialogInput.getInputState("Please enter the customer's 2 letter state abbreviation.");
				if (searchString == null) {
					System.out.println("Customer search cancelled by the user.");
					break;
				}
				customersFound = currentLibrary.findCustomers(searchString, searchType);
				break;
			case 5:
				Integer searchZip = DialogInput.getInputInt("Please enter the customer's 5 digit zip code as a number.",
						5);
				System.out.println("customerSearch - looking for zip code " + searchZip);
				if (searchZip == null) {
					System.out.println("Customer search cancelled by the user.");
					break;
				}
				customersFound = currentLibrary.findCustomers(searchZip);
				break;
			case 6:
				Long searchPhone = DialogInput.getInputLong(
						"Please enter the customer's phone number as a number without separating characters.", 10);
				if (searchPhone == null) {
					System.out.println("Customer search cancelled by the user.");
					break;
				}
				customersFound = currentLibrary.findCustomers(searchPhone);
				break;
			case 7:
				searchString = DialogInput.getInputString("Please enter part or all of the customer's email address.");
				if (searchString == null) {
					System.out.println("Customer search cancelled by the user.");
					break;
				}
				customersFound = currentLibrary.findCustomers(searchString, 5);
				break;
			default:
				displayError("Your selection was invalid.", "Input Invalid");
			}
			String searchResults = "";
			if (customersFound != null && customersFound.length > 0) {
				searchResults = "Your search returned: \n";
				for (int index = 0; index < customersFound.length; index++) {
					searchResults += (index + 1) + ". " + customersFound[index].toString() + "\n";
				}
				Integer userChoice = DialogInput.getInputChoice(
						searchResults + "\nIf you would like to set a customer as the current selection, "
								+ "please enter their number, or cancel if not.",
						customersFound.length);
				if (userChoice != null) {
					currentCustomer = customersFound[userChoice - 1];
					currentLibrary = checkoutMenu(currentLibrary);
				}
			} else {
				displayError("Your search did not find any customers.", "Nothing Found");
			}
		} else {
			System.out.println("Customer search cancelled by the user.");
		}
		return currentLibrary;
	}

	private static Library bookAdd(Library currentLibrary) {
		System.out.println("bookAdd");
		// Call addCustomer requesting user input.
		if (currentLibrary.addBook(true)) {
			displayMessage("Book added successfully.");
		} else {
			displayError("Book was not added.", "Invalid Input");
		}
		;
		return currentLibrary;
	}

	private static Library customerAdd(Library currentLibrary) {
		System.out.println("customerAdd");
		// Call addCustomer requesting user input.
		if (currentLibrary.addCustomer(true)) {
			displayMessage("Customer added successfully.");
		} else {
			displayError("Customer was not added.", "Invalid Input");
		}
		;
		return currentLibrary;
	}

	private static Library branchAdd(Library currentLibrary) {
		System.out.println("branchAdd");
		// Call addBranch requesting user input.
		if (currentLibrary.addBranch(true)) {
			displayMessage("Branch added successfully.");
		} else {
			displayError("Branch was not added.", "Invalid Input");
		}
		;
		return currentLibrary;
	}

	private static void displayNumLibraryBooks(Library currentLibrary) {
		System.out.println("displayNumLibraryBooks");
		Integer numOfBooks = currentLibrary.numberOfBooks(0);
		String output = "There are " + numOfBooks + " books in the " + currentLibrary.getLibraryName() + ".\n";
		numOfBooks = currentLibrary.numberOfBooks(1);
		output = "There are " + numOfBooks + " checked out, and " + currentLibrary.numberOfBooks(2) + " in stock.";
		displayMessage(output);
	}

	private static void displayNumBranchBooks(Library currentLibrary) {
		System.out.println("displayNumBranchBooks");
		Branch selectedBranch = currentLibrary.userChooseBranch();
		if (selectedBranch != null) {
			Integer numOfBooks = selectedBranch.numberOfBooks(0);
			String output = "There are " + numOfBooks + " books in the " + currentLibrary.getLibraryName() + ".\n";
			numOfBooks = selectedBranch.numberOfBooks(1);
			output = "There are " + numOfBooks + " checked out, and " + selectedBranch.numberOfBooks(2) + " in stock.";
			displayMessage(output);
		}
	}

	private static Library generateBooks(Library currentLibrary) {
		Integer numberToGenerate = DialogInput
				.getInputInt("How many new random books would you like to add to the library\n"
						+ "and assign to a randomly selected branch?");
		// Check if user cancelled
		if (numberToGenerate == null) {
			displayMessage("User cancelled book generation.");
			return currentLibrary;
		}
		for (int num = 1; num <= numberToGenerate; num++) {
			// Call addBook without user input.
			if (currentLibrary.addBook(false)) {
				displayMessage("Book generated successfully.");
			} else {
				displayError("Book was not generated.", "File Input Error");
			}
		}
		return currentLibrary;
	}

	private static Library generateCustomers(Library currentLibrary) {
		Integer numberToGenerate = DialogInput
				.getInputInt("How many new random customers would you like to add to the library?");
		// Check if user cancelled
		if (numberToGenerate == null) {
			displayMessage("User cancelled customer generation.");
			return currentLibrary;
		}
		for (int num = 1; num <= numberToGenerate; num++) {
			// Call addCustomer without user input.
			if (currentLibrary.addCustomer(false)) {
				displayMessage("Customer generated successfully.");
			} else {
				displayError("Customer was not generated.", "File Input Error");
			}
		}
		return currentLibrary;
	}

	private static Library generateBranches(Library currentLibrary) {
		Integer numberToGenerate = DialogInput
				.getInputInt("How many new random branches would you like to add to the library?");
		// Check if user cancelled
		if (numberToGenerate == null) {
			displayMessage("User cancelled branch generation.");
			return currentLibrary;
		}
		for (int num = 1; num <= numberToGenerate; num++) {
			// Call addBranch requesting user input.
			if (currentLibrary.addBranch(false)) {
				displayMessage("Branch generated successfully.");
			} else {
				displayError("Branch was not generated.", "Invalid Input");
			}
		}
		return currentLibrary;
	}

	/**
	 * Method setDefaultBranchesBooksCustomers assigns a set of default branches,
	 * books, and customers to test the functions of the library.
	 * 
	 * @param ourLibrary
	 * @return Library - updated version of the library with default branches,
	 *         books, and customers
	 */
	private static Library setDefaultBranchesBooksCustomers(Library ourLibrary) {
		Branch defaultBranch1 = new Branch("O'Fallon Virtual", "150 Main St.", "", "O'Fallon", "IL", 62269, 0,
				ourLibrary.getLibraryName());
		Branch defaultBranch2 = new Branch("St. Louis Virtual", "1520 Washington Ave.", "", "St. Louis", "MO", 63103, 0,
				ourLibrary.getLibraryName());
		// Add the Branches to the library and output an error if there was a problem
		// adding the branch.
		if (!ourLibrary.addBranch(defaultBranch1))
			System.out.println("Error with defaultBranch1");
		if (!ourLibrary.addBranch(defaultBranch2))
			System.out.println("Error with defaultBranch2");
		Book[] defaultBookArray = new Book[18];
		defaultBookArray[0] = new Book("Perelandra", "C. S. Lewis", 9780743234917L, 192, "Science Fiction",
				defaultBranch1.getBranchName(), false, null, false);
		defaultBookArray[1] = new Book("Foundation", "Isaac Asimov", 9780553293357L, 296, "Science Fiction",
				defaultBranch1.getBranchName(), false, null, false);
		defaultBookArray[2] = new Book("The War of the Worlds", "H. G. Wells", 9781505260793L, 108, "Science Fiction",
				defaultBranch2.getBranchName(), false, null, false);
		defaultBookArray[3] = new Book("A Wrinkle in Time", "Madeleine L'Engle", 9780312367541L, 256, "Science Fiction",
				defaultBranch1.getBranchName(), false, null, false);
		defaultBookArray[4] = new Book("Twenty Thousand Leagues Under the Sea", "Jules Verne", 9781512093599L, 212,
				"Science Fiction", defaultBranch2.getBranchName(), false, null, false);
		defaultBookArray[5] = new Book("I, Robot", "Isaac Asimov", 9780553382563L, 256, "Science Fiction",
				defaultBranch1.getBranchName(), false, null, false);
		defaultBookArray[6] = new Book("Ready Player One: A Novel", "Ernest Cline", 9780307887443L, 384,
				"Science Fiction", defaultBranch2.getBranchName(), false, null, false);
		defaultBookArray[7] = new Book("Starship Troopers", "Robert A. Heinlein", 9780441783589L, 263,
				"Science Fiction", defaultBranch1.getBranchName(), false, null, false);
		defaultBookArray[8] = new Book("The Martian Chronicles", "Ray Bradbury", 9781451678192L, 263, "Science Fiction",
				defaultBranch1.getBranchName(), false, null, false);
		defaultBookArray[9] = new Book("Foundation", "Isaac Asimov", 9780553293357L, 296, "Science Fiction",
				defaultBranch2.getBranchName(), false, null, false);
		defaultBookArray[10] = new Book("The War of the Worlds", "H. G. Wells", 9781505260793L, 108, "Science Fiction",
				defaultBranch1.getBranchName(), false, null, false);
		defaultBookArray[11] = new Book("A Wrinkle in Time", "Madeleine L'Engle", 9780312367541L, 256,
				"Science Fiction", defaultBranch2.getBranchName(), false, null, false);
		defaultBookArray[12] = new Book("Twenty Thousand Leagues Under the Sea", "Jules Verne", 9781512093599L, 212,
				"Science Fiction", defaultBranch1.getBranchName(), true, null, false);
		defaultBookArray[13] = new Book("I, Robot", "Isaac Asimov", 9780553382563L, 256, "Science Fiction",
				defaultBranch1.getBranchName(), false, null, false);
		defaultBookArray[14] = new Book("Ready Player One: A Novel", "Ernest Cline", 9780307887443L, 384,
				"Science Fiction", defaultBranch1.getBranchName(), false, null, false);
		defaultBookArray[15] = new Book("Starship Troopers", "Robert A. Heinlein", 9780441783589L, 263,
				"Science Fiction", defaultBranch1.getBranchName(), false, null, false);
		defaultBookArray[16] = new Book("The Martian Chronicles", "Ray Bradbury", 9781451678192L, 263,
				"Science Fiction", defaultBranch1.getBranchName(), false, null, false);
		defaultBookArray[17] = new Book("Ender's Game", "Orson Scott Card", 9780812550702L, 352, "Science Fiction",
				defaultBranch1.getBranchName(), false, null, false);
		for (Book tempBook : defaultBookArray) {
			if (!ourLibrary.addBook(tempBook)) {
				System.out.println("Added default book " + tempBook.toString());
			} else {
				System.out.println("Failed to add default book " + tempBook.toString());
			}
		}
		Customer[] defaultCustomerArray = new Customer[5];
		defaultCustomerArray[0] = new Customer("Dan Bradford", "120 Civic Plaza", "", "O'Fallon", "IL", 62269, 0,
				6186323783L, "daniel.a.bradford@outlook.com", 0.0, true);
		defaultCustomerArray[1] = new Customer("Miri Belle", "101 Dalmatian St.", "", "Yorkville", "IL", 60560, 0,
				6301234567L, "miri.belle15@nowhere.org", 0.0, false);
		defaultCustomerArray[2] = new Customer("Noah June", "910 2nd St.", "", "Maryville", "IL", 62062, 0, 6187654321L,
				"junen1@claim.com", 0.0, true);
		defaultCustomerArray[3] = new Customer("Brandon Owners", "406 W US Highway 50", "", "O'Fallon", "IL", 62269, 0,
				6181234321L, "brandown@claim.com", 0.0, true);
		defaultCustomerArray[4] = new Customer("Noah Noodles", "951 S Green Mount Rd", "", "Belleville", "IL", 62220,
				4814, 6182330513L, "noodleboy@okinawa.com", 0.0, true);
		for (Customer tempCustomer : defaultCustomerArray) {
			if (ourLibrary.addCustomer(tempCustomer)) {
				System.out.println("Added default customer " + tempCustomer.toString());
			} else {
				System.out.println("Failed to add default customer " + tempCustomer.toString());
			}
		}
		// NOTE: Default users can be no more than the number of default books or this
		// loop will go out of bounds.
		for (int index = 0; index < defaultCustomerArray.length; index++) {
			if (ourLibrary.checkOutBook(defaultCustomerArray[index], defaultBookArray[index])) {
				System.out.println("Default customer " + defaultCustomerArray[index].getCustomerName() + " checked out "
						+ defaultBookArray[index].getTitle() + " copy number " + defaultBookArray[index].getCopyNum()
						+ ".");
			} else {
				System.out.println("Default customer " + defaultCustomerArray[index].getCustomerName()
						+ " cannot check out " + defaultBookArray[index].getTitle() + " copy number "
						+ defaultBookArray[index].getCopyNum() + ".");
			}
		}
		// NOTE: Default users can be no more than half of the number of default books
		// or this loop will go out of bounds.
		int index2 = defaultCustomerArray.length;
		for (int index = 0; index < defaultCustomerArray.length; index++) {
			if (ourLibrary.checkOutBook(defaultCustomerArray[index], defaultBookArray[index2])) {
				System.out.println("Default customer " + defaultCustomerArray[index].getCustomerName() + " checked out "
						+ defaultBookArray[index].getTitle() + " copy number " + defaultBookArray[index2].getCopyNum()
						+ ".");
			} else {
				System.out.println("Default customer " + defaultCustomerArray[index].getCustomerName()
						+ " cannot check out " + defaultBookArray[index].getTitle() + " copy number "
						+ defaultBookArray[index2].getCopyNum() + ".");
			}
			index2++;
		}
		return ourLibrary;
	}

}

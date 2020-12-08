package libraryProject;

import java.time.LocalDate;

public class Book {
	private long bookID = 0;
	private String title;
	private String author;
	private long isbn; 
	private int numberOfPages;
	private String genre;
	private String assignedBranch;
	private boolean checkedOut = false;
	private LocalDate dueDate = null;
	private boolean needsToBeReplaced = false;
	
	/** Constructor for object Book with only BookID and ISBN (minimum required information).
	 * @param bookID - the library's unique number identifier of this book.
	 * @param isbn
	 */
	public Book (long isbn) {
		this.isbn = isbn;
		this.bookID = isbn * 1000;
	}
	
	/** Constructor for object Book which populates all data values except for the library assigned bookID.
	 * @param bookID - the library's unique number identifier of this book.
	 * @param title
	 * @param author
	 * @param isbn
	 * @param numberOfPages
	 * @param genre
	 * @param copyNumber
	 * @param checkedOut
	 * @param dueDate
	 * @param needsToBeReplaced
	 */
	public Book(String title, String author, long isbn, int numberOfPages, String genre,
			String assignedBranch, boolean checkedOut, LocalDate dueDate, boolean needsToBeReplaced) {
		super();
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.numberOfPages = numberOfPages;
		this.genre = genre;
		this.assignedBranch = assignedBranch;
		this.checkedOut = checkedOut;
		this.dueDate = dueDate;
		this.needsToBeReplaced = needsToBeReplaced;
	}
	
	/** Constructor for object Book which populates all data values.
	 * @param bookID - the library's unique number identifier of this book.
	 * @param title
	 * @param author
	 * @param isbn
	 * @param numberOfPages
	 * @param genre
	 * @param copyNumber
	 * @param checkedOut
	 * @param dueDate
	 * @param needsToBeReplaced
	 */
	public Book(long bookID, String title, String author, long isbn, int numberOfPages, String genre,
			String assignedBranch, boolean checkedOut, LocalDate dueDate, boolean needsToBeReplaced) {
		super();
		this.bookID = bookID;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.numberOfPages = numberOfPages;
		this.genre = genre;
		this.assignedBranch = assignedBranch;
		this.checkedOut = checkedOut;
		this.dueDate = dueDate;
		this.needsToBeReplaced = needsToBeReplaced;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAssignedBranch() {
		return assignedBranch;
	}

	public void setAssignedBranch(String assignedBranch) {
		this.assignedBranch = assignedBranch;
	}

	public boolean isCheckedOut() {
		return checkedOut;
	}

	public void setCheckedOut(boolean checkedOut) {
		this.checkedOut = checkedOut;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isNeedsToBeReplaced() {
		return needsToBeReplaced;
	}

	public void setNeedsToBeReplaced(boolean needsToBeReplaced) {
		this.needsToBeReplaced = needsToBeReplaced;
	}

	public long getBookID() {
		return bookID;
	}

	public void setBookID(long bookID) {
		this.bookID = bookID;
	}
	
	public Integer getCopyNum() {
		if (this.bookID == 0) {
			return null;
		}
		int extractedCopyNum = 0;
		long tempBookID = this.bookID;
		// Loop and increment by multiplying index by ten to get place value numbers 1, 10, 100
		for (int index = 1; index <= 100; index = index * 10) {
			// Use the remainder of tempBookID to get the least significant digit.
			extractedCopyNum = extractedCopyNum + (int)(tempBookID % 10L) * index;
			// Divide tempBookID by 10 to remove the least significant digit.
			tempBookID = tempBookID / 10L;
		}
		return extractedCopyNum;
	}
	
	/** Method promptUserForBookInfo uses dialog boxes to prompt the user for the book title, author, 
	 * 	ISBN, number of pages, genre, assign a branch where the book is held. 
	 * @param Customer newCustomer - new customer object shell with customerID assigned
	 * @return true if the information was valid, false if the user cancelled
	 */
	public boolean promptUserForBookInfo() {
		userCancelled:
		do {
			String title = DialogInput.getInputString("Please enter the title of the book:");
			// If DialogInput return is null, then the user pressed Cancel, so break out of the loop.
			if (title == null) {
				break userCancelled;
			}
			this.title = title;

			String author = DialogInput.getInputString("Please enter the author of the book:");
			// If DialogInput return is null, then the user pressed Cancel, so break out of the loop.
			if (author == null) {
				break userCancelled;
			}
			this.author = author;
			
			Long isbn = DialogInput.getInputLong("Please enter book's 13 digit ISBN:", 13);
			// If DialogInput return is null, then the user pressed Cancel, so break out of the loop.
			if (isbn == (Long)null) {
				break userCancelled;
			}
			this.isbn = isbn;
			
			Integer numberOfPages = DialogInput.getInputInt("Please enter the number of pages in the book:");
			// If DialogInput return is null, then the user pressed Cancel, so break out of the loop.
			if (numberOfPages == null) {
				break userCancelled;
			}
			this.numberOfPages = numberOfPages;
			
			String genre = DialogInput.getInputString("Please enter the book's genre:");
			// If DialogInput return is null, then the user pressed Cancel, so break out of the loop.
			if (genre == null) {
				break userCancelled;
			}
			this.genre = genre;
			this.checkedOut = false;
			this.needsToBeReplaced = false;
			System.out.println("promptUserForBookInfo - book information updated");	
			// All inputs received without user canceling.
			return true;
		} while (false);
		System.out.println("promptUserForBookInfo - book information invalid");
		this.bookID = 0;
		return false;
	}
	
	/** Method generateNewBook randomly generates basic book information and assigns it to the attributes of this book.
	 * @return true if successful, false if there was an error generating the book information.
	 */
	public boolean generateNewBook() {
		BookInfoGenerator gen = new BookInfoGenerator();
		if (gen.isValid()) {
			this.author = gen.getAuthor();
			this.title = gen.getTitle();
			this.isbn = Long.parseLong(gen.getIsbn());
			this.numberOfPages = Integer.parseInt(gen.getNumberOfPages());
			this.genre = gen.getGenre();
			return true;
		}
		this.bookID = 0;
		return false;
	}
	
	/** Method isValidBook checks the current Book object to ensure all properties have non-blank or null values.
	 * 	It prints the book to the console and if invalid, the first reason why it was found to be invalid.
	 * @param isNewBook - true if the Book is new so it does not check for an assigned branch, bookID or no due date if it is checked out.
	 * @return
	 */
	public boolean isValidBook(boolean isNewBook) {
		// Print this book information to the console.
		System.out.println(this.toString());
		if (!isNewBook) {
			if (this.assignedBranch.isBlank() || this.assignedBranch.isEmpty()) {
				System.out.println("isValidBook - existing book is not assigned to a branch.");
				return false;
			}
			if ((Long)this.bookID == null || this.bookID == 0) {
				System.out.println("isValidBook - existing book has no bookID.");
				return false;
			}
			if (this.checkedOut && this.dueDate == null) {
				System.out.println("isValidBook - book checked out with no due date.");
				return false;
			}
		}
		if ((Long)this.isbn == null || this.isbn == 0) {
			System.out.println("isValidBook - book has no ISBN.");
			return false;
		}
		if (this.title.isBlank() || this.title.isEmpty()) {
			System.out.println("isValidBook - book has no title.");
			return false;
		}
		if (this.author.isBlank() || this.author.isEmpty()) {
			System.out.println("isValidBook - book has no author.");
			return false;
		}
		if (this.genre.isBlank() || this.genre.isEmpty()) {
			System.out.println("isValidBook - book has no genre.");
			return false;
		}
		if ((Integer)this.numberOfPages == null || this.numberOfPages == 0) {
			System.out.println("isValidBook - book has no number of pages specified.");
			return false;
		}
		System.out.println("isValidBook - book is valid.");
		return true;
	}
	
	/** Method toString overrides the default Object.toString to summarize the library.
	 * @return String with the salient details of the library
	 */
	@Override
	public String toString() {
		String outputString = "Book ISBN " + this.isbn + " " + this.title +  " by " + this.author + " with " + this.numberOfPages + " pages, of the " + 
				this.genre + " genre\n";
		return outputString;
	}
	
}
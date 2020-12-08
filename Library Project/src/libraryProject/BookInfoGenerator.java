package libraryProject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

public class BookInfoGenerator {
	private Random rand = new Random();
	
	private String title;
	private String author;
	private String isbn; 
	private String numberOfPages;
	private String genre;
	
	/** The default constructor calls the methods necessary to randomly generate all the attributes. If any generator
	 *  encounters an error, it clears all attributes.
	 */
	public BookInfoGenerator() {
		if (!generateAuthor() || !generateISBNTitle() || !generatePages() || !generateGenre()) {
			// If any of the generate methods did not work, clear all the object values and return false.
			this.title = "";
			this.author = "";
			this.isbn = "";
			this.numberOfPages = "";
			this.genre = "";
		}
	}
	
	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getNumberOfPages() {
		return numberOfPages;
	}

	public String getGenre() {
		return genre;
	}

	/** Method isValid checks to see if the constructor randomly populated the customer information. It returns false
	 *  if the attribute isbn is not set.
	 * @return
	 */
	public boolean isValid() {
		if (this.isbn.isEmpty()) {
			return false;
		}
		return true;
	}
	
	/** The method generateAuthor randomly chooses a first name from a file of first names and a last name from a file
	 * of last names and assigns the chosen value to the firstName and lastName attributes respectively.
	 * @return true if successful, false if the input file was not found or contained invalid data.
	 */
	private boolean generateAuthor() {
		List<String> firstNameList = readFileToStringList("FirstNames.txt");
		List<String> lastNameList = readFileToStringList("LastNames.txt");
		if (firstNameList.isEmpty() || lastNameList.isEmpty()) {
			return false;
		}
		int firstIndex = rand.nextInt(firstNameList.size() - 1);
		int lastIndex = rand.nextInt(lastNameList.size() - 1);
		this.author = (firstNameList.get(firstIndex) + " " + lastNameList.get(lastIndex));
		return true;
	}
	
	/** Method generateEmail randomly chooses a book genre based on a file containing book genres.
	 *  The choice is assigned to the email attribute.
	 * @return true if successful, false if the input file was not found or contained invalid data.
	 */
	public boolean generateGenre() {
		String genre = "";
		List<String> genreList = readFileToStringList("BookGenres.txt");
		// If the genreList is empty, there was an issue with the file, so return false
		if (genreList.isEmpty()) {
			return false;
		}
		int genreIndex = rand.nextInt(genreList.size() - 1);
		this.genre = genreList.get(genreIndex).trim();
		return true;
	}
	/** Method generatePages randomly generates a number of pages up to 860 and assigns it to the numberOfPages attribute.
	 * @return true if successful, false if the input file was not found or contained invalid data.
	 */
	private boolean generatePages() {
		this.numberOfPages = String.valueOf(rand.nextInt(860));
		return true;
	}

	/** Method generateISBNTitle loads a file of corresponding ISBN followed by book title on each line. It randomly 
	 *   chooses a line, and sets the class attributes to reflect the chosen information. 10 digit ISBN are have 978 
	 *   appended to the beginning of the ISBN since only 978 and 979 have been implemented since the 13 digit ISBN standard
	 *   has been established. It updates the appropriate attributes.
	 * @return true if successful, false if the input file was not found or contained invalid data.
	 */
	private boolean generateISBNTitle() {
		StringChecker check = new StringChecker();
		List<String> bookList = readFileToStringList("BookList.txt");
		if (bookList.isEmpty()) {
			return false;
		}
		int bookIndex = rand.nextInt(bookList.size() - 1);
		String isbnTitle = bookList.get(bookIndex).trim();
		String isbn = "";
		String title = "";
		if (!isbnTitle.isBlank()) {
			// Split the string expecting the following format ISBN Title.
			String[] parseArray = isbnTitle.split(" ");
			isbn = parseArray[0].trim();
	
			for (int index = 1; index < parseArray.length - 1; index++) {
				title += parseArray[index] + " ";
			}
			if (parseArray.length <= 1) {
				System.out.println("Not enough characters to parse an ISBN followed by a book title.");
				return false;
			}
		} else {
			System.out.println("Input file line is blank");
			return false;
		}
		if (check.isValidInt(isbn, 10)) {
			this.isbn = "978" + isbn;
		} else if (check.isValidInt(isbn, 13)){
			this.isbn = isbn;
		} else {
			System.out.println("Not a valid 10 or 13 digit ISBN");
			return false;
		}
		this.title = title;
		return true;
	}
	
	/** Method readFileToStringList reads the text file specified by fileName. It
	 * stores each line as a string and returns an ArrayList of these Strings.
	 * 
	 * @param fileName
	 * @return
	 */
	private List<String> readFileToStringList(String fileName) {

		List<String> inputStringList = List.of();
		try {
			inputStringList = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
		}
		// If there is a problem reading the file, return a blank List.
		catch (IOException e) {
			e.printStackTrace();
			return List.of();
		}
		// Loop through the list of strings, trimming white space and removing blank
		// strings.
		for (int index = 0; index < inputStringList.size(); index++) {
			inputStringList.set(index, inputStringList.get(index).trim());
			if (inputStringList.get(index).isBlank()) {
				inputStringList.remove(index);
			}
		}
		return inputStringList;
	}


}

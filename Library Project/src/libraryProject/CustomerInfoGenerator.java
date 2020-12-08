package libraryProject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

public class CustomerInfoGenerator {
	
	private Random rand = new Random();
	
	private String firstName = "";
	private String lastName = "";
	private String streetNumberName = "";
	private String city = "";
	private String state = "";
	private String zipCode = "";
	private String phoneNumber = "";
	private String email = "";
	
	/** The default constructor calls the methods necessary to randomly generate all the attributes. If any generator
	 *  encounters an error, it clears all attributes.
	 */
	public CustomerInfoGenerator() {
		if (!generateName() || !generateEmail() || !generateStreet() || !generateCityStateZipPhone()) {
			// If any of the generate methods did not work, clear all the object values and return false.
			this.firstName = "";
			this.lastName = "";
			this.streetNumberName = "";
			this.city = "";
			this.state = "";
			this.zipCode = "";
			this.phoneNumber = "";
			this.email = "";
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getStreetNumberName() {
		return streetNumberName;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}
	
	/** Method isValid checks to see if the constructor randomly populated the customer information. It returns false
	 *  if the attribute firstName is not set.
	 * @return
	 */
	public boolean isValid() {
		if (this.firstName.isEmpty()) {
			return false;
		}
		return true;
	}
	
	/** The method generateName randomly chooses a first name from a file of first names and a last name from a file
	 * of last names and assigns the chosen value to the firstName and lastName attributes respectively.
	 * @return true if successful, false if the input file was not found or contained invalid data.
	 */
	private boolean generateName() {
		List<String> firstNameList = readFileToStringList("FirstNames.txt");
		List<String> lastNameList = readFileToStringList("LastNames.txt");
		if (firstNameList.isEmpty() || lastNameList.isEmpty()) {
			return false;
		}
		int firstIndex = rand.nextInt(firstNameList.size() - 1);
		int lastIndex = rand.nextInt(lastNameList.size() - 1);
		this.firstName = firstNameList.get(firstIndex);
		this.lastName = lastNameList.get(lastIndex);
		return true;
	}
	
	/** Method generateEmail randomly generates an email address based on a file containing e-mail domains, and the 
	 *  assigned first name and last name. It randomly chooses letters from the beginning of the first name followed
	 *  by letters from the beginning of the last name and concatenates them with a random number of up to 3 digits.
	 *  It then concatenates an @ and a randomly selected e-mail domain from the file. If first or last name are
	 *  blank, use "A" as the first name and the last name. The result is assigned to the email attribute.
	 * @return true if successful, false if the input file was not found or contained invalid data.
	 */
	private boolean generateEmail() {
		// Create a placeholder name if first or last name are blank
		if (this.firstName.isBlank()) {
			this.firstName = "A";
		}
		if (this.lastName.isBlank()) {
			this.lastName = "A";
		}
		String email = "";
		List<String> domainList = readFileToStringList("EmailDomains.txt");
		// If the domainList is empty, there was an issue with the file, so return false
		if (domainList.isEmpty()) {
			return false;
		}
		int domainIndex = rand.nextInt(domainList.size() - 1);
		// Select a random substring from the beginning of the first name and the beginning of the last name.
		email = this.firstName.substring(0, rand.nextInt(this.firstName.length() - 1));
		email += this.lastName.substring(0, rand.nextInt(this.lastName.length() - 1));
		email += rand.nextInt(999);
		this.email = (email + "@" + domainList.get(domainIndex));
		return true;
	}
	/** Method generateStreet randomly generates a street number up to 4 digits and appends it to a street name
	 * randomly selected from a file, then assigns the result to the streetNumberName attribute.
	 * @return true if successful, false if the input file was not found or contained invalid data.
	 */
	private boolean generateStreet() {
		List<String> streetNameList = readFileToStringList("StreetNames.txt");
		if (streetNameList.isEmpty()) {
			return false;
		}
		int streetIndex = rand.nextInt(streetNameList.size() - 1);
		this.streetNumberName = (rand.nextInt(9999) + " " + streetNameList.get(streetIndex));
		return true;
	}

	/** Method generateCityStateZipPhone loads a file of corresponding City names(c), Zipcodes(z) Area Codes(a) in the 
	 * following format "zzzzz c... (aaa)\n where city names can be one or multiple words of an unspecified length.
	 * The only state implemented is Illinois (IL). It randomly chooses a line, randomly generates the rest of the 
	 * phone number after the area code, and sets the class attributes to reflect the chosen information.
	 * @return true if successful, false if the input file was not found or contained invalid data.
	 */
	private boolean generateCityStateZipPhone() {
		StringChecker check = new StringChecker();
		List<String> zipCityCodeList = readFileToStringList("ILZipCityNameAreaCode.txt");
		if (zipCityCodeList.isEmpty()) {
			return false;
		}
		int streetIndex = rand.nextInt(zipCityCodeList.size() - 1);
		String zipCityCode = zipCityCodeList.get(streetIndex).trim();
		String zipCode = "";
		String cityName = "";
		String areaCode = "";
		if (!zipCityCode.isBlank()) {
			// Split the string expecting the following format zipCode City Name(s) Area Code surrounded by parentheses.
			String[] parseArray = zipCityCode.split(" ");
			zipCode = parseArray[0].trim();
			
			for (int index = 1; index < parseArray.length - 1; index++) {
				cityName += parseArray[index] + " ";
			}
			if (parseArray.length > 1) {
				areaCode = parseArray[parseArray.length - 1].trim();
			} else {
				System.out.println("Not enough characters to parse as an area code");
				return false;
			}
		} else {
			return false;
		}
		if (!check.isValidInt(zipCode, 5)) {
			System.out.println("Not a valid 5 digit zip code");
			return false;
		}
		if (areaCode.length() != 5) {
			System.out.println("Area code not 5 characters including parentheses");
			return false;
		}
		areaCode = areaCode.substring(1, 4);
		if (!check.isValidInt(areaCode, 3)) {
			System.out.println("Area code not a valid integer value");
			return false;
		}
		DecimalFormat paddedPhone = new DecimalFormat("0000000");
		String phoneNumber = paddedPhone.format(rand.nextInt(9999999));
		phoneNumber = areaCode + phoneNumber;
		this.city = cityName.trim();
		this.state = "IL";
		this.zipCode = zipCode;
		this.phoneNumber = phoneNumber;
		return true;
	}

	/** Method readFileToStringList reads the text file specified by fileName. It
	 * stores each line as a string and returns an ArrayList of these Strings.
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

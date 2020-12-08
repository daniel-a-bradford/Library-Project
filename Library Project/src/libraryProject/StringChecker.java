package libraryProject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

/** This class consists of methods to validate Strings to make sure they can be converted to the requested type
 * without generating an exception.
 * Parameters in curly brackets {} are optional. 
 * Methods contained -
 * 	boolean isValidInt(String, {int fromThis}, {int toThis}, {boolean silent}) 
 *  boolean isValidLong(String, {int fromThis}, {int toThis}, {boolean silent})
 * 	boolean isValidDouble(String, {int fromThis}, {int toThis}, {boolean silent})
 *  boolean isValidChar(String, {int fromThis}, {int toThis}, {boolean silent})
 *  boolean isValidString(String, {boolean silent})
 *  boolean isValidDate(String, {boolean silent})
 *  boolean isValidTime(String, {boolean silent})
 *  boolean isValidState(String)
 */

public class StringChecker {
	
	/** Method displayError is the class-wide standard way to display an error message specified by 
	 * 	errorString. If errorString is blank, display the standard error message.
	 * @param errorString
	 */
	private void displayError(String errorString) {
		if (errorString.isBlank() || errorString.isEmpty()) {
			errorString = "You did not provide a valid input.";
		}
		JOptionPane.showMessageDialog(null, errorString, "Invalid Input", JOptionPane.WARNING_MESSAGE);;
	}
	
	/** Method isValidInt tests to see if the inputString can be parsed as an integer.
	 * If so, it returns true, otherwise false. Will not display error messages.
	 * @param inputString
	 * @return boolean
	 */
	public boolean isValidInt(String inputString) {
		return isValidInt(inputString, true);
	}
	
	/** Method isValidInt tests to see if the inputString can be parsed as an integer.
	 * If so, it returns true, otherwise false.
	 * If silent is true, then do not display an error message.
	 * @param inputString
	 * @param silent
	 * @return boolean
	 */
	public boolean isValidInt(String inputString, boolean silent) {
		// If inputString is blank, return false
		if(!isValidString(inputString)) {
			return false;
		}
		inputString = inputString.trim();
		// Try to interpret the user input string as an integer and assign it to userInt
		try {
			// If parseInt does not throw an exception, the user's input is an integer.
			int inputInt = Integer.parseInt(inputString);
		} 
		// Integer.parseInt will throw a NumberFormatException if the input is not cannot be converted to an integer.
		catch (NumberFormatException e) {
			if (!silent) {
				displayError("Your input was not an integer. \n"
					+ "Please enter a number without a decimal.\n");
			}
			return false;
		}
		return true;
	}
	
	/** Method isValidInt tests to see if the inputString can be parsed as an integer with the required
	 * 	number of digits (numDigits). Will not display error messages.
	 * If so, it returns true, otherwise false.
	 * @param inputString
	 * @param numDigits
	 * @return boolean
	 */
	public boolean isValidInt(String inputString, int numDigits) {
		return isValidInt(inputString, numDigits, true);
	}
	
	/** Method isValidInt tests to see if the inputString can be parsed as an integer with the required
	 * 	number of digits (numDigits).
	 * If so, it returns true, otherwise false.
	 * If silent is true, then do not display error messages.
	 * @param inputString
	 * @param numDigits
	 * @param silent
	 * @return boolean
	 */
	public boolean isValidInt(String inputString, int numDigits, boolean silent) {
		// Call isValid to validate type and display applicable error messages if not.
		if (isValidInt(inputString)) {
			if (inputString.length() != numDigits) {
				if (!silent) {
					displayError("The number does not have exactly " + numDigits + " digits.");	
				}
				return false;
			}
			return true;
		}
		return false;
	}
	
	/** Method isValidInt tests to see if the inputString can be parsed as an integer.
	 * If it can, then it checks if the integer is between fromThis and toThis inclusively.
	 * If so, it returns true, otherwise false. Will not display error messages.
	 * @param inputString
	 * @param fromThis
	 * @param toThis
	 * @return boolean
	 */
	public boolean isValidInt(String inputString, int fromThis, int toThis) {
		return isValidLong(inputString, fromThis, toThis, true);
	}
	
	/** Method isValidInt tests to see if the inputString can be parsed as an integer.
	 * If it can, then it checks if the integer is between fromThis and toThis inclusively.
	 * If so, it returns true, otherwise false.
	 * If silent is true, then do not display an error message.
	 * @param inputString
	 * @param fromThis
	 * @param toThis
	 * @param silent
	 * @return boolean
	 */
	public boolean isValidInt(String inputString, int fromThis, int toThis, boolean silent) {
		int inputInt = 0;
		if (toThis < fromThis) {
			// Values are reversed so swap them rather than returning false because of invalid parameters.
			return isValidInt(inputString, toThis, fromThis, silent);
		}
		if (isValidInt(inputString, silent)) {
			inputInt = Integer.parseInt(inputString);
		}
		if (inputInt >= fromThis && inputInt <= toThis) {
			return true;
		}
		if (!silent) {
			displayError("Your input was not between " + fromThis + " and " + toThis + ".");
		}
		return false;
	}
	
//Long
	
	/** Method isValidLong tests to see if the inputString can be parsed as a long integer.
	 * If so, it returns true, otherwise false. Will not display error messages.
	 * @param inputString
	 * @return boolean
	 */
	public boolean isValidLong(String inputString) {
		return isValidLong(inputString, true);
	}
	
	/** Method isValidLong tests to see if the inputString can be parsed as a long integer.
	 * If so, it returns true, otherwise false.
	 * If silent is true, then do not display an error message.
	 * @param inputString
	 * @param silent
	 * @return boolean
	 */
	public boolean isValidLong(String inputString, boolean silent) {
		// If inputString is blank, return false
		if(!isValidString(inputString)) {
			return false;
		}
		inputString = inputString.trim();
		// Try to interpret the user input string as an integer and assign it to userLong
		try {
			// If parseLong does not throw an exception, the user's input is a long integer.
			long inputLong = Long.parseLong(inputString);
		} 
		// Long.parseLong will throw a NumberFormatException if the input is not cannot be converted to an integer.
		catch (NumberFormatException e) {
			if (!silent) {
				displayError("Your input was not an integer. Please enter a number without a decimal.\n");
			}
			return false;
		}
		return true;
	}
	
	/** Method isValidLong tests to see if the inputString can be parsed as an integer with the required
	 * 	number of digits (numDigits). Will not display error messages.
	 * If so, it returns true, otherwise false.
	 * @param inputString
	 * @param numDigits
	 * @return boolean
	 */
	public boolean isValidLong(String inputString, int numDigits) {
		return isValidLong(inputString, numDigits, true);
	}
	
	/** Method isValidLong tests to see if the inputString can be parsed as an integer with the required
	 * 	number of digits (numDigits).
	 * If so, it returns true, otherwise false.
	 * If silent is true, then do not display error messages.
	 * @param inputString
	 * @param numDigits
	 * @param silent
	 * @return boolean
	 */
	public boolean isValidLong(String inputString, int numDigits, boolean silent) {
		// Call isValid to validate type and display applicable error messages if not.
		if (isValidLong(inputString)) {
			if (inputString.length() != numDigits) {
				if (!silent) {
					displayError("The number does not have exactly " + numDigits + " digits.");	
				}
				return false;
			}
			return true;
		}
		return false;
	}
	
	/** Method isValidLong tests to see if the inputString can be parsed as a long integer.
	 * If it can, then it checks if the integer is between fromThis and toThis inclusively.
	 * If so, it returns true, otherwise false. Will not display error messages.
	 * @param inputString
	 * @param fromThis
	 * @param toThis
	 * @return boolean
	 */
	public boolean isValidLong(String inputString, long fromThis, long toThis) {
		return isValidLong(inputString, fromThis, toThis, true);
	}
	
	/** Method isValidLong tests to see if the inputString can be parsed as an integer.
	 * If it can, then it checks if the integer is between fromThis and toThis inclusively.
	 * If so, it returns true, otherwise false.
	 * If silent is true, then do not display an error message.
	 * @param inputString
	 * @param fromThis
	 * @param toThis
	 * @param silent
	 * @return boolean
	 */
	public boolean isValidLong(String inputString, long fromThis, long toThis, boolean silent) {
		long inputLong = 0L;
		if (toThis < fromThis) {
			// Values are reversed so swap them rather than returning false because of invalid parameters.
			return isValidLong(inputString, toThis, fromThis, silent);
		}
		if (isValidLong(inputString, silent)) {
			inputLong = Long.parseLong(inputString);
		}
		if (inputLong >= fromThis && inputLong <= toThis) {
			return true;
		}
		if (!silent) {
			displayError("Your input was not between " + fromThis + " and " + toThis + ".");
		}
		return false;
	}

//Doubles
	
	/** Method isValidDouble tests to see if the inputString can be parsed as an decimal.
	 * If so, it returns true, otherwise false. Will not display error messages.
	 * @param inputString
	 * @return boolean
	 */
	public boolean isValidDouble(String inputString) {
		return isValidDouble(inputString, true);
	}
	
	/** Method isValidDouble tests to see if the inputString can be parsed as an decimal.
	 * If so, it returns true, otherwise false.
	 * If silent is true, then do not display an error message.
	 * @param inputString
	 * @param silent
	 * @return boolean
	 */
	public boolean isValidDouble(String inputString, boolean silent) {
		// If inputString is blank, return false
		if(!isValidString(inputString)) {
			return false;
		}
		inputString = inputString.trim();
		// Try to interpret the user input string as an decimal and assign it to userDouble
		try {
			// If parseDouble does not throw an exception, the user's input is an decimal.
			double inputDouble = Double.parseDouble(inputString);
		} 
		// Double.parseDouble will throw a NumberFormatException if the input is not cannot be converted to an decimal.
		catch (NumberFormatException e) {
			if (!silent) {
				displayError("Your input was not an decimal number.\n");
			}
			return false;
		}
		return true;
	}
	
	/** Method isValidDouble tests to see if the inputString can be parsed as an integer with the required
	 * 	number of digits (numDigits) excluding the decimal point. Will not display error messages.
	 * If so, it returns true, otherwise false.
	 * @param inputString
	 * @param numDigits
	 * @return boolean
	 */
	public boolean isValidDouble(String inputString, int numDigits) {
		return isValidDouble(inputString, numDigits, true);
	}
	
	/** Method isValidDouble tests to see if the inputString can be parsed as an integer with the required
	 * 	number of digits (numDigits) excluding the decimal point.
	 * If so, it returns true, otherwise false.
	 * If silent is true, then do not display error messages.
	 * @param inputString
	 * @param numDigits
	 * @param silent
	 * @return boolean
	 */
	public boolean isValidDouble(String inputString, int numDigits, boolean silent) {
		// Call isValid to validate type and display applicable error messages if not.
		// .length() - 1 excludes the decimal point from the number of digits.
		if (isValidDouble(inputString)) {
			if (inputString.contains(".")) {
				if (inputString.length() - 1 != numDigits) {
					if (!silent) {
						displayError("Excluding the decimal point, the number does not have exactly " 
								+ numDigits + " digits.");	
					}
					return false;
				}
			} else if (inputString.length() != numDigits) {
				if (!silent) {
					displayError("The number does not have exactly " + numDigits + " digits.");	
				}
				return false;
				
			}
			return true;
		}
		return false;
	}
	
	/** Method isValidDouble tests to see if the inputString can be parsed as an decimal.
	 * If it can, then it checks if the decimal is between fromThis and toThis inclusively.
	 * If so, it returns true, otherwise false. Will not display error messages.
	 * @param inputString
	 * @param fromThis
	 * @param toThis
	 * @return boolean
	 */
	public boolean isValidDouble(String inputString, double fromThis, double toThis) {
		return isValidDouble(inputString, fromThis, toThis, true);
	}
	
	/** Method isValidDouble tests to see if the inputString can be parsed as an decimal.
	 * If it can, then it checks if the decimal is between fromThis and toThis inclusively.
	 * If so, it returns true, otherwise false.
	 * If silent is true, then do not display an error message.
	 * @param inputString
	 * @param fromThis
	 * @param toThis
	 * @param silent
	 * @return
	 */
	public boolean isValidDouble(String inputString, double fromThis, double toThis, boolean silent) {
		double inputDouble = 0.0;
		if (toThis < fromThis) {
			// Values are reversed so swap them rather than returning false because of invalid parameters.
			return isValidDouble(inputString, toThis, fromThis, silent);
		}
		if (isValidDouble(inputString, silent)) {
			inputDouble = Double.parseDouble(inputString);
		}
		if (inputDouble >= fromThis && inputDouble <= toThis) {
			return true;
		}
		if (!silent) {
			displayError("Your input was not between " + fromThis + " and " + toThis + ".");
		}
		return false;
	}
	
//Character
	
	/** Method isValidChar tests to see if the inputString not is empty or blank.
	 * If so, it returns true, otherwise false. Will not display error messages.
	 * @param inputString
	 * @return boolean
	 */
	public boolean isValidChar(String inputString) {
		return isValidChar(inputString, true);
	}
	
	/** Method isValidChar tests to see if the inputString is not empty or blank.
	 * If so, it returns true, otherwise false.
	 * If silent is true, then do not display an error message.
	 * @param inputString
	 * @param silent
	 * @return
	 */
	public boolean isValidChar(String inputString, boolean silent) {
		// If the input string is blank or empty advise the user (if not silent) and return false.
		if (inputString.isBlank() || inputString.isEmpty()) {
			if (!silent) {
				displayError("You did not provide an input.\n");
			}
			return false;
		}
		return true;
	}
	
	/** Method isValidChar tests to see if the inputString is not empty or blank.
	 * If it can, then it checks if the character is between fromThis and toThis inclusively
	 * using the ASCII values. Will not display error messages.
	 * If so, it returns true, otherwise false.
	 * @param inputString
	 * @param fromThis
	 * @param toThis
	 * @return
	 */
	public boolean isValidChar(String inputString, char fromThis, char toThis) {
		return isValidChar(inputString, fromThis, toThis, true);
	}
	
	/** Method isValidChar tests to see if the inputString can be parsed as an decimal.
	 * If it can, then it checks if the decimal is between fromThis and toThis inclusively.
	 * If so, it returns true, otherwise false.
	 * If silent is true, then do not display an error message.
	 * @param inputString
	 * @param fromThis
	 * @param toThis
	 * @param silent
	 * @return
	 */
	public boolean isValidChar(String inputString, char fromThis, char toThis, boolean silent) {
		char inputChar = 'a';
		if (toThis < fromThis) {
			// Values are reversed so swap them rather than returning false because of invalid parameters.
			return isValidChar(inputString, toThis, fromThis, silent);
		}
		if (isValidChar(inputString)) {
			inputChar = inputString.charAt(0);
		}
		if (inputChar >= fromThis && inputChar <= toThis) {
			return true;
		}
		if (!silent) {
			displayError("Your input was not between " + fromThis + " and " + toThis + ".");
		}
		return false;
	}
	
//String
	
	/** Method isValidString tests to see if the inputString not is empty or blank.
	 * If so, it returns true, otherwise false. Will not display error messages.
	 * @param inputString
	 * @return
	 */
	public boolean isValidString(String inputString) {
		return isValidString(inputString, true);
	}
	
	/** Method isValidString tests to see if the inputString is not empty or blank.
	 * If so, it returns true, otherwise false.
	 * If silent is true, then do not display an error message.
	 * @param inputString
	 * @param silent
	 * @return
	 */
	public boolean isValidString(String inputString, boolean silent) {
		if (inputString == null) {
			return false;
		}
		// If the input string is blank or empty advise the user (if not silent) and return false.
		if (inputString.isBlank() || inputString.isEmpty()) {
			if (!silent) {
				displayError("You did not provide an input.\n");
			}
			return false;
		}
		return true;
	}
	
	/** Method isValidString tests to see if the inputString is not empty or blank. If so, it checks to
	 *  see if the length of the string is numChars and returns true, otherwise returns false.
	 *  Will not display error messages.
	 * @param inputString
	 * @param numChars
	 * @param silent
	 * @return
	 */
	public boolean isValidString(String inputString, int numChars) {
		return isValidString(inputString, numChars, true);
	}
	
	/** Method isValidString tests to see if the inputString is not empty or blank. If so, it checks to
	 *  see if the length of the string is numChars and returns true, otherwise returns false.
	 * If silent is true, then do not display error messages.
	 * @param inputString
	 * @param numChars
	 * @param silent
	 * @return
	 */
	public boolean isValidString(String inputString, int numChars, boolean silent) {
		// Call isValid to validate type and display applicable error messages if not.
		if (isValidString(inputString) && inputString.length() != numChars) {
			if(!silent) {
				displayError("The string does not have exactly " + numChars + " characters.");
				return false;
			}
		}
		return true;
	}
	
	/** Method isValidDate attempts to parse inputString to see if it can be interpreted as a valid LocalDate. 
	 * Returns true if it parses successfully and returns false if the trimmed string is blank or incorrectly
	 * formatted. No error messages are displayed
	 * @param inputString
	 * @return
	 */
	public boolean isValidDate(String inputString) {
		return isValidDate(inputString, true);
	}
	
	/** Method isValidDate attempts to parse inputString to see if it can be interpreted as a valid LocalDate. 
	 * Returns true if it parses successfully and returns false if the trimmed string is blank or incorrectly
	 * formatted. If silent is false, appropriate error messages are displayed.
	 * @param inputString
	 * @return
	 */
	public boolean isValidDate(String inputString, boolean silent) {
		if (!isValidString(inputString, silent)) {
			return false;
		}
		inputString = inputString.trim();
		try {
			LocalDate testDate = LocalDate.parse(inputString);
			return true;
		} catch (DateTimeParseException e) {
			if (!silent) {
				displayError("Incorrectly formatted date: " + inputString);
			}
		}
		return false;
	}
	
	/** Method isValidTime attempts to parse inputString to see if it can be interpreted as a valid LocalTime. 
	 * Returns true if it parses successfully and returns false if the trimmed string is blank or incorrectly
	 * formatted. No error messages are displayed.
	 * @param inputString
	 * @return
	 */
	public boolean isValidTime(String inputString) {
		return isValidTime(inputString, true); 
	}
	
	/** Method isValidTime attempts to parse inputString to see if it can be interpreted as a valid LocalTime. 
	 * Returns true if it parses successfully and returns false if the trimmed string is blank or incorrectly
	 * formatted. If silent is false, display appropriate error messages.
	 * @param inputString
	 * @param silent
	 * @return
	 */
	public boolean isValidTime(String inputString, boolean silent) {
		if (!isValidString(inputString, silent)) {
			return false;
			}
		inputString = inputString.trim();
		try {
			LocalTime testTime = LocalTime.parse(inputString);
			return true;
		} catch (DateTimeParseException e) {
			if (!silent) {
				displayError("Incorrectly formatted time: " + inputString);
			}
		}
		return false;
	}
	
	/** Method isValidE-mail tests to see if the inputString not is empty or blank. Then it checks to see if 
	 *  there is one and only one @ sign followed by at least one period. 
	 *  If so, it returns true, otherwise false. Will not display error messages.
	 * @param inputString
	 * @return
	 */
	public boolean isValidEmail(String inputString) {
		return isValidEmail(inputString, true);
	}
	/** Method isValidE-mail tests to see if the inputString not is empty or blank. Then it checks to see if 
	 *  there is one and only one @ sign followed by a string with at least one period. 
	 *  If so, it returns true, otherwise false. Will not display error messages.
	 *  If silent is false, display appropriate error messages.
	 * @param inputString
	 * @param silent
	 * @return
	 */
	public boolean isValidEmail(String inputString, boolean silent) {
		if (!isValidString(inputString, silent)) {
			return false;
		}
		// Split the inputString into an array of strings delimited by @
		String[] tempArray = inputString.split("@");
		// If the resulting array has only two elements there is only one @.
		if (tempArray.length == 2) {
			// If the second element of the array contains a . then it is a valid e-mail, so return true
			if (tempArray[1].contains(".")) {
				return true;
			}
		}
		if (!silent) {
			displayError(inputString + " is not a valid e-mail containing only one @ followed by a string containing"
					+ " at least one period.");
		}
		return false;
	}
	
	/** Method isStateAbbreviation returns true if the input String represents a two letter US state, 
	 * commonwealth, or territory. Otherwise it returns false. No error messages are displayed.
	 * @param inputString
	 * @return 
	 * */
	public boolean isStateAbbreviation(String inputString) {
		return isStateAbbreviation(inputString, true);
	}
	
	/** Method isStateAbbreviation returns true if the input String represents a two letter US state, 
	 * commonwealth, or territory. Otherwise it returns false.
	 * If silent is false, display appropriate error messages.
	 * @param inputString
	 * @param silent
	 * @return 
	 * */
	public boolean isStateAbbreviation(String inputString, boolean silent) {
		if (!isValidString(inputString)) {
			return false;
		}
		String[] abbreviationArray = {"AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA","HI","ID","IL",
				"IN","IA","KS","KY","LA","ME","MD","MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ",
				"NM","NY","NC","ND","OH","OK","OR","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA",
				"WV","WI","WY","AS","DC","FM","GU","MH","MP","PW","PR","VI"};
		// Remove all white space from the userInput
		inputString = inputString.replaceAll("\\s", "");
		// Convert the inputString to all upper case before comparing to the abbreviationArray Strings.
		inputString = inputString.toUpperCase();
		for (int index = 0; index < abbreviationArray.length; index++) {
			if (inputString.contentEquals(abbreviationArray[index])) {
				return true;
			}
		}
		if (!silent) {
			String errorMessage = inputString + " is not a valid US State, Commonwealth, or Territory. Acceptable values are: ";
			for(String currentState : abbreviationArray) {
				errorMessage += currentState + " ";
			}
			displayError(errorMessage);
		}
		return false;
	}
}

package tuv.lib.models;

import javafx.scene.control.Alert;

/**
 * Validates fields
 * 
 * @author Zheni
 *
 */
public class Validator {

	/**
	 * Check if string is null or empty
	 * 
	 * @param str
	 *            string for checking
	 * @return boolean result
	 */
	public static boolean isNullOrEmpty(String str) {
		if (str == null)
			return true;
		str = str.trim();
		if (str.isEmpty())
			return true;
		return false;
	}

	/**
	 * Check if string has chars or white spaces only
	 * 
	 * @param str
	 *            string to be checked
	 * @return boolean result
	 */
	public static boolean hasCharsOnly(String str) {
		if (Validator.isNullOrEmpty(str)) {
			return false;
		}
		for (int i = 0; i < str.length(); i++) {
			if ((Character.isLetter(str.charAt(i)) == false) && !Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Check if string checks if string is correct phone number : has 10 symbols,
	 * all of the symbols are digits
	 * 
	 * @param str
	 *            phone number
	 * @return boolean result
	 */
	public static boolean checkPhone(String str) {
		if (Validator.isNullOrEmpty(str)) {
			return false;
		}
		if (str.length() != 10) {
			return false;
		}
		for (int i = 0; i < str.length(); i++) {
			if ((Character.isDigit(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Check if string has digits only
	 * 
	 * @param str
	 *            string to be checked
	 * @return
	 */
	public static boolean hasDigitsOnly(String str) {
		if (Validator.isNullOrEmpty(str)) {
			return false;
		}
		for (int i = 0; i < str.length(); i++) {
			if ((Character.isDigit(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	public static void showWrongInputAllert() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Wrong input");
		alert.setHeaderText("Please input correct values!");
		alert.showAndWait();
	}
	
	public static void showSQLErrorAllert() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("SQL Error");
		alert.setHeaderText("The transaction cannot be executed!");
		alert.showAndWait();
	}

}

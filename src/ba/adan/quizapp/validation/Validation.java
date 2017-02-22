package ba.adan.quizapp.validation;

import java.util.ArrayList;

import ba.adan.quizapp.account.Account;

public class Validation {

	// metoda koja provjerava da li string sadrzi odredjene karaktere
	public static boolean checkString(String username) {
		for (int i = 0; i < username.length(); i++) {
			char ch = username.charAt(i);

			if (ch == 'š' || ch == 'ð' || ch == 'è' || ch == 'æ' || ch == 'ž') {
				return false;
			}
		}

		return true;
	}

	// metoda koja provjerava da li je username u ispravnom formatu
	public static boolean checkUsername(String username) {
		boolean isGoodUsername = true;

		if (username.length() > 4 && username.length() < 14) {
			for (int i = 0; i < username.length(); i++) {
				if (checkCharacters(username.charAt(i))
						|| username.charAt(i) == '-'
						|| username.charAt(i) == '_') {
					isGoodUsername = true;
				} else {
					isGoodUsername = false;
					break;
				}
			}
		} else {
			isGoodUsername = false;
		}

		return isGoodUsername;
	}

	// metoda koja provjerava karakter
	public static boolean checkCharacters(char ch) {
		boolean isGoodUsername = true;

		if ((ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122)
				|| (ch >= 48 && ch <= 57)) {
			isGoodUsername = true;
		} else {
			isGoodUsername = false;
		}

		return isGoodUsername;
	}

	// metoda koja provjerava da li je password u ispravnom formatu
	public static boolean checkPassword(String password) {
		boolean isGoodPassword = true;

		if (password.length() > 4 && password.length() < 21) {
			for (int i = 0; i < password.length(); i++) {
				if (checkCharacters(password.charAt(i))) {
					isGoodPassword = true;
				} else {
					isGoodPassword = false;
					break;
				}
			}
		} else {
			isGoodPassword = false;
		}

		return isGoodPassword;
	}

	// metoda koja provjerava da li je username dostupan (vraca true ako se ne
	// nalazi u grupi accounta)
	public static boolean isAvailableUsername(ArrayList<Account> accountGroup,
			String username) {
		for (int i = 0; i < accountGroup.size(); i++) {
			if (accountGroup.get(i).getUsername().equals(username)) {
				return false;
			}
		}

		return true;
	}

	// metoda koja provjerava da li je validna prijava (vraca true ako se
	// uneseni password poklapa sa passwordom accounta)
	public static boolean isValidLogin(ArrayList<Account> accountGroup,
			String password, int indexOfAccount) {
		if (accountGroup.get(indexOfAccount).getPassword().equals(password)) {
			return true;
		}

		return false;
	}

}

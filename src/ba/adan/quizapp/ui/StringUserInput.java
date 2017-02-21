package ba.adan.quizapp.ui;

import java.util.ArrayList;
import java.util.Scanner;

import ba.adan.quizapp.account.Account;
import ba.adan.quizapp.validation.Validation;

public class StringUserInput {

	// metoda koja uzima string username od korisnika prilikom kreiranja novog
	// racuna
	public static String getUsernameWhenRegister(Scanner input,
			ArrayList<Account> accountGroup) {

		String username = "";
		boolean wrongUsernameInput = true;

		System.out
				.println("\nKorisnicko ime moze da sadrzi min 5 a max 13 karaktera."
						+ "\nDozvoljeni karakteri: slova, brojevi, '-' i '_'\n");

		input.nextLine();

		while (wrongUsernameInput) {
			System.out.print("Unesite korisnicko ime: ");
			username = input.nextLine();

			wrongUsernameInput = false;

			if (!Validation.checkUsername(username)) {
				System.out.println("Pogresan unos. Pokusajte ponovo.");
				wrongUsernameInput = true;
			} else if (!Validation.isAvailableUsername(accountGroup, username)) {
				System.out
						.println("Korisnicko ime '"
								+ username
								+ "' je zauzeto. Molimo odaberite drugo korisnicko ime.");
				wrongUsernameInput = true;
			}
		}

		return username;
	}

	// metoda koja uzima string password od korisnika prilikom kreiranja novog
	// racuna
	public static String getPasswordWhenRegister(Scanner input) {
		String password = "";
		String password2 = "";
		boolean passwordsNotMatched = true;

		System.out.println("\nLozinka moze da sadrzi min 5 a max 20 karaktera."
				+ "\nDozvoljeni karakteri: slova i brojevi\n");

		while (passwordsNotMatched) {
			boolean wrongPasswordInput = true;

			while (wrongPasswordInput) {
				System.out.print("Unesite lozinku: ");
				password = input.nextLine();

				wrongPasswordInput = false;

				if (!Validation.checkPassword(password)) {
					System.out.println("Pogresan unos. Pokusajte ponovo.");
					wrongPasswordInput = true;
				}
			}

			wrongPasswordInput = true;

			while (wrongPasswordInput) {
				System.out.print("Ponovo unesite lozinku: ");
				password2 = input.nextLine();

				wrongPasswordInput = false;

				if (!Validation.checkPassword(password2)) {
					System.out.println("Pogresan unos. Pokusajte ponovo.");
					wrongPasswordInput = true;
				}
			}

			passwordsNotMatched = false;

			if (!password.equals(password2)) {
				System.out
						.println("Unesene lozinke se ne poklapaju. Pokusajte ponovo.");
				passwordsNotMatched = true;
			}
		}

		return password;
	}

}

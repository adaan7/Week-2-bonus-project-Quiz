package ba.adan.quizapp.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ba.adan.quizapp.account.Account;
import ba.adan.quizapp.control.admin.AdminOption;
import ba.adan.quizapp.control.user.UserOption;
import ba.adan.quizapp.quiz.Question;
import ba.adan.quizapp.quiz.ScoreRecord;
import ba.adan.quizapp.validation.Validation;

public class LoginOption {

	// metoda koja uzima prijavu od korisnika
	public static void login(Scanner input, ArrayList<Account> accountGroup,
			ArrayList<Question> questionGroup,
			ArrayList<ScoreRecord> scoreRecordGroup) throws IOException {

		input.nextLine();

		System.out.print("\nUnesite korisnicko ime: ");
		String username = input.nextLine();

		System.out.print("Unesite lozinku: ");
		String password = input.nextLine();

		if (!Validation.isAvailableUsername(accountGroup, username)) {
			// ako username postoji u bazi, pozivamo metodu koja vraca
			// index accounta koji sadrzi taj username
			int indexOfAccount = getAccountIndexFromUsername(accountGroup,
					username);

			if (Validation.isValidLogin(accountGroup, password, indexOfAccount)) {
				// pozivamo metodu koja provjerava da li je validan login
				if (accountGroup.get(indexOfAccount).isAdmin()) {
					// ako je korisnik prijavljen kao admin

					AdminOption.adminUser(input, accountGroup, questionGroup,
							scoreRecordGroup, indexOfAccount);
				} else {
					// ako je korisnik prijavljen kao obicni korisnik

					UserOption.user(input, accountGroup, questionGroup,
							scoreRecordGroup, indexOfAccount);
				}
			} else {
				System.out.println("\nNeispravno korisnicko ime ili lozinka.");
			}
		} else {
			System.out.println("\nNeispravno korisnicko ime ili lozinka.");
		}

	}

	// metoda koja vraca index account-a iz arrayliste prema unesenom username-u
	public static int getAccountIndexFromUsername(
			ArrayList<Account> accountGroup, String username) {
		int index = 0;

		for (int i = 0; i < accountGroup.size(); i++) {
			if (accountGroup.get(i).getUsername().equals(username)) {
				index = i;
				break;
			}
		}

		return index;
	}

}

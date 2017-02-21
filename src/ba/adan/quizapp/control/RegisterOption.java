package ba.adan.quizapp.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ba.adan.quizapp.account.Account;
import ba.adan.quizapp.fileio.FileInput;
import ba.adan.quizapp.ui.StringUserInput;

public class RegisterOption {

	// metoda za registraciju novog korisnika
	public static void register(Scanner input, ArrayList<Account> accountGroup)
			throws IOException {

		String username = StringUserInput.getUsernameWhenRegister(input,
				accountGroup);
		String password = StringUserInput.getPasswordWhenRegister(input);

		Account newAccount = new Account(username, password, false);
		accountGroup.add(newAccount);

		System.out.println("\nNovi korisnicki racun je uspjesno kreiran.");

		// pozivamo metodu koja upisuje account grupu u fajl
		FileInput.copyAccountGroupToFile(accountGroup);
	}

}

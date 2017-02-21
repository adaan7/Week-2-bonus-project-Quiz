package ba.adan.quizapp.control.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ba.adan.quizapp.account.Account;
import ba.adan.quizapp.display.Display;
import ba.adan.quizapp.fileio.FileInput;
import ba.adan.quizapp.ui.IntUserInput;

public class DeleteUserOption {

	// metoda koja sluzi za brisanje korisnickog racuna
	public static void deleteUser(Scanner input, ArrayList<Account> accountGroup)
			throws IOException {

		if (accountGroup.size() == 0) {
			System.out.println("\nLista racuna je prazna.");
		} else {
			// pozivamo metodu koja ispisuje listu korisnickih racuna
			Display.printAccountList(accountGroup);

			System.out.println("");

			int index = IntUserInput.getInt(input,
					"Unesite redni broj racuna kojeg zelite obrisati: ", 1);

			if (index > accountGroup.size()) {
				System.out.println("\nRacun sa rednim brojem '" + index
						+ "' ne postoji u nasoj bazi.");
			} else {
				if (accountGroup.get(index - 1).isAdmin()) {
					System.out.println("\nNe mozete obrisati administratora.");
				} else {
					accountGroup.remove(index - 1);

					System.out.println("\nRacun je uspjesno obrisan.");
				}

				// pozivamo metodu koja ispisuje grupu accounta u fajl
				FileInput.copyAccountGroupToFile(accountGroup);
			}
		}
	}

}

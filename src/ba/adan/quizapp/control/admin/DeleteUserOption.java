package ba.adan.quizapp.control.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ba.adan.quizapp.account.Account;
import ba.adan.quizapp.display.Display;
import ba.adan.quizapp.fileio.FileInput;
import ba.adan.quizapp.quiz.ScoreRecord;
import ba.adan.quizapp.ui.IntUserInput;

public class DeleteUserOption {

	// metoda koja sluzi za brisanje korisnickog racuna
	public static void deleteUser(Scanner input,
			ArrayList<Account> accountGroup,
			ArrayList<ScoreRecord> scoreRecordGroup) throws IOException {

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
					String username = accountGroup.get(index - 1).getUsername();

					// uklanjamo sve rezultate odabranog korisnika iz grupe
					// rezultata
					deleteUserRecordFromScoreRecordGroup(scoreRecordGroup,
							username);

					// uklanjamo korisnika iz grupe accounta
					accountGroup.remove(index - 1);

					System.out.println("\nRacun je uspjesno obrisan.");

					// pozivamo metodu koja ispisuje grupu accounta u fajl
					FileInput.copyAccountGroupToFile(accountGroup);
				}

			}
		}
	}

	public static void deleteUserRecordFromScoreRecordGroup(
			ArrayList<ScoreRecord> scoreRecordGroup, String username)
			throws IOException {

		boolean notClear = true;

		while (notClear) {
			for (int i = 0; i < scoreRecordGroup.size(); i++) {
				if (scoreRecordGroup.get(i).getUsername().equals(username)) {
					scoreRecordGroup.remove(i);
					break;
				}
			}

			notClear = false;

			for (int i = 0; i < scoreRecordGroup.size(); i++) {
				if (scoreRecordGroup.get(i).getUsername().equals(username)) {
					notClear = true;
				}
			}
		}

		// pozivamo metodu koja ispisuje grupu rezultata u fajl
		FileInput.copyScoreRecordGroupToFile(scoreRecordGroup);
	}

}

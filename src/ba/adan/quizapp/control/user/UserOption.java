package ba.adan.quizapp.control.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ba.adan.quizapp.account.Account;
import ba.adan.quizapp.display.Display;
import ba.adan.quizapp.quiz.Question;
import ba.adan.quizapp.quiz.Quiz;
import ba.adan.quizapp.quiz.ScoreRecord;
import ba.adan.quizapp.ui.IntUserInput;

public class UserOption {

	public static void user(Scanner input, ArrayList<Account> accountGroup,
			ArrayList<Question> questionGroup,
			ArrayList<ScoreRecord> scoreRecordGroup, int indexOfAccount) throws IOException {

		System.out.println("\nPozdrav, "
				+ accountGroup.get(indexOfAccount).getUsername() + "!");

		int userOption = 0;

		while (userOption != 4) {
			Display.printUserMenu();

			userOption = IntUserInput.getInt(input, "Izaberite opciju: ", 1, 4);

			if (userOption == 1) {
				// IGRAJ KVIZ

				if (questionGroup.size() == 0) {
					System.out.println("\nPitanja trenutno nisu dostupna.");
				} else {
					Quiz.playGame(input, accountGroup, questionGroup,
							scoreRecordGroup, indexOfAccount);
				}

			} else if (userOption == 2) {
				// PRETHODNI REZULTATI

				if (scoreRecordGroup.size() == 0) {
					System.out.println("\nNema prethodnih rezultata.");
				} else {
					Display.printUserScoreList(accountGroup, scoreRecordGroup,
							indexOfAccount);
				}

			} else if (userOption == 3) {
				// TOP 100 REZULTATA REGISTROVANIH KORISNIKA

				if (scoreRecordGroup.size() == 0) {
					System.out.println("\nTrenutno nema rezultata.");
				} else {
					Display.printTop100ScoreList(scoreRecordGroup);
				}

			} else if (userOption == 4) {
				// ODJAVA

				// ispisujemo poruku kada se korisnik odjavi
				System.out.println("\nUspjesno ste se odjavili.");
			}
		}

	}

}

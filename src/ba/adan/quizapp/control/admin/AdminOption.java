package ba.adan.quizapp.control.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ba.adan.quizapp.account.Account;
import ba.adan.quizapp.display.Display;
import ba.adan.quizapp.quiz.Question;
import ba.adan.quizapp.quiz.ScoreRecord;
import ba.adan.quizapp.ui.IntUserInput;

public class AdminOption {

	// metoda koja sadrzi opcije za admin korisnika
	public static void adminUser(Scanner input,
			ArrayList<Account> accountGroup, ArrayList<Question> questionGroup,
			ArrayList<ScoreRecord> scoreRecordGroup, int indexOfAccount)
			throws IOException {

		System.out.println("\nPozdrav, "
				+ accountGroup.get(indexOfAccount).getUsername() + "!");

		int userOption = 0;

		while (userOption != 4) {
			Display.printAdminMenu();

			userOption = IntUserInput.getInt(input, "Izaberite opciju: ", 1, 4);

			if (userOption == 1) {
				// DODAJ NOVO PITANJE

				AddQuestionOption.addQuestion(input, questionGroup);
			} else if (userOption == 2) {
				// UREDI POSTOJECE PITANJE

				EditQuestionOption.editQuestion(input, questionGroup);
			} else if (userOption == 3) {
				// OBRISI KORISNIKA

				DeleteUserOption.deleteUser(input, accountGroup,
						scoreRecordGroup);
			} else if (userOption == 4) {
				// ODJAVA

				System.out.println("\nUspjesno ste se odjavili.");
			}
		}
	}

}

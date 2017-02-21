package ba.adan.quizapp.control;

import java.io.FilterInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ba.adan.quizapp.account.Account;
import ba.adan.quizapp.display.Display;
import ba.adan.quizapp.fileio.FileOutput;
import ba.adan.quizapp.quiz.Question;
import ba.adan.quizapp.quiz.ScoreRecord;
import ba.adan.quizapp.ui.IntUserInput;

public class QuizControl {

	// metoda pomocu koje pokrecemo aplikaciju
	public static void quizApplication() throws IOException {

		Scanner input = new Scanner(new FilterInputStream(System.in) {
			@Override
			public void close() throws IOException {
				// overrajdamo close() metodu i ostavimo je bez implementacije
			}
		});

		ArrayList<Account> accountGroup = new ArrayList<>();
		ArrayList<Question> questionGroup = new ArrayList<>();
		ArrayList<ScoreRecord> scoreRecordGroup = new ArrayList<>();

		// pozivamo metodu za citanje podataka sa fajla
		FileOutput.copyFileToAccountGroup(accountGroup);
		FileOutput.copyFileToQuestionGroup(questionGroup);
		FileOutput.copyFileToScoreRecordGroup(scoreRecordGroup);

		int userOption = 0;

		// pozivamo metodu koja ispisuje header
		Display.printHeader();

		while (userOption != 3) {
			Display.printMainMenu();

			userOption = IntUserInput.getInt(input, "Izaberite opciju: ", 1, 3);

			if (userOption == 1) {
				// PRIJAVA

				// pozivamo metodu za prijavu korisnika
				LoginOption.login(input, accountGroup, questionGroup,
						scoreRecordGroup);

			} else if (userOption == 2) {
				// REGISTRACIJA

				// pozivamo metodu za kreiranje novog korisnika
				RegisterOption.register(input, accountGroup);
			}
		}

		input.close();

		System.out.println("\nVidimo se uskoro!");

	}

}

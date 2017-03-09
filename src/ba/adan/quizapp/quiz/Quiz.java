package ba.adan.quizapp.quiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import ba.adan.quizapp.account.Account;
import ba.adan.quizapp.display.Display;
import ba.adan.quizapp.fileio.FileInput;
import ba.adan.quizapp.ui.IntUserInput;

public class Quiz {

	// metoda koja pokrece igru kviz
	public static void playGame(Scanner input, ArrayList<Account> accountGroup,
			ArrayList<Question> questionGroup,
			ArrayList<ScoreRecord> scoreRecordGroup, int indexOfAccount)
			throws IOException {

		boolean isCorrectAnswer = true;
		int score = 0;
		String username = accountGroup.get(indexOfAccount).getUsername();

		// na pocetku igre ispisujemo pravila kviza
		Display.printQuizRules();

		System.out.println("\nPritisnite ENTER za pocetak kviza");
		input.nextLine();
		input.nextLine();

		System.out.print("Kviz pocinje za: ");

		// pauziramo aplikaciju po jednu sekundi i simuliramo odbrojavanje do
		// pocetka igre
		try {
			Thread.sleep(1000);
			System.out.print("3 ");
			Thread.sleep(1000);
			System.out.print("2 ");
			Thread.sleep(1000);
			System.out.print("1\n");
			Thread.sleep(1000);
		} catch (Exception ex) {

		}

		while (isCorrectAnswer) {
			// generisemo random integer od 0 do velicine niza grupe pitanja,
			// koji nam sluzi da generisemo nasumicno pitanje
			int randomNumber = (int) (Math.random() * questionGroup.size());

			Display.printRandomQuestion(questionGroup, randomNumber);

			ArrayList<Answer> answerList = questionGroup.get(randomNumber)
					.getAnswerList();

			// pocetak mjerenja vremena za odgovor korisnika
			long startTime = System.currentTimeMillis();

			int userAnswer = IntUserInput.getInt(input,
					"Unesite broj odgovora koji smatrate tacnim: ", 1,
					answerList.size());

			// kraj mjerenja vremena za odgovor korisnika
			long endTime = System.currentTimeMillis();

			// izracunamo ukupno vrijeme koje je potrebno za odgovor korisnika
			long answerTime = endTime - startTime;

			// provjeravamo da li je odgovor tacan
			if (answerList.get(userAnswer - 1).isCorrectAnswer()) {
				// dodjeljujemo broj bodova u zavisnosti od brzine odgovora
				// korisnika
				if (answerTime <= 5000) {
					System.out
							.println("\nOdgovorili ste tacno i osvojili 5 bodova.");
					score += 5;
				} else if (answerTime <= 10000) {
					System.out
							.println("\nOdgovorili ste tacno i osvojili 3 boda.");
					score += 3;
				} else if (answerTime <= 15000) {
					System.out
							.println("\nOdgovorili ste tacno i osvojili 2 boda.");
					score += 2;
				} else if (answerTime <= 20000) {
					System.out
							.println("\nOdgovorili ste tacno i osvojili 1 bod.");
					score += 1;
				} else {
					System.out
							.println("\nVrijeme za predaju odgovora je isteklo. "
									+ "Igra je zavrsena."
									+ "\nUkupan broj bodova: " + score);
					isCorrectAnswer = false;
				}
			} else {
				System.out.println("\nOdgovor nije tacan. Igra je zavrsena."
						+ "\nUkupan broj bodova: " + score);
				isCorrectAnswer = false;
			}
		}

		Calendar calendar = new GregorianCalendar();

		// upisujemo rezultat u grupu rezultata
		ScoreRecord newScoreRecord = new ScoreRecord(username, score, calendar);
		scoreRecordGroup.add(newScoreRecord);

		if (score > accountGroup.get(indexOfAccount).getHighScore()) {
			// ako je rezultat korisnika veci od dosadadnjeg najboljeg
			// rezultata, novi rezultat postavljamo kao najbolji
			System.out.println("\n*** POSTAVILI STE NOVI "
					+ "LICNI REKORD, CESTITAMO ***");

			accountGroup.get(indexOfAccount).setHighScore(score);
		}

		// pozivamo metode koje ispisuju podatke u fajl
		FileInput.copyAccountGroupToFile(accountGroup);
		FileInput.copyScoreRecordGroupToFile(scoreRecordGroup);
	}

}

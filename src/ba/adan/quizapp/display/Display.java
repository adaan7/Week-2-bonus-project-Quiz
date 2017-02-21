package ba.adan.quizapp.display;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import ba.adan.quizapp.account.Account;
import ba.adan.quizapp.quiz.Answer;
import ba.adan.quizapp.quiz.Question;
import ba.adan.quizapp.quiz.ScoreRecord;

public class Display {

	// metoda koja ispisuje header
	public static void printHeader() {
		System.out.println("============================"
				+ "============================"
				+ "\n||                          "
				+ "                          ||" + "\n||                  Kviz"
				+ " Aplikacija                   ||"
				+ "\n||                          "
				+ "                          ||"
				+ "\n============================"
				+ "============================");
	}

	// metoda koja ispisuje glavni izbornik
	public static void printMainMenu() {
		System.out.println("\n- GLAVNI IZBORNIK -"
				+ "\n----------------------------"
				+ "----------------------------" + "\n1. Prijava"
				+ "\n2. Registracija" + "\n3. Izlaz iz aplikacije"
				+ "\n----------------------------"
				+ "----------------------------");
	}

	// metoda koja ispisuje korisnicki izbornik
	public static void printUserMenu() {
		System.out.println("\n- IZBORNIK -" + "\n----------------------------"
				+ "----------------------------" + "\n1. Igraj kviz"
				+ "\n2. Prethodni rezultati"
				+ "\n3. Top 100 rezultata registrovanih korisnika"
				+ "\n4. Odjava" + "\n----------------------------"
				+ "----------------------------");
	}

	// metoda koja ispisuje admin izbornik
	public static void printAdminMenu() {
		System.out.println("\n- IZBORNIK -" + "\n----------------------------"
				+ "----------------------------" + "\n1. Dodaj novo pitanje"
				+ "\n2. Uredi postojece pitanje" + "\n3. Obrisi korisnika"
				+ "\n4. Odjava" + "\n----------------------------"
				+ "----------------------------");
	}

	// metoda koja ispisuje pravila kviza
	public static void printQuizRules() {
		System.out
				.println("\n- PRAVILA KVIZA:"
						+ "\n----------------------------"
						+ "----------------------------"
						+ "\n1. Odgovarate na pitanje tako sto odaberete jedan od ponudjenih odgovora."
						+ "\n2. Kviz traje sve dok je vas odgovor tacan i u okviru vremenskog ogranicenja."
						+ "\n3. Maksimalno vrijeme za prihvatanje vaseg odgovora je 20 sekundi."
						+ "\n4. Ako odgovorite tacno u roku 5 sekundi, osvajate 5 bodova."
						+ "\n5. Ako odgovorite tacno u roku 10 sekundi, osvajate 3 boda."
						+ "\n6. Ako odgovorite tacno u roku 15 sekundi, osvajate 2 boda."
						+ "\n7. Ako odgovorite tacno u roku 20 sekundi, osvajate 1 bod."
						+ "\n----------------------------"
						+ "----------------------------");
	}

	// metoda koja ispisuje listu korisnickih racuna
	public static void printAccountList(ArrayList<Account> accountGroup) {
		Collections.sort(accountGroup);

		System.out.printf("\n %-10s\t%-15s", "Redni broj", "Korisnicko ime");
		System.out.print("\n----------------------------"
				+ "----------------------------\n");

		for (int i = 0; i < accountGroup.size(); i++) {
			System.out.printf(" %-10s\t%-15s\n", (i + 1), accountGroup.get(i)
					.getUsername());
		}
	}

	// metoda koja ispisuje listu pitanja
	public static void printQuestionList(ArrayList<Question> questionGroup) {
		Collections.sort(questionGroup);

		System.out.printf("\n %-10s\t%-20s", "Redni broj", "Pitanje");
		System.out.print("\n----------------------------"
				+ "----------------------------\n");

		for (int i = 0; i < questionGroup.size(); i++) {
			System.out.printf(" %-10s\t%-20s\n", (i + 1), questionGroup.get(i)
					.getQuestion());
		}
	}

	// metoda koja ispisuje sve rezultate od korisnika
	public static void printUserScoreList(ArrayList<Account> accountGroup,
			ArrayList<ScoreRecord> scoreRecordGroup, int indexOfAccount) {
		String username = accountGroup.get(indexOfAccount).getUsername();

		System.out.printf("\n %-11s\t%11s", "Datum", "Broj bodova");
		System.out.print("\n----------------------------"
				+ "----------------------------\n");

		for (int i = 0; i < scoreRecordGroup.size(); i++) {
			if (scoreRecordGroup.get(i).getUsername().equals(username)) {
				Calendar currentCalendar = scoreRecordGroup.get(i)
						.getCalendar();

				String currentDate = currentCalendar.get(Calendar.DATE) + "."
						+ (currentCalendar.get(Calendar.MONTH) + 1) + "."
						+ currentCalendar.get(Calendar.YEAR) + ".";

				int score = scoreRecordGroup.get(i).getScore();

				System.out.printf(" %-11s\t%11d\n", currentDate, score);
			}
		}
	}

	// metoda koja ispisuje top 100 rezultata svih registrovanih korisnika
	public static void printTop100ScoreList(
			ArrayList<ScoreRecord> scoreRecordGroup) {
		Collections.sort(scoreRecordGroup);
		Collections.reverse(scoreRecordGroup);

		System.out.println("\n- Top 100 rezultata registrovanih korisnika -");

		System.out.printf("\n %-8s\t%-20s\t%11s", "Pozicija", "Korisnicko ime",
				"Broj bodova");
		System.out.print("\n----------------------------"
				+ "----------------------------\n");

		for (int i = 0; i < scoreRecordGroup.size(); i++) {
			System.out.printf(" %-8d\t%-20s\t%11d\n", (i + 1), scoreRecordGroup
					.get(i).getUsername(), scoreRecordGroup.get(i).getScore());

			if (i == 100) {
				break;
			}
		}
	}

	// metoda koja ispisuje nasumicno odabrano pitanje sa ponudjenim odgovorima
	public static void printRandomQuestion(ArrayList<Question> questionGroup,
			int randomNumber) {
		int counter = 0;

		String question = questionGroup.get(randomNumber).getQuestion();
		String[] questionWords = question.split(" ");

		ArrayList<Answer> answerList = questionGroup.get(randomNumber)
				.getAnswerList();

		System.out.println("");

		for (int i = 0; i < questionWords.length; i++) {
			System.out.print(questionWords[i] + " ");
			counter++;

			// formatiramo ispis pitanja, ako je broj ispisanih rijeci jednak
			// 10, prelazimo u novih red
			if (counter == 10) {
				System.out.println();
				counter = 0;
			}
		}

		System.out.print("\n----------------------------"
				+ "----------------------------");

		for (int i = 0; i < answerList.size(); i++) {
			System.out.print("\n(" + (i + 1) + ") "
					+ answerList.get(i).getAnswer());
		}

		System.out.print("\n----------------------------"
				+ "----------------------------\n");
	}

}

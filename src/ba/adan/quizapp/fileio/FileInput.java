package ba.adan.quizapp.fileio;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;

import ba.adan.quizapp.account.Account;
import ba.adan.quizapp.quiz.Answer;
import ba.adan.quizapp.quiz.Question;
import ba.adan.quizapp.quiz.ScoreRecord;

public class FileInput {

	// metoda koja ispisuje grupu accounta u fajl
	public static void copyAccountGroupToFile(ArrayList<Account> accountGroup)
			throws IOException {

		Path path = Paths.get("src/ba/adan/quizapp/account/accounts.txt");

		if (!Files.exists(path)) {
			Files.createFile(path);
		}

		BufferedWriter writer = Files.newBufferedWriter(path);

		for (int i = 0; i < accountGroup.size(); i++) {
			// ispisujemo username u fajl
			writer.write(accountGroup.get(i).getUsername() + " ");
			// ispisujemo password u fajl
			writer.write(accountGroup.get(i).getPassword() + " ");

			// ako je korisnik admin ispisujemo 1, ako nije ispisujemo 0 u fajl
			if (accountGroup.get(i).isAdmin()) {
				writer.write(1 + " ");
			} else {
				writer.write(0 + " ");
			}

			// ispisujemo najbolji rezultat korisnika u fajl
			writer.write(accountGroup.get(i).getHighScore() + "");
			writer.newLine();
		}

		writer.close();
	}

	// metoda koja ispisuje grupu rezultata u fajl
	public static void copyScoreRecordGroupToFile(
			ArrayList<ScoreRecord> scoreRecordGroup) throws IOException {

		Path path = Paths.get("src/ba/adan/quizapp/quiz/score records.txt");

		if (!Files.exists(path)) {
			Files.createFile(path);
		}

		BufferedWriter writer = Files.newBufferedWriter(path);

		for (int i = 0; i < scoreRecordGroup.size(); i++) {
			// ispisujemo username u fajl
			writer.write(scoreRecordGroup.get(i).getUsername() + " ");
			// ispisujemo rezultat u fajl
			writer.write(scoreRecordGroup.get(i).getScore() + "");
			writer.newLine();

			int day = scoreRecordGroup.get(i).getCalendar().get(Calendar.DATE);
			int month = scoreRecordGroup.get(i).getCalendar()
					.get(Calendar.MONTH);
			int year = scoreRecordGroup.get(i).getCalendar().get(Calendar.YEAR);

			// ispisujemo datum u fajl
			writer.write(day + " ");
			writer.write(month + " ");
			writer.write(year + "");
			writer.newLine();
		}

		writer.close();
	}

	// metoda koja ispisuje grupu pitanja u fajl
	public static void copyQuestionGroupToFile(ArrayList<Question> questionGroup)
			throws IOException {

		Path path = Paths.get("src/ba/adan/quizapp/quiz/questions.txt");

		if (!Files.exists(path)) {
			Files.createFile(path);
		}

		BufferedWriter writer = Files.newBufferedWriter(path);

		for (int i = 0; i < questionGroup.size(); i++) {
			// ispisujemo pitanje u fajl
			writer.write(questionGroup.get(i).getQuestion() + "");
			writer.newLine();

			ArrayList<Answer> answerList = questionGroup.get(i).getAnswerList();

			// ispisujemo odgovore u fajl
			for (int j = 0; j < answerList.size(); j++) {
				writer.write(answerList.get(j).getAnswer() + "###");
			}

			writer.newLine();

			// ispisujemo index tacnog odgovora u fajl
			for (int j = 0; j < answerList.size(); j++) {
				if (answerList.get(j).isCorrectAnswer()) {
					writer.write(j);
				}
			}

			writer.newLine();
		}

		writer.close();
	}

}

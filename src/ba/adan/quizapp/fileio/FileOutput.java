package ba.adan.quizapp.fileio;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import ba.adan.quizapp.account.Account;
import ba.adan.quizapp.quiz.Answer;
import ba.adan.quizapp.quiz.Question;
import ba.adan.quizapp.quiz.ScoreRecord;

public class FileOutput {

	// metoda koja ispisuje podatke u grupu accounta iz fajla
	public static void copyFileToAccountGroup(ArrayList<Account> accountGroup)
			throws IOException {

		Path path = Paths.get("src/ba/adan/quizapp/account/accounts.txt");

		if (!Files.exists(path)) {
			Files.createFile(path);
		}

		BufferedReader reader = Files.newBufferedReader(path);

		String line = "";

		while ((line = reader.readLine()) != null) {
			String[] words = line.split(" ");

			String username = words[0];
			String password = words[1];
			int adminNumber = Integer.parseInt(words[2]);
			boolean isAdmin = true;

			if (adminNumber == 1) {
				isAdmin = true;
			} else {
				isAdmin = false;
			}

			int highScore = Integer.parseInt(words[3]);

			Account newAccount = new Account(username, password, isAdmin);
			newAccount.setHighScore(highScore);

			accountGroup.add(newAccount);
		}

		reader.close();
	}

	// metoda koja ispisuje podatke u grupu rezultata iz fajla
	public static void copyFileToScoreRecordGroup(
			ArrayList<ScoreRecord> scoreRecordGroup) throws IOException {

		Path path = Paths.get("src/ba/adan/quizapp/quiz/score records.txt");

		if (!Files.exists(path)) {
			Files.createFile(path);
		}

		Scanner reader = new Scanner(path);

		while (reader.hasNextLine()) {
			String usernameAndScoreLine = reader.nextLine();
			String date = reader.nextLine();

			String[] usernameAndScoreArray = usernameAndScoreLine.split(" ");
			String username = usernameAndScoreArray[0];
			int score = Integer.parseInt(usernameAndScoreArray[1]);

			String[] dateArray = date.split(" ");
			int day = Integer.parseInt(dateArray[0]);
			int month = Integer.parseInt(dateArray[1]);
			int year = Integer.parseInt(dateArray[2]);

			Calendar calendar = new GregorianCalendar(year, month, day);

			ScoreRecord newScoreRecord = new ScoreRecord(username, score,
					calendar);

			scoreRecordGroup.add(newScoreRecord);
		}

		reader.close();
	}

	// metoda koja ispisuje podatke u grupu pitanja iz fajla
	public static void copyFileToQuestionGroup(ArrayList<Question> questionGroup)
			throws IOException {
		Path path = Paths.get("src/ba/adan/quizapp/quiz/questions.txt");

		if (!Files.exists(path)) {
			Files.createFile(path);
		}

		Scanner reader = new Scanner(path);

		while (reader.hasNextLine()) {
			String question = reader.nextLine();
			String answerLine = reader.nextLine();
			int correctAnswer = Integer.parseInt(reader.nextLine());

			String[] answers = answerLine.split("###");

			ArrayList<Answer> answerList = new ArrayList<>();

			for (int i = 0; i < answers.length; i++) {
				Answer answer = new Answer(answers[i]);

				if (i == correctAnswer) {
					answer.setCorrectAnswer(true);
				}

				answerList.add(answer);
			}

			Question newQuestion = new Question(question, answerList);

			questionGroup.add(newQuestion);
		}

		reader.close();
	}

}

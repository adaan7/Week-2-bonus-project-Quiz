package ba.adan.quizapp.control.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ba.adan.quizapp.fileio.FileInput;
import ba.adan.quizapp.quiz.Answer;
import ba.adan.quizapp.quiz.Question;
import ba.adan.quizapp.ui.IntUserInput;
import ba.adan.quizapp.ui.StringUserInput;

public class AddQuestionOption {

	// metoda koja dodaje novo pitanje u grupu pitanja
	public static void addQuestion(Scanner input,
			ArrayList<Question> questionGroup) throws IOException {

		input.nextLine();

		String questionInputText = "\nUnesite novo pitanje: ";
		String question = StringUserInput.getString(input, questionInputText,
				200);

		int numberOfOfferedAnswers = IntUserInput.getInt(input,
				"Unesite broj ponudjenih odgovora (min 2, max 4): ", 2, 4);

		ArrayList<Answer> answerList = new ArrayList<>();

		input.nextLine();

		for (int i = 0; i < numberOfOfferedAnswers; i++) {
			String answerInputText = "Unesite ponudjeni odgovor broj "
					+ (i + 1) + ": ";
			String answer = StringUserInput.getString(input, answerInputText,
					20);

			Answer newAnswer = new Answer(answer);

			answerList.add(newAnswer);
		}

		int indexOfCorrectAnswer = IntUserInput.getInt(input,
				"Unesite broj ponudjenog odgovora koji je tacan (1 - "
						+ numberOfOfferedAnswers + "): ", 1,
				numberOfOfferedAnswers);

		// postavljamo tacan odgovor koji korisnik odredi
		answerList.get(indexOfCorrectAnswer - 1).setCorrectAnswer(true);

		Question newQuestion = new Question(question, answerList);
		questionGroup.add(newQuestion);

		System.out.println("\nPitanje je uspjesno spremljeno.");

		// pozivamo metodu koja upisuje grupu pitanja u fajl
		FileInput.copyQuestionGroupToFile(questionGroup);

	}

}

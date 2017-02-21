package ba.adan.quizapp.control.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ba.adan.quizapp.display.Display;
import ba.adan.quizapp.fileio.FileInput;
import ba.adan.quizapp.quiz.Answer;
import ba.adan.quizapp.quiz.Question;
import ba.adan.quizapp.ui.IntUserInput;

public class EditQuestionOption {

	// metoda koja sluzi za editovanje postojeceg pitanja
	public static void editQuestion(Scanner input,
			ArrayList<Question> questionGroup) throws IOException {

		if (questionGroup.size() == 0) {
			System.out.println("\nLista pitanja je prazna.");
		} else {
			Display.printQuestionList(questionGroup);

			System.out.println("");

			int index = IntUserInput.getInt(input,
					"Unesite redni broj pitanja koje zelite urediti: ", 1);

			if (index > questionGroup.size()) {
				System.out.println("\nPitanje sa rednim brojem '" + index
						+ "' ne postoji u nasoj bazi.");
			} else {
				input.nextLine();

				System.out.print("\nUnesite novo pitanje: ");
				String question = input.nextLine();

				int numberOfOfferedAnswers = IntUserInput.getInt(input,
						"Unesite broj ponudjenih odgovora (min 2, max 4): ", 2,
						4);

				ArrayList<Answer> answerGroup = new ArrayList<>();

				input.nextLine();

				for (int i = 0; i < numberOfOfferedAnswers; i++) {
					System.out.print("Unesite ponudjeni odgovor broj "
							+ (i + 1) + ": ");
					String answer = input.nextLine();

					Answer newAnswer = new Answer(answer);
					answerGroup.add(newAnswer);
				}

				int indexOfCorrectAnswer = IntUserInput.getInt(input,
						"Unesite broj ponudjenog odgovora koji je tacan (1 - "
								+ numberOfOfferedAnswers + "): ", 1,
						numberOfOfferedAnswers);

				answerGroup.get(indexOfCorrectAnswer - 1)
						.setCorrectAnswer(true);

				questionGroup.get(index - 1).setQuestion(question);
				questionGroup.get(index - 1).setAnswerList(answerGroup);

				System.out.println("\nPitanje je uspjesno editovano.");

				// pozivamo metodu koja ispisuje grupu pitanja u fajl
				FileInput.copyQuestionGroupToFile(questionGroup);
			}
		}
	}

}

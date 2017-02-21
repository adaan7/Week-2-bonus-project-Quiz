package ba.adan.quizapp.ui;

import java.util.Scanner;

public class IntUserInput {

	// metoda koja uzima unos integera od korisnika sa dva uslova
	public static int getInt(Scanner input, String text, int firstCondition,
			int secondCondition) {
		int userInput = 0;
		boolean wrongUserInput = true;

		while (wrongUserInput) {
			try {
				System.out.print(text);
				userInput = input.nextInt();

				wrongUserInput = false;

				if (userInput < firstCondition || userInput > secondCondition) {
					System.out
							.println("Pogresan unos. Morate unijeti cijeli broj od "
									+ firstCondition
									+ " do "
									+ secondCondition
									+ ".");
					wrongUserInput = true;
				}
			} catch (Exception ex) {
				System.out
						.println("Pogresan unos. Morate unijeti cijeli broj od "
								+ firstCondition
								+ " do "
								+ secondCondition
								+ ".");
				input.nextLine();
			}
		}

		return userInput;
	}

	// metoda koja uzima unos integera od korisnika sa jednim uslovom
	public static int getInt(Scanner input, String text, int condition) {
		int userInput = 0;
		boolean wrongUserInput = true;

		while (wrongUserInput) {
			try {
				System.out.print(text);
				userInput = input.nextInt();

				wrongUserInput = false;

				if (userInput < condition) {
					System.out
							.println("Pogresan unos. Morate unijeti cijeli broj veci ili jednak od "
									+ condition);
					wrongUserInput = true;
				}
			} catch (Exception ex) {
				System.out
						.println("Pogresan unos. Morate unijeti cijeli broj veci ili jednak od "
								+ condition);
				input.nextLine();
			}
		}

		return userInput;
	}

}

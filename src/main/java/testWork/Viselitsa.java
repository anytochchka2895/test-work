package testWork;

import java.util.*;

public class Viselitsa {
	public static void main(String[] args) {
		// Реализуй игру «Виселица», где игрок пытается угадать слово, вводя одну букву за раз.
		// Если буква отсутствует в слове, то игрок теряет жизнь.
		// Игра заканчивается, когда игрок угадывает слово или теряет все жизни.
		// Слово для отгадывания выбирать случайно из списка слов-констант внутри программы.
		// После каждой введенной буквы выводить в консоль загадываемое слово
		// с пропусками букв и сообщение о том, сколько жизней осталось.
		// Рисовать в консоли человечка на виселице как иллюстрацию количества жизней
		// (как это обычно бывает в «Виселице») не обязательно, но будет приветствоваться.

		//  ____
		// |   |
		// |   O
		// |  \|/
		// |  / \


		List<String> words = new ArrayList<>(
				Arrays.asList("буква", "консоль", "пропуск", "сообщение", "человек", "потеря", "константа", "программа",
				              "вывод", "приветствие", "обучение", "разработка", "ошибка", "строка", "победа", "проигрыш",
				              "солнце", "погода", "трава", "цветок", "листок", "дерево", "осень"));

		Scanner scanner = new Scanner(System.in);
		String play;

		do {
			int wordIndex = (int) (Math.random() * (words.size()));
			String myWord = words.get(wordIndex);
			makeOneGame(myWord);
			words.remove(myWord);
			if (words.size() == 0) {
				System.out.println("Поздравляем! Вы полностью прошли игру! <Салютик>");
				play = "";
			}
			else {
				System.out.println();
				System.out.println("Вы хотите сыграть еще раз?");
				play = scanner.nextLine();
			}
		}
		while (play.equals("да"));
	}


	public static void makeOneGame(String myWord) {
		Scanner scanner = new Scanner(System.in);
		int countLives = 7;
		char[] alphabet = "йцукенгшщзхъфывапролджэячсмитьбю".toCharArray();
		char[] closedWord = createClosedWord(myWord);


		while (countLives > 0) {
			String letter;
			boolean isLetterUsedBefore;
			do {
				System.out.println();
				System.out.println();
				System.out.println("Введите строчную букву");
				letter = scanner.nextLine();
				if (letter.equals("")) {
					System.out.println("Вы ввели некорректное значение. Введите букву заново");
					isLetterUsedBefore = true;
				}
				else {
					isLetterUsedBefore = thisLetterUsedBefore(letter, alphabet);
					if (isLetterUsedBefore) {
						System.out.println("Эта буква уже использовалась или некорректна");
					}
				}
			}
			while (isLetterUsedBefore);


			boolean wasUpdated = updateClosedWord(myWord, letter, closedWord);


			if (wasUpdated) {
				System.out.println("Количество жизней: " + countLives);
				showViselitsa(countLives);
				boolean guessedFullWord = isGuessedFullWord(closedWord);
				if (guessedFullWord) {
					System.out.println("Поздравляем! Вы выиграли");
				}
				showClosedWord(closedWord);
				updateAlphabet(alphabet, letter);
				showAlphabet(alphabet);
				if (guessedFullWord) {
					break;
				}
			}
			else {
				countLives -= 1;
				System.out.println("Количество жизней: " + countLives);
				showViselitsa(countLives);
				showClosedWord(closedWord);
				updateAlphabet(alphabet, letter);
				showAlphabet(alphabet);
				if (countLives == 0) {
					System.out.println();
					System.out.println("Игра окончена. Вы проиграли. Загаданное слово - " + myWord);
				}
			}
		}
	}


	public static boolean thisLetterUsedBefore(String letter, char[] alphabet) {
		for (int i = 0; i < alphabet.length; i++) {
			if (alphabet[i] == letter.charAt(0)) {
				return false;
			}
		}
		return true;
	}

	public static char[] createClosedWord(String myWord) {
		char[] closedWord = new char[myWord.length()];
		for (int i = 0; i < closedWord.length; i++) {
			closedWord[i] = '_';
		}
		return closedWord;
	}

	public static void showClosedWord(char[] closedWord) {
		System.out.println();
		for (int i = 0; i < closedWord.length; i++) {
			System.out.print(closedWord[i] + " ");
		}
	}

	public static boolean updateClosedWord(String myWord, String letter, char[] closedWord) {
		boolean success = false;
		for (int i = 0; i < myWord.length(); i++) {
			if (myWord.charAt(i) == letter.charAt(0)) {
				closedWord[i] = letter.charAt(0);
				success = true;
			}
		}
		return success;
	}

	public static boolean isGuessedFullWord(char[] closedWord) {
		for (int i = 0; i < closedWord.length; i++) {
			if (closedWord[i] == '_') {
				return false;
			}
		}
		return true;
	}


	public static void showAlphabet(char[] alphabet) {
		System.out.println();
		for (int i = 0; i < alphabet.length; i++) {
			if (i == 12 || i == 23) {
				System.out.println(alphabet[i] + " ");
			}
			System.out.print(alphabet[i] + " ");

		}
	}

	public static void updateAlphabet(char[] alphabet, String letter) {
		for (int i = 0; i < alphabet.length; i++) {
			if (alphabet[i] == letter.charAt(0)) {
				alphabet[i] = '*';
			}
		}
	}


	public static void showViselitsa(int countLives) {
		if (countLives == 7) {
			System.out.println("         ____\n" +
					                   "\t\t|\n" +
					                   "\t\t|\n" +
					                   "\t\t|\n" +
					                   "\t\t|");
		}
		if (countLives == 6) {
			System.out.println("         ____\n" +
					                   "\t\t|   |\n" +
					                   "\t\t|\n" +
					                   "\t\t|\n" +
					                   "\t\t|");
		}
		if (countLives == 5) {
			System.out.println("         ____\n" +
					                   "\t\t|   |\n" +
					                   "\t\t|   O\n" +
					                   "\t\t|\n" +
					                   "\t\t|");
		}
		if (countLives == 4) {
			System.out.println("         ____\n" +
					                   "\t\t|   |\n" +
					                   "\t\t|   O\n" +
					                   "\t\t|   |\n" +
					                   "\t\t|");
		}

		if (countLives == 3) {
			System.out.println("         ____\n" +
					                   "\t\t|   |\n" +
					                   "\t\t|   O\n" +
					                   "\t\t|  \\|\n" +
					                   "\t\t|");
		}

		if (countLives == 2) {
			System.out.println("         ____\n" +
					                   "\t\t|   |\n" +
					                   "\t\t|   O\n" +
					                   "\t\t|  \\|/\n" +
					                   "\t\t|");
		}

		if (countLives == 1) {
			System.out.println("         ____\n" +
					                   "\t\t|   |\n" +
					                   "\t\t|   O\n" +
					                   "\t\t|  \\|/\n" +
					                   "\t\t|  /");
		}

		if (countLives == 0) {
			System.out.println("         ____\n" +
					                   "\t\t|   |\n" +
					                   "\t\t|   O\n" +
					                   "\t\t|  \\|/\n" +
					                   "\t\t|  / \\");
		}
	}

}

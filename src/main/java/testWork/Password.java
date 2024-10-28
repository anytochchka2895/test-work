package testWork;

import java.util.*;

public class Password {
	//Напиши программу, которая генерирует случайный безопасный пароль длиной от 8 до 12 символов.
	// Пароль должен содержать заглавные и строчные буквы, цифры и специальные символы.
	// Предоставь пользователю возможность выбрать длину пароля.

	public static void main(String[] args) {
		String answer;

		do {
			generatedPassword();
			System.out.println();
			System.out.println();
			System.out.println("Вас устроил пароль?");
			Scanner scanner = new Scanner(System.in);
			answer = scanner.nextLine();
		}
		while (answer.equals("нет"));
	}

	public static void generatedPassword() {
		System.out.println();
		System.out.println("Введите длину желаемого пароля от 8 до 12 символов");
		Scanner scanner = new Scanner(System.in);
		int countSymbols = scanner.nextInt();

		List<String> myPassword = new ArrayList<>(countSymbols);

		String mySymbols = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#%^&*-_+=|\\/:;?.,~";

		// индексы [36-61]
		int symbolABCIndex = (int) (Math.random() * (62 - 36)) + 36;
		// индексы [10-35]
		int abcSymbolIndex = (int) (Math.random() * (36 - 10)) + 10;
		// индексы [0-9]
		int numbersIndex = (int) (Math.random() * (10));
		// индексы [62-81]
		int specialSymbolIndex = (int) (Math.random() * (82 - 62)) + 62;
		// индексы всей строки [0-81]

		String firstSymbol = mySymbols.charAt(symbolABCIndex) + "";
		myPassword.add(firstSymbol);
		String secondSymbol = mySymbols.charAt(abcSymbolIndex) + "";
		myPassword.add(secondSymbol);
		String thirdSymbol = mySymbols.charAt(numbersIndex) + "";
		myPassword.add(thirdSymbol);
		String fourthSymbol = mySymbols.charAt(specialSymbolIndex) + "";
		myPassword.add(fourthSymbol);

		for (int i = 4; i < countSymbols; i++) {
			String allSymbol = mySymbols.charAt((int) (Math.random() * (82))) + "";
			myPassword.add(allSymbol);
		}

		Collections.shuffle(myPassword);

		System.out.print("Ваш пароль: ");
		for (String symbol : myPassword) {
			System.out.print(symbol);
		}
	}
}

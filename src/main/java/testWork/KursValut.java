package testWork;

import java.util.*;

public class KursValut {
	// ДОЛЛАР
	public static final String USD = "USD";
	// ЕВРО
	public static final String EUR = "EUR";
	// РУБЛЬ
	public static final String RUB = "RUB";
	// ДИРХАМ
	public static final String AED = "AED";
	// ЮАНЬ
	public static final String CNY = "CNY";

	public static List<String> valutes = List.of(USD, EUR, RUB, AED, CNY);

	public static Scanner scanner = new Scanner(System.in);


	public static void main(String[] args) {
		// Создаём мапу, где ключ - строка с текстом, где указано отношение одной валюты к другой,
		// а значение - само отношение как число с плавающей точкой
		Map<String, Double> exchange = new HashMap<>();

		// Выбираем главную валюту, через которую будет происходить расчет.
		// Кладём в мапу основные отношения (главной валюты к другим)
		exchange.put(CNY + CNY, 1.0);
		exchange.put(CNY + USD, 0.13);
		exchange.put(CNY + EUR, 0.12);
		exchange.put(CNY + RUB, 13.24);
		exchange.put(CNY + AED, 0.48);

		// Также, добавляем дополнительные прямые отношения, чтобы пользователь мог понять, как ему выгоднее сделать перевод
		exchange.put(USD + EUR, 0.85);
		exchange.put(USD + AED, 3.59);

		String answer;

		do {
			String startValuta = readValuta();
			double valutaSumm = readValutaSumm();
			valueExchangeValuta(startValuta, valutaSumm, exchange);
			System.out.println();
			System.out.println("Вы хотите начать заново?");
			answer = scanner.nextLine();
		}
		while (answer.equals("да"));
	}

	// Вводим код валюты
	public static String readValuta() {
		String startValuta;
		boolean isValutaNotCorrect = false;
		// В одной строке покажем все валюты, которые у нас есть
		String myValutesView = String.join(", ", valutes);
		do {
			System.out.println("Введите код валюты: " + myValutesView);
			// Пользователь вводит валюту, которую он хочет конвертировать
			startValuta = scanner.nextLine();
			// Если введенный код валюты содержится в нашем списке, то продолжаем работать, если нет, то просим ввести заново
			isValutaNotCorrect = !valutes.contains(startValuta);
			if (isValutaNotCorrect) {
				System.out.println("Вы вводите некорректное значение");
			}
		}
		while (isValutaNotCorrect);
		return startValuta;
	}

	// Метод проверяет число это (на входе) или нет
	public static boolean isItNumber(String valutaSumm) {
		try {
			Double.parseDouble(valutaSumm);
		}
		catch (NumberFormatException exception) {
			return false;
		}
		return true;
	}

	// Вводим сумму валюты
	// Если пользователь ввел что-то некорректное, просим ввести снова
	public static double readValutaSumm() {
		String valutaSumm;
		boolean isValutaSummNotCorrect = false;
		do {
			System.out.println("Введите сумму");
			valutaSumm = scanner.nextLine();
			isValutaSummNotCorrect = !isItNumber(valutaSumm);
			if (isValutaSummNotCorrect) {
				System.out.println("Вы ввели некорректное значение");
				System.out.println();
			}
		}
		while (isValutaSummNotCorrect);
		return Double.parseDouble(valutaSumm);
	}

	// Конвертация
	public static void valueExchangeValuta(String startValuta,
	                                       double valutaSumm,
	                                       Map<String, Double> exchange) {
		// Задана основная валюта, через которую будет сделан перевод, если прямого отношения между валютами нет
		String mainValuta = CNY;
		double finishValutaSumm;

		for (String finishValuta : valutes) {
			Double otnoshenie = exchange.get(startValuta + finishValuta);
			// Если есть прямое отношение, то делаем перевод валют напрямую и выводим на экран информацию об этом
			if (Objects.nonNull(otnoshenie)){
				finishValutaSumm = valutaSumm / otnoshenie;
				System.out.println("Перевод напрямую: " + finishValuta + " " + String.format("%.2f", finishValutaSumm));
			}
			// Если прямого отношения нет, то делаем конвертацию через основную валюту и выводим это на экран
			finishValutaSumm = valutaSumm / exchange.get(mainValuta + startValuta) * exchange.get(mainValuta + finishValuta);
			System.out.println("Перевод через основную валюту (" + mainValuta + "): " + finishValuta +
					                   " " + String.format("%.2f", finishValutaSumm));
		}
	}


}

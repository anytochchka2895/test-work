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
		Map<String, Double> exchange = new HashMap<>();

		exchange.put(CNY + CNY, 1.0);
		exchange.put(CNY + USD, 0.13);
		exchange.put(CNY + EUR, 0.12);
		exchange.put(CNY + RUB, 13.24);
		exchange.put(CNY + AED, 0.48);

		exchange.put(USD + EUR, 0.85);
		exchange.put(USD + AED, 3.59);

		String answer;

		do {
			String startValuta = vvodValuta();
			double valutaSumm = vvodValutaSumm();
			valueExchangeThroughMainValuta(startValuta, valutaSumm, exchange);
			System.out.println();
			System.out.println("Вы хотите начать заново?");
			answer = scanner.nextLine();
		}
		while (answer.equals("да"));
	}


	public static String vvodValuta() {
		String startValuta;
		boolean isValutaNotCorrect = false;
		String myValutesView = String.join(", ", valutes);
		do {
			System.out.println("Введите код валюты: " + myValutesView);
			startValuta = scanner.nextLine();
			isValutaNotCorrect = !valutes.contains(startValuta);
			if (isValutaNotCorrect) {
				System.out.println("Вы вводите некорректное значение");
			}
		}
		while (isValutaNotCorrect);
		return startValuta;
	}


	public static boolean isItNumber(String valutaSumm) {
		try {
			Double.parseDouble(valutaSumm);
		}
		catch (NumberFormatException exception) {
			return false;
		}
		return true;
	}


	public static double vvodValutaSumm() {
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


	public static void valueExchangeThroughMainValuta(String startValuta,
	                                                  double valutaSumm,
	                                                  Map<String, Double> exchange) {
		String mainValuta = CNY;
		double finishValutaSumm;

		for (String finishValuta : valutes) {
			Double otnoshenie = exchange.get(startValuta + finishValuta);
			if (Objects.nonNull(otnoshenie)){
				finishValutaSumm = valutaSumm / otnoshenie;
				System.out.println("Перевод напрямую: " + finishValuta + " " + String.format("%.2f", finishValutaSumm));
			}
			finishValutaSumm = valutaSumm / exchange.get(mainValuta + startValuta) * exchange.get(mainValuta + finishValuta);
			System.out.println("Перевод через основную валюту: " + finishValuta + " " + String.format("%.2f", finishValutaSumm));
		}
	}


}

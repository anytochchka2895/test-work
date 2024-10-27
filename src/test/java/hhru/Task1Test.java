package hhru;

import org.junit.Test;

import java.util.Random;

public class Task1Test {

	@Test
	public void main() {
		Random r = new Random();
//		int x = r.nextInt(10_000);
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < 100_000; i++) {
			str.append(r.nextInt(10_000)).append(" ");
		}
		long start = System.currentTimeMillis();
		Task1.vhod(str.toString());
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}

	@Test
	public void twoo() {
		String str = "2 4 3 2 1 4 1";
		Task1.vhod(str);
	}



}

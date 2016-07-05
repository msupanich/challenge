package challenge;

import java.util.concurrent.ThreadLocalRandom;

public class RandomNumbers {
	private static final int min = 0;
	private static final int max = 9;
	
	public int pickThree() {
		int number = randomNumber();
		number += randomNumber() * 10;
		number += randomNumber() * 100;
		
		return number;
	}
	
	public int pickFour() {
		int number = randomNumber();
		number += randomNumber() * 10;
		number += randomNumber() * 100;
		number += randomNumber() * 1000;
		
		return number;
	}
	
	public int pickFive() {
		int number = randomNumber();
		number += randomNumber() * 10;
		number += randomNumber() * 100;
		number += randomNumber() * 1000;
		number += randomNumber() * 10000;
		
		return number;
	}
	
	private int randomNumber() {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
}

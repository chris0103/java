package crypto.rationale;

import java.math.BigDecimal;
import java.util.Random;

public class BirthdayAttack {
	
	private static BigDecimal DAY_OF_YEAR = new BigDecimal("365");
	
	private BigDecimal factorial(BigDecimal n) {
		if (n.compareTo(new BigDecimal("1")) < 0) {
			return new BigDecimal("0");
		}
		if (n.compareTo(new BigDecimal("1")) == 0) {
			return new BigDecimal("1");
		} else {
			return n.multiply(factorial(n.subtract(new BigDecimal("1"))));
		}
	}
	
	private Integer getSpecificCollisionShreshold() {
		Integer threshold = 1;
		BigDecimal P;
		while (true) {
			P = new BigDecimal("1").subtract(
					DAY_OF_YEAR.subtract(new BigDecimal("1")).pow(threshold).divide(
							DAY_OF_YEAR.pow(threshold), 3, BigDecimal.ROUND_HALF_UP
						)
				);
			if (P.compareTo(new BigDecimal("0.500")) >= 0) {
				break;
			}
			threshold++;
		}
		return threshold;
	}
	
	private Integer getGeneralCollisionShreshold() {
		Integer threshold = 1;
		BigDecimal P;
		while (true) {
			P = new BigDecimal("1").subtract(
					factorial(DAY_OF_YEAR).divide(
							DAY_OF_YEAR.pow(threshold).multiply(factorial(DAY_OF_YEAR.subtract(new BigDecimal(threshold)))),
							3, BigDecimal.ROUND_HALF_UP)
				);
			if (P.compareTo(new BigDecimal("0.500")) >= 0) {
				break;
			}
			threshold++;
		}
		return threshold;
	}
	
	private void BirthdayAttacking() {
		System.out.println("See how often two persons share the same birthday among 23 persons:");
		Random random = new Random();
		int i = 0;
		int tryTime = 10;
		boolean found = false;
		Integer[] birthdays = new Integer[23];
		while (tryTime > 0) {
			found = false;
			System.out.println("Test - " + tryTime);
			for (i = 0; i < birthdays.length; i++) {
				birthdays[i] = random.nextInt(365);
			}
			for (i = 0; i < birthdays.length; i++) {
				System.out.print(birthdays[i] + " ");
			}
			System.out.println();
			for (i = 0; i < birthdays.length; i++) {
				for (int j = i + 1; j < birthdays.length; j++) {
					if (birthdays[i].equals(birthdays[j])) {
						System.out.println("Same birthday: " + birthdays[i]);
						found = true;
						break;
					}
				}
			}
			if (!found) {
				System.out.println("Same birthday not found.");
			} 
			tryTime--;
		}
	}
	
	public static void main(String[] args) {
		BirthdayAttack ba = new BirthdayAttack();
		int persons = ba.getSpecificCollisionShreshold();
		System.out.println("There are at least " 
				+ persons + " persons to make one's birthday the same as yours."); 
		persons = ba.getGeneralCollisionShreshold();
		System.out.println("There are at least "
				+ persons + " persons to make at least two of them share the same birthday.");
		ba.BirthdayAttacking();
	}
}

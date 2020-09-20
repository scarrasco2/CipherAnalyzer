package ciphers;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Main.CipherText;
import options.ColumnarTranspositionOptions;

public class ColumnarTransposition {

	public static String decryption(String cipherText, String key) {

		int numberOfRowsRows = cipherText.length() / key.length();

		// array with dummy values
		char[][] characterMatrix = new char[numberOfRowsRows][key.length()];

		// assigning numbers to keywords
		int[] keywordNumberList = keywordNumAssign(key);

		String numberLocation = getNumberLocation(key, keywordNumberList);

		for (int i = 0, k = 0; i < cipherText.length(); i++, k++) {
			int d = 0;
			if (k == key.length()) {
				k = 0;
			} else {
				d = Character.getNumericValue(numberLocation.charAt(k));
			}

			for (int j = 0; j < numberOfRowsRows; j++, i++) {
				characterMatrix[j][d] = cipherText.charAt(i);
			} // for loop
			--i;
		}

		StringBuilder plainText = new StringBuilder();

		for (int i = 0; i < numberOfRowsRows; i++) {
			for (int j = 0; j < key.length(); j++) {
				plainText.append(characterMatrix[i][j]);
			} // inner for loop
		} // for loop

		return plainText.toString().toLowerCase();

	}

	private static String getNumberLocation(String keyword, int[] kywrdNumList) {
		String numberLocation = "";
		for (int i = 1; i < keyword.length() + 1; i++) {
			for (int j = 0; j < keyword.length(); j++) {
				if (kywrdNumList[j] == i) {
					numberLocation += j;
				}
			}
		}
		return numberLocation;
	} // getting number location function

	private static int[] keywordNumAssign(String keyword) {
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int[] keywordNumberList = new int[keyword.length()];
		int init = 0;
		for (int i = 0; i < alpha.length(); i++) {
			for (int j = 0; j < keyword.length(); j++) {
				if (alpha.charAt(i) == keyword.charAt(j)) {
					init++;
					keywordNumberList[j] = init;
				}
			}
		}
		return keywordNumberList;
	}
	
	public static void run(Scanner scanner, CipherText cipherText) {
		ColumnarTranspositionOptions columnarTranspositionOptions;
		int intOption;
		boolean isDone = false;
		while (!isDone) {
			try {
				System.out.println("Welcome to Columnar Transposition Cipher Analyzer.");
				for (ColumnarTranspositionOptions option : ColumnarTranspositionOptions.values()) {
					System.out.println((option.ordinal() + 1) + " -- " + option.getDescription());
				}
				System.out.println();
				System.out.println("Select an option: ");
				intOption = scanner.nextInt();
				scanner.nextLine();
				columnarTranspositionOptions = ColumnarTranspositionOptions.values()[intOption - 1];

				switch (columnarTranspositionOptions) {
				
				case DECRYPT_WITH_A_KEY:
					System.out.println("Input a key value below:");
					String input = scanner.nextLine();
					String key = VigenereCipher.generateKey(cipherText.cipherTextString, input);
					String plainText = VigenereCipher.decryption(cipherText.getCipherTextString(), key);
					System.out.println("Decrypting with the key value of " + input + " gives:");
					System.out.println(plainText);
					break;

				case EXIT:
					System.out.println("Exiting Columnar Transposition Cipher Analyzer");
					isDone = true;
					break;

				default:
					break;
				}
			} catch (InputMismatchException e) { // catches any option input that was not an int
				System.out.println("You did not type a number.");
				System.out.println("Please try again.");
				scanner.nextLine();
			} catch (NoSuchElementException noSuchElement) { // catches any invalid option inputs
				System.out.println("Invalid Selection. Please try again.");
				scanner.nextLine();
			} catch (ArrayIndexOutOfBoundsException e) { // catches any invalid action input
				System.out.println("You selected an action that does not exist.");
				System.out.println("Please try again.");
			}
		}

	}

}

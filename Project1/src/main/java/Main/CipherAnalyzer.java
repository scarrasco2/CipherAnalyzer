package Main;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import ciphers.ShiftCipher;
import ciphers.VigenereCipher;
import options.UserOptions;

public class CipherAnalyzer {

	public static void run(Scanner scanner, CipherText cipherText) {
		UserOptions userOptions;
		int intOption;
		boolean isDone = false;
		while (!isDone) {
			try {
				System.out.println("Welcome to Cipher Analyzer.");
				for (UserOptions option : UserOptions.values()) {
					System.out.println((option.ordinal() + 1) + " -- " + option.getDescription());
				}
				System.out.println();
				System.out.println("Select an option: ");
				intOption = scanner.nextInt();
				scanner.nextLine();
				userOptions = UserOptions.values()[intOption - 1];

				switch (userOptions) {
				case FREQUENCY_ANALYSIS:
					cipherText.calculateFrequnceyAnalysis();
					System.out.println(cipherText.getFreqencyAnalysis());
					break;

				case INDEX_OF_COINCIDENCE:
					cipherText.calculateIndexOfCoincidence();
					System.out.printf("Index of Coincidence = %.4f \n", cipherText.getIndexOfCoincidence());
					if (cipherText.getIndexOfCoincidence() < .063) {
						System.out.println(
								"Based on the index of coincidence the cipher used is more likey a polyalphabetic cipher");
					} else {
						System.out.println(
								"Based on the index of coincidence the cipher used is more likey a monoalphabetic cipher");
					}
					break;

				case SHIFT_CIPHER:
					ShiftCipher.run(scanner, cipherText);
					break;

				case VINGENERE_CIPHER:
					VigenereCipher.run(scanner, cipherText);
					break;

				case EXIT:
					System.out.println("GoodBye");
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

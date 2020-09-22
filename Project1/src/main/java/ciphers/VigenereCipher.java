package ciphers;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import Main.CipherText;
import options.VigenereCipherOptions;

public class VigenereCipher {

	static String generateKey(String str, String key) {
		int letter = str.length();
		for (int i = 0;; i++) {
			if (letter == i)
				i = 0;
			if (key.length() == str.length())
				break;
			key += (key.charAt(i));
		}
		return key.toUpperCase();
	}

	// Cipher Text is UpperCase since the text files are in UpperCase
	public static String decryption(String cipherText, String key) {
		String plainText = "";

		for (int i = 0; i < cipherText.length() && i < key.length(); i++) {

			int letter = (cipherText.charAt(i) - key.charAt(i) + 26) % 26;

			letter = letter + 'A';
			plainText = plainText + (char) (letter);
		}
		return plainText.toLowerCase();
	}

	public static double calculateShiftedIndexOfCoincidence(String cipherText, int keyLength) {
		double sum = 0.0;
		int n = keyLength;
		for (int i = 0; i < n; i++) {
			String shiftedCipherTextString = "";
			for (int j = i; j < cipherText.length(); j += n) {
				shiftedCipherTextString = shiftedCipherTextString + cipherText.charAt(j);
			}
			CipherText shiftedCipherText = new CipherText();
			shiftedCipherText.cipherTextString = shiftedCipherTextString;
			shiftedCipherText.calculateIndexOfCoincidence();
			sum = sum + shiftedCipherText.indexOfCoincidence;
		}
		return (sum) / n;
	}

	public static void run(Scanner scanner, CipherText cipherText) {
		VigenereCipherOptions vigenereCipherOptions;
		int intOption;
		boolean isDone = false;
		while (!isDone) {
			try {
				System.out.println("Welcome to Vigenere Cipher Analyzer.");
				for (VigenereCipherOptions option : VigenereCipherOptions.values()) {
					System.out.println((option.ordinal() + 1) + " -- " + option.getDescription());
				}
				System.out.println();
				System.out.println("Select an option: ");
				intOption = scanner.nextInt();
				scanner.nextLine();
				vigenereCipherOptions = VigenereCipherOptions.values()[intOption - 1];

				switch (vigenereCipherOptions) {
				case FIND_KEY_Length:
					System.out.println("Estimate the maxmimum length of the key below:");
					int maxKeyLength = scanner.nextInt();
					for (int i = 0; i < maxKeyLength; i++) {
						if (calculateShiftedIndexOfCoincidence(cipherText.cipherTextString, i) > .060) {
							System.out.println("The key mostly likely has a length of " + i);
						}
					}
					break;

				case DECRYPT_WITH_A_KEY:
					System.out.println("Input a key value below:");
					String input = scanner.nextLine();
					String key = VigenereCipher.generateKey(cipherText.cipherTextString, input);
					String plainText = VigenereCipher.decryption(cipherText.getCipherTextString(), key);
					System.out.println("Decrypting with the key value of " + input + " gives:");
					System.out.println(plainText);
					break;

				case BREAK_TEXT_INTO_GROUPS:
					System.out.println("What is the key length?");
					int numberOfGroups = scanner.nextInt();
					for (int i = 0; i < numberOfGroups; i++) {
						String shiftedCipherTextString = "";
						for (int j = i; j < cipherText.cipherTextString.length(); j += numberOfGroups) {
							shiftedCipherTextString = shiftedCipherTextString + cipherText.cipherTextString.charAt(j);
						}
						System.out.println("Group " + i);
						System.out.println(shiftedCipherTextString);
					}
					break;

				case FIND_KEY:
					System.out.println("Enter the key length below:");
					int keyLength = scanner.nextInt();
					for (int i = 0; i < keyLength; i++) {
						String shiftedCipherTextString = "";
						for (int j = i; j < cipherText.cipherTextString.length(); j += keyLength) {
							shiftedCipherTextString = shiftedCipherTextString + cipherText.cipherTextString.charAt(j);
						}
						for (int k = 0; k < 26; k++) {
							String shifted = ShiftCipher.decipher(shiftedCipherTextString, k);
							double sum = 0;
							double value = 0;
							int[] freqs = new int[26];
							for (int m = 0; m < shifted.length(); m++) {
								if (shifted.charAt(m) >= 'a' && shifted.charAt(m) <= 'z') {
									freqs[shifted.charAt(m) - 'a']++;
									sum++;
								}

							}

							double[] englishFrequency = new double[26];
							englishFrequency[0] = .082;
							englishFrequency[1] = .015;
							englishFrequency[2] = .028;
							englishFrequency[3] = .043;
							englishFrequency[4] = .13;
							englishFrequency[5] = .022;
							englishFrequency[6] = .02;
							englishFrequency[7] = .061;
							englishFrequency[8] = .07;
							englishFrequency[9] = .0015;
							englishFrequency[10] = .0077;
							englishFrequency[11] = .04;
							englishFrequency[12] = .024;
							englishFrequency[13] = .067;
							englishFrequency[14] = .075;
							englishFrequency[15] = .019;
							englishFrequency[16] = .00095;
							englishFrequency[17] = .06;
							englishFrequency[18] = .063;
							englishFrequency[19] = .091;
							englishFrequency[20] = .028;
							englishFrequency[21] = .0098;
							englishFrequency[22] = .024;
							englishFrequency[23] = .0015;
							englishFrequency[24] = .02;
							englishFrequency[25] = .00074;
							System.out.printf(" %c = ", (k + 97));
							for (int t = 0; t < 26; t++) {
								value = value + (freqs[t] / sum) * englishFrequency[t];
							}
							System.out.printf(" %f ", value);
						}
						System.out.println("Position = " + i);
					}

					break;

				case EXIT:
					System.out.println("Exiting Vigenere Cipher Analyzer");
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

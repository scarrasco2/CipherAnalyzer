package ciphers;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Main.CipherText;
import options.ShiftCipherOptions;

public class ShiftCipher {
	public static void run(Scanner scanner, CipherText cipherText) {
		ShiftCipherOptions shiftCipherOptions;
		int intOption;
		boolean isDone = false;
		while (!isDone) {
			try {
				System.out.println("Welcome to Shift Cipher Analyzer.");
				for (ShiftCipherOptions option : ShiftCipherOptions.values()) {
					System.out.println((option.ordinal() + 1) + " -- " + option.getDescription());
				}
				System.out.println();
				System.out.println("Select an option: ");
				intOption = scanner.nextInt();
				scanner.nextLine();
				shiftCipherOptions = ShiftCipherOptions.values()[intOption - 1];

				switch (shiftCipherOptions) {
				case FIND_KEY:
					for (int i = 0; i < 26; i++) {
						String plainText = ShiftCipher.decipher(cipherText.cipherTextString, i);
						if (plainText.contains("the") && plainText.contains("of") && plainText.contains("to")
								&& plainText.contains("is")) {
							System.out.println("The key value of " + i + " is a possible key");
						}
					}
					break;

				case DECRYPT_WITH_A_KEY:
					System.out.println("Input a key value below:");
					int key = scanner.nextInt();
					String plainText = ShiftCipher.decipher(cipherText.getCipherTextString(), key);
					System.out.println("Decrypting with the key value of " + key + " gives:");
					System.out.println(plainText);
					break;

				case EXIT:
					System.out.println("Exiting Shift Cipher Analyzer");
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

	public static List<String> generateWordList() {
		String token = "";
		Scanner inFile = null;
		try {
			inFile = extracted();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		List<String> wordList = new ArrayList<String>();
		while (inFile.hasNext()) {
			// find next line
			token = inFile.next();
			wordList.add(token);
		}
		inFile.close();
		return wordList;
	}

	private static Scanner extracted() throws FileNotFoundException {
		return new Scanner(new File("src\\main\\resources\\1000_Common_English_Words.txt")).useDelimiter(",\\s*");
	}

	// Cipher Text is UpperCase since the text files are in UpperCase
	static String decipher(String cipherText, int key) {
		cipherText = cipherText.toLowerCase();
		String plaintext = "";
		for (int i = 0; i < cipherText.length(); i++) {
			char shiftedLetter = (char) (((int) cipherText.charAt(i) + (26 - key) - 97) % 26 + 97);
			plaintext = plaintext + shiftedLetter;
		}
		return plaintext;
	}
	
	static String encode(String cipherText, int key) {
		cipherText = cipherText.toLowerCase();
		String plaintext = "";
		for (int i = 0; i < cipherText.length(); i++) {
			char shiftedLetter = (char) (((int) cipherText.charAt(i) +  key - 97) % 26 + 97);
			plaintext = plaintext + shiftedLetter;
		}
		return plaintext;
	}


}

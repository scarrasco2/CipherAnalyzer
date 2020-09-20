package ciphers;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Main.CipherText;
import options.SubsitutionCipherOptions;

public class SubstitutionCipher {
	public static HashMap<Character, Integer> sortByValue(HashMap<Character, Integer> hm) {
		// Create a list from elements of HashMap
		List<Map.Entry<Character, Integer>> list = new LinkedList<Map.Entry<Character, Integer>>(hm.entrySet());

		// Sort the list
		Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
			public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		// put data from sorted list to hashmap
		HashMap<Character, Integer> temp = new LinkedHashMap<Character, Integer>();
		for (Map.Entry<Character, Integer> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	}

	public static void run(Scanner scanner, CipherText cipherText) {
		SubsitutionCipherOptions subsitutionCipherOptions;
		String savedCipherText = cipherText.getCipherTextString();
		int intOption;
		boolean isDone = false;
		while (!isDone) {
			try {
				System.out.println("Welcome to Substitution Cipher Analyzer.");
				for (SubsitutionCipherOptions option : SubsitutionCipherOptions.values()) {
					System.out.println((option.ordinal() + 1) + " -- " + option.getDescription());
				}
				System.out.println();
				System.out.println("Select an option: ");
				intOption = scanner.nextInt();
				scanner.nextLine();
				subsitutionCipherOptions = SubsitutionCipherOptions.values()[intOption - 1];

				switch (subsitutionCipherOptions) {

				case SORTED_FREQUENCY_ANALYSIS:
					HashMap<Character, Integer> frequencyAnalysis = new HashMap<Character, Integer>();
					cipherText.calculateFrequnceyAnalysis();
					frequencyAnalysis = cipherText.getFreqencyAnalysis();
					System.out.println(sortByValue(frequencyAnalysis).toString());
					break;

				case PRINT_CIPHER_TEXT:
					System.out.println(cipherText.getCipherTextString());
					break;

				case REPLACE_A_LETTER:
					System.out.println("Enter the a letter you wish to replace:");
					char oldLetter = scanner.next().toUpperCase().charAt(0);
					System.out.println("Enter the replacement letter");
					char newLetter = scanner.next().toLowerCase().charAt(0);
					String newCipherText = cipherText.cipherTextString.replace(oldLetter, newLetter);
					System.out.println("The new cipher text is the following:");
					System.out.println(newCipherText);
					cipherText.setCipherTextString(newCipherText);
					break;

				case RESET_CIPHER_TEXT:
					cipherText.setCipherTextString(savedCipherText);
					System.out.println("The cipher text has been restored");

				case EXIT:
					System.out.println("Exiting substitution cipher Analyzer");
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

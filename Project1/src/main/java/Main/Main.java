package Main;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		CipherText test = new CipherText();
		String content = Files.readString(Paths.get("src\\main\\resources\\cipher3.txt"), StandardCharsets.US_ASCII);
		test.cipherTextString = content;
		test.calculateFrequnceyAnalysis();
		test.calculateIndexOfCoincidence();
		System.out.println(test.freqencyAnalysis);
		System.out.println(test.indexOfCoincidence);
		String key = VigenereCipher.generateKey(content, "Tucson");
		String plainText = VigenereCipher.decryption(content, key);
		System.out.println(test.cipherTextString);
		System.out.println(plainText);
		double ic = VigenereCipher.calculateShiftedIndexOfCoincidence(content, 6);
		System.out.println(ic);
		Scanner scanner = new Scanner(System.in);
		String a = Input.run(scanner);
		System.out.println("The following cipher text has been inputted:");
		System.out.println(a);
		CipherAnalyzer.run(scanner);
	}
}

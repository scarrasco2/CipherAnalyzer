package Main;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Scanner scanner = new Scanner(System.in);
		String input = Input.run(scanner);
		System.out.println("The following cipher text has been inputted:");
		System.out.println(input);
		CipherText cipherText = new CipherText();
		cipherText.setCipherTextString(input);
		CipherAnalyzer.run(scanner,cipherText);
	}
}

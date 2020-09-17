package Main;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) throws IOException {
		CipherText test = new CipherText();
		String content = Files.readString(Paths.get("src\\main\\resources\\cipher1.txt"), StandardCharsets.US_ASCII);
		test.cipherTextString = content;
		test.calculateFrequnceyAnalysis();
		test.calculateIndexOfCoincidence();
		System.out.println(test.freqencyAnalysis);
		System.out.println(test.indexOfCoincidence);
		

	}

}

package Main;

public class ShiftCipher {
	
	// Cipher Text is UpperCase since the text files are in UpperCase
	static String decipher(String cipherText, int key) {
		cipherText = cipherText.toLowerCase();
		System.out.println(cipherText);
		String plaintext = "";
		for (int i = 0; i < cipherText.length(); i++) {
			char shiftedLetter = (char)(((int)cipherText.charAt(i) + (26 - key) - 97) % 26 + 97);
			plaintext = plaintext + shiftedLetter;
		}
		return plaintext;
	}
}

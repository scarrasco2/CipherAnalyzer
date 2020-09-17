package Main;

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
	static String decryption(String cipherText, String key) {
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
}

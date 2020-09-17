package Main;

import java.util.HashMap;
import java.util.Map.Entry;

public class CipherText {
	String cipherTextString;
	HashMap<Character, Integer> freqencyAnalysis;
	Double indexOfCoincidence;

	public CipherText() {
		cipherTextString = null;
		freqencyAnalysis = null;
		indexOfCoincidence = 0.0;
	}

	public void calculateFrequnceyAnalysis() {
		HashMap<Character, Integer> letterMap = new HashMap<Character, Integer>();
		for (int i = 0; i < this.cipherTextString.length(); i++) {
			char letter = this.cipherTextString.charAt(i);
			Integer val = letterMap.get(letter);
			if (val != null) {
				letterMap.put(letter, (val + 1));
			} else {
				letterMap.put(letter, 1);
			}
		}
		freqencyAnalysis = letterMap;
	}

	public void calculateIndexOfCoincidence() {
		calculateFrequnceyAnalysis();
		int n = cipherTextString.length();
		double sum = 0.0;

		for (Entry<Character, Integer> letter : this.freqencyAnalysis.entrySet()) {
			sum = sum + letter.getValue() * (letter.getValue() - 1);
		}
		this.indexOfCoincidence = sum / (n * (n - 1));

	}
}

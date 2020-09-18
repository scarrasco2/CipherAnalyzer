package Main;

import java.util.HashMap;
import java.util.Map.Entry;

public class CipherText {
	public String cipherTextString;
	public String getCipherTextString() {
		return cipherTextString;
	}

	public HashMap<Character, Integer> getFreqencyAnalysis() {
		return freqencyAnalysis;
	}

	public Double getIndexOfCoincidence() {
		return indexOfCoincidence;
	}

	public void setCipherTextString(String cipherTextString) {
		this.cipherTextString = cipherTextString;
	}

	public void setFreqencyAnalysis(HashMap<Character, Integer> freqencyAnalysis) {
		this.freqencyAnalysis = freqencyAnalysis;
	}

	public void setIndexOfCoincidence(Double indexOfCoincidence) {
		this.indexOfCoincidence = indexOfCoincidence;
	}

	public HashMap<Character, Integer> freqencyAnalysis;
	public Double indexOfCoincidence;

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

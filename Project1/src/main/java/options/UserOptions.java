package options;

public enum UserOptions {
	FREQUENCY_ANALYSIS("Run frequency analysis of the cipher text."), 
	INDEX_OF_COINCIDENCE("Calculate index of coincidence"),
	SHIFT_CIPHER("Go to shift cipher tools"),
	VINGENERE_CIPHER("Go to Vigenere cipher tools"),
	EXIT("Leave program");

	private String description;

	public String getDescription() {
		return description;
	}

	UserOptions(String description) {
		this.description = description;
	}
}


package options;

public enum VigenereCipherOptions {
	FIND_KEY_Length("Find the key length using a shifted index of coincidence"), 
	DECRYPT_WITH_A_KEY("Decrypt with a specific key value"),
	BREAK_TEXT_INTO_GROUPS("Break the cipher text into groups. Note first find the key length"),
	EXIT("Leave program");

	private String description;

	public String getDescription() {
		return description;
	}

	VigenereCipherOptions(String description) {
		this.description = description;
	}
}

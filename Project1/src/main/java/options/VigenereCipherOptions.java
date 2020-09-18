package options;

public enum VigenereCipherOptions {
	FIND_KEY_Length("Find the key length using a shifted index of coincidence"), 
	DECRYPT_WITH_A_KEY("Decrypt with a specific key value"),
	EXIT("Leave program");

	private String description;

	public String getDescription() {
		return description;
	}

	VigenereCipherOptions(String description) {
		this.description = description;
	}
}

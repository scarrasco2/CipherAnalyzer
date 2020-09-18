package options;

public enum ShiftCipherOptions {
	FIND_KEY("Find key value"),
	DECRYPT_WITH_A_KEY("Decrypt with a specific key value"),
	EXIT("Leave program");

	private String description;

	public String getDescription() {
		return description;
	}

	ShiftCipherOptions(String description) {
		this.description = description;
	}
}

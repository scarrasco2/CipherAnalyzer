package options;

public enum ColumnarTranspositionOptions { 
	DECRYPT_WITH_A_KEY("Decrypt with a specific key value"),
	EXIT("Leave program");

	private String description;

	public String getDescription() {
		return description;
	}

	ColumnarTranspositionOptions(String description) {
		this.description = description;
	}
}

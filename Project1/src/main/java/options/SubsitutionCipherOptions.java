package options;

public enum SubsitutionCipherOptions {
	SORTED_FREQUENCY_ANALYSIS("Run sorted frequency analysis of the cipher text."),
	REPLACE_A_LETTER("Replace a ciphertext letter to a plaintext letter"),
	PRINT_CIPHER_TEXT("Print out the cipher text"),
	RESET_CIPHER_TEXT("Reset the cipher text"),
	EXIT("Leave program");

	private String description;

	public String getDescription() {
		return description;
	}

	SubsitutionCipherOptions(String description) {
		this.description = description;
	}
}

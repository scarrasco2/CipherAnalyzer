package options;

public enum SubsitutionCipherOptions {
	SORTED_FREQUENCY_ANALYSIS("Run sorted frequency analysis of the cipher text."),
	EXIT("Leave program");

	private String description;

	public String getDescription() {
		return description;
	}

	SubsitutionCipherOptions(String description) {
		this.description = description;
	}
}

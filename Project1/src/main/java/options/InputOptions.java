package options;

public enum InputOptions {
	USER_INPUT("Input your own cipher text."),
	TEXT_FILE_INPUT("Chose a text file."),
	EXIT("Leave program");

	private String description;

	public String getDescription() {
		return description;
	}

	InputOptions(String description) {
		this.description = description;
	}
}


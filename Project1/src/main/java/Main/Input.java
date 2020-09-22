package Main;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import options.InputOptions;

public class Input {
	public static String run(Scanner scanner) throws IOException {
		InputOptions inputOptions;
		int intOption;
		boolean isDone = false;
		String input = null;
		while (!isDone) {
			try {
				System.out.println("Welcome to the Input Section.");
				for (InputOptions option : InputOptions.values()) {
					System.out.println((option.ordinal() + 1) + " -- " + option.getDescription());
				}
				System.out.println();
				System.out.println("Select an option: ");
				intOption = scanner.nextInt();
				scanner.nextLine();
				inputOptions = InputOptions.values()[intOption - 1];

				switch (inputOptions) {

				case USER_INPUT:
					System.out.println("Input your cipher text below:");
					input = scanner.nextLine();
					input = input.toUpperCase();
					isDone = true;
					break;

				case TEXT_FILE_INPUT:
					FileDialog dialog = new FileDialog((Frame) null, "Select File to Open");
					dialog.setMode(FileDialog.LOAD);
					dialog.setVisible(true);
					String path = dialog.getDirectory() + dialog.getFile();
					input = Files.readString(Paths.get("" + path), StandardCharsets.US_ASCII);
					isDone = true;
					break;

				case EXIT:
					System.out.println("GoodBye");
					isDone = true;
					break;

				default:
					break;
				}
			} catch (InputMismatchException e) { // catches any option input that was not an int
				System.out.println("You did not type a number.");
				System.out.println("Please try again.");
				scanner.nextLine();
			} catch (NoSuchElementException noSuchElement) { // catches any invalid option inputs
				System.out.println("Invalid Selection. Please try again.");
				scanner.nextLine();
			} catch (ArrayIndexOutOfBoundsException e) { // catches any invalid action input
				System.out.println("You selected an action that does not exist.");
				System.out.println("Please try again.");
			}
		}
		return input;
	}

}

package control.factories;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.MissingResourceException;
import java.util.Scanner;

import control.Controller;

public class TextFileReader<V> {

	public static final String ERROR_MESSAGE = "Missing class names";
	public static final char COMMENT_CHARACTER = '#';


	protected Scanner getScanner(String indexFile) {
		FileReader fileToBeRead = null;
		String currentDirectory = System.getProperty(Controller.USER_DIR);
		try {
			fileToBeRead = new FileReader(currentDirectory + indexFile);
		} catch (FileNotFoundException e) {
			throw new MissingResourceException(ERROR_MESSAGE, "", "");
		}
		Scanner line = new Scanner(fileToBeRead);
		return line;
	}

	protected boolean commentedLine(String line) {
		return line.charAt(0) == COMMENT_CHARACTER || line.length() <= 0;
	}

}
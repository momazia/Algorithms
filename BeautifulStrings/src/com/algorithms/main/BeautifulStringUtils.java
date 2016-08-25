package com.algorithms.main;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class BeautifulStringUtils {

	/**
	 * Default path to read the file from
	 */
	public static final String IO_PATH = "../BeautifulStrings/io/";

	/**
	 * static Singleton instance
	 */
	private static BeautifulStringUtils instance;

	/**
	 * Private constructor for singleton
	 */
	private BeautifulStringUtils() {
	}

	/**
	 * Static getter method for retrieving the singleton instance
	 */
	public static BeautifulStringUtils getInstance() {
		if (instance == null) {
			instance = new BeautifulStringUtils();
		}
		return instance;
	}

	public List<String> readFile(String fileName) throws URISyntaxException, IOException {
		return Files.readAllLines(Paths.get(IO_PATH + fileName));
	}
}

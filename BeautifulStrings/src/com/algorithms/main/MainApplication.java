package com.algorithms.main;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * The main application to be executed to solve beautiful strings problem. Place
 * the input file into io folder of the project and update the constant below.
 * 
 * @author Max
 *
 */
public class MainApplication {

	private static final String FILE_NAME = "input.txt";

	public static void main(String[] args) {
		try {
			List<String> lines = BeautifulStringUtils.getInstance().readFile(FILE_NAME);
			for (String line : lines) {
				System.out.println(line);
				System.out.println("Score: " + BeautifulStringUtils.getInstance().calculateScore(line));
			}
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
			fail("An exception happened");
		}
	}

}

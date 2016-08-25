package com.algorithms.main;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The main utility class for Beautiful Strings problem.
 * 
 * @author Max
 *
 */
/**
 * @author Max
 *
 */
public class BeautifulStringUtils {

	/**
	 * Default path to read the file from
	 */
	public static final String IO_PATH = "../BeautifulStrings/io/";

	public static final int MAX_SCORE = 26;

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

	/**
	 * Reads fileName given and tries to read the file from io folder.
	 * 
	 * @param fileName
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public List<String> readFile(String fileName) throws URISyntaxException, IOException {
		return Files.readAllLines(Paths.get(IO_PATH + fileName));
	}

	/**
	 * Calculates the score for the string given.
	 * 
	 * @param line
	 * @return
	 */
	public int calculateScore(String line) {
		// Count the characters occurrences, ignoring the special characters.
		List<Alphabet> results = countCharacters(line);
		// Set the scores on the characters from MAX to lowest.
		List<Alphabet> scoredAlphabets = setScoresOnCharacters(results);
		// Calculating the final score.
		return calculateScore(scoredAlphabets);
	}

	/**
	 * Calculates the total score by multiplying the elements counters to their
	 * scores and summing them up all together.
	 * 
	 * @param scoredAlphabets
	 * @return
	 */
	private int calculateScore(List<Alphabet> scoredAlphabets) {
		int score = 0;
		for (Alphabet alphabet : scoredAlphabets) {
			score += alphabet.getCounter() * alphabet.getScore();
		}
		return score;
	}

	/**
	 * Sets the score on the list of the alphabets given and returns the result
	 * in a tree format.
	 * 
	 * @param result
	 * @return
	 */
	private List<Alphabet> setScoresOnCharacters(List<Alphabet> alphabets) {
		// Sorting the list of alphabets based on their counter in descending
		// order
		Collections.sort(alphabets, new Comparator<Alphabet>() {
			@Override
			public int compare(Alphabet o1, Alphabet o2) {
				return -Integer.compare(o1.getCounter(), o2.getCounter());
			}
		});

		// Setting the scores from the highest to the lowest
		int score = MAX_SCORE;
		for (Alphabet alphabet : alphabets) {
			alphabet.setScore(score);
			score--;
		}
		return alphabets;
	}

	/**
	 * Counts the occurrences of the each characters and put them into a map in
	 * which the key is the character and the value is the result of the count.
	 * 
	 * @param line
	 * @return
	 */
	public List<Alphabet> countCharacters(String line) {
		Map<String, Alphabet> results = new HashMap<>();
		for (int i = 0; i < line.length(); i++) {
			char chr = Character.toLowerCase(line.charAt(i));
			// Checking to see if the character is alphabetic, if not, just
			// ignore it.
			if (Character.isAlphabetic(chr)) {
				String chrString = String.valueOf(chr);
				if (results.containsKey(chrString)) {
					// If the character was added to the map before, increment
					// the counter value by one.
					Alphabet existingAlphabet = results.get(chrString);
					existingAlphabet.setCounter(existingAlphabet.getCounter() + 1);
					results.put(chrString, existingAlphabet);
				} else {
					// If the character was not added to the map before, set its
					// counter to 1.
					Alphabet alphabet = new Alphabet(chr);
					alphabet.setCounter(1);
					results.put(chrString, alphabet);
				}
			}
		}
		// Converting the result into a list
		List<Alphabet> list = new ArrayList<>();
		list.addAll(results.values());
		return list;
	}
}

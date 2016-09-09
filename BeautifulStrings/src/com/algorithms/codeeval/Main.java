package com.algorithms.codeeval;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static final int MAX_SCORE = 26;

	public static void main(String[] args) throws IOException {
		File file = new File(args[0]);
		@SuppressWarnings("resource")
		BufferedReader buffer = new BufferedReader(new FileReader(file));
		String line;
		while ((line = buffer.readLine()) != null) {
			line = line.trim();
			// Process line of input Here
			System.out.println(calculateScore(line));
		}
	}

	/**
	 * Calculates the score for the string given.
	 * 
	 * @param line
	 * @return
	 */
	public static int calculateScore(String line) {
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
	private static int calculateScore(List<Alphabet> scoredAlphabets) {
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
	private static List<Alphabet> setScoresOnCharacters(List<Alphabet> alphabets) {
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
	public static List<Alphabet> countCharacters(String line) {
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
					Alphabet alphabet = new Main().new Alphabet(chr);
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

	/**
	 * A POJO to hold the result of the application.
	 * 
	 * @author Max
	 *
	 */
	public class Alphabet {

		private char chr;
		private int score;
		private int counter;

		public Alphabet(char chr) {
			this.chr = chr;
		}

		public int getScore() {
			return score;
		}

		public void setScore(int score) {
			this.score = score;
		}

		public int getCounter() {
			return counter;
		}

		public void setCounter(int counter) {
			this.counter = counter;
		}

		public char getChr() {
			return chr;
		}

		public void setChr(char chr) {
			this.chr = chr;
		}

		public String toString() {
			return this.chr + ": Score (" + this.score + "), Counter (" + this.counter + ")";
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Alphabet))
				return false;
			if (obj == this)
				return true;

			Alphabet rhs = (Alphabet) obj;
			return rhs.getChr() == this.chr;
		}
	}

}

package com.typeahead.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Main {

	private static final String WHITE_SPACE = " ";
	private static final String INPUT_TEXT = "Mary had a little lamb its fleece was white as snow;" + "And everywhere that Mary went, the lamb was sure to go."
			+ "It followed her to school one day, which was against the rule;" + "It made the children laugh and play, to see a lamb at school."
			+ "And so the teacher turned it out, but still it lingered near," + "And waited patiently about till Mary did appear."
			+ "\"Why does the lamb love Mary so?\" the eager children cry; \"Why, Mary loves the lamb, you know\" the teacher did reply.\"";
	private static final char SPACE_CHAR = ' ';
	private static final String[] WORDS = removeSpecialCharacters(INPUT_TEXT);
	private static DecimalFormat df = new DecimalFormat("#.000");

	public static void main(String[] args) throws IOException {
		File file = new File(args[0]);
		@SuppressWarnings("resource")
		BufferedReader buffer = new BufferedReader(new FileReader(file));
		String line;
		while ((line = buffer.readLine()) != null) {
			line = line.trim();
			// Process line of input Here
			Input input = readInput(line);
			process(input);
			System.out.println(line);
		}
	}

	/**
	 * Runs ngram algorithm on INPUT_TEXT using the input passed.
	 * 
	 * @param input
	 */
	public static void process(Input input) {
		String text = input.getText();
		int ngram = input.getNgram();
		int totalNumOfOccurrences = 0;
		Map<String, Integer> result = new HashMap<>();
		for (int i = 0; i < WORDS.length; i++) {
			if (text.equals(WORDS[i])) {
				totalNumOfOccurrences++;
				String key = String.join(" ", getNextWords(i, ngram)); // Key is the next ngram words
				if (result.containsKey(key)) {
					// Increment by one
					result.put(key, result.get(key) + 1);
				} else {
					// First time seeing this key, initialize by setting the counter to 1.
					result.put(key, 1);
				}
			}
		}
		// Sorting the result based on Value and then Key
		List<Map.Entry<String, Integer>> resultList = new ArrayList<Map.Entry<String, Integer>>(result.entrySet());
		Collections.sort(resultList, (arg1, arg2) -> {
			// Using -ve for descending order
			int cmp1 = -arg1.getValue().compareTo(arg2.getValue());
			if (cmp1 != 0) {
				// If the values are not the same, then return the compare
				return cmp1;
			} else {
				// If the values are the same, look at the keys
				return arg1.getKey().compareTo(arg2.getKey());
			}
		});

		// Printing the final result
		for (Entry<String, Integer> entry : resultList) {
			System.out.print(entry.getKey() + "," + df.format((double) entry.getValue() / totalNumOfOccurrences) + ";");
		}
	}

	/**
	 * Gets the next ngram words starting from index. The data comes from WORDS.
	 * 
	 * @param index
	 * @param ngram
	 * @return
	 */
	private static List<String> getNextWords(int index, int ngram) {
		List<String> result = new ArrayList<>();
		for (int i = index + 1; i < WORDS.length && i < index + ngram; i++) {
			result.add(WORDS[i]);
		}
		return result;
	}

	/**
	 * Converts the input line into Input object.
	 * 
	 * @param line
	 * @return
	 */
	public static Input readInput(String line) {
		int indexOfComma = line.indexOf(',');
		int ngram = Integer.valueOf(line.substring(0, indexOfComma));
		String text = line.substring(indexOfComma + 1, line.length());
		return new Main().new Input(text, ngram);
	}

	/**
	 * Removes non alphabetic characters from the string passed and splits the result into an array of strings (each containing a word)
	 * 
	 * @param string
	 * @return
	 */
	public static String[] removeSpecialCharacters(String string) {
		StringBuffer result = new StringBuffer();
		char[] charArray = string.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if (Character.isAlphabetic(charArray[i]) || charArray[i] == SPACE_CHAR) {
				result.append(charArray[i]);
			} else if (i < charArray.length - 1 && Character.isAlphabetic(charArray[i + 1])) {
				// For the case where the two words are separated with special characters.
				result.append(WHITE_SPACE);
			}
		}
		return result.toString().split(WHITE_SPACE);
	}

	public class Input {

		private String text;
		private int ngram;

		public Input(String text, int ngram) {
			this.text = text;
			this.ngram = ngram;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public int getNgram() {
			return ngram;
		}

		public void setNgram(int ngram) {
			this.ngram = ngram;
		}
	}

}

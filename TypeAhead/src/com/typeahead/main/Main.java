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
	public static final String INPUT_TEXT = "Mary had a little lamb its fleece was white as snow;" + "And everywhere that Mary went, the lamb was sure to go."
			+ "It followed her to school one day, which was against the rule;" + "It made the children laugh and play, to see a lamb at school."
			+ "And so the teacher turned it out, but still it lingered near," + "And waited patiently about till Mary did appear."
			+ "\"Why does the lamb love Mary so?\" the eager children cry; \"Why, Mary loves the lamb, you know\" the teacher did reply.\"";
	private static final char SPACE_CHAR = ' ';
	private static final String[] WORDS = split(INPUT_TEXT);
	private static DecimalFormat df = new DecimalFormat("#0.000");

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
			System.out.println();
		}
	}

	/**
	 * Runs ngram algorithm on INPUT_TEXT using the input passed.
	 * 
	 * @param input
	 * @return
	 */
	public static boolean process(Input input) {
		String text = input.getText();
		int ngram = input.getNgram();
		int totalNumOfOccurrences = 0;
		Map<String, Integer> result = new HashMap<>();
		for (int i = 0; i < WORDS.length; i++) {
			// Finding the number of words based on the spaces in text
			String[] texts = split(text);
			if (isSame(texts, i, WORDS)) {
				totalNumOfOccurrences++;
				String key = String.join(" ", getNextWords(i, texts.length, ngram)); // Key is the next ngram words
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
		List<String> finalResult = new ArrayList<>();
		for (Entry<String, Integer> entry : resultList) {
			finalResult.add(entry.getKey() + "," + df.format((double) entry.getValue() / totalNumOfOccurrences));
		}
		System.out.print(String.join(";", finalResult));
		return finalResult.size() > 0;
	}

	/**
	 * Checks the words in text to see if they match the words at i'th index in words.
	 * 
	 * @param texts
	 * @param i
	 * @param words
	 * @return
	 */
	public static boolean isSame(String[] texts, int i, final String[] words) {
		// Checking all the words to make sure they are the same
		for (int j = 0; j < texts.length; j++) {
			if (!texts[j].equals(words[i + j])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Gets the next ngram words starting from index. The data comes from WORDS.
	 * 
	 * @param index
	 * @param textsLength
	 *            Length of the words in the search phrase
	 * @param ngram
	 * @return
	 */
	private static List<String> getNextWords(int index, int textsLength, int ngram) {
		List<String> result = new ArrayList<>();
		for (int i = index + textsLength; i < WORDS.length && i < index + textsLength + Math.abs(ngram - textsLength); i++) {
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
	public static String[] split(String string) {
		StringBuffer result = new StringBuffer();
		char[] charArray = string.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if (Character.isAlphabetic(charArray[i]) || charArray[i] == SPACE_CHAR) {
				result.append(charArray[i]);
			} else {
				// Skipping non alphabetic characters
				int j = i;
				while (j < charArray.length) {
					if (Character.isAlphabetic(charArray[j])) {
						break;
					}
					j++;
				}
				result.append(SPACE_CHAR);
				i = j - 1;
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

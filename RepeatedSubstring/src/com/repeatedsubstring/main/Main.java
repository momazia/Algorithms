package com.repeatedsubstring.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The main class to be executed for finding the longest substring problem.
 * 
 * @author Max
 *
 */
public class Main {

	/**
	 * Constants
	 */
	public static final String NONE = "NONE";

	/**
	 * Main method to be run in order to find the longest substring.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		File file = new File(args[0]);
		@SuppressWarnings("resource")
		BufferedReader buffer = new BufferedReader(new FileReader(file));
		String line;
		List<String> inputs = new ArrayList<>();
		while ((line = buffer.readLine()) != null) {
			line = line.trim();
			inputs.add(line);
			System.out.println(findLongestRepeatedSubString(line));
		}
	}

	/**
	 * Finds the longest repeated substring in the given string.
	 * 
	 * @param string
	 * @return
	 */
	public static String findLongestRepeatedSubString(String string) {
		// Creating all the possible substrings
		Map<String, SubString> result = createSubStrings(string);
		// Finding the best match
		return findBestMatch(result);
	}

	/**
	 * Creates all the possible substrings for the given string.
	 * 
	 * @param string
	 * @return
	 */
	private static Map<String, SubString> createSubStrings(String string) {
		// A placeholder to keep the substrings as KEY and their indexes and counter as VALUE.
		Map<String, SubString> result = new HashMap<>();
		for (int windowSize = 1; windowSize < string.length() / 2 + 1; windowSize++) {
			for (int i = 0; i <= string.length() - windowSize; i++) {
				// Creating substrings by applying different window sizes on the string.
				String str = string.substring(i, i + windowSize);
				// Ignoring substrings which are all white spaces.
				if (!isBlank(str)) {
					if (!result.containsKey(str)) {
						// First time seeing the substring, setting the counter to 1
						result.put(str, new Main().new SubString(1, i));
					} else {
						SubString subString = result.get(str);
						// Checking to make sure the substring which was stored before is not overlapping with the current substring.
						if (!isOverlapping(subString, i, windowSize)) {
							subString.setCounter(subString.getCounter() + 1);
							result.put(str, subString);
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * This is to check if the substring given is not overlapping with the index i passed based on the window size.
	 * 
	 * @param subString
	 * @param i
	 * @param windowSize
	 * @return
	 */
	private static boolean isOverlapping(SubString subString, int i, int windowSize) {
		return i - subString.getIndex() < windowSize;
	}

	/**
	 * Checks to see if the given string is all white spaces.
	 * 
	 * @param str
	 * @return
	 */
	private static boolean isBlank(String str) {
		for (char chr : str.toCharArray()) {
			if (chr != ' ') {
				return false;
			}
		}
		return true;
	}

	/**
	 * Finds the best matching result among the all the substrings founds
	 * 
	 * @param map
	 * @return
	 */
	private static String findBestMatch(Map<String, SubString> map) {
		// Filtering out those substrings which are not repeated.
		List<Entry<String, SubString>> longestRepeatedStrings = findLongestRepeatedStrings(map);
		// Finding the first occurrence of the longest repeated substring.
		return findFirstString(longestRepeatedStrings);
	}

	/**
	 * Finds the longest strings which they have appeared in the original string more than once.
	 * 
	 * @param map
	 * @return
	 */
	private static List<Entry<String, SubString>> findLongestRepeatedStrings(Map<String, SubString> map) {
		List<Entry<String, SubString>> longestRepeatedStrings = new ArrayList<>();
		int maximumLength = 0;
		// Going through all the elements of the map.
		for (Entry<String, SubString> subStrEntry : map.entrySet()) {
			int length = subStrEntry.getKey().length();
			SubString subString = subStrEntry.getValue();
			// If the substring was repeated (counter > 1)
			if (length >= maximumLength && subString.getCounter() > 1) {
				// Checking to see if we found a new maximum length
				if (length > maximumLength) {
					// Found a better length, will reset the list by removing all the items in the list
					longestRepeatedStrings.clear();
				}
				maximumLength = length;
				longestRepeatedStrings.add(subStrEntry);
			}
		}
		return longestRepeatedStrings;
	}

	/**
	 * Finds the first (lowest index) substring match among all the eligible longest repeated strings.
	 * 
	 * @param longestRepeatedStrings
	 * @return
	 */
	private static String findFirstString(List<Entry<String, SubString>> longestRepeatedStrings) {
		int minimumIndex = Integer.MAX_VALUE;
		// By default, if no longest repeated string is eligible, NONE will be the final result.
		String result = NONE;
		for (Entry<String, SubString> subString : longestRepeatedStrings) {
			int index = subString.getValue().getIndex();
			// Finding the minimum index
			if (minimumIndex > index) {
				minimumIndex = index;
				// Setting the final result
				result = subString.getKey();
			}
		}
		return result;
	}

	/**
	 * A placeholder to keep a substring's counter and the index it was found at.
	 * 
	 * @author Max
	 *
	 */
	public class SubString {

		private int counter;
		private int index;

		public SubString(int counter, int index) {
			super();
			this.counter = counter;
			this.index = index;
		}

		public int getCounter() {
			return counter;
		}

		public void setCounter(int counter) {
			this.counter = counter;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		@Override
		public String toString() {
			return "Counter: " + counter + " Index: " + index;
		}
	}

}

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

public class Main {

	public static final String NONE = "NONE";

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

	public static String findLongestRepeatedSubString(String line) {
		Map<String, SubString> result = createSubStrings(line);
		return findBestMatch(result, line);
	}

	private static Map<String, SubString> createSubStrings(String line) {
		Map<String, SubString> result = new HashMap<>();
		for (int windowSize = 1; windowSize < line.length() / 2 + 1; windowSize++) {
			for (int i = 0; i <= line.length() - windowSize; i++) {
				String str = line.substring(i, i + windowSize);
				if (!isBlank(str)) {
					if (!result.containsKey(str)) {
						result.put(str, new Main().new SubString(1, i));
					} else {
						SubString subString = result.get(str);
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

	private static boolean isOverlapping(SubString subString, int i, int windowSize) {
		return i - subString.getIndex() < windowSize;
	}

	private static boolean isBlank(String str) {
		for (char chr : str.toCharArray()) {
			if (chr != ' ') {
				return false;
			}
		}
		return true;
	}

	private static String findBestMatch(Map<String, SubString> map, String line) {
		List<Entry<String, SubString>> longestRepeatedStrings = new ArrayList<>();
		int maximumLength = 0;
		// Finding the longest repeated subStrings
		for (Entry<String, SubString> subStrEntry : map.entrySet()) {
			int length = subStrEntry.getKey().length();
			SubString subString = subStrEntry.getValue();
			if (length >= maximumLength && subString.getCounter() > 1) {
				if (length > maximumLength) {
					// Found a better length, will remove all the items in the list
					longestRepeatedStrings.clear();
				}
				maximumLength = length;
				longestRepeatedStrings.add(subStrEntry);
			}
		}

		int minimumIndex = Integer.MAX_VALUE;
		String result = NONE;
		for (Entry<String, SubString> subString : longestRepeatedStrings) {
			int index = subString.getValue().getIndex();
			if (minimumIndex > index) {
				minimumIndex = index;
				result = subString.getKey();
			}
		}
		return result;
	}

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

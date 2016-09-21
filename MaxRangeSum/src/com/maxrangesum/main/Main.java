package com.maxrangesum.main;

import java.io.*;

/**
 * Main class running the algorithm.
 * 
 * @author Max
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		File file = new File(args[0]);
		@SuppressWarnings("resource")
		BufferedReader buffer = new BufferedReader(new FileReader(file));
		String line;
		while ((line = buffer.readLine()) != null) {
			line = line.trim();
			// Process line of input Here
			Entry entry = readFile(line);
			System.out.println(findMaxGain(entry));
		}
	}

	/**
	 * Finds the max gain for the given entry.
	 * 
	 * @param entry
	 * @return
	 */
	public static int findMaxGain(Entry entry) {
		int[] values = entry.getValues();
		int valuesLength = values.length;
		int maxGain = 0; // Initializing the maxGain with value zero since that is the minimum expected result.
		// Goes through the array of the values by looking at a window of size
		// NumOfDays.
		for (int i = 0; i <= valuesLength - entry.getNumOfDays(); i++) {
			int sum = 0;
			for (int j = i; j < i + entry.getNumOfDays(); j++) {
				sum += values[j];
			}
			// Keeping the maximum gain.
			if (sum > maxGain) {
				maxGain = sum;
			}
		}
		return maxGain;
	}

	/**
	 * Reads the line of file given and converts it into an Entry object.
	 * 
	 * @param line
	 * @return
	 */
	public static Entry readFile(String line) {
		int indexOfSemiColen = line.indexOf(";");
		int numOfDays = Integer.valueOf(line.substring(0, indexOfSemiColen));
		String[] strValues = line.substring(indexOfSemiColen + 1).split(" ");
		int[] values = new int[strValues.length];
		for (int index = 0; index < strValues.length; index++) {
			values[index] = Integer.valueOf(strValues[index]);
		}
		return new Main().new Entry(numOfDays, values);
	}

	/**
	 * A POJO to contain the data stored in the input file.
	 * 
	 * @author Max
	 *
	 */
	public class Entry {

		private int numOfDays;
		private int[] values;

		public Entry(int numOfDays, int[] values) {
			this.numOfDays = numOfDays;
			this.values = values;
		}

		public int getNumOfDays() {
			return numOfDays;
		}

		public void setNumOfDays(int numOfDays) {
			this.numOfDays = numOfDays;
		}

		public int[] getValues() {
			return values;
		}

		public void setValues(int[] values) {
			this.values = values;
		}

	}

}

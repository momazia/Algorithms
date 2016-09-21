package com.maxrangesum.main;

import java.io.*;

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
			findMaxCrossingSubArray(entry, 0, entry.getValues().length);
		}
	}

	private static int findMaxCrossingSubArray(Entry entry, int low, int high) {
		if (low == high){
			return entry.getValues()[low];
		}
		return 0;
	}

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

	public class Entry {

		private int numOfDays;
		private int[] values;
		private int result;

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

		public int getResult() {
			return result;
		}

		public void setResult(int result) {
			this.result = result;
		}

	}

}

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
		}
	}
	
}

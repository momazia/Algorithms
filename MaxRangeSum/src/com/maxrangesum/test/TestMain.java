package com.maxrangesum.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.maxrangesum.main.Main;
import com.maxrangesum.main.Main.Entry;

public class TestMain {

	@Test
	public void test_readFile() {
		String line = "5;7 -3 -10 4 2 8 -2 4 -5 -2";
		Entry entry = Main.readFile(line);
		assertEquals(5, entry.getNumOfDays());
		assertEquals(10, entry.getValues().length);
		assertEquals(7, entry.getValues()[0]);
		assertEquals(-2, entry.getValues()[9]);
	}

	@Test
	public void test_findMaxSubArray1() {
		String line = "5;7 -3 -10 4 2 8 -2 4 -5 -2";
		Entry entry = Main.readFile(line);
		assertEquals(16, Main.findMaxGain(entry));
	}

	@Test
	public void test_findMaxSubArray2() {
		String line = "6;-4 3 -10 5 3 -7 -3 7 -6 3";
		Entry entry = Main.readFile(line);
		assertEquals(0, Main.findMaxGain(entry));
	}

	@Test
	public void test_findMaxSubArray3() {
		String line = "3;-7 0 -45 34 -24 7";
		Entry entry = Main.readFile(line);
		assertEquals(17, Main.findMaxGain(entry));
	}

}
